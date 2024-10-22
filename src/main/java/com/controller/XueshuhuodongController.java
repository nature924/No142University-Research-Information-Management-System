
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
 * 学术活动
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xueshuhuodong")
public class XueshuhuodongController {
    private static final Logger logger = LoggerFactory.getLogger(XueshuhuodongController.class);

    private static final String TABLE_NAME = "xueshuhuodong";

    @Autowired
    private XueshuhuodongService xueshuhuodongService;


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
        PageUtils page = xueshuhuodongService.queryPage(params);

        //字典表数据转换
        List<XueshuhuodongView> list =(List<XueshuhuodongView>)page.getList();
        for(XueshuhuodongView c:list){
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
        XueshuhuodongEntity xueshuhuodong = xueshuhuodongService.selectById(id);
        if(xueshuhuodong !=null){
            //entity转view
            XueshuhuodongView view = new XueshuhuodongView();
            BeanUtils.copyProperties( xueshuhuodong , view );//把实体数据重构到view中
            //级联表 科研人员
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(xueshuhuodong.getYonghuId());
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
    public R save(@RequestBody XueshuhuodongEntity xueshuhuodong, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xueshuhuodong:{}",this.getClass().getName(),xueshuhuodong.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("科研人员".equals(role))
            xueshuhuodong.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<XueshuhuodongEntity> queryWrapper = new EntityWrapper<XueshuhuodongEntity>()
            .eq("yonghu_id", xueshuhuodong.getYonghuId())
            .eq("xueshuhuodong_name", xueshuhuodong.getXueshuhuodongName())
            .eq("xueshuhuodong_address", xueshuhuodong.getXueshuhuodongAddress())
            .eq("xueshuhuodong_types", xueshuhuodong.getXueshuhuodongTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshuhuodongEntity xueshuhuodongEntity = xueshuhuodongService.selectOne(queryWrapper);
        if(xueshuhuodongEntity==null){
            xueshuhuodong.setInsertTime(new Date());
            xueshuhuodong.setXueshuhuodongYesnoTypes(1);
            xueshuhuodong.setCreateTime(new Date());
            xueshuhuodongService.insert(xueshuhuodong);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",xueshuhuodong.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XueshuhuodongEntity xueshuhuodong, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xueshuhuodong:{}",this.getClass().getName(),xueshuhuodong.toString());
        XueshuhuodongEntity oldXueshuhuodongEntity = xueshuhuodongService.selectById(xueshuhuodong.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("科研人员".equals(role))
//            xueshuhuodong.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<XueshuhuodongEntity> queryWrapper = new EntityWrapper<XueshuhuodongEntity>()
            .notIn("id",xueshuhuodong.getId())
            .andNew()
            .eq("yonghu_id", xueshuhuodong.getYonghuId())
            .eq("xueshuhuodong_name", xueshuhuodong.getXueshuhuodongName())
            .eq("xueshuhuodong_time", xueshuhuodong.getXueshuhuodongTime())
            .eq("xueshuhuodong_address", xueshuhuodong.getXueshuhuodongAddress())
            .eq("xueshuhuodong_types", xueshuhuodong.getXueshuhuodongTypes())
            .eq("insert_time", xueshuhuodong.getInsertTime())
            .eq("xueshuhuodong_shenhe_time", xueshuhuodong.getXueshuhuodongShenheTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XueshuhuodongEntity xueshuhuodongEntity = xueshuhuodongService.selectOne(queryWrapper);
        if("".equals(xueshuhuodong.getXueshuhuodongPhoto()) || "null".equals(xueshuhuodong.getXueshuhuodongPhoto())){
                xueshuhuodong.setXueshuhuodongPhoto(null);
        }
        if(xueshuhuodongEntity==null){
            xueshuhuodongService.updateById(xueshuhuodong);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(xueshuhuodong, oldXueshuhuodongEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody XueshuhuodongEntity xueshuhuodongEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,xueshuhuodongEntity:{}",this.getClass().getName(),xueshuhuodongEntity.toString());

        XueshuhuodongEntity oldXueshuhuodong = xueshuhuodongService.selectById(xueshuhuodongEntity.getId());//查询原先数据

//        if(xueshuhuodongEntity.getXueshuhuodongYesnoTypes() == 2){//通过
//            xueshuhuodongEntity.setXueshuhuodongTypes();
//        }else if(xueshuhuodongEntity.getXueshuhuodongYesnoTypes() == 3){//拒绝
//            xueshuhuodongEntity.setXueshuhuodongTypes();
//        }
        xueshuhuodongEntity.setXueshuhuodongShenheTime(new Date());//审核时间
        xueshuhuodongService.updateById(xueshuhuodongEntity);//审核

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"审核数据","审核"+oldXueshuhuodong+"数据,审核结果是"+(xueshuhuodongEntity.getXueshuhuodongYesnoTypes()==2?"通过":"拒绝"));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XueshuhuodongEntity> oldXueshuhuodongList =xueshuhuodongService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xueshuhuodongService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldXueshuhuodongList.toString());
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
            List<XueshuhuodongEntity> xueshuhuodongList = new ArrayList<>();//上传的东西
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
                            XueshuhuodongEntity xueshuhuodongEntity = new XueshuhuodongEntity();
//                            xueshuhuodongEntity.setYonghuId(Integer.valueOf(data.get(0)));   //科研人员 要改的
//                            xueshuhuodongEntity.setXueshuhuodongUuidNumber(data.get(0));                    //学术活动编号 要改的
//                            xueshuhuodongEntity.setXueshuhuodongName(data.get(0));                    //学术活动名称 要改的
//                            xueshuhuodongEntity.setXueshuhuodongPhoto("");//详情和图片
//                            xueshuhuodongEntity.setXueshuhuodongTime(sdf.parse(data.get(0)));          //学术活动时间 要改的
//                            xueshuhuodongEntity.setXueshuhuodongAddress(data.get(0));                    //学术活动地点 要改的
//                            xueshuhuodongEntity.setXueshuhuodongTypes(Integer.valueOf(data.get(0)));   //学术活动类型 要改的
//                            xueshuhuodongEntity.setXueshuhuodongContent("");//详情和图片
//                            xueshuhuodongEntity.setInsertTime(date);//时间
//                            xueshuhuodongEntity.setXueshuhuodongYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            xueshuhuodongEntity.setXueshuhuodongYesnoText(data.get(0));                    //审核意见 要改的
//                            xueshuhuodongEntity.setXueshuhuodongShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            xueshuhuodongEntity.setCreateTime(date);//时间
                            xueshuhuodongList.add(xueshuhuodongEntity);


                            //把要查询是否重复的字段放入map中
                                //学术活动编号
                                if(seachFields.containsKey("xueshuhuodongUuidNumber")){
                                    List<String> xueshuhuodongUuidNumber = seachFields.get("xueshuhuodongUuidNumber");
                                    xueshuhuodongUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xueshuhuodongUuidNumber = new ArrayList<>();
                                    xueshuhuodongUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xueshuhuodongUuidNumber",xueshuhuodongUuidNumber);
                                }
                        }

                        //查询是否重复
                         //学术活动编号
                        List<XueshuhuodongEntity> xueshuhuodongEntities_xueshuhuodongUuidNumber = xueshuhuodongService.selectList(new EntityWrapper<XueshuhuodongEntity>().in("xueshuhuodong_uuid_number", seachFields.get("xueshuhuodongUuidNumber")));
                        if(xueshuhuodongEntities_xueshuhuodongUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XueshuhuodongEntity s:xueshuhuodongEntities_xueshuhuodongUuidNumber){
                                repeatFields.add(s.getXueshuhuodongUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [学术活动编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xueshuhuodongService.insertBatch(xueshuhuodongList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",xueshuhuodongList.toString());
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
