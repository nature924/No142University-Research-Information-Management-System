package com.entity.vo;

import com.entity.FankuiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 反馈
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("fankui")
public class FankuiVO implements Serializable {
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
     * 反馈编号
     */

    @TableField(value = "fankui_uuid_number")
    private String fankuiUuidNumber;


    /**
     * 反馈名称
     */

    @TableField(value = "fankui_name")
    private String fankuiName;


    /**
     * 反馈类型
     */

    @TableField(value = "fankui_types")
    private Integer fankuiTypes;


    /**
     * 反馈内容
     */

    @TableField(value = "fankui_content")
    private String fankuiContent;


    /**
     * 反馈时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


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
	 * 设置：反馈编号
	 */
    public String getFankuiUuidNumber() {
        return fankuiUuidNumber;
    }


    /**
	 * 获取：反馈编号
	 */

    public void setFankuiUuidNumber(String fankuiUuidNumber) {
        this.fankuiUuidNumber = fankuiUuidNumber;
    }
    /**
	 * 设置：反馈名称
	 */
    public String getFankuiName() {
        return fankuiName;
    }


    /**
	 * 获取：反馈名称
	 */

    public void setFankuiName(String fankuiName) {
        this.fankuiName = fankuiName;
    }
    /**
	 * 设置：反馈类型
	 */
    public Integer getFankuiTypes() {
        return fankuiTypes;
    }


    /**
	 * 获取：反馈类型
	 */

    public void setFankuiTypes(Integer fankuiTypes) {
        this.fankuiTypes = fankuiTypes;
    }
    /**
	 * 设置：反馈内容
	 */
    public String getFankuiContent() {
        return fankuiContent;
    }


    /**
	 * 获取：反馈内容
	 */

    public void setFankuiContent(String fankuiContent) {
        this.fankuiContent = fankuiContent;
    }
    /**
	 * 设置：反馈时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：反馈时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
