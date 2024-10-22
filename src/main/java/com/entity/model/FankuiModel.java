package com.entity.model;

import com.entity.FankuiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 反馈
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class FankuiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 科研人员
     */
    private Integer yonghuId;


    /**
     * 反馈编号
     */
    private String fankuiUuidNumber;


    /**
     * 反馈名称
     */
    private String fankuiName;


    /**
     * 反馈类型
     */
    private Integer fankuiTypes;


    /**
     * 反馈内容
     */
    private String fankuiContent;


    /**
     * 反馈时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
