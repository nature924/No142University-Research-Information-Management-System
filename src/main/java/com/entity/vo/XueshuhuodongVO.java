package com.entity.vo;

import com.entity.XueshuhuodongEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学术活动
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xueshuhuodong")
public class XueshuhuodongVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 科研人员
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 学术活动编号
     */

    @TableField(value = "xueshuhuodong_uuid_number")
    private String xueshuhuodongUuidNumber;


    /**
     * 学术活动名称
     */

    @TableField(value = "xueshuhuodong_name")
    private String xueshuhuodongName;


    /**
     * 学术活动照片
     */

    @TableField(value = "xueshuhuodong_photo")
    private String xueshuhuodongPhoto;


    /**
     * 学术活动时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xueshuhuodong_time")
    private Date xueshuhuodongTime;


    /**
     * 学术活动地点
     */

    @TableField(value = "xueshuhuodong_address")
    private String xueshuhuodongAddress;


    /**
     * 学术活动类型
     */

    @TableField(value = "xueshuhuodong_types")
    private Integer xueshuhuodongTypes;


    /**
     * 学术活动内容
     */

    @TableField(value = "xueshuhuodong_content")
    private String xueshuhuodongContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 申请状态
     */

    @TableField(value = "xueshuhuodong_yesno_types")
    private Integer xueshuhuodongYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "xueshuhuodong_yesno_text")
    private String xueshuhuodongYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xueshuhuodong_shenhe_time")
    private Date xueshuhuodongShenheTime;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 设置：科研人员
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：科研人员
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：学术活动编号
	 */
    public String getXueshuhuodongUuidNumber() {
        return xueshuhuodongUuidNumber;
    }


    /**
	 * 获取：学术活动编号
	 */

    public void setXueshuhuodongUuidNumber(String xueshuhuodongUuidNumber) {
        this.xueshuhuodongUuidNumber = xueshuhuodongUuidNumber;
    }
    /**
	 * 设置：学术活动名称
	 */
    public String getXueshuhuodongName() {
        return xueshuhuodongName;
    }


    /**
	 * 获取：学术活动名称
	 */

    public void setXueshuhuodongName(String xueshuhuodongName) {
        this.xueshuhuodongName = xueshuhuodongName;
    }
    /**
	 * 设置：学术活动照片
	 */
    public String getXueshuhuodongPhoto() {
        return xueshuhuodongPhoto;
    }


    /**
	 * 获取：学术活动照片
	 */

    public void setXueshuhuodongPhoto(String xueshuhuodongPhoto) {
        this.xueshuhuodongPhoto = xueshuhuodongPhoto;
    }
    /**
	 * 设置：学术活动时间
	 */
    public Date getXueshuhuodongTime() {
        return xueshuhuodongTime;
    }


    /**
	 * 获取：学术活动时间
	 */

    public void setXueshuhuodongTime(Date xueshuhuodongTime) {
        this.xueshuhuodongTime = xueshuhuodongTime;
    }
    /**
	 * 设置：学术活动地点
	 */
    public String getXueshuhuodongAddress() {
        return xueshuhuodongAddress;
    }


    /**
	 * 获取：学术活动地点
	 */

    public void setXueshuhuodongAddress(String xueshuhuodongAddress) {
        this.xueshuhuodongAddress = xueshuhuodongAddress;
    }
    /**
	 * 设置：学术活动类型
	 */
    public Integer getXueshuhuodongTypes() {
        return xueshuhuodongTypes;
    }


    /**
	 * 获取：学术活动类型
	 */

    public void setXueshuhuodongTypes(Integer xueshuhuodongTypes) {
        this.xueshuhuodongTypes = xueshuhuodongTypes;
    }
    /**
	 * 设置：学术活动内容
	 */
    public String getXueshuhuodongContent() {
        return xueshuhuodongContent;
    }


    /**
	 * 获取：学术活动内容
	 */

    public void setXueshuhuodongContent(String xueshuhuodongContent) {
        this.xueshuhuodongContent = xueshuhuodongContent;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getXueshuhuodongYesnoTypes() {
        return xueshuhuodongYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setXueshuhuodongYesnoTypes(Integer xueshuhuodongYesnoTypes) {
        this.xueshuhuodongYesnoTypes = xueshuhuodongYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getXueshuhuodongYesnoText() {
        return xueshuhuodongYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setXueshuhuodongYesnoText(String xueshuhuodongYesnoText) {
        this.xueshuhuodongYesnoText = xueshuhuodongYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getXueshuhuodongShenheTime() {
        return xueshuhuodongShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setXueshuhuodongShenheTime(Date xueshuhuodongShenheTime) {
        this.xueshuhuodongShenheTime = xueshuhuodongShenheTime;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
