package com.entity.view;

import com.entity.GongyingshangEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 供应商
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("gongyingshang")
public class GongyingshangView extends GongyingshangEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 物品类型的值
		*/
		private String gongyingshangValue;



	public GongyingshangView() {

	}

	public GongyingshangView(GongyingshangEntity gongyingshangEntity) {
		try {
			BeanUtils.copyProperties(this, gongyingshangEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 物品类型的值
			*/
			public String getGongyingshangValue() {
				return gongyingshangValue;
			}
			/**
			* 设置： 物品类型的值
			*/
			public void setGongyingshangValue(String gongyingshangValue) {
				this.gongyingshangValue = gongyingshangValue;
			}














}
