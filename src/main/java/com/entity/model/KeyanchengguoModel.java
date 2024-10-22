package com.entity.model;

import com.entity.KeyanchengguoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 科研成果
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class KeyanchengguoModel implements Serializable {
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
     * 科研成果编号
     */
    private String keyanchengguoUuidNumber;


    /**
     * 科研成果名称
     */
    private String keyanchengguoName;


    /**
     * 学科
     */
    private Integer xuekeTypes;


    /**
     * 成果类别
     */
    private Integer keyanchengguoTypes;


    /**
     * 成果类型
     */
    private Integer keyanchengguoLeixingTypes;


    /**
     * 附件
     */
    private String keyanchengguoFile;


    /**
     * 科研成果介绍
     */
    private String keyanchengguoContent;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 申请状态
     */
    private Integer keyanchengguoYesnoTypes;


    /**
     * 审核意见
     */
    private String keyanchengguoYesnoText;


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
