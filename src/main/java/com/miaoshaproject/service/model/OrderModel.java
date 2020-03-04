package com.miaoshaproject.service.model;

import java.math.BigDecimal;

/**
 * Created by chenhaiyang on 2019/9/24.
 */
//用户下单的交易模型
public class OrderModel {
    //20181021000012828—订单号
    private String id;

    //购买商品的单价
    private BigDecimal itemPrice;

    //购买的用户id
    private Integer userId;

    //购买的商品id
    private Integer itemId;

    //购买数量
    private Integer amount;

    //购买金额
    private BigDecimal orderPrice;

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
}
