package com.mmall.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.id
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.order_no
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Long orderNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.user_id
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.shipping_id
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer shippingId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.payment
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private BigDecimal payment;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.payment_type
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer paymentType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.postage
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer postage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.status
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.payment_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date paymentTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.send_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date sendTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.end_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.close_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date closeTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.create_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mmall_order.update_time
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order(Integer id, Long orderNo, Integer userId, Integer shippingId, BigDecimal payment, Integer paymentType, Integer postage, Integer status, Date paymentTime, Date sendTime, Date endTime, Date closeTime, Date createTime, Date updateTime) {
        this.id = id;
        this.orderNo = orderNo;
        this.userId = userId;
        this.shippingId = shippingId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postage = postage;
        this.status = status;
        this.paymentTime = paymentTime;
        this.sendTime = sendTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.id
     *
     * @return the value of mmall_order.id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withId(Integer id) {
        this.setId(id);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.id
     *
     * @param id the value for mmall_order.id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.order_no
     *
     * @return the value of mmall_order.order_no
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Long getOrderNo() {
        return orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withOrderNo(Long orderNo) {
        this.setOrderNo(orderNo);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.order_no
     *
     * @param orderNo the value for mmall_order.order_no
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.user_id
     *
     * @return the value of mmall_order.user_id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withUserId(Integer userId) {
        this.setUserId(userId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.user_id
     *
     * @param userId the value for mmall_order.user_id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.shipping_id
     *
     * @return the value of mmall_order.shipping_id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getShippingId() {
        return shippingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withShippingId(Integer shippingId) {
        this.setShippingId(shippingId);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.shipping_id
     *
     * @param shippingId the value for mmall_order.shipping_id
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.payment
     *
     * @return the value of mmall_order.payment
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public BigDecimal getPayment() {
        return payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withPayment(BigDecimal payment) {
        this.setPayment(payment);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.payment
     *
     * @param payment the value for mmall_order.payment
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.payment_type
     *
     * @return the value of mmall_order.payment_type
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withPaymentType(Integer paymentType) {
        this.setPaymentType(paymentType);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.payment_type
     *
     * @param paymentType the value for mmall_order.payment_type
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.postage
     *
     * @return the value of mmall_order.postage
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getPostage() {
        return postage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withPostage(Integer postage) {
        this.setPostage(postage);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.postage
     *
     * @param postage the value for mmall_order.postage
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setPostage(Integer postage) {
        this.postage = postage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.status
     *
     * @return the value of mmall_order.status
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withStatus(Integer status) {
        this.setStatus(status);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.status
     *
     * @param status the value for mmall_order.status
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.payment_time
     *
     * @return the value of mmall_order.payment_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withPaymentTime(Date paymentTime) {
        this.setPaymentTime(paymentTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.payment_time
     *
     * @param paymentTime the value for mmall_order.payment_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.send_time
     *
     * @return the value of mmall_order.send_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getSendTime() {
        return sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withSendTime(Date sendTime) {
        this.setSendTime(sendTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.send_time
     *
     * @param sendTime the value for mmall_order.send_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.end_time
     *
     * @return the value of mmall_order.end_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withEndTime(Date endTime) {
        this.setEndTime(endTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.end_time
     *
     * @param endTime the value for mmall_order.end_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.close_time
     *
     * @return the value of mmall_order.close_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getCloseTime() {
        return closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withCloseTime(Date closeTime) {
        this.setCloseTime(closeTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.close_time
     *
     * @param closeTime the value for mmall_order.close_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.create_time
     *
     * @return the value of mmall_order.create_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withCreateTime(Date createTime) {
        this.setCreateTime(createTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.create_time
     *
     * @param createTime the value for mmall_order.create_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mmall_order.update_time
     *
     * @return the value of mmall_order.update_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public Order withUpdateTime(Date updateTime) {
        this.setUpdateTime(updateTime);
        return this;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mmall_order.update_time
     *
     * @param updateTime the value for mmall_order.update_time
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_order
     *
     * @mbg.generated Sun Sep 08 15:35:55 CST 2019
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", userId=").append(userId);
        sb.append(", shippingId=").append(shippingId);
        sb.append(", payment=").append(payment);
        sb.append(", paymentType=").append(paymentType);
        sb.append(", postage=").append(postage);
        sb.append(", status=").append(status);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", closeTime=").append(closeTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}