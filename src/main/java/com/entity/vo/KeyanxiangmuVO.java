package com.entity.vo;

import com.entity.KeyanxiangmuEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 科研项目
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("keyanxiangmu")
public class KeyanxiangmuVO implements Serializable {
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
     * 科研项目编号
     */

    @TableField(value = "keyanxiangmu_uuid_number")
    private String keyanxiangmuUuidNumber;


    /**
     * 项目来源
     */

    @TableField(value = "keyanxiangmu_laiyuan")
    private String keyanxiangmuLaiyuan;


    /**
     * 科研项目名称
     */

    @TableField(value = "keyanxiangmu_name")
    private String keyanxiangmuName;


    /**
     * 项目等级
     */

    @TableField(value = "dengji_types")
    private Integer dengjiTypes;


    /**
     * 学科
     */

    @TableField(value = "xueke_types")
    private Integer xuekeTypes;


    /**
     * 项目预算(元)
     */

    @TableField(value = "keyanxiangmu_huafei")
    private Integer keyanxiangmuHuafei;


    /**
     * 项目开始时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "kaishi_time")
    private Date kaishiTime;


    /**
     * 项目结束时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jieshu_time")
    private Date jieshuTime;


    /**
     * 项目申报书
     */

    @TableField(value = "keyanxiangmu_file")
    private String keyanxiangmuFile;


    /**
     * 科研项目介绍
     */

    @TableField(value = "keyanxiangmu_content")
    private String keyanxiangmuContent;


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

    @TableField(value = "keyanxiangmu_yesno_types")
    private Integer keyanxiangmuYesnoTypes;


    /**
     * 审核意见
     */

