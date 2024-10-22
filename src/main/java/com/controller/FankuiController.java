
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 反馈
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fankui")
public class FankuiController {
    private static final Logger logger = LoggerFactory.getLogger(FankuiController.class);

    private static final String TABLE_NAME = "fankui";

    @Autowired
    private FankuiService fankuiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CaozuorizhiService caozuorizhiService;

    //级联表非注册的service
    //注册表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private XuexiaoguanliyuanService xuexiaoguanliyuanService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("科研人员".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("学校管理员".equals(role))
            params.put("xuexiaoguanliyuanId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = fankuiService.queryPage(params);

        //字典表数据转换
        List<FankuiView> list =(List<FankuiView>)page.getList();
        for(FankuiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"列表查询",list.toString());
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FankuiEntity fankui = fankuiService.selectById(id);
        if(fankui !=null){
            //entity转view
            FankuiView view = new FankuiView();
            BeanUtils.copyProperties( fankui , view );//把实体数据重构到view中
            //级联表 科研人员
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(fankui.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
    caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"单条数据查看",view.toString());
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody FankuiEntity fankui, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fankui:{}",this.getClass().getName(),fankui.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("科研人员".equals(role))
            fankui.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<FankuiEntity> queryWrapper = new EntityWrapper<FankuiEntity>()
            .eq("yonghu_id", fankui.getYonghuId())
            .eq("fankui_name", fankui.getFankuiName())
            .eq("fankui_types", fankui.getFankuiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FankuiEntity fankuiEntity = fankuiService.selectOne(queryWrapper);
        if(fankuiEntity==null){
            fankui.setInsertTime(new Date());
            fankui.setCreateTime(new Date());
            fankuiService.insert(fankui);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",fankui.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FankuiEntity fankui, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fankui:{}",this.getClass().getName(),fankui.toString());
        FankuiEntity oldFankuiEntity = fankuiService.selectById(fankui.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("科研人员".equals(role))
//            fankui.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<FankuiEntity> queryWrapper = new EntityWrapper<FankuiEntity>()
            .notIn("id",fankui.getId())
            .andNew()
            .eq("yonghu_id", fankui.getYonghuId())
            .eq("fankui_name", fankui.getFankuiName())
            .eq("fankui_types", fankui.getFankuiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FankuiEntity fankuiEntity = fankuiService.selectOne(queryWrapper);
        if(fankuiEntity==null){
            fankuiService.updateById(fankui);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(fankui, oldFankuiEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FankuiEntity> oldFankuiList =fankuiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        fankuiService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldFankuiList.toString());
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<FankuiEntity> fankuiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            FankuiEntity fankuiEntity = new FankuiEntity();
//                            fankuiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //科研人员 要改的
//                            fankuiEntity.setFankuiUuidNumber(data.get(0));                    //反馈编号 要改的
//                            fankuiEntity.setFankuiName(data.get(0));                    //反馈名称 要改的
//                            fankuiEntity.setFankuiTypes(Integer.valueOf(data.get(0)));   //反馈类型 要改的
//                            fankuiEntity.setFankuiContent("");//详情和图片
//                            fankuiEntity.setInsertTime(date);//时间
//                            fankuiEntity.setCreateTime(date);//时间
                            fankuiList.add(fankuiEntity);


                            //把要查询是否重复的字段放入map中
                                //反馈编号
                                if(seachFields.containsKey("fankuiUuidNumber")){
                                    List<String> fankuiUuidNumber = seachFields.get("fankuiUuidNumber");
                                    fankuiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fankuiUuidNumber = new ArrayList<>();
                                    fankuiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("fankuiUuidNumber",fankuiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //反馈编号
                        List<FankuiEntity> fankuiEntities_fankuiUuidNumber = fankuiService.selectList(new EntityWrapper<FankuiEntity>().in("fankui_uuid_number", seachFields.get("fankuiUuidNumber")));
                        if(fankuiEntities_fankuiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FankuiEntity s:fankuiEntities_fankuiUuidNumber){
                                repeatFields.add(s.getFankuiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [反馈编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fankuiService.insertBatch(fankuiList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",fankuiList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





}
