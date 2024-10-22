package com.entity.model;

import com.entity.XuexiaoguanliyuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学校管理员
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XuexiaoguanliyuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 账户
     */
    private String username;


    /**
     * 密码
     */
    private String password;


    /**
     * 学校管理员编号
     */
    private String xuexiaoguanliyuanUuidNumber;


    /**
     * 学校管理员姓名
     */
    private String xuexiaoguanliyuanName;


    /**
     * 学校管理员手机号
     */
    private String xuexiaoguanliyuanPhone;


    /**
     * 学校管理员身份证号
     */
    private String xuexiaoguanliyuanIdNumber;


    /**
     * 学校管理员头像
     */
    private String xuexiaoguanliyuanPhoto;


    /**
     * 性别
     */
    private Integer sexTypes;


    /**
     * 电子邮箱
     */
    private String xuexiaoguanliyuanEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 设置：账户
	 */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 获取：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 设置：密码
	 */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 获取：学校管理员编号
	 */
    public String getXuexiaoguanliyuanUuidNumber() {
        return xuexiaoguanliyuanUuidNumber;
    }


    /**
	 * 设置：学校管理员编号
	 */
    public void setXuexiaoguanliyuanUuidNumber(String xuexiaoguanliyuanUuidNumber) {
        this.xuexiaoguanliyuanUuidNumber = xuexiaoguanliyuanUuidNumber;
    }
    /**
	 * 获取：学校管理员姓名
	 */
    public String getXuexiaoguanliyuanName() {
        return xuexiaoguanliyuanName;
    }


    /**
	 * 设置：学校管理员姓名
	 */
    public void setXuexiaoguanliyuanName(String xuexiaoguanliyuanName) {
        this.xuexiaoguanliyuanName = xuexiaoguanliyuanName;
    }
    /**
	 * 获取：学校管理员手机号
	 */
    public String getXuexiaoguanliyuanPhone() {
        return xuexiaoguanliyuanPhone;
    }


    /**
	 * 设置：学校管理员手机号
	 */
    public void setXuexiaoguanliyuanPhone(String xuexiaoguanliyuanPhone) {
        this.xuexiaoguanliyuanPhone = xuexiaoguanliyuanPhone;
    }
    /**
	 * 获取：学校管理员身份证号
	 */
    public String getXuexiaoguanliyuanIdNumber() {
        return xuexiaoguanliyuanIdNumber;
    }


    /**
	 * 设置：学校管理员身份证号
	 */
    public void setXuexiaoguanliyuanIdNumber(String xuexiaoguanliyuanIdNumber) {
        this.xuexiaoguanliyuanIdNumber = xuexiaoguanliyuanIdNumber;
    }
    /**
	 * 获取：学校管理员头像
	 */
    public String getXuexiaoguanliyuanPhoto() {
        return xuexiaoguanliyuanPhoto;
    }


    /**
	 * 设置：学校管理员头像
	 */
    public void setXuexiaoguanliyuanPhoto(String xuexiaoguanliyuanPhoto) {
        this.xuexiaoguanliyuanPhoto = xuexiaoguanliyuanPhoto;
    }
    /**
	 * 获取：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 设置：性别
	 */
    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getXuexiaoguanliyuanEmail() {
        return xuexiaoguanliyuanEmail;
    }


    /**
	 * 设置：电子邮箱
	 */
    public void setXuexiaoguanliyuanEmail(String xuexiaoguanliyuanEmail) {
        this.xuexiaoguanliyuanEmail = xuexiaoguanliyuanEmail;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
