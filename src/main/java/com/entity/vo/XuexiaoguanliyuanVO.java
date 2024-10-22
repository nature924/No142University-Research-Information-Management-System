package com.entity.vo;

import com.entity.XuexiaoguanliyuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学校管理员
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xuexiaoguanliyuan")
public class XuexiaoguanliyuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 账户
     */

    @TableField(value = "username")
    private String username;


    /**
     * 密码
     */

    @TableField(value = "password")
    private String password;


    /**
     * 学校管理员编号
     */

    @TableField(value = "xuexiaoguanliyuan_uuid_number")
    private String xuexiaoguanliyuanUuidNumber;


    /**
     * 学校管理员姓名
     */

    @TableField(value = "xuexiaoguanliyuan_name")
    private String xuexiaoguanliyuanName;


    /**
     * 学校管理员手机号
     */

    @TableField(value = "xuexiaoguanliyuan_phone")
    private String xuexiaoguanliyuanPhone;


    /**
     * 学校管理员身份证号
     */

    @TableField(value = "xuexiaoguanliyuan_id_number")
    private String xuexiaoguanliyuanIdNumber;


    /**
     * 学校管理员头像
     */

    @TableField(value = "xuexiaoguanliyuan_photo")
    private String xuexiaoguanliyuanPhoto;


    /**
     * 性别
     */

    @TableField(value = "sex_types")
    private Integer sexTypes;


    /**
     * 电子邮箱
     */

    @TableField(value = "xuexiaoguanliyuan_email")
    private String xuexiaoguanliyuanEmail;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：账户
	 */
    public String getUsername() {
        return username;
    }


    /**
	 * 获取：账户
	 */

    public void setUsername(String username) {
        this.username = username;
    }
    /**
	 * 设置：密码
	 */
    public String getPassword() {
        return password;
    }


    /**
	 * 获取：密码
	 */

    public void setPassword(String password) {
        this.password = password;
    }
    /**
	 * 设置：学校管理员编号
	 */
    public String getXuexiaoguanliyuanUuidNumber() {
        return xuexiaoguanliyuanUuidNumber;
    }


    /**
	 * 获取：学校管理员编号
	 */

    public void setXuexiaoguanliyuanUuidNumber(String xuexiaoguanliyuanUuidNumber) {
        this.xuexiaoguanliyuanUuidNumber = xuexiaoguanliyuanUuidNumber;
    }
    /**
	 * 设置：学校管理员姓名
	 */
    public String getXuexiaoguanliyuanName() {
        return xuexiaoguanliyuanName;
    }


    /**
	 * 获取：学校管理员姓名
	 */

    public void setXuexiaoguanliyuanName(String xuexiaoguanliyuanName) {
        this.xuexiaoguanliyuanName = xuexiaoguanliyuanName;
    }
    /**
	 * 设置：学校管理员手机号
	 */
    public String getXuexiaoguanliyuanPhone() {
        return xuexiaoguanliyuanPhone;
    }


    /**
	 * 获取：学校管理员手机号
	 */

    public void setXuexiaoguanliyuanPhone(String xuexiaoguanliyuanPhone) {
        this.xuexiaoguanliyuanPhone = xuexiaoguanliyuanPhone;
    }
    /**
	 * 设置：学校管理员身份证号
	 */
    public String getXuexiaoguanliyuanIdNumber() {
        return xuexiaoguanliyuanIdNumber;
    }


    /**
	 * 获取：学校管理员身份证号
	 */

    public void setXuexiaoguanliyuanIdNumber(String xuexiaoguanliyuanIdNumber) {
        this.xuexiaoguanliyuanIdNumber = xuexiaoguanliyuanIdNumber;
    }
    /**
	 * 设置：学校管理员头像
	 */
    public String getXuexiaoguanliyuanPhoto() {
        return xuexiaoguanliyuanPhoto;
    }


    /**
	 * 获取：学校管理员头像
	 */

    public void setXuexiaoguanliyuanPhoto(String xuexiaoguanliyuanPhoto) {
        this.xuexiaoguanliyuanPhoto = xuexiaoguanliyuanPhoto;
    }
    /**
	 * 设置：性别
	 */
    public Integer getSexTypes() {
        return sexTypes;
    }


    /**
	 * 获取：性别
	 */

    public void setSexTypes(Integer sexTypes) {
        this.sexTypes = sexTypes;
    }
    /**
	 * 设置：电子邮箱
	 */
    public String getXuexiaoguanliyuanEmail() {
        return xuexiaoguanliyuanEmail;
    }


    /**
	 * 获取：电子邮箱
	 */

    public void setXuexiaoguanliyuanEmail(String xuexiaoguanliyuanEmail) {
        this.xuexiaoguanliyuanEmail = xuexiaoguanliyuanEmail;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
