package com.entity.view;

import com.entity.SingleSeachEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 单页数据
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("single_seach")
public class SingleSeachView extends SingleSeachEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 数据类型的值
		*/
		private String singleSeachValue;



	public SingleSeachView() {

	}

	public SingleSeachView(SingleSeachEntity singleSeachEntity) {
		try {
			BeanUtils.copyProperties(this, singleSeachEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 数据类型的值
			*/
			public String getSingleSeachValue() {
				return singleSeachValue;
			}
			/**
			* 设置： 数据类型的值
			*/
			public void setSingleSeachValue(String singleSeachValue) {
				this.singleSeachValue = singleSeachValue;
			}














}
