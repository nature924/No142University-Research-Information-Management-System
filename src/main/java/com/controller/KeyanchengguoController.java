
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
 * 科研成果
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/keyanchengguo")
public class KeyanchengguoController {
    private static final Logger logger = LoggerFactory.getLogger(KeyanchengguoController.class);

    private static final String TABLE_NAME = "keyanchengguo";

    @Autowired
    private KeyanchengguoService keyanchengguoService;


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
        PageUtils page = keyanchengguoService.queryPage(params);

        //字典表数据转换
        List<KeyanchengguoView> list =(List<KeyanchengguoView>)page.getList();
        for(KeyanchengguoView c:list){
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
        KeyanchengguoEntity keyanchengguo = keyanchengguoService.selectById(id);
        if(keyanchengguo !=null){
            //entity转view
            KeyanchengguoView view = new KeyanchengguoView();
            BeanUtils.copyProperties( keyanchengguo , view );//把实体数据重构到view中
            //级联表 科研人员
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(keyanchengguo.getYonghuId());
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
    public R save(@RequestBody KeyanchengguoEntity keyanchengguo, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,keyanchengguo:{}",this.getClass().getName(),keyanchengguo.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("科研人员".equals(role))
            keyanchengguo.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<KeyanchengguoEntity> queryWrapper = new EntityWrapper<KeyanchengguoEntity>()
            .eq("yonghu_id", keyanchengguo.getYonghuId())
            .eq("keyanchengguo_name", keyanchengguo.getKeyanchengguoName())
            .eq("xueke_types", keyanchengguo.getXuekeTypes())
            .eq("keyanchengguo_types", keyanchengguo.getKeyanchengguoTypes())
            .eq("keyanchengguo_leixing_types", keyanchengguo.getKeyanchengguoLeixingTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KeyanchengguoEntity keyanchengguoEntity = keyanchengguoService.selectOne(queryWrapper);
        if(keyanchengguoEntity==null){
            keyanchengguo.setInsertTime(new Date());
            keyanchengguo.setKeyanchengguoYesnoTypes(1);
            keyanchengguo.setCreateTime(new Date());
            keyanchengguoService.insert(keyanchengguo);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",keyanchengguo.toString());
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody KeyanchengguoEntity keyanchengguo, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,keyanchengguo:{}",this.getClass().getName(),keyanchengguo.toString());
        KeyanchengguoEntity oldKeyanchengguoEntity = keyanchengguoService.selectById(keyanchengguo.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("科研人员".equals(role))
//            keyanchengguo.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<KeyanchengguoEntity> queryWrapper = new EntityWrapper<KeyanchengguoEntity>()
            .notIn("id",keyanchengguo.getId())
            .andNew()
            .eq("yonghu_id", keyanchengguo.getYonghuId())
            .eq("keyanchengguo_name", keyanchengguo.getKeyanchengguoName())
            .eq("xueke_types", keyanchengguo.getXuekeTypes())
            .eq("keyanchengguo_types", keyanchengguo.getKeyanchengguoTypes())
            .eq("keyanchengguo_leixing_types", keyanchengguo.getKeyanchengguoLeixingTypes())
            .eq("insert_time", keyanchengguo.getInsertTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        KeyanchengguoEntity keyanchengguoEntity = keyanchengguoService.selectOne(queryWrapper);
        if("".equals(keyanchengguo.getKeyanchengguoFile()) || "null".equals(keyanchengguo.getKeyanchengguoFile())){
                keyanchengguo.setKeyanchengguoFile(null);
        }
        if(keyanchengguoEntity==null){
            keyanchengguoService.updateById(keyanchengguo);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(keyanchengguo, oldKeyanchengguoEntity, request,new String[]{"updateTime"});
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
    public R shenhe(@RequestBody KeyanchengguoEntity keyanchengguoEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,keyanchengguoEntity:{}",this.getClass().getName(),keyanchengguoEntity.toString());

        KeyanchengguoEntity oldKeyanchengguo = keyanchengguoService.selectById(keyanchengguoEntity.getId());//查询原先数据

//        if(keyanchengguoEntity.getKeyanchengguoYesnoTypes() == 2){//通过
//            keyanchengguoEntity.setKeyanchengguoTypes();
//        }else if(keyanchengguoEntity.getKeyanchengguoYesnoTypes() == 3){//拒绝
//            keyanchengguoEntity.setKeyanchengguoTypes();
//        }
        keyanchengguoService.updateById(keyanchengguoEntity);//审核

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"审核数据","审核"+oldKeyanchengguo+"数据,审核结果是"+(keyanchengguoEntity.getKeyanchengguoYesnoTypes()==2?"通过":"拒绝"));
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<KeyanchengguoEntity> oldKeyanchengguoList =keyanchengguoService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        keyanchengguoService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldKeyanchengguoList.toString());
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
            List<KeyanchengguoEntity> keyanchengguoList = new ArrayList<>();//上传的东西
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
                            KeyanchengguoEntity keyanchengguoEntity = new KeyanchengguoEntity();
//                            keyanchengguoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //科研人员 要改的
//                            keyanchengguoEntity.setKeyanchengguoUuidNumber(data.get(0));                    //科研成果编号 要改的
//                            keyanchengguoEntity.setKeyanchengguoName(data.get(0));                    //科研成果名称 要改的
//                            keyanchengguoEntity.setXuekeTypes(Integer.valueOf(data.get(0)));   //学科 要改的
//                            keyanchengguoEntity.setKeyanchengguoTypes(Integer.valueOf(data.get(0)));   //成果类别 要改的
//                            keyanchengguoEntity.setKeyanchengguoLeixingTypes(Integer.valueOf(data.get(0)));   //成果类型 要改的
//                            keyanchengguoEntity.setKeyanchengguoFile(data.get(0));                    //附件 要改的
//                            keyanchengguoEntity.setKeyanchengguoContent("");//详情和图片
//                            keyanchengguoEntity.setInsertTime(date);//时间
//                            keyanchengguoEntity.setKeyanchengguoYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            keyanchengguoEntity.setKeyanchengguoYesnoText(data.get(0));                    //审核意见 要改的
//                            keyanchengguoEntity.setCreateTime(date);//时间
                            keyanchengguoList.add(keyanchengguoEntity);


                            //把要查询是否重复的字段放入map中
                                //科研成果编号
                                if(seachFields.containsKey("keyanchengguoUuidNumber")){
                                    List<String> keyanchengguoUuidNumber = seachFields.get("keyanchengguoUuidNumber");
                                    keyanchengguoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> keyanchengguoUuidNumber = new ArrayList<>();
                                    keyanchengguoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("keyanchengguoUuidNumber",keyanchengguoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //科研成果编号
                        List<KeyanchengguoEntity> keyanchengguoEntities_keyanchengguoUuidNumber = keyanchengguoService.selectList(new EntityWrapper<KeyanchengguoEntity>().in("keyanchengguo_uuid_number", seachFields.get("keyanchengguoUuidNumber")));
                        if(keyanchengguoEntities_keyanchengguoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(KeyanchengguoEntity s:keyanchengguoEntities_keyanchengguoUuidNumber){
                                repeatFields.add(s.getKeyanchengguoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [科研成果编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        keyanchengguoService.insertBatch(keyanchengguoList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",keyanchengguoList.toString());
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
