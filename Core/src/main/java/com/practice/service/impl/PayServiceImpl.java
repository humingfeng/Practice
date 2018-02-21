package com.practice.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.practice.po.OrderInfo;
import com.practice.service.OrderService;
import com.practice.service.PayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Xushd  2018/2/5 21:54
 */
@Service
public class PayServiceImpl implements PayService {


    //alipay
    static final String APP_ID = "2018020302137545";

    static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPqnbBvf17ytm2Z8NmJODqqFGkMmlDLfNhuDdBo4RNknah7C161wDZaersNjtvYnT/SFSfKSJz2oOcXPkGporFAIiK0DTtnB/8kHMtLwr7rgl36M0QOiV43Gq4hKRQmzZBc6Vy2TqHzLP+3sQAzIynRtVJwX6BtXTsb4Yopo+iD4+dPkJHl2IjEUF8thFDSadneIf4sBVl+YdE90qsggov0yH/DzHNTLyjbfm/7PxhdpRmX5OV5CJZplYvn0Ba9w8EqD413/ydm7fOkcKxM1yO7CNXzzLAWKhoRUwriBOFiiCShPL+zx02owEkyi8hsWiM2Yx3EzztMw1aRI1i3FwTAgMBAAECggEAHX4zLwbf2OAF3cpjdL6xZbr0N68ZqO7v/emhflohOQOOUyN3Gra0t+1kiuV/QhiGSDoDFUGOjxYW2qzDergJ2POJFGY112mrr1/AJVB5Ar3xt6oVHvWw56ToS8T/LoUOmFUza+Zoe6RsdzAoMLnNTjHea2vLmrkzra4fbozzA6O53SU/eNRWoLik6R/lgHrGLnAQ8+/zdKoQHf15GZmRhLWxdYGfyWAN2GbHKySVqJbv3YJIrLe1k4RTvq5ik/+QUOIYuvCMqL3wBYF83zcL2L+3VfjYG/AjDgOeVo9Jy5g85dny/cA8AGfDcmItrY/eosc+W3S0QZyAyNCA3LiWAQKBgQDp7ZLQSMonEebxqlmfcXn3BO1M5FYfW/cw/q87ozOSRDmeMQgw8YWYSqcGZIPkwr2WDFIcFu8cMX1HLxNgLADwbNoreKEXj3rpFnLMpu0/3bVju9I7wk573ZlfuIKKc06HrmLxjRPfUYlslbcVNh+xDWSm43NphDCZj04WOAbEkwKBgQCdOKYRVSdH1WTPbTGof5FhIU/WbCDjpYNx3SREBwKtsAUEylp5BOGHgtEU4rPusBpLBnrMCVHphAdHftm2qFForcFU+WzfoHDJLHdbaCiZSkgjZaQt98GByImDwUVizvzpCNciXjk/oiBlI4lgeu7pQyQLivcrPIvTU0qc0a46gQKBgQDowsWEjgjnmz6KVBBdQBI1oidkjQbMqTtq+t/tnVQhQ9V4/BzEZm2hvRY4nCdIpiTyekvxOziseunGvKaw2NgOYqErBBp2UQPhjb6ot6f2Fmr4U9i6MXracQK9ZeAuVtFa3EADTXbqjD0LWGUVrAcYqtTt4f/ii++3vAncvd6uFQKBgDlQVGHPLBGoviZLxSWK06GX9jXRly/Fv6Q7nKPb9mMA3uN/LPofCGRREQ1PE474zaz6ALvuzdiV092BZbaLp5nEdc0XXFQtddAwkJZW4y32gVO+EtrbDcTVKSRJyZSapmIBYgPaW35SCQM1sDfmuL96RWKb1z0AxmxUWcfPPHeBAoGBALUKPO904VfmUvoOs1aeIqmukH4nPJNaf5sfdL8BvFRkVOjUrt0XA86V140RzFhvrCC+zrqKE7v67fgnTfxaEa/hUIdG+yQydxEZULmyFNxO0yN/O8xaZvgyFBp3z8hZNm7/H0VIX7+T/dvQKlImmHYMqbJLMG8/ycuBr2mCEkuR";

