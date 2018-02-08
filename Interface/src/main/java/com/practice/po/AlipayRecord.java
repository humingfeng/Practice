package com.practice.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Xushd  2018/2/6 22:47
 */
public class AlipayRecord implements Serializable{
    private Long id;

    private String gmCreate;

    private String charset;

    private String sellerEmail;

    private String subject;

    private String sign;

    private String body;

    private String buyerId;

    private String invoiceAmount;

    private String notifyId;

    private String notifyType;

    private String tradeStatus;

    private String receiptAmount;

    private String appId;

    private String buyerPayAmount;

    private String signType;

    private String sellerId;

    private String gmtPayment;

    private String notifyTime;

    private String version;

    private String outTradeNo;

    private String totalAmount;

    private String tradeNo;

    private String authAppId;

    private String buyerLogonId;

    private String pointAmount;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGmCreate() {
        return gmCreate;
    }

    public void setGmCreate(String gmCreate) {
        this.gmCreate = gmCreate == null ? null : gmCreate.trim();
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset == null ? null : charset.trim();
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail == null ? null : sellerEmail.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount == null ? null : invoiceAmount.trim();
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId == null ? null : notifyId.trim();
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType == null ? null : notifyType.trim();
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    public String getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(String receiptAmount) {
        this.receiptAmount = receiptAmount == null ? null : receiptAmount.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(String buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount == null ? null : buyerPayAmount.trim();
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType == null ? null : signType.trim();
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId == null ? null : sellerId.trim();
    }

    public String getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment == null ? null : gmtPayment.trim();
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime == null ? null : notifyTime.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount == null ? null : totalAmount.trim();
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public String getAuthAppId() {
        return authAppId;
    }

    public void setAuthAppId(String authAppId) {
        this.authAppId = authAppId == null ? null : authAppId.trim();
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId == null ? null : buyerLogonId.trim();
    }

    public String getPointAmount() {
        return pointAmount;
    }

    public void setPointAmount(String pointAmount) {
        this.pointAmount = pointAmount == null ? null : pointAmount.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}