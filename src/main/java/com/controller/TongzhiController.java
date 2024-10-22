
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
 * 通知
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/tongzhi")
public class TongzhiController {
    private static final Logger logger = LoggerFactory.getLogger(TongzhiController.class);

    private static final String TABLE_NAME = "tongzhi";

    @Autowired
    private TongzhiService tongzhiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private CaozuorizhiService caozuorizhiService;

    //级联表非注册的service
    @Autowired
    private XueyuanbumenService xueyuanbumenService;
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
        else if("科研人员".equals(role)){
            YonghuEntity yong = yonghuService.selectById((Integer)request.getSession().getAttribute("userId"));
            params.put("yonghuId",request.getSession().getAttribute("userId"));
            params.put("xueyuanbumenId",yong.getXueyuanbumenId());

        }
        else if("学校管理员".equals(role))
            params.put("xuexiaoguanliyuanId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = tongzhiService.queryPage(params);

        //字典表数据转换
        List<TongzhiView> list =(List<TongzhiView>)page.getList();
        for(TongzhiView c:list){
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
        TongzhiEntity tongzhi = tongzhiService.selectById(id);
        if(tongzhi !=null){
            //entity转view
            TongzhiView view = new TongzhiView();
            BeanUtils.copyProperties( tongzhi , view );//把实体数据重构到view中
            //级联表 学院部门
            //级联表
            XueyuanbumenEntity xueyuanbumen = xueyuanbumenService.selectById(tongzhi.getXueyuanbumenId());
            if(xueyuanbumen != null){
            BeanUtils.copyProperties( xueyuanbumen , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setXueyuanbumenId(xueyuanbumen.getId());
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
    public R save(@RequestBody TongzhiEntity tongzhi, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,tongzhi:{}",this.getClass().getName(),tongzhi.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<TongzhiEntity> queryWrapper = new EntityWrapper<TongzhiEntity>()
            .eq("xueyuanbumen_id", tongzhi.getXueyuanbumenId())
            .eq("tongzhi_name", tongzhi.getTongzhiName())
            .eq("tongzhi_types", tongzhi.getTongzhiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TongzhiEntity tongzhiEntity = tongzhiService.selectOne(queryWrapper);
        if(tongzhiEntity==null){
            tongzhi.setInsertTime(new Date());
            tongzhi.setCreateTime(new Date());
            tongzhiService.insert(tongzhi);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",tongzhi.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody TongzhiEntity tongzhi, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,tongzhi:{}",this.getClass().getName(),tongzhi.toString());
        TongzhiEntity oldTongzhiEntity = tongzhiService.selectById(tongzhi.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<TongzhiEntity> queryWrapper = new EntityWrapper<TongzhiEntity>()
            .notIn("id",tongzhi.getId())
            .andNew()
            .eq("xueyuanbumen_id", tongzhi.getXueyuanbumenId())
            .eq("tongzhi_name", tongzhi.getTongzhiName())
            .eq("tongzhi_types", tongzhi.getTongzhiTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        TongzhiEntity tongzhiEntity = tongzhiService.selectOne(queryWrapper);
        if(tongzhiEntity==null){
            tongzhiService.updateById(tongzhi);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(tongzhi, oldTongzhiEntity, request,new String[]{"updateTime"});
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
        List<TongzhiEntity> oldTongzhiList =tongzhiService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        tongzhiService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldTongzhiList.toString());
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
            List<TongzhiEntity> tongzhiList = new ArrayList<>();//上传的东西
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
                            TongzhiEntity tongzhiEntity = new TongzhiEntity();
//                            tongzhiEntity.setXueyuanbumenId(Integer.valueOf(data.get(0)));   //学院部门 要改的
//                            tongzhiEntity.setTongzhiUuidNumber(data.get(0));                    //通知编号 要改的
//                            tongzhiEntity.setTongzhiName(data.get(0));                    //通知名称 要改的
//                            tongzhiEntity.setTongzhiTypes(Integer.valueOf(data.get(0)));   //通知类型 要改的
//                            tongzhiEntity.setTongzhiContent("");//详情和图片
//                            tongzhiEntity.setInsertTime(date);//时间
//                            tongzhiEntity.setCreateTime(date);//时间
                            tongzhiList.add(tongzhiEntity);


                            //把要查询是否重复的字段放入map中
                                //通知编号
                                if(seachFields.containsKey("tongzhiUuidNumber")){
                                    List<String> tongzhiUuidNumber = seachFields.get("tongzhiUuidNumber");
                                    tongzhiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> tongzhiUuidNumber = new ArrayList<>();
                                    tongzhiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("tongzhiUuidNumber",tongzhiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //通知编号
                        List<TongzhiEntity> tongzhiEntities_tongzhiUuidNumber = tongzhiService.selectList(new EntityWrapper<TongzhiEntity>().in("tongzhi_uuid_number", seachFields.get("tongzhiUuidNumber")));
                        if(tongzhiEntities_tongzhiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(TongzhiEntity s:tongzhiEntities_tongzhiUuidNumber){
                                repeatFields.add(s.getTongzhiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [通知编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        tongzhiService.insertBatch(tongzhiList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",tongzhiList.toString());
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
