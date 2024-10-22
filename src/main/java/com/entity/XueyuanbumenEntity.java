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
 * 学院部门
 *
 * @author 
 * @email
 */
@TableName("xueyuanbumen")
public class XueyuanbumenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueyuanbumenEntity() {

	}

	public XueyuanbumenEntity(T t) {
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
     * 学院部门编号
     */
    @ColumnInfo(comment="学院部门编号",type="varchar(200)")
    @TableField(value = "xueyuanbumen_uuid_number")

    private String xueyuanbumenUuidNumber;


    /**
     * 学院部门名称
     */
    @ColumnInfo(comment="学院部门名称",type="varchar(200)")
    @TableField(value = "xueyuanbumen_name")

    private String xueyuanbumenName;


    /**
     * 学院部门位置
     */
    @ColumnInfo(comment="学院部门位置",type="varchar(200)")
    @TableField(value = "xueyuanbumen_address")

    private String xueyuanbumenAddress;


    /**
     * 学院部门描述
     */
    @ColumnInfo(comment="学院部门描述",type="text")
    @TableField(value = "xueyuanbumen_content")

    private String xueyuanbumenContent;


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
	 * 获取：学院部门编号
	 */
    public String getXueyuanbumenUuidNumber() {
        return xueyuanbumenUuidNumber;
    }
    /**
	 * 设置：学院部门编号
	 */

    public void setXueyuanbumenUuidNumber(String xueyuanbumenUuidNumber) {
        this.xueyuanbumenUuidNumber = xueyuanbumenUuidNumber;
    }
    /**
	 * 获取：学院部门名称
	 */
    public String getXueyuanbumenName() {
        return xueyuanbumenName;
    }
    /**
	 * 设置：学院部门名称
	 */

    public void setXueyuanbumenName(String xueyuanbumenName) {
        this.xueyuanbumenName = xueyuanbumenName;
    }
    /**
	 * 获取：学院部门位置
	 */
    public String getXueyuanbumenAddress() {
        return xueyuanbumenAddress;
    }
    /**
	 * 设置：学院部门位置
	 */

    public void setXueyuanbumenAddress(String xueyuanbumenAddress) {
        this.xueyuanbumenAddress = xueyuanbumenAddress;
    }
    /**
	 * 获取：学院部门描述
	 */
    public String getXueyuanbumenContent() {
        return xueyuanbumenContent;
    }
    /**
	 * 设置：学院部门描述
	 */

    public void setXueyuanbumenContent(String xueyuanbumenContent) {
        this.xueyuanbumenContent = xueyuanbumenContent;
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
        return "Xueyuanbumen{" +
            ", id=" + id +
            ", xueyuanbumenUuidNumber=" + xueyuanbumenUuidNumber +
            ", xueyuanbumenName=" + xueyuanbumenName +
            ", xueyuanbumenAddress=" + xueyuanbumenAddress +
            ", xueyuanbumenContent=" + xueyuanbumenContent +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
