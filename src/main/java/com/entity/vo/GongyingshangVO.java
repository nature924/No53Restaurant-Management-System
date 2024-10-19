package com.entity.vo;

import com.entity.GongyingshangEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 供应商
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("gongyingshang")
public class GongyingshangVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 供应商名称
     */

    @TableField(value = "gongyingshang_name")
    private String gongyingshangName;


    /**
     * 联系方式
     */

    @TableField(value = "gongyingshang_phone")
    private String gongyingshangPhone;


    /**
     * 供应物品
     */

    @TableField(value = "gongyingshang_email")
    private String gongyingshangEmail;


    /**
     * 物品类型
     */

    @TableField(value = "gongyingshang_types")
    private Integer gongyingshangTypes;


    /**
     * 供应价格
     */

    @TableField(value = "gongyingshang_money")
    private Double gongyingshangMoney;


    /**
     * 供应物品简介
     */

    @TableField(value = "gongyingshang_content")
    private String gongyingshangContent;


    /**
     * 创建时间
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
	 * 设置：供应商名称
	 */
    public String getGongyingshangName() {
        return gongyingshangName;
    }


    /**
	 * 获取：供应商名称
	 */

    public void setGongyingshangName(String gongyingshangName) {
        this.gongyingshangName = gongyingshangName;
    }
    /**
	 * 设置：联系方式
	 */
    public String getGongyingshangPhone() {
        return gongyingshangPhone;
    }


    /**
	 * 获取：联系方式
	 */

    public void setGongyingshangPhone(String gongyingshangPhone) {
        this.gongyingshangPhone = gongyingshangPhone;
    }
    /**
	 * 设置：供应物品
	 */
    public String getGongyingshangEmail() {
        return gongyingshangEmail;
    }


    /**
	 * 获取：供应物品
	 */

    public void setGongyingshangEmail(String gongyingshangEmail) {
        this.gongyingshangEmail = gongyingshangEmail;
    }
    /**
	 * 设置：物品类型
	 */
    public Integer getGongyingshangTypes() {
        return gongyingshangTypes;
    }


    /**
	 * 获取：物品类型
	 */

    public void setGongyingshangTypes(Integer gongyingshangTypes) {
        this.gongyingshangTypes = gongyingshangTypes;
    }
    /**
	 * 设置：供应价格
	 */
    public Double getGongyingshangMoney() {
        return gongyingshangMoney;
    }


    /**
	 * 获取：供应价格
	 */

    public void setGongyingshangMoney(Double gongyingshangMoney) {
        this.gongyingshangMoney = gongyingshangMoney;
    }
    /**
	 * 设置：供应物品简介
	 */
    public String getGongyingshangContent() {
        return gongyingshangContent;
    }


    /**
	 * 获取：供应物品简介
	 */

    public void setGongyingshangContent(String gongyingshangContent) {
        this.gongyingshangContent = gongyingshangContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
