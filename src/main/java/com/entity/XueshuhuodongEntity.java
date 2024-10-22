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
 * 学术活动
 *
 * @author 
 * @email
 */
@TableName("xueshuhuodong")
public class XueshuhuodongEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueshuhuodongEntity() {

	}

	public XueshuhuodongEntity(T t) {
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
     * 科研人员
     */
    @ColumnInfo(comment="科研人员",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 学术活动编号
     */
    @ColumnInfo(comment="学术活动编号",type="varchar(200)")
    @TableField(value = "xueshuhuodong_uuid_number")

    private String xueshuhuodongUuidNumber;


    /**
     * 学术活动名称
     */
    @ColumnInfo(comment="学术活动名称",type="varchar(200)")
    @TableField(value = "xueshuhuodong_name")

    private String xueshuhuodongName;


    /**
     * 学术活动照片
     */
    @ColumnInfo(comment="学术活动照片",type="varchar(200)")
    @TableField(value = "xueshuhuodong_photo")

    private String xueshuhuodongPhoto;


    /**
     * 学术活动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="学术活动时间",type="timestamp")
    @TableField(value = "xueshuhuodong_time")

    private Date xueshuhuodongTime;


    /**
     * 学术活动地点
     */
    @ColumnInfo(comment="学术活动地点",type="varchar(200)")
    @TableField(value = "xueshuhuodong_address")

    private String xueshuhuodongAddress;


    /**
     * 学术活动类型
     */
    @ColumnInfo(comment="学术活动类型",type="int(11)")
    @TableField(value = "xueshuhuodong_types")

    private Integer xueshuhuodongTypes;


    /**
     * 学术活动内容
     */
    @ColumnInfo(comment="学术活动内容",type="text")
    @TableField(value = "xueshuhuodong_content")

    private String xueshuhuodongContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "xueshuhuodong_yesno_types")

    private Integer xueshuhuodongYesnoTypes;


    /**
     * 审核意见
     */
    @ColumnInfo(comment="审核意见",type="text")
    @TableField(value = "xueshuhuodong_yesno_text")

    private String xueshuhuodongYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "xueshuhuodong_shenhe_time")

    private Date xueshuhuodongShenheTime;


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
	 * 获取：科研人员
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：科研人员
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：学术活动编号
	 */
    public String getXueshuhuodongUuidNumber() {
        return xueshuhuodongUuidNumber;
    }
    /**
	 * 设置：学术活动编号
	 */

    public void setXueshuhuodongUuidNumber(String xueshuhuodongUuidNumber) {
        this.xueshuhuodongUuidNumber = xueshuhuodongUuidNumber;
    }
    /**
	 * 获取：学术活动名称
	 */
    public String getXueshuhuodongName() {
        return xueshuhuodongName;
    }
    /**
	 * 设置：学术活动名称
	 */

    public void setXueshuhuodongName(String xueshuhuodongName) {
        this.xueshuhuodongName = xueshuhuodongName;
    }
    /**
	 * 获取：学术活动照片
	 */
    public String getXueshuhuodongPhoto() {
        return xueshuhuodongPhoto;
    }
    /**
	 * 设置：学术活动照片
	 */

    public void setXueshuhuodongPhoto(String xueshuhuodongPhoto) {
        this.xueshuhuodongPhoto = xueshuhuodongPhoto;
    }
    /**
	 * 获取：学术活动时间
	 */
    public Date getXueshuhuodongTime() {
        return xueshuhuodongTime;
    }
    /**
	 * 设置：学术活动时间
	 */

    public void setXueshuhuodongTime(Date xueshuhuodongTime) {
        this.xueshuhuodongTime = xueshuhuodongTime;
    }
    /**
	 * 获取：学术活动地点
	 */
    public String getXueshuhuodongAddress() {
        return xueshuhuodongAddress;
    }
    /**
	 * 设置：学术活动地点
	 */

    public void setXueshuhuodongAddress(String xueshuhuodongAddress) {
        this.xueshuhuodongAddress = xueshuhuodongAddress;
    }
    /**
	 * 获取：学术活动类型
	 */
    public Integer getXueshuhuodongTypes() {
        return xueshuhuodongTypes;
    }
    /**
	 * 设置：学术活动类型
	 */

    public void setXueshuhuodongTypes(Integer xueshuhuodongTypes) {
        this.xueshuhuodongTypes = xueshuhuodongTypes;
    }
    /**
	 * 获取：学术活动内容
	 */
    public String getXueshuhuodongContent() {
        return xueshuhuodongContent;
    }
    /**
	 * 设置：学术活动内容
	 */

    public void setXueshuhuodongContent(String xueshuhuodongContent) {
        this.xueshuhuodongContent = xueshuhuodongContent;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getXueshuhuodongYesnoTypes() {
        return xueshuhuodongYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setXueshuhuodongYesnoTypes(Integer xueshuhuodongYesnoTypes) {
        this.xueshuhuodongYesnoTypes = xueshuhuodongYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getXueshuhuodongYesnoText() {
        return xueshuhuodongYesnoText;
    }
    /**
	 * 设置：审核意见
	 */

    public void setXueshuhuodongYesnoText(String xueshuhuodongYesnoText) {
        this.xueshuhuodongYesnoText = xueshuhuodongYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getXueshuhuodongShenheTime() {
        return xueshuhuodongShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setXueshuhuodongShenheTime(Date xueshuhuodongShenheTime) {
        this.xueshuhuodongShenheTime = xueshuhuodongShenheTime;
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
        return "Xueshuhuodong{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", xueshuhuodongUuidNumber=" + xueshuhuodongUuidNumber +
            ", xueshuhuodongName=" + xueshuhuodongName +
            ", xueshuhuodongPhoto=" + xueshuhuodongPhoto +
            ", xueshuhuodongTime=" + DateUtil.convertString(xueshuhuodongTime,"yyyy-MM-dd") +
            ", xueshuhuodongAddress=" + xueshuhuodongAddress +
            ", xueshuhuodongTypes=" + xueshuhuodongTypes +
            ", xueshuhuodongContent=" + xueshuhuodongContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", xueshuhuodongYesnoTypes=" + xueshuhuodongYesnoTypes +
            ", xueshuhuodongYesnoText=" + xueshuhuodongYesnoText +
            ", xueshuhuodongShenheTime=" + DateUtil.convertString(xueshuhuodongShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
