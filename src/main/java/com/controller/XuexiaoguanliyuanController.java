
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
 * 学校管理员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xuexiaoguanliyuan")
public class XuexiaoguanliyuanController {
    private static final Logger logger = LoggerFactory.getLogger(XuexiaoguanliyuanController.class);

    private static final String TABLE_NAME = "xuexiaoguanliyuan";

    @Autowired
    private XuexiaoguanliyuanService xuexiaoguanliyuanService;


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
        PageUtils page = xuexiaoguanliyuanService.queryPage(params);

        //字典表数据转换
        List<XuexiaoguanliyuanView> list =(List<XuexiaoguanliyuanView>)page.getList();
        for(XuexiaoguanliyuanView c:list){
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
        XuexiaoguanliyuanEntity xuexiaoguanliyuan = xuexiaoguanliyuanService.selectById(id);
        if(xuexiaoguanliyuan !=null){
            //entity转view
            XuexiaoguanliyuanView view = new XuexiaoguanliyuanView();
            BeanUtils.copyProperties( xuexiaoguanliyuan , view );//把实体数据重构到view中
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
    public R save(@RequestBody XuexiaoguanliyuanEntity xuexiaoguanliyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xuexiaoguanliyuan:{}",this.getClass().getName(),xuexiaoguanliyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<XuexiaoguanliyuanEntity> queryWrapper = new EntityWrapper<XuexiaoguanliyuanEntity>()
            .eq("username", xuexiaoguanliyuan.getUsername())
            .or()
            .eq("xuexiaoguanliyuan_phone", xuexiaoguanliyuan.getXuexiaoguanliyuanPhone())
            .or()
            .eq("xuexiaoguanliyuan_id_number", xuexiaoguanliyuan.getXuexiaoguanliyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexiaoguanliyuanEntity xuexiaoguanliyuanEntity = xuexiaoguanliyuanService.selectOne(queryWrapper);
        if(xuexiaoguanliyuanEntity==null){
            xuexiaoguanliyuan.setCreateTime(new Date());
            xuexiaoguanliyuan.setPassword("123456");
            xuexiaoguanliyuanService.insert(xuexiaoguanliyuan);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"新增",xuexiaoguanliyuan.toString());
            return R.ok();
        }else {
            return R.error(511,"账户或者学校管理员手机号或者学校管理员身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XuexiaoguanliyuanEntity xuexiaoguanliyuan, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,xuexiaoguanliyuan:{}",this.getClass().getName(),xuexiaoguanliyuan.toString());
        XuexiaoguanliyuanEntity oldXuexiaoguanliyuanEntity = xuexiaoguanliyuanService.selectById(xuexiaoguanliyuan.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<XuexiaoguanliyuanEntity> queryWrapper = new EntityWrapper<XuexiaoguanliyuanEntity>()
            .notIn("id",xuexiaoguanliyuan.getId())
            .andNew()
            .eq("username", xuexiaoguanliyuan.getUsername())
            .or()
            .eq("xuexiaoguanliyuan_phone", xuexiaoguanliyuan.getXuexiaoguanliyuanPhone())
            .or()
            .eq("xuexiaoguanliyuan_id_number", xuexiaoguanliyuan.getXuexiaoguanliyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        XuexiaoguanliyuanEntity xuexiaoguanliyuanEntity = xuexiaoguanliyuanService.selectOne(queryWrapper);
        if("".equals(xuexiaoguanliyuan.getXuexiaoguanliyuanPhoto()) || "null".equals(xuexiaoguanliyuan.getXuexiaoguanliyuanPhoto())){
                xuexiaoguanliyuan.setXuexiaoguanliyuanPhoto(null);
        }
        if(xuexiaoguanliyuanEntity==null){
            xuexiaoguanliyuanService.updateById(xuexiaoguanliyuan);//根据id更新
            List<String> strings = caozuorizhiService.clazzDiff(xuexiaoguanliyuan, oldXuexiaoguanliyuanEntity, request,new String[]{"updateTime"});
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"修改",strings.toString());
            return R.ok();
        }else {
            return R.error(511,"账户或者学校管理员手机号或者学校管理员身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<XuexiaoguanliyuanEntity> oldXuexiaoguanliyuanList =xuexiaoguanliyuanService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        xuexiaoguanliyuanService.deleteBatchIds(Arrays.asList(ids));

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"删除",oldXuexiaoguanliyuanList.toString());
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
            List<XuexiaoguanliyuanEntity> xuexiaoguanliyuanList = new ArrayList<>();//上传的东西
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
                            XuexiaoguanliyuanEntity xuexiaoguanliyuanEntity = new XuexiaoguanliyuanEntity();
//                            xuexiaoguanliyuanEntity.setUsername(data.get(0));                    //账户 要改的
//                            //xuexiaoguanliyuanEntity.setPassword("123456");//密码
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanUuidNumber(data.get(0));                    //学校管理员编号 要改的
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanName(data.get(0));                    //学校管理员姓名 要改的
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanPhone(data.get(0));                    //学校管理员手机号 要改的
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanIdNumber(data.get(0));                    //学校管理员身份证号 要改的
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanPhoto("");//详情和图片
//                            xuexiaoguanliyuanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            xuexiaoguanliyuanEntity.setXuexiaoguanliyuanEmail(data.get(0));                    //电子邮箱 要改的
//                            xuexiaoguanliyuanEntity.setCreateTime(date);//时间
                            xuexiaoguanliyuanList.add(xuexiaoguanliyuanEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //学校管理员编号
                                if(seachFields.containsKey("xuexiaoguanliyuanUuidNumber")){
                                    List<String> xuexiaoguanliyuanUuidNumber = seachFields.get("xuexiaoguanliyuanUuidNumber");
                                    xuexiaoguanliyuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xuexiaoguanliyuanUuidNumber = new ArrayList<>();
                                    xuexiaoguanliyuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xuexiaoguanliyuanUuidNumber",xuexiaoguanliyuanUuidNumber);
                                }
                                //学校管理员手机号
                                if(seachFields.containsKey("xuexiaoguanliyuanPhone")){
                                    List<String> xuexiaoguanliyuanPhone = seachFields.get("xuexiaoguanliyuanPhone");
                                    xuexiaoguanliyuanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> xuexiaoguanliyuanPhone = new ArrayList<>();
                                    xuexiaoguanliyuanPhone.add(data.get(0));//要改的
                                    seachFields.put("xuexiaoguanliyuanPhone",xuexiaoguanliyuanPhone);
                                }
                                //学校管理员身份证号
                                if(seachFields.containsKey("xuexiaoguanliyuanIdNumber")){
                                    List<String> xuexiaoguanliyuanIdNumber = seachFields.get("xuexiaoguanliyuanIdNumber");
                                    xuexiaoguanliyuanIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xuexiaoguanliyuanIdNumber = new ArrayList<>();
                                    xuexiaoguanliyuanIdNumber.add(data.get(0));//要改的
                                    seachFields.put("xuexiaoguanliyuanIdNumber",xuexiaoguanliyuanIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<XuexiaoguanliyuanEntity> xuexiaoguanliyuanEntities_username = xuexiaoguanliyuanService.selectList(new EntityWrapper<XuexiaoguanliyuanEntity>().in("username", seachFields.get("username")));
                        if(xuexiaoguanliyuanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoguanliyuanEntity s:xuexiaoguanliyuanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学校管理员编号
                        List<XuexiaoguanliyuanEntity> xuexiaoguanliyuanEntities_xuexiaoguanliyuanUuidNumber = xuexiaoguanliyuanService.selectList(new EntityWrapper<XuexiaoguanliyuanEntity>().in("xuexiaoguanliyuan_uuid_number", seachFields.get("xuexiaoguanliyuanUuidNumber")));
                        if(xuexiaoguanliyuanEntities_xuexiaoguanliyuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoguanliyuanEntity s:xuexiaoguanliyuanEntities_xuexiaoguanliyuanUuidNumber){
                                repeatFields.add(s.getXuexiaoguanliyuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [学校管理员编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学校管理员手机号
                        List<XuexiaoguanliyuanEntity> xuexiaoguanliyuanEntities_xuexiaoguanliyuanPhone = xuexiaoguanliyuanService.selectList(new EntityWrapper<XuexiaoguanliyuanEntity>().in("xuexiaoguanliyuan_phone", seachFields.get("xuexiaoguanliyuanPhone")));
                        if(xuexiaoguanliyuanEntities_xuexiaoguanliyuanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoguanliyuanEntity s:xuexiaoguanliyuanEntities_xuexiaoguanliyuanPhone){
                                repeatFields.add(s.getXuexiaoguanliyuanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [学校管理员手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //学校管理员身份证号
                        List<XuexiaoguanliyuanEntity> xuexiaoguanliyuanEntities_xuexiaoguanliyuanIdNumber = xuexiaoguanliyuanService.selectList(new EntityWrapper<XuexiaoguanliyuanEntity>().in("xuexiaoguanliyuan_id_number", seachFields.get("xuexiaoguanliyuanIdNumber")));
                        if(xuexiaoguanliyuanEntities_xuexiaoguanliyuanIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XuexiaoguanliyuanEntity s:xuexiaoguanliyuanEntities_xuexiaoguanliyuanIdNumber){
                                repeatFields.add(s.getXuexiaoguanliyuanIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [学校管理员身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xuexiaoguanliyuanService.insertBatch(xuexiaoguanliyuanList);
                        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"批量新增",xuexiaoguanliyuanList.toString());
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        XuexiaoguanliyuanEntity xuexiaoguanliyuan = xuexiaoguanliyuanService.selectOne(new EntityWrapper<XuexiaoguanliyuanEntity>().eq("username", username));
        if(xuexiaoguanliyuan==null || !xuexiaoguanliyuan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(xuexiaoguanliyuan.getId(),username, "xuexiaoguanliyuan", "学校管理员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","学校管理员");
        r.put("username",xuexiaoguanliyuan.getXuexiaoguanliyuanName());
        r.put("tableName","xuexiaoguanliyuan");
        r.put("userId",xuexiaoguanliyuan.getId());
        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"登录",xuexiaoguanliyuan.toString());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody XuexiaoguanliyuanEntity xuexiaoguanliyuan, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<XuexiaoguanliyuanEntity> queryWrapper = new EntityWrapper<XuexiaoguanliyuanEntity>()
            .eq("username", xuexiaoguanliyuan.getUsername())
            .or()
            .eq("xuexiaoguanliyuan_phone", xuexiaoguanliyuan.getXuexiaoguanliyuanPhone())
            .or()
            .eq("xuexiaoguanliyuan_id_number", xuexiaoguanliyuan.getXuexiaoguanliyuanIdNumber())
            ;
        XuexiaoguanliyuanEntity xuexiaoguanliyuanEntity = xuexiaoguanliyuanService.selectOne(queryWrapper);
        if(xuexiaoguanliyuanEntity != null)
            return R.error("账户或者学校管理员手机号或者学校管理员身份证号已经被使用");
        xuexiaoguanliyuan.setXuexiaoguanliyuanUuidNumber(String.valueOf(new Date().getTime()));
        xuexiaoguanliyuan.setCreateTime(new Date());
        xuexiaoguanliyuanService.insert(xuexiaoguanliyuan);

        caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"注册",xuexiaoguanliyuan.toString());
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        XuexiaoguanliyuanEntity xuexiaoguanliyuan = xuexiaoguanliyuanService.selectById(id);
        xuexiaoguanliyuan.setPassword("123456");
        xuexiaoguanliyuanService.updateById(xuexiaoguanliyuan);
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"重置密码",xuexiaoguanliyuan.toString());
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        XuexiaoguanliyuanEntity xuexiaoguanliyuan = xuexiaoguanliyuanService.selectOne(new EntityWrapper<XuexiaoguanliyuanEntity>().eq("username", username));
        if(xuexiaoguanliyuan!=null){
            xuexiaoguanliyuan.setPassword("123456");
            boolean b = xuexiaoguanliyuanService.updateById(xuexiaoguanliyuan);
            if(!b){
               return R.error();
            }
            caozuorizhiService.insertCaozuorizhi(String.valueOf(request.getSession().getAttribute("role")),TABLE_NAME,String.valueOf(request.getSession().getAttribute("username")),"忘记密码",xuexiaoguanliyuan.toString());
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrXuexiaoguanliyuan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        XuexiaoguanliyuanEntity xuexiaoguanliyuan = xuexiaoguanliyuanService.selectById(id);
        if(xuexiaoguanliyuan !=null){
            //entity转view
            XuexiaoguanliyuanView view = new XuexiaoguanliyuanView();
            BeanUtils.copyProperties( xuexiaoguanliyuan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



}
