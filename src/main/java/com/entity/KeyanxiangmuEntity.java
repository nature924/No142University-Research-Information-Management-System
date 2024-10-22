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
 * 科研项目
 *
 * @author 
 * @email
 */
@TableName("keyanxiangmu")
public class KeyanxiangmuEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public KeyanxiangmuEntity() {

	}

	public KeyanxiangmuEntity(T t) {
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
     * 科研项目编号
     */
    @ColumnInfo(comment="科研项目编号",type="varchar(200)")
    @TableField(value = "keyanxiangmu_uuid_number")

    private String keyanxiangmuUuidNumber;


    /**
     * 项目来源
     */
    @ColumnInfo(comment="项目来源",type="varchar(200)")
    @TableField(value = "keyanxiangmu_laiyuan")

    private String keyanxiangmuLaiyuan;


    /**
     * 科研项目名称
     */
    @ColumnInfo(comment="科研项目名称",type="varchar(200)")
    @TableField(value = "keyanxiangmu_name")

    private String keyanxiangmuName;


    /**
     * 项目等级
     */
    @ColumnInfo(comment="项目等级",type="int(11)")
    @TableField(value = "dengji_types")

    private Integer dengjiTypes;


    /**
     * 学科
     */
    @ColumnInfo(comment="学科",type="int(11)")
    @TableField(value = "xueke_types")

    private Integer xuekeTypes;


    /**
     * 项目预算(元)
     */
    @ColumnInfo(comment="项目预算(元)",type="int(11)")
    @TableField(value = "keyanxiangmu_huafei")

    private Integer keyanxiangmuHuafei;


    /**
     * 项目开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="项目开始时间",type="timestamp")
    @TableField(value = "kaishi_time")

    private Date kaishiTime;


    /**
     * 项目结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="项目结束时间",type="timestamp")
    @TableField(value = "jieshu_time")

    private Date jieshuTime;


    /**
     * 项目申报书
     */
    @ColumnInfo(comment="项目申报书",type="varchar(200)")
    @TableField(value = "keyanxiangmu_file")

    private String keyanxiangmuFile;


    /**
     * 科研项目介绍
     */
    @ColumnInfo(comment="科研项目介绍",type="text")
    @TableField(value = "keyanxiangmu_content")

    private String keyanxiangmuContent;


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
    @TableField(value = "keyanxiangmu_yesno_types")

    private Integer keyanxiangmuYesnoTypes;


    /**
     * 审核意见
     */
    @ColumnInfo(comment="审核意见",type="text")
    @TableField(value = "keyanxiangmu_yesno_text")

    private String keyanxiangmuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "keyanxiangmu_shenhe_time")

    private Date keyanxiangmuShenheTime;


    /**
     * 项目立项文件
     */
    @ColumnInfo(comment="项目立项文件",type="varchar(200)")
    @TableField(value = "keyanxiangmu_lixiang_file")

    private String keyanxiangmuLixiangFile;


    /**
     * 项目结项书
     */
    @ColumnInfo(comment="项目结项书",type="varchar(200)")
    @TableField(value = "keyanxiangmu_jiexiang_file")

    private String keyanxiangmuJiexiangFile;


    /**
     * 项目结项文件
     */
    @ColumnInfo(comment="项目结项文件",type="varchar(200)")
    @TableField(value = "keyanxiangmu_jiexiangwenjian_file")

    private String keyanxiangmuJiexiangwenjianFile;


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
	 * 获取：科研项目编号
	 */
    public String getKeyanxiangmuUuidNumber() {
        return keyanxiangmuUuidNumber;
    }
    /**
	 * 设置：科研项目编号
	 */

    public void setKeyanxiangmuUuidNumber(String keyanxiangmuUuidNumber) {
        this.keyanxiangmuUuidNumber = keyanxiangmuUuidNumber;
    }
    /**
	 * 获取：项目来源
	 */
    public String getKeyanxiangmuLaiyuan() {
        return keyanxiangmuLaiyuan;
    }
    /**
	 * 设置：项目来源
	 */

    public void setKeyanxiangmuLaiyuan(String keyanxiangmuLaiyuan) {
        this.keyanxiangmuLaiyuan = keyanxiangmuLaiyuan;
    }
    /**
	 * 获取：科研项目名称
	 */
    public String getKeyanxiangmuName() {
        return keyanxiangmuName;
    }
    /**
	 * 设置：科研项目名称
	 */

    public void setKeyanxiangmuName(String keyanxiangmuName) {
        this.keyanxiangmuName = keyanxiangmuName;
    }
    /**
	 * 获取：项目等级
	 */
    public Integer getDengjiTypes() {
        return dengjiTypes;
    }
    /**
	 * 设置：项目等级
	 */

    public void setDengjiTypes(Integer dengjiTypes) {
        this.dengjiTypes = dengjiTypes;
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
	 * 获取：项目预算(元)
	 */
    public Integer getKeyanxiangmuHuafei() {
        return keyanxiangmuHuafei;
    }
    /**
	 * 设置：项目预算(元)
	 */

    public void setKeyanxiangmuHuafei(Integer keyanxiangmuHuafei) {
        this.keyanxiangmuHuafei = keyanxiangmuHuafei;
    }
    /**
	 * 获取：项目开始时间
	 */
    public Date getKaishiTime() {
        return kaishiTime;
    }
    /**
	 * 设置：项目开始时间
	 */

    public void setKaishiTime(Date kaishiTime) {
        this.kaishiTime = kaishiTime;
    }
    /**
	 * 获取：项目结束时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }
    /**
	 * 设置：项目结束时间
	 */

    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
    }
    /**
	 * 获取：项目申报书
	 */
    public String getKeyanxiangmuFile() {
        return keyanxiangmuFile;
    }
    /**
	 * 设置：项目申报书
	 */

    public void setKeyanxiangmuFile(String keyanxiangmuFile) {
        this.keyanxiangmuFile = keyanxiangmuFile;
    }
    /**
	 * 获取：科研项目介绍
	 */
    public String getKeyanxiangmuContent() {
        return keyanxiangmuContent;
    }
    /**
	 * 设置：科研项目介绍
	 */

    public void setKeyanxiangmuContent(String keyanxiangmuContent) {
        this.keyanxiangmuContent = keyanxiangmuContent;
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
    public Integer getKeyanxiangmuYesnoTypes() {
        return keyanxiangmuYesnoTypes;
    }
    /**
	 * 设置：申请状态
	 */

    public void setKeyanxiangmuYesnoTypes(Integer keyanxiangmuYesnoTypes) {
        this.keyanxiangmuYesnoTypes = keyanxiangmuYesnoTypes;
    }
    /**
	 * 获取：审核意见
	 */
    public String getKeyanxiangmuYesnoText() {
        return keyanxiangmuYesnoText;
    }
    /**
	 * 设置：审核意见
	 */

    public void setKeyanxiangmuYesnoText(String keyanxiangmuYesnoText) {
        this.keyanxiangmuYesnoText = keyanxiangmuYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getKeyanxiangmuShenheTime() {
        return keyanxiangmuShenheTime;
    }
    /**
	 * 设置：审核时间
	 */

    public void setKeyanxiangmuShenheTime(Date keyanxiangmuShenheTime) {
        this.keyanxiangmuShenheTime = keyanxiangmuShenheTime;
    }
    /**
	 * 获取：项目立项文件
	 */
    public String getKeyanxiangmuLixiangFile() {
        return keyanxiangmuLixiangFile;
    }
    /**
	 * 设置：项目立项文件
	 */

    public void setKeyanxiangmuLixiangFile(String keyanxiangmuLixiangFile) {
        this.keyanxiangmuLixiangFile = keyanxiangmuLixiangFile;
    }
    /**
	 * 获取：项目结项书
	 */
    public String getKeyanxiangmuJiexiangFile() {
        return keyanxiangmuJiexiangFile;
    }
    /**
	 * 设置：项目结项书
	 */

    public void setKeyanxiangmuJiexiangFile(String keyanxiangmuJiexiangFile) {
        this.keyanxiangmuJiexiangFile = keyanxiangmuJiexiangFile;
    }
    /**
	 * 获取：项目结项文件
	 */
    public String getKeyanxiangmuJiexiangwenjianFile() {
        return keyanxiangmuJiexiangwenjianFile;
    }
    /**
	 * 设置：项目结项文件
	 */

    public void setKeyanxiangmuJiexiangwenjianFile(String keyanxiangmuJiexiangwenjianFile) {
        this.keyanxiangmuJiexiangwenjianFile = keyanxiangmuJiexiangwenjianFile;
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
        return "Keyanxiangmu{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", keyanxiangmuUuidNumber=" + keyanxiangmuUuidNumber +
            ", keyanxiangmuLaiyuan=" + keyanxiangmuLaiyuan +
            ", keyanxiangmuName=" + keyanxiangmuName +
            ", dengjiTypes=" + dengjiTypes +
            ", xuekeTypes=" + xuekeTypes +
            ", keyanxiangmuHuafei=" + keyanxiangmuHuafei +
            ", kaishiTime=" + DateUtil.convertString(kaishiTime,"yyyy-MM-dd") +
            ", jieshuTime=" + DateUtil.convertString(jieshuTime,"yyyy-MM-dd") +
            ", keyanxiangmuFile=" + keyanxiangmuFile +
            ", keyanxiangmuContent=" + keyanxiangmuContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", keyanxiangmuYesnoTypes=" + keyanxiangmuYesnoTypes +
            ", keyanxiangmuYesnoText=" + keyanxiangmuYesnoText +
            ", keyanxiangmuShenheTime=" + DateUtil.convertString(keyanxiangmuShenheTime,"yyyy-MM-dd") +
            ", keyanxiangmuLixiangFile=" + keyanxiangmuLixiangFile +
            ", keyanxiangmuJiexiangFile=" + keyanxiangmuJiexiangFile +
            ", keyanxiangmuJiexiangwenjianFile=" + keyanxiangmuJiexiangwenjianFile +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