    static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj6p2wb39e8rZtmfDZiTg6qhRpDJpQy3zYbg3QaOETZJ2oewtetcA2Wnq7DY7b2J0/0hUnykic9qDnFz5BqaKxQCIitA07Zwf/JBzLS8K+64Jd+jNEDoleNxquISkUJs2QXOlctk6h8yz/t7EAMyMp0bVScF+gbV07G+GKKaPog+PnT5CR5diIxFBfLYRQ0mnZ3iH+LAVZfmHRPdKrIIKL9Mh/w8xzUy8o235v+z8YXaUZl+TleQiWaZWL59AWvcPBKg+Nd/8nZu3zpHCsTNcjuwjV88ywFioaEVMK4gThYogkoTy/s8dNqMBJMovIbFojNmMdxM87TMNWkSNYtxcEwIDAQAB";

    static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";

    static final String CHARSET = "utf-8";

    static final String ZIFUBAO_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgmQPOKVLsVhY3Jek82DNU9nHauH/MJ/WNvu9m6cUs81p5WmkEcHYxqfxu9ZphBCfustLBfblW1Ln89+3Zcz846VuWu2HTUC7aEDjfMNc4FZ80cZQ3R1rrzYgDMlY78UGfjDKSEGjPLzs6CmsHgC5Yq3uCSqSV+wsZ6ZGkDuMd6RwUh5Sd4wR8TsWOogh4lndkCyht45rCuk6iCxwxg6FA2zk2AxQBC4t02p1iQ+ThVE5YC1ZBqZimU+6jZ6zCceMbEBpDRE4ckxA/+vZ4l4ACmgvo2xDr/OeYJDZ7/LGOw/g2KCMWydY0Njcd14KYx9OLNVl4+hf+KBPk2APuWdgwwIDAQAB";


    //weixinpay

    static final String WX_PAY_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";

    static final String WX_APP_ID="wx9c13e51e43a1b064";

    static final String WX_MCH_ID="1421346502";





    @Resource
    private OrderService orderService;

    /**
     * Get AliPay order string
     *
     * @param orderNum
     * @return
     */
    @Override
    public String getAliPayOrderString(String orderNum) {


        OrderInfo orderInfo = orderService.getOderInfoByOrderNum(orderNum);

        if(orderInfo==null){
            return null;
        }

        /**
         * 实例化客户端
         */
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        /**
         * 实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
         */
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();

        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("去哪实践 活动订单");
        model.setSubject(orderInfo.getOrderName());
        model.setOutTradeNo(orderNum);
        model.setTimeoutExpress("15m");
        /**
         * 订单金额 分 => 元
         */
        Integer price = orderInfo.getPrice();
        String totalAmount = new BigDecimal(price).divide(new BigDecimal(100)).toPlainString();
        model.setTotalAmount(totalAmount);
        model.setProductCode("QUICK_MSECURITY_PAY");

        request.setBizModel(model);
        request.setNotifyUrl("http://practise.uping.wang/alipay/pay/result");
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);

            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Validate AliPay call back
     *
     * @param params
     * @return
     */
    @Override
    public Boolean validateAliPayCallBack(Map<String, String> params) {
        try {
            return AlipaySignature.rsaCheckV1(params, ZIFUBAO_PUBLIC_KEY, CHARSET, "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Get WeixinPay order string
     *
     * @param orderNum
     * @return
     */
    @Override
    public String getWeixinPayOrderString(String orderNum) {


        OrderInfo orderInfo = orderService.getOderInfoByOrderNum(orderNum);

        if(orderInfo==null){
            return null;
        }

        SortedMap<Object,Object> param = new TreeMap<Object,Object>();

        param.put("appid",WX_APP_ID);
        param.put("mch_id",WX_MCH_ID);
        String randomStr = getRandomString(18);

        param.put("nonce_str",randomStr);

        param.put("body","去哪实践 活动订单");

        param.put("out_trade_no",orderNum);

        Integer price = orderInfo.getPrice();

        param.put("total_fee",price);

        param.put("notify_url", "http://practise.uping.wang/weixin/pay/result");

        param.put("trade_type", "APP");


//        String sign =

















        return null;
    }

    // 随机字符串生成
    public static String getRandomString(int length) {
        // length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

//    //创建md5 数字签证
//    public static String createSign(SortedMap<Object, Object> parame){
//        StringBuffer buffer = new StringBuffer();
//        Set set = parame.entrySet();
//        Iterator iterator = set.iterator();
//        while(iterator.hasNext()){
//            Map.Entry entry = (Map.Entry) iterator.next();
//            String key = (String)entry.getKey();
//            Object value = (String)entry.getValue();
//            if(null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)){
//                buffer.append(key+"="+value+"&");
//            }
//        }
//        buffer.append("key="+APIKEY);
//        String sign =MyMd5Util.md5(buffer.toString()).toUpperCase();
//        System.out.println("签名参数："+sign);
//        return sign;
//    }
}
