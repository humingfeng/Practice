package com.practice.service.impl;

import com.practice.enums.SMSTemplateEnum;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.SmsService;
import com.practice.utils.AliSMSUtils;
import com.practice.utils.CommonUtils;
import com.practice.utils.IDUtils;
import com.practice.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/16 23:14
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Resource
    private CacheService cacheService;
    /**
     * Send sms
     *
     * @param phone
     * @param smsTemplateEnum
     * @return
     */
    @Override
    public JsonResult sendSms(String phone, SMSTemplateEnum smsTemplateEnum) {

        if(!ValidatorUtils.isMobile(phone)){
            return JsonResult.error("手机号格式错误!");
        }

        if(cacheService.isExistPhoneVerifyCode(phone)){
            return JsonResult.error("验证码已发送，请勿重复发送");
        }

        String verifyCode = IDUtils.getVerifyCode();
        System.out.println(verifyCode);

        if(AliSMSUtils.SendSmsByAli(phone, verifyCode, smsTemplateEnum)){

            cacheService.setPhoneVerifyCode(phone,verifyCode);
            return JsonResult.success("发送成功，请注意查收");
        }

        return JsonResult.error("短信服务器欠费了，马上去充值别急！");
    }

    /**
     * Verify phone code
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public JsonResult verifyPhoneCode(String phone, String code) {

        if(!ValidatorUtils.isMobile(phone)){
            return JsonResult.error("手机号格式错误!");
        }

        if(cacheService.isExistPhoneVerifyCode(phone)){
            String phoneVerifyCode = cacheService.getPhoneVerifyCode(phone);
            if(StringUtils.equals(code,phoneVerifyCode)){
                return JsonResult.success("验证码正确");
            }else{
                return JsonResult.error("验证码错误");
            }
        }else{
            return JsonResult.error("验证码过期，或未曾发送过");
        }

    }
}
