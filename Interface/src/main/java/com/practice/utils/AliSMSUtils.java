package com.practice.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.practice.enums.SMSTemplateEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xushd  2018/1/16 23:21
 */
public class AliSMSUtils {

    static final Logger LOGGER = LoggerFactory.getLogger(AliSMSUtils.class);


    static final String PRODUCT = "Dysmsapi";

    static final String DOMAIN = "dysmsapi.aliyuncs.com";

    static final String ACCESSKEYID = "LTAIybEZNaAyEegx";
    static final String ACCESSKEYSECRET = "Fwn9Nt4X81B6b5Sgi6qO4Dji3My2OF";

    public static boolean SendSmsByAli(String phone,String code,SMSTemplateEnum smsTemplateEnum){

        try {
            //可自助调整超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID, ACCESSKEYSECRET);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(smsTemplateEnum.getSign());
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(smsTemplateEnum.getCode());
            request.setTemplateParam("{\"code\":\""+code+"\"}");

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

            if(StringUtils.equals(sendSmsResponse.getCode(),"OK")){
                return true;
            }else{
                System.out.println(sendSmsResponse.getCode());
                System.out.println(sendSmsResponse.getMessage());
                LOGGER.error("验证码发送错误code:{} message:{}",sendSmsResponse.getCode(),sendSmsResponse.getMessage());
            }

        } catch (Exception e){

            e.printStackTrace();

        }

        return false;
    }

}