    @TableField(value = "keyanxiangmu_yesno_text")
    private String keyanxiangmuYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "keyanxiangmu_shenhe_time")
    private Date keyanxiangmuShenheTime;


    /**
     * 项目立项文件
     */

    @TableField(value = "keyanxiangmu_lixiang_file")
    private String keyanxiangmuLixiangFile;


    /**
     * 项目结项书
     */

    @TableField(value = "keyanxiangmu_jiexiang_file")
    private String keyanxiangmuJiexiangFile;


    /**
     * 项目结项文件
     */

    @TableField(value = "keyanxiangmu_jiexiangwenjian_file")
    private String keyanxiangmuJiexiangwenjianFile;


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
	 * 设置：科研项目编号
	 */
    public String getKeyanxiangmuUuidNumber() {
        return keyanxiangmuUuidNumber;
    }


    /**
	 * 获取：科研项目编号
	 */

    public void setKeyanxiangmuUuidNumber(String keyanxiangmuUuidNumber) {
        this.keyanxiangmuUuidNumber = keyanxiangmuUuidNumber;
    }
    /**
	 * 设置：项目来源
	 */
    public String getKeyanxiangmuLaiyuan() {
        return keyanxiangmuLaiyuan;
    }


    /**
	 * 获取：项目来源
	 */

    public void setKeyanxiangmuLaiyuan(String keyanxiangmuLaiyuan) {
        this.keyanxiangmuLaiyuan = keyanxiangmuLaiyuan;
    }
    /**
	 * 设置：科研项目名称
	 */
    public String getKeyanxiangmuName() {
        return keyanxiangmuName;
    }


    /**
	 * 获取：科研项目名称
	 */

    public void setKeyanxiangmuName(String keyanxiangmuName) {
        this.keyanxiangmuName = keyanxiangmuName;
    }
    /**
	 * 设置：项目等级
	 */
    public Integer getDengjiTypes() {
        return dengjiTypes;
    }


    /**
	 * 获取：项目等级
	 */

    public void setDengjiTypes(Integer dengjiTypes) {
        this.dengjiTypes = dengjiTypes;
    }
    /**
	 * 设置：学科
	 */
    public Integer getXuekeTypes() {
        return xuekeTypes;
    }


    /**
	 * 获取：学科
	 */

    public void setXuekeTypes(Integer xuekeTypes) {
        this.xuekeTypes = xuekeTypes;
    }
    /**
	 * 设置：项目预算(元)
	 */
    public Integer getKeyanxiangmuHuafei() {
        return keyanxiangmuHuafei;
    }


    /**
	 * 获取：项目预算(元)
	 */

    public void setKeyanxiangmuHuafei(Integer keyanxiangmuHuafei) {
        this.keyanxiangmuHuafei = keyanxiangmuHuafei;
    }
    /**
	 * 设置：项目开始时间
	 */
    public Date getKaishiTime() {
        return kaishiTime;
    }


    /**
	 * 获取：项目开始时间
	 */

    public void setKaishiTime(Date kaishiTime) {
        this.kaishiTime = kaishiTime;
    }
    /**
	 * 设置：项目结束时间
	 */
    public Date getJieshuTime() {
        return jieshuTime;
    }


    /**
	 * 获取：项目结束时间
	 */

    public void setJieshuTime(Date jieshuTime) {
        this.jieshuTime = jieshuTime;
    }
    /**
	 * 设置：项目申报书
	 */
    public String getKeyanxiangmuFile() {
        return keyanxiangmuFile;
    }


    /**
	 * 获取：项目申报书
	 */

    public void setKeyanxiangmuFile(String keyanxiangmuFile) {
        this.keyanxiangmuFile = keyanxiangmuFile;
    }
    /**
	 * 设置：科研项目介绍
	 */
    public String getKeyanxiangmuContent() {
        return keyanxiangmuContent;
    }


    /**
	 * 获取：科研项目介绍
	 */

    public void setKeyanxiangmuContent(String keyanxiangmuContent) {
        this.keyanxiangmuContent = keyanxiangmuContent;
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
    public Integer getKeyanxiangmuYesnoTypes() {
        return keyanxiangmuYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setKeyanxiangmuYesnoTypes(Integer keyanxiangmuYesnoTypes) {
        this.keyanxiangmuYesnoTypes = keyanxiangmuYesnoTypes;
    }
    /**
	 * 设置：审核意见
	 */
    public String getKeyanxiangmuYesnoText() {
        return keyanxiangmuYesnoText;
    }


    /**
	 * 获取：审核意见
	 */

    public void setKeyanxiangmuYesnoText(String keyanxiangmuYesnoText) {
        this.keyanxiangmuYesnoText = keyanxiangmuYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getKeyanxiangmuShenheTime() {
        return keyanxiangmuShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setKeyanxiangmuShenheTime(Date keyanxiangmuShenheTime) {
        this.keyanxiangmuShenheTime = keyanxiangmuShenheTime;
    }
    /**
	 * 设置：项目立项文件
	 */
    public String getKeyanxiangmuLixiangFile() {
        return keyanxiangmuLixiangFile;
    }


    /**
	 * 获取：项目立项文件
	 */

    public void setKeyanxiangmuLixiangFile(String keyanxiangmuLixiangFile) {
        this.keyanxiangmuLixiangFile = keyanxiangmuLixiangFile;
    }
    /**
	 * 设置：项目结项书
	 */
    public String getKeyanxiangmuJiexiangFile() {
        return keyanxiangmuJiexiangFile;
    }


    /**
	 * 获取：项目结项书
	 */

    public void setKeyanxiangmuJiexiangFile(String keyanxiangmuJiexiangFile) {
        this.keyanxiangmuJiexiangFile = keyanxiangmuJiexiangFile;
    }
    /**
	 * 设置：项目结项文件
	 */
    public String getKeyanxiangmuJiexiangwenjianFile() {
        return keyanxiangmuJiexiangwenjianFile;
    }


    /**
	 * 获取：项目结项文件
	 */

    public void setKeyanxiangmuJiexiangwenjianFile(String keyanxiangmuJiexiangwenjianFile) {
        this.keyanxiangmuJiexiangwenjianFile = keyanxiangmuJiexiangwenjianFile;
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
