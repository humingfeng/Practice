package com.practice.enums;

/**
 * @author Xushd  2018/1/16 23:10
 */
public enum SMSTemplateEnum {
    /**
     * Sms template
     */
    REGISTER("SMS_121907712","register"),
    REST_PASS("SMS_125019661","resetpass"),
    REST_PHONE("SMS_125119425","resetphone"),
    FORGET("SMS_122286243","forget");

    private String code;
    private String sign;

    SMSTemplateEnum(String code,String sign) {
        this.code = code;
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public String getSign() {
        return sign;
    }

    public static SMSTemplateEnum stateOf(String sign) {
        for (SMSTemplateEnum state : values()) {
            if (state.getSign().equals(sign)) {
                return state;
            }
        }
        return null;
    }
}
