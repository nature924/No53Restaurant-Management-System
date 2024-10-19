package com.entity.model;

import com.entity.CaipinOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 菜品订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class CaipinOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String caipinOrderUuidNumber;


    /**
     * 菜品
     */
    private Integer caipinId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 预定数量
     */
    private Integer buyNumber;


    /**
     * 预定时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date caipinOrderTime;


    /**
     * 实付价格
     */
    private Double caipinOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer caipinOrderTypes;


    /**
     * 支付类型
     */
    private Integer caipinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
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
	 * 获取：订单号
	 */
    public String getCaipinOrderUuidNumber() {
        return caipinOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setCaipinOrderUuidNumber(String caipinOrderUuidNumber) {
        this.caipinOrderUuidNumber = caipinOrderUuidNumber;
    }
    /**
	 * 获取：菜品
	 */
    public Integer getCaipinId() {
        return caipinId;
    }


    /**
	 * 设置：菜品
	 */
    public void setCaipinId(Integer caipinId) {
        this.caipinId = caipinId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预定数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：预定数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：预定时间
	 */
    public Date getCaipinOrderTime() {
        return caipinOrderTime;
    }


    /**
	 * 设置：预定时间
	 */
    public void setCaipinOrderTime(Date caipinOrderTime) {
        this.caipinOrderTime = caipinOrderTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getCaipinOrderTruePrice() {
        return caipinOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setCaipinOrderTruePrice(Double caipinOrderTruePrice) {
        this.caipinOrderTruePrice = caipinOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getCaipinOrderTypes() {
        return caipinOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setCaipinOrderTypes(Integer caipinOrderTypes) {
        this.caipinOrderTypes = caipinOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getCaipinOrderPaymentTypes() {
        return caipinOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setCaipinOrderPaymentTypes(Integer caipinOrderPaymentTypes) {
        this.caipinOrderPaymentTypes = caipinOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
