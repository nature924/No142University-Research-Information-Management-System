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
 * 反馈
 *
 * @author 
 * @email
 */
@TableName("fankui")
public class FankuiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public FankuiEntity() {

	}

	public FankuiEntity(T t) {
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
     * 反馈编号
     */
    @ColumnInfo(comment="反馈编号",type="varchar(200)")
    @TableField(value = "fankui_uuid_number")

    private String fankuiUuidNumber;


    /**
     * 反馈名称
     */
    @ColumnInfo(comment="反馈名称",type="varchar(200)")
    @TableField(value = "fankui_name")

    private String fankuiName;


    /**
     * 反馈类型
     */
    @ColumnInfo(comment="反馈类型",type="int(11)")
    @TableField(value = "fankui_types")

    private Integer fankuiTypes;


    /**
     * 反馈内容
     */
    @ColumnInfo(comment="反馈内容",type="text")
    @TableField(value = "fankui_content")

    private String fankuiContent;


    /**
     * 反馈时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="反馈时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 获取：反馈编号
	 */
    public String getFankuiUuidNumber() {
        return fankuiUuidNumber;
    }
    /**
	 * 设置：反馈编号
	 */

    public void setFankuiUuidNumber(String fankuiUuidNumber) {
        this.fankuiUuidNumber = fankuiUuidNumber;
    }
    /**
	 * 获取：反馈名称
	 */
    public String getFankuiName() {
        return fankuiName;
    }
    /**
	 * 设置：反馈名称
	 */

    public void setFankuiName(String fankuiName) {
        this.fankuiName = fankuiName;
    }
    /**
	 * 获取：反馈类型
	 */
    public Integer getFankuiTypes() {
        return fankuiTypes;
    }
    /**
	 * 设置：反馈类型
	 */

    public void setFankuiTypes(Integer fankuiTypes) {
        this.fankuiTypes = fankuiTypes;
    }
    /**
	 * 获取：反馈内容
	 */
    public String getFankuiContent() {
        return fankuiContent;
    }
    /**
	 * 设置：反馈内容
	 */

    public void setFankuiContent(String fankuiContent) {
        this.fankuiContent = fankuiContent;
    }
    /**
	 * 获取：反馈时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：反馈时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Fankui{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", fankuiUuidNumber=" + fankuiUuidNumber +
            ", fankuiName=" + fankuiName +
            ", fankuiTypes=" + fankuiTypes +
            ", fankuiContent=" + fankuiContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
