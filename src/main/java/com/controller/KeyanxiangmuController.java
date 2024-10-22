
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
 * 科研项目
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/keyanxiangmu")
public class KeyanxiangmuController {
    private static final Logger logger = LoggerFactory.getLogger(KeyanxiangmuController.class);

    private static final String TABLE_NAME = "keyanxiangmu";

    @Autowired
    private KeyanxiangmuService keyanxiangmuService;


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
        PageUtils page = keyanxiangmuService.queryPage(params);

        //字典表数据转换
        List<KeyanxiangmuView> list =(List<KeyanxiangmuView>)page.getList();
        for(KeyanxiangmuView c:list){
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
        KeyanxiangmuEntity keyanxiangmu = keyanxiangmuService.selectById(id);
        if(keyanxiangmu !=null){
            //entity转view
            KeyanxiangmuView view = new KeyanxiangmuView();
            BeanUtils.copyProperties( keyanxiangmu , view );//把实体数据重构到view中
            //级联表 科研人员
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(keyanxiangmu.getYonghuId());
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
    public R save(@RequestBody KeyanxiangmuEntity keyanxiangmu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,keyanxiangmu:{}",this.getClass().getName(),keyanxiangmu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("科研人员".equals(role))
            keyanxiangmu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<KeyanxiangmuEntity> queryWrapper = new EntityWrapper<KeyanxiangmuEntity>()
            .eq("yonghu_id", keyanxiangmu.getYonghuId())
            .eq("keyanxiangmu_laiyuan", keyanxiangmu.getKeyanxiangmuLaiyuan())
            .eq("keyanxiangmu_name", keyanxiangmu.getKeyanxiangmuName())
            .eq("dengji_types", keyanxiangmu.getDengjiTypes())
            .eq("xueke_types", keyanxiangmu.getXuekeTypes())
            .eq("keyanxiangmu_huafei", keyanxiangmu.getKeyanxiangmuHuafei())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KeyanxiangmuEntity keyanxiangmuEntity = keyanxiangmuService.selectOne(queryWrapper);
        if(keyanxiangmuEntity==null){
            keyanxiangmu.setInsertTime(new Date());
            keyanxiangmu.setKeyanxiangmuYesnoTypes(1);
            keyanxiangmu.setCreateTime(new Date());
            keyanxiangmuService.insert(keyanxiangmu);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",keyanxiangmu.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KeyanxiangmuEntity keyanxiangmu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,keyanxiangmu:{}",this.getClass().getName(),keyanxiangmu.toString());
        KeyanxiangmuEntity oldKeyanxiangmuEntity = keyanxiangmuService.selectById(keyanxiangmu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("科研人员".equals(role))
//            keyanxiangmu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<KeyanxiangmuEntity> queryWrapper = new EntityWrapper<KeyanxiangmuEntity>()
            .notIn("id",keyanxiangmu.getId())
            .andNew()
            .eq("yonghu_id", keyanxiangmu.getYonghuId())
            .eq("keyanxiangmu_laiyuan", keyanxiangmu.getKeyanxiangmuLaiyuan())
            .eq("keyanxiangmu_name", keyanxiangmu.getKeyanxiangmuName())
            .eq("dengji_types", keyanxiangmu.getDengjiTypes())
            .eq("xueke_types", keyanxiangmu.getXuekeTypes())
            .eq("keyanxiangmu_huafei", keyanxiangmu.getKeyanxiangmuHuafei())
            .eq("kaishi_time", keyanxiangmu.getKaishiTime())
            .eq("jieshu_time", keyanxiangmu.getJieshuTime())
            .eq("insert_time", keyanxiangmu.getInsertTime())
            .eq("keyanxiangmu_shenhe_time", keyanxiangmu.getKeyanxiangmuShenheTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KeyanxiangmuEntity keyanxiangmuEntity = keyanxiangmuService.selectOne(queryWrapper);
        if("".equals(keyanxiangmu.getKeyanxiangmuFile()) || "null".equals(keyanxiangmu.getKeyanxiangmuFile())){
                keyanxiangmu.setKeyanxiangmuFile(null);
        }
        if("".equals(keyanxiangmu.getKeyanxiangmuLixiangFile()) || "null".equals(keyanxiangmu.getKeyanxiangmuLixiangFile())){
                keyanxiangmu.setKeyanxiangmuLixiangFile(null);
        }
        if("".equals(keyanxiangmu.getKeyanxiangmuJiexiangFile()) || "null".equals(keyanxiangmu.getKeyanxiangmuJiexiangFile())){
                keyanxiangmu.setKeyanxiangmuJiexiangFile(null);
        }
        if("".equals(keyanxiangmu.getKeyanxiangmuJiexiangwenjianFile()) || "null".equals(keyanxiangmu.getKeyanxiangmuJiexiangwenjianFile())){
                keyanxiangmu.setKeyanxiangmuJiexiangwenjianFile(null);
        }
        if(keyanxiangmuEntity==null){
            keyanxiangmuService.updateById(keyanxiangmu);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(keyanxiangmu, oldKeyanxiangmuEntity, request,new String[]{"updateTime"});
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
    public R shenhe(@RequestBody KeyanxiangmuEntity keyanxiangmuEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,keyanxiangmuEntity:{}",this.getClass().getName(),keyanxiangmuEntity.toString());

        KeyanxiangmuEntity oldKeyanxiangmu = keyanxiangmuService.selectById(keyanxiangmuEntity.getId());//查询原先数据

//        if(keyanxiangmuEntity.getKeyanxiangmuYesnoTypes() == 2){//通过
//            keyanxiangmuEntity.setKeyanxiangmuTypes();
//        }else if(keyanxiangmuEntity.getKeyanxiangmuYesnoTypes() == 3){//拒绝
//            keyanxiangmuEntity.setKeyanxiangmuTypes();
//        }
        keyanxiangmuEntity.setKeyanxiangmuShenheTime(new Date());//审核时间
        keyanxiangmuService.updateById(keyanxiangmuEntity);//审核

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"审核数据","审核"+oldKeyanxiangmu+"数据,审核结果是"+(keyanxiangmuEntity.getKeyanxiangmuYesnoTypes()==2?"通过":"拒绝"));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<KeyanxiangmuEntity> oldKeyanxiangmuList =keyanxiangmuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        keyanxiangmuService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldKeyanxiangmuList.toString());
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
            List<KeyanxiangmuEntity> keyanxiangmuList = new ArrayList<>();//上传的东西
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
                            KeyanxiangmuEntity keyanxiangmuEntity = new KeyanxiangmuEntity();
//                            keyanxiangmuEntity.setYonghuId(Integer.valueOf(data.get(0)));   //科研人员 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuUuidNumber(data.get(0));                    //科研项目编号 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuLaiyuan(data.get(0));                    //项目来源 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuName(data.get(0));                    //科研项目名称 要改的
//                            keyanxiangmuEntity.setDengjiTypes(Integer.valueOf(data.get(0)));   //项目等级 要改的
//                            keyanxiangmuEntity.setXuekeTypes(Integer.valueOf(data.get(0)));   //学科 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuHuafei(Integer.valueOf(data.get(0)));   //项目预算(元) 要改的
//                            keyanxiangmuEntity.setKaishiTime(sdf.parse(data.get(0)));          //项目开始时间 要改的
//                            keyanxiangmuEntity.setJieshuTime(sdf.parse(data.get(0)));          //项目结束时间 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuFile(data.get(0));                    //项目申报书 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuContent("");//详情和图片
//                            keyanxiangmuEntity.setInsertTime(date);//时间
//                            keyanxiangmuEntity.setKeyanxiangmuYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuYesnoText(data.get(0));                    //审核意见 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuLixiangFile(data.get(0));                    //项目立项文件 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuJiexiangFile(data.get(0));                    //项目结项书 要改的
//                            keyanxiangmuEntity.setKeyanxiangmuJiexiangwenjianFile(data.get(0));                    //项目结项文件 要改的
//                            keyanxiangmuEntity.setCreateTime(date);//时间
                            keyanxiangmuList.add(keyanxiangmuEntity);


                            //把要查询是否重复的字段放入map中
                                //科研项目编号
                                if(seachFields.containsKey("keyanxiangmuUuidNumber")){
                                    List<String> keyanxiangmuUuidNumber = seachFields.get("keyanxiangmuUuidNumber");
                                    keyanxiangmuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> keyanxiangmuUuidNumber = new ArrayList<>();
                                    keyanxiangmuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("keyanxiangmuUuidNumber",keyanxiangmuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //科研项目编号
                        List<KeyanxiangmuEntity> keyanxiangmuEntities_keyanxiangmuUuidNumber = keyanxiangmuService.selectList(new EntityWrapper<KeyanxiangmuEntity>().in("keyanxiangmu_uuid_number", seachFields.get("keyanxiangmuUuidNumber")));
                        if(keyanxiangmuEntities_keyanxiangmuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(KeyanxiangmuEntity s:keyanxiangmuEntities_keyanxiangmuUuidNumber){
                                repeatFields.add(s.getKeyanxiangmuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [科研项目编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        keyanxiangmuService.insertBatch(keyanxiangmuList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",keyanxiangmuList.toString());
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
