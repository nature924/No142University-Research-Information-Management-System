package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.TongzhiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 通知
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("tongzhi")
public class TongzhiView extends TongzhiEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 通知类型的值
	*/
	@ColumnInfo(comment="通知类型的字典表值",type="varchar(200)")
	private String tongzhiValue;

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



	public TongzhiView() {

	}

	public TongzhiView(TongzhiEntity tongzhiEntity) {
		try {
			BeanUtils.copyProperties(this, tongzhiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 通知类型的值
	*/
	public String getTongzhiValue() {
		return tongzhiValue;
	}
	/**
	* 设置： 通知类型的值
	*/
	public void setTongzhiValue(String tongzhiValue) {
		this.tongzhiValue = tongzhiValue;
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
		return "TongzhiView{" +
			", tongzhiValue=" + tongzhiValue +
			", xueyuanbumenUuidNumber=" + xueyuanbumenUuidNumber +
			", xueyuanbumenName=" + xueyuanbumenName +
			", xueyuanbumenAddress=" + xueyuanbumenAddress +
			", xueyuanbumenContent=" + xueyuanbumenContent +
			"} " + super.toString();
	}
}
