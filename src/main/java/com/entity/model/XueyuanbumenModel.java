package com.entity.model;

import com.entity.XueyuanbumenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学院部门
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XueyuanbumenModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学院部门编号
     */
    private String xueyuanbumenUuidNumber;


    /**
     * 学院部门名称
     */
    private String xueyuanbumenName;


    /**
     * 学院部门位置
     */
    private String xueyuanbumenAddress;


    /**
     * 学院部门描述
     */
    private String xueyuanbumenContent;


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

    }
