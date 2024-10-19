package com.entity.vo;

import com.entity.CaipinOrderEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 菜品订单
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("caipin_order")
public class CaipinOrderVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 订单号
     */

    @TableField(value = "caipin_order_uuid_number")
    private String caipinOrderUuidNumber;


    /**
     * 菜品
     */

    @TableField(value = "caipin_id")
    private Integer caipinId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预定数量
     */

    @TableField(value = "buy_number")
    private Integer buyNumber;


    /**
     * 预定时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "caipin_order_time")
    private Date caipinOrderTime;


    /**
     * 实付价格
     */

    @TableField(value = "caipin_order_true_price")
    private Double caipinOrderTruePrice;


    /**
     * 订单类型
     */

    @TableField(value = "caipin_order_types")
    private Integer caipinOrderTypes;


    /**
     * 支付类型
     */

    @TableField(value = "caipin_order_payment_types")
    private Integer caipinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3
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
	 * 设置：订单号
	 */
    public String getCaipinOrderUuidNumber() {
        return caipinOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setCaipinOrderUuidNumber(String caipinOrderUuidNumber) {
        this.caipinOrderUuidNumber = caipinOrderUuidNumber;
    }
    /**
	 * 设置：菜品
	 */
    public Integer getCaipinId() {
        return caipinId;
    }


    /**
	 * 获取：菜品
	 */

    public void setCaipinId(Integer caipinId) {
        this.caipinId = caipinId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预定数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 获取：预定数量
	 */

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 设置：预定时间
	 */
    public Date getCaipinOrderTime() {
        return caipinOrderTime;
    }


    /**
	 * 获取：预定时间
	 */

    public void setCaipinOrderTime(Date caipinOrderTime) {
        this.caipinOrderTime = caipinOrderTime;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getCaipinOrderTruePrice() {
        return caipinOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setCaipinOrderTruePrice(Double caipinOrderTruePrice) {
        this.caipinOrderTruePrice = caipinOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getCaipinOrderTypes() {
        return caipinOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setCaipinOrderTypes(Integer caipinOrderTypes) {
        this.caipinOrderTypes = caipinOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getCaipinOrderPaymentTypes() {
        return caipinOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setCaipinOrderPaymentTypes(Integer caipinOrderPaymentTypes) {
        this.caipinOrderPaymentTypes = caipinOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
