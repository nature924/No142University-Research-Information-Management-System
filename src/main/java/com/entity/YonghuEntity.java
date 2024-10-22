package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 科研人员
 *
 * @author 
 * @email
 */
@TableName("yonghu")
public class YonghuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public YonghuEntity() {

	}

	public YonghuEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 账户
     */
    @ColumnInfo(comment="账户",type="varchar(200)")
    @TableField(value = "username")

    private String username;


    /**
     * 密码
     */
    @ColumnInfo(comment="密码",type="varchar(200)")
    @TableField(value = "password")

    private String password;


    /**
     * 学院部门
     */
    @ColumnInfo(comment="学院部门",type="int(11)")
    @TableField(value = "xueyuanbumen_id")

    private Integer xueyuanbumenId;


    /**
     * 科研人员编号
     */
    @ColumnInfo(comment="科研人员编号",type="varchar(200)")
    @TableField(value = "yonghu_uuid_number")

    private String yonghuUuidNumber;


    /**
     * 科研人员姓名
     */
    @ColumnInfo(comment="科研人员姓名",type="varchar(200)")
    @TableField(value = "yonghu_name")

    private String yonghuName;


    /**
     * 科研人员手机号
     */
    @ColumnInfo(comment="科研人员手机号",type="varchar(200)")
    @TableField(value = "yonghu_phone")

    private String yonghuPhone;


    /**
     * 科研人员身份证号
     */
    @ColumnInfo(comment="科研人员身份证号",type="varchar(200)")
    @TableField(value = "yonghu_id_number")

    private String yonghuIdNumber;


    /**
     * 科研人员头像
     */
    @ColumnInfo(comment="科研人员头像",type="varchar(200)")
    @TableField(value = "yonghu_photo")

    private String yonghuPhoto;


    /**
     * 性别
     */
    @ColumnInfo(comment="性别",type="int(11)")
    @TableField(value = "sex_types")

    private Integer sexTypes;


    /**
     * 年龄
     */
    @ColumnInfo(comment="年龄",type="int(11)")
    @TableField(value = "age")

    private Integer age;


    /**
     * 电子邮箱
     */
    @ColumnInfo(comment="电子邮箱",type="varchar(200)")
    @TableField(value = "yonghu_email")

    private String yonghuEmail;


    /**
     * 职务
     */
    @ColumnInfo(comment="职务",type="int(11)")
    @TableField(value = "zhiwu_types")

    private Integer zhiwuTypes;


    /**
     * 职称
     */
    @ColumnInfo(comment="职称",type="int(11)")
    @TableField(value = "zhicheng_types")

    private Integer zhichengTypes;


    /**
     * 学历
     */
    @ColumnInfo(comment="学历",type="int(11)")
    @TableField(value = "xueli_types")

    private Integer xueliTypes;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：学院部门
	 */
    public Integer getXueyuanbumenId() {
        return xueyuanbumenId;
    }
    /**
	 * 设置：学院部门
	 */

    public void setXueyuanbumenId(Integer xueyuanbumenId) {
        this.xueyuanbumenId = xueyuanbumenId;
    }
    /**
	 * 获取：科研人员编号
	 */
    public String getYonghuUuidNumber() {
        return yonghuUuidNumber;
    }
    /**
	 * 设置：科研人员编号
	 */

    public void setYonghuUuidNumber(String yonghuUuidNumber) {
        this.yonghuUuidNumber = yonghuUuidNumber;
    }
    /**
	 * 获取：科研人员姓名
	 */
    public String getYonghuName() {
        return yonghuName;
    }
    /**
	 * 设置：科研人员姓名
	 */

    public void setYonghuName(String yonghuName) {
        this.yonghuName = yonghuName;
    }
    /**
	 * 获取：科研人员手机号
	 */
    public String getYonghuPhone() {
        return yonghuPhone;
    }
    /**
	 * 设置：科研人员手机号
	 */

    public void setYonghuPhone(String yonghuPhone) {
        this.yonghuPhone = yonghuPhone;
    }
    /**
	 * 获取：科研人员身份证号
	 */
    public String getYonghuIdNumber() {
        return yonghuIdNumber;
    }
    /**
	 * 设置：科研人员身份证号
	 */

    public void setYonghuIdNumber(String yonghuIdNumber) {
        this.yonghuIdNumber = yonghuIdNumber;
    }
    /**
	 * 获取：科研人员头像
	 */
    public String getYonghuPhoto() {
        return yonghuPhoto;
    }
    /**
	 * 设置：科研人员头像
	 */

    public void setYonghuPhoto(String yonghuPhoto) {
        this.yonghuPhoto = yonghuPhoto;
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
	 * 获取：年龄
	 */
    public Integer getAge() {
        return age;
    }
    /**
	 * 设置：年龄
	 */

    public void setAge(Integer age) {
        this.age = age;
    }
    /**
	 * 获取：电子邮箱
	 */
    public String getYonghuEmail() {
        return yonghuEmail;
    }
    /**
	 * 设置：电子邮箱
	 */

    public void setYonghuEmail(String yonghuEmail) {
        this.yonghuEmail = yonghuEmail;
    }
    /**
	 * 获取：职务
	 */
    public Integer getZhiwuTypes() {
        return zhiwuTypes;
    }
    /**
	 * 设置：职务
	 */

    public void setZhiwuTypes(Integer zhiwuTypes) {
        this.zhiwuTypes = zhiwuTypes;
    }
    /**
	 * 获取：职称
	 */
    public Integer getZhichengTypes() {
        return zhichengTypes;
    }
    /**
	 * 设置：职称
	 */

    public void setZhichengTypes(Integer zhichengTypes) {
        this.zhichengTypes = zhichengTypes;
    }
    /**
	 * 获取：学历
	 */
    public Integer getXueliTypes() {
        return xueliTypes;
    }
    /**
	 * 设置：学历
	 */

    public void setXueliTypes(Integer xueliTypes) {
        this.xueliTypes = xueliTypes;
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

    @Override
    public String toString() {
        return "Yonghu{" +
            ", id=" + id +
            ", username=" + username +
            ", password=" + password +
            ", xueyuanbumenId=" + xueyuanbumenId +
            ", yonghuUuidNumber=" + yonghuUuidNumber +
            ", yonghuName=" + yonghuName +
            ", yonghuPhone=" + yonghuPhone +
            ", yonghuIdNumber=" + yonghuIdNumber +
            ", yonghuPhoto=" + yonghuPhoto +
            ", sexTypes=" + sexTypes +
            ", age=" + age +
            ", yonghuEmail=" + yonghuEmail +
            ", zhiwuTypes=" + zhiwuTypes +
            ", zhichengTypes=" + zhichengTypes +
            ", xueliTypes=" + xueliTypes +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
