package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.YonghuEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 科研人员
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("yonghu")
public class YonghuView extends YonghuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 性别的值
	*/
	@ColumnInfo(comment="性别的字典表值",type="varchar(200)")
	private String sexValue;
	/**
	* 职务的值
	*/
	@ColumnInfo(comment="职务的字典表值",type="varchar(200)")
	private String zhiwuValue;
	/**
	* 职称的值
	*/
	@ColumnInfo(comment="职称的字典表值",type="varchar(200)")
	private String zhichengValue;
	/**
	* 学历的值
	*/
	@ColumnInfo(comment="学历的字典表值",type="varchar(200)")
	private String xueliValue;

	//级联表 学院部门
		/**
		* 学院部门编号
		*/

		@ColumnInfo(comment="学院部门编号",type="varchar(200)")
		private String xueyuanbumenUuidNumber;
		/**
		* 学院部门名称
		*/

		@ColumnInfo(comment="学院部门名称",type="varchar(200)")
		private String xueyuanbumenName;
		/**
		* 学院部门位置
		*/

		@ColumnInfo(comment="学院部门位置",type="varchar(200)")
		private String xueyuanbumenAddress;
		/**
		* 学院部门描述
		*/

		@ColumnInfo(comment="学院部门描述",type="text")
		private String xueyuanbumenContent;



	public YonghuView() {

	}

	public YonghuView(YonghuEntity yonghuEntity) {
		try {
			BeanUtils.copyProperties(this, yonghuEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 性别的值
	*/
	public String getSexValue() {
		return sexValue;
	}
	/**
	* 设置： 性别的值
	*/
	public void setSexValue(String sexValue) {
		this.sexValue = sexValue;
	}
	//当前表的
	/**
	* 获取： 职务的值
	*/
	public String getZhiwuValue() {
		return zhiwuValue;
	}
	/**
	* 设置： 职务的值
	*/
	public void setZhiwuValue(String zhiwuValue) {
		this.zhiwuValue = zhiwuValue;
	}
	//当前表的
	/**
	* 获取： 职称的值
	*/
	public String getZhichengValue() {
		return zhichengValue;
	}
	/**
	* 设置： 职称的值
	*/
	public void setZhichengValue(String zhichengValue) {
		this.zhichengValue = zhichengValue;
	}
	//当前表的
	/**
	* 获取： 学历的值
	*/
	public String getXueliValue() {
		return xueliValue;
	}
	/**
	* 设置： 学历的值
	*/
	public void setXueliValue(String xueliValue) {
		this.xueliValue = xueliValue;
	}


	//级联表的get和set 学院部门

		/**
		* 获取： 学院部门编号
		*/
		public String getXueyuanbumenUuidNumber() {
			return xueyuanbumenUuidNumber;
		}
		/**
		* 设置： 学院部门编号
		*/
		public void setXueyuanbumenUuidNumber(String xueyuanbumenUuidNumber) {
			this.xueyuanbumenUuidNumber = xueyuanbumenUuidNumber;
		}

		/**
		* 获取： 学院部门名称
		*/
		public String getXueyuanbumenName() {
			return xueyuanbumenName;
		}
		/**
		* 设置： 学院部门名称
		*/
		public void setXueyuanbumenName(String xueyuanbumenName) {
			this.xueyuanbumenName = xueyuanbumenName;
		}

		/**
		* 获取： 学院部门位置
		*/
		public String getXueyuanbumenAddress() {
			return xueyuanbumenAddress;
		}
		/**
		* 设置： 学院部门位置
		*/
		public void setXueyuanbumenAddress(String xueyuanbumenAddress) {
			this.xueyuanbumenAddress = xueyuanbumenAddress;
		}

		/**
		* 获取： 学院部门描述
		*/
		public String getXueyuanbumenContent() {
			return xueyuanbumenContent;
		}
		/**
		* 设置： 学院部门描述
		*/
		public void setXueyuanbumenContent(String xueyuanbumenContent) {
			this.xueyuanbumenContent = xueyuanbumenContent;
		}


	@Override
	public String toString() {
		return "YonghuView{" +
			", sexValue=" + sexValue +
			", zhiwuValue=" + zhiwuValue +
			", zhichengValue=" + zhichengValue +
			", xueliValue=" + xueliValue +
			", xueyuanbumenUuidNumber=" + xueyuanbumenUuidNumber +
			", xueyuanbumenName=" + xueyuanbumenName +
			", xueyuanbumenAddress=" + xueyuanbumenAddress +
			", xueyuanbumenContent=" + xueyuanbumenContent +
			"} " + super.toString();
	}
}
