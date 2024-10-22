package com.entity.vo;

import com.entity.XueyuanbumenEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学院部门
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xueyuanbumen")
public class XueyuanbumenVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学院部门编号
     */

    @TableField(value = "xueyuanbumen_uuid_number")
    private String xueyuanbumenUuidNumber;


    /**
     * 学院部门名称
     */

    @TableField(value = "xueyuanbumen_name")
    private String xueyuanbumenName;


    /**
     * 学院部门位置
     */

    @TableField(value = "xueyuanbumen_address")
    private String xueyuanbumenAddress;


    /**
     * 学院部门描述
     */

    @TableField(value = "xueyuanbumen_content")
    private String xueyuanbumenContent;


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
	 * 设置：学院部门编号
	 */
    public String getXueyuanbumenUuidNumber() {
        return xueyuanbumenUuidNumber;
    }


    /**
	 * 获取：学院部门编号
	 */

    public void setXueyuanbumenUuidNumber(String xueyuanbumenUuidNumber) {
        this.xueyuanbumenUuidNumber = xueyuanbumenUuidNumber;
    }
    /**
	 * 设置：学院部门名称
	 */
    public String getXueyuanbumenName() {
        return xueyuanbumenName;
    }


    /**
	 * 获取：学院部门名称
	 */

    public void setXueyuanbumenName(String xueyuanbumenName) {
        this.xueyuanbumenName = xueyuanbumenName;
    }
    /**
	 * 设置：学院部门位置
	 */
    public String getXueyuanbumenAddress() {
        return xueyuanbumenAddress;
    }


    /**
	 * 获取：学院部门位置
	 */

    public void setXueyuanbumenAddress(String xueyuanbumenAddress) {
        this.xueyuanbumenAddress = xueyuanbumenAddress;
    }
    /**
	 * 设置：学院部门描述
	 */
    public String getXueyuanbumenContent() {
        return xueyuanbumenContent;
    }


    /**
	 * 获取：学院部门描述
	 */

    public void setXueyuanbumenContent(String xueyuanbumenContent) {
        this.xueyuanbumenContent = xueyuanbumenContent;
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
