package com.practice.service;

import java.util.Map;

/**
 * @author Xushd  2018/2/5 21:53
 */
public interface PayService {

    /**
     * Get AliPay order string
     * @param orderNum
     * @return
     */
    String getAliPayOrderString(String orderNum);

    /**
     * Validate AliPay call back
     * @param params
     * @return
     */
    Boolean validateAliPayCallBack(Map<String,String> params);

    /**
     * Get WeixinPay order string
     * @param orderNum
     * @return
     */
    String getWeixinPayOrderString(String orderNum,String ip);

    /**
     * Validate Weixpay call back
     * @param xmlString
     * @return
     */
    Boolean validateWeixinPaySign(String xmlString);
}
