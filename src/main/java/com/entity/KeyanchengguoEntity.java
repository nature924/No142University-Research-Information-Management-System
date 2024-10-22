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
 * 科研成果
 *
 * @author 
 * @email
 */
@TableName("keyanchengguo")
public class KeyanchengguoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KeyanchengguoEntity() {

	}

	public KeyanchengguoEntity(T t) {
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
     * 科研成果编号
     */
    @ColumnInfo(comment="科研成果编号",type="varchar(200)")
    @TableField(value = "keyanchengguo_uuid_number")

    private String keyanchengguoUuidNumber;


    /**
     * 科研成果名称
     */
    @ColumnInfo(comment="科研成果名称",type="varchar(200)")
    @TableField(value = "keyanchengguo_name")

    private String keyanchengguoName;


    /**
     * 学科
     */
    @ColumnInfo(comment="学科",type="int(11)")
    @TableField(value = "xueke_types")

    private Integer xuekeTypes;


    /**
     * 成果类别
     */
    @ColumnInfo(comment="成果类别",type="int(11)")
    @TableField(value = "keyanchengguo_types")

    private Integer keyanchengguoTypes;


    /**
     * 成果类型
     */
    @ColumnInfo(comment="成果类型",type="int(11)")
    @TableField(value = "keyanchengguo_leixing_types")

    private Integer keyanchengguoLeixingTypes;


    /**
     * 附件
     */
    @ColumnInfo(comment="附件",type="varchar(200)")
    @TableField(value = "keyanchengguo_file")

    private String keyanchengguoFile;


    /**
     * 科研成果介绍
     */
    @ColumnInfo(comment="科研成果介绍",type="text")
    @TableField(value = "keyanchengguo_content")

    private String keyanchengguoContent;


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
    @TableField(value = "keyanchengguo_yesno_types")

    private Integer keyanchengguoYesnoTypes;


    /**
     * 审核意见
     */
    @ColumnInfo(comment="审核意见",type="text")
    @TableField(value = "keyanchengguo_yesno_text")

    private String keyanchengguoYesnoText;


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
	 * 获取：科研成果编号
	 */
    public String getKeyanchengguoUuidNumber() {
        return keyanchengguoUuidNumber;
    }
    /**
	 * 设置：科研成果编号
	 */

    public void setKeyanchengguoUuidNumber(String keyanchengguoUuidNumber) {
        this.keyanchengguoUuidNumber = keyanchengguoUuidNumber;
    }
    /**
	 * 获取：科研成果名称
	 */
    public String getKeyanchengguoName() {
        return keyanchengguoName;
    }
    /**
	 * 设置：科研成果名称
	 */

    public void setKeyanchengguoName(String keyanchengguoName) {
        this.keyanchengguoName = keyanchengguoName;
    }
    /**
	 * 获取：学科
	 */
    public Integer getXuekeTypes() {
        return xuekeTypes;
    }
    /**
	 * 设置：学科
	 */

    public void setXuekeTypes(Integer xuekeTypes) {
        this.xuekeTypes = xuekeTypes;
    }
    /**
	 * 获取：成果类别
	 */
    public Integer getKeyanchengguoTypes() {
        return keyanchengguoTypes;
    }
    /**
	 * 设置：成果类别
	 */

    public void setKeyanchengguoTypes(Integer keyanchengguoTypes) {
        this.keyanchengguoTypes = keyanchengguoTypes;
    }
    /**
	 * 获取：成果类型
	 */
    public Integer getKeyanchengguoLeixingTypes() {
        return keyanchengguoLeixingTypes;
    }
    /**
	 * 设置：成果类型
	 */

    public void setKeyanchengguoLeixingTypes(Integer keyanchengguoLeixingTypes) {
        this.keyanchengguoLeixingTypes = keyanchengguoLeixingTypes;
    }
    /**
	 * 获取：附件
	 */
    public String getKeyanchengguoFile() {
        return keyanchengguoFile;
    }
    /**
	 * 设置：附件
	 */

    public void setKeyanchengguoFile(String keyanchengguoFile) {
        this.keyanchengguoFile = keyanchengguoFile;
    }
    /**
	 * 获取：科研成果介绍
	 */
    public String getKeyanchengguoContent() {
        return keyanchengguoContent;
    }
    /**
	 * 设置：科研成果介绍
	 */

    public void setKeyanchengguoContent(String keyanchengguoContent) {
        this.keyanchengguoContent = keyanchengguoContent;
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
    public Integer getKeyanchengguoYesnoTypes() {
        return keyanchengguoYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setKeyanchengguoYesnoTypes(Integer keyanchengguoYesnoTypes) {
        this.keyanchengguoYesnoTypes = keyanchengguoYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getKeyanchengguoYesnoText() {
        return keyanchengguoYesnoText;
    }
    /**
	 * 设置：审核意见
	 */

    public void setKeyanchengguoYesnoText(String keyanchengguoYesnoText) {
        this.keyanchengguoYesnoText = keyanchengguoYesnoText;
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
        return "Keyanchengguo{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", keyanchengguoUuidNumber=" + keyanchengguoUuidNumber +
            ", keyanchengguoName=" + keyanchengguoName +
            ", xuekeTypes=" + xuekeTypes +
            ", keyanchengguoTypes=" + keyanchengguoTypes +
            ", keyanchengguoLeixingTypes=" + keyanchengguoLeixingTypes +
            ", keyanchengguoFile=" + keyanchengguoFile +
            ", keyanchengguoContent=" + keyanchengguoContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", keyanchengguoYesnoTypes=" + keyanchengguoYesnoTypes +
            ", keyanchengguoYesnoText=" + keyanchengguoYesnoText +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
