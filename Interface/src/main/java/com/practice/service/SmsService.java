package com.practice.service;

import com.practice.enums.SMSTemplateEnum;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2018/1/16 23:12
 */
public interface SmsService {

    /**
     * Send sms
     * @param phone
     * @param smsTemplateEnum
     * @return
     */
    JsonResult sendSms(String phone, SMSTemplateEnum smsTemplateEnum);

    /**
     * Verify phone code
     * @param phone
     * @param code
     * @return
     */
    JsonResult verifyPhoneCode(String phone, String code);
}
