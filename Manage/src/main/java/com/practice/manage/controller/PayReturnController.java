package com.practice.manage.controller;

import com.practice.service.OrderService;
import com.practice.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Xushd  2018/2/5 21:39
 */
@Controller
public class PayReturnController {


    Logger LOGGER = LoggerFactory.getLogger(PayReturnController.class);
    @Resource
    private PayService payService;
    @Resource
    private OrderService orderService;

    /**
     * Ali pay return
     * @param request
     * @param response
     */
    @RequestMapping(value = "/alipay/pay/result")
    public void aliPayCallBack(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> params = new HashMap<String,String>();

        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        /**
         * 验证签名
         */
        if(payService.validateAliPayCallBack(params)){
            orderService.updateAndRecordPayInfo(params);
            LOGGER.info("AliPay callback {}",params);
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();

        }
        out.println("success");
    }

    /**
     * Weixin pay call back
     * @param request
     * @param response
     */
    @RequestMapping(value = "/weixin/pay/result")
    public void weixinPayCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader reader = null;

        reader = request.getReader();
        String line = "";
        String xmlString = null;
        StringBuffer inputString = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            inputString.append(line);
        }
        xmlString = inputString.toString();
        request.getReader().close();

        String result;
        if (payService.validateWeixinPaySign(xmlString)) {

            try {
                orderService.updateAndRecordPayInfoWeiXin(xmlString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            LOGGER.info("WeixinPay callback {}",xmlString);

            result = returnXML("SUCCESS");
        } else {
            result = returnXML("FAIL");
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter out= null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();

        }
        out.println(result);

    }

    private String returnXML(String return_code) {

        return "<xml><return_code><![CDATA["+ return_code+ "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
