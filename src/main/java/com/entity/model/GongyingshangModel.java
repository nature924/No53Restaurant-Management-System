package com.entity.model;

import com.entity.GongyingshangEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 供应商
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class GongyingshangModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 供应商名称
     */
    private String gongyingshangName;


    /**
     * 联系方式
     */
    private String gongyingshangPhone;


    /**
     * 供应物品
     */
    private String gongyingshangEmail;


    /**
     * 物品类型
     */
    private Integer gongyingshangTypes;


    /**
     * 供应价格
     */
    private Double gongyingshangMoney;


    /**
     * 供应物品简介
     */
    private String gongyingshangContent;


    /**
     * 创建时间
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
	 * 获取：供应商名称
	 */
    public String getGongyingshangName() {
        return gongyingshangName;
    }


    /**
	 * 设置：供应商名称
	 */
    public void setGongyingshangName(String gongyingshangName) {
        this.gongyingshangName = gongyingshangName;
    }
    /**
	 * 获取：联系方式
	 */
    public String getGongyingshangPhone() {
        return gongyingshangPhone;
    }


    /**
	 * 设置：联系方式
	 */
    public void setGongyingshangPhone(String gongyingshangPhone) {
        this.gongyingshangPhone = gongyingshangPhone;
    }
    /**
	 * 获取：供应物品
	 */
    public String getGongyingshangEmail() {
        return gongyingshangEmail;
    }


    /**
	 * 设置：供应物品
	 */
    public void setGongyingshangEmail(String gongyingshangEmail) {
        this.gongyingshangEmail = gongyingshangEmail;
    }
    /**
	 * 获取：物品类型
	 */
    public Integer getGongyingshangTypes() {
        return gongyingshangTypes;
    }


    /**
	 * 设置：物品类型
	 */
    public void setGongyingshangTypes(Integer gongyingshangTypes) {
        this.gongyingshangTypes = gongyingshangTypes;
    }
    /**
	 * 获取：供应价格
	 */
    public Double getGongyingshangMoney() {
        return gongyingshangMoney;
    }


    /**
	 * 设置：供应价格
	 */
    public void setGongyingshangMoney(Double gongyingshangMoney) {
        this.gongyingshangMoney = gongyingshangMoney;
    }
    /**
	 * 获取：供应物品简介
	 */
    public String getGongyingshangContent() {
        return gongyingshangContent;
    }


    /**
	 * 设置：供应物品简介
	 */
    public void setGongyingshangContent(String gongyingshangContent) {
        this.gongyingshangContent = gongyingshangContent;
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

    }
