package com.entity.vo;

import com.entity.TongzhiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 通知
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("tongzhi")
public class TongzhiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学院部门
     */

    @TableField(value = "xueyuanbumen_id")
    private Integer xueyuanbumenId;


    /**
     * 通知编号
     */

    @TableField(value = "tongzhi_uuid_number")
    private String tongzhiUuidNumber;


    /**
     * 通知名称
     */

    @TableField(value = "tongzhi_name")
    private String tongzhiName;


    /**
     * 通知类型
     */

    @TableField(value = "tongzhi_types")
    private Integer tongzhiTypes;


    /**
     * 通知内容
     */

    @TableField(value = "tongzhi_content")
    private String tongzhiContent;


    /**
     * 通知时间
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
	 * 设置：学院部门
	 */
    public Integer getXueyuanbumenId() {
        return xueyuanbumenId;
    }


    /**
	 * 获取：学院部门
	 */

    public void setXueyuanbumenId(Integer xueyuanbumenId) {
        this.xueyuanbumenId = xueyuanbumenId;
    }
    /**
	 * 设置：通知编号
	 */
    public String getTongzhiUuidNumber() {
        return tongzhiUuidNumber;
    }


    /**
	 * 获取：通知编号
	 */

    public void setTongzhiUuidNumber(String tongzhiUuidNumber) {
        this.tongzhiUuidNumber = tongzhiUuidNumber;
    }
    /**
	 * 设置：通知名称
	 */
    public String getTongzhiName() {
        return tongzhiName;
    }


    /**
	 * 获取：通知名称
	 */

    public void setTongzhiName(String tongzhiName) {
        this.tongzhiName = tongzhiName;
    }
    /**
	 * 设置：通知类型
	 */
    public Integer getTongzhiTypes() {
        return tongzhiTypes;
    }


    /**
	 * 获取：通知类型
	 */

    public void setTongzhiTypes(Integer tongzhiTypes) {
        this.tongzhiTypes = tongzhiTypes;
    }
    /**
	 * 设置：通知内容
	 */
    public String getTongzhiContent() {
        return tongzhiContent;
    }


    /**
	 * 获取：通知内容
	 */

    public void setTongzhiContent(String tongzhiContent) {
        this.tongzhiContent = tongzhiContent;
    }
    /**
	 * 设置：通知时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：通知时间
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
