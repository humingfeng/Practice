package com.practice.enums;

/**
 * Constant enums Class
 * @author Xushd  2017/11/27 21:55
 */
public enum ConstantEnum {
    /**
     * json result status
     */
    JSONRESULT_STATUS_ERROR(500),
    JSONRESULT_STATUS_SUCCESS(200),
    JSONRESULT_STATUS_AUTH(403),

    /**
     * cache keys
     */
    CACHE_MANGE_USER_NAVS("CACHE_MANGE_USER_NAVS"),
    CACHE_MANGE_USER_PERMISSION("CACHE_MANGE_USER_PERMISSION"),
    CACHE_DICTIONARY("CACHE_DICTIONARY"),
    CACHE_PROVINCE("CACHE_PROVINCE"),
    CACHE_CITY("CACHE_CITY"),
    CACHE_AREA("CACHE_AREA"),
    CACHE_BASES("CACHE_BASES"),
    ACTIVITY_SOLR_ITEM("ACTIVITY_SOLR_ITEM"),
    ACTIVITY_CLASSIFY("ACTIVITY_CLASSIFY"),
    ACTIVITY_THEME("ACTIVITY_THEME"),
    ACTIVITY_DETAIL("ACTIVITY_DETAIL"),
    ACTIVITY_STOCK_QUEUE("ACTIVITY_STOCK_QUEUE"),
    ACTIVITY_LIKE("ACTIVITY_LIKE"),
    ACTIVITY_MESSAGE_ITEM("ACTIVITY_MESSAGE_ITEM"),

    ORDER_PAY_DELAY_MESSAGE("ORDER_PAY_DELAY_MESSAGE"),
    SOLR_UPDATE_MESSAGE("SOLR_UPDATE_MESSAGE"),

    FILTER_PERIOD("FILTER_PERIOD"),

    /**
     * Parent keys
     */
    PARENT_DTO("PARENT_DTO"),
    /**
     * App Slider
     */
    APP_SLIDER("APP_SLIDER"),

    /**
     * System param
     */
    SYSTEM_PARAM("SYSTEM_PARAM"),
    /**
     * phone verify code
     */
    PHONE_VERIFY_CODE("PHONE_VERIFY_CODE"),


    /**
     * Status Activity Message
     */
    ENROLL_SUCCESS("恭喜您，报名成功！"),
    PAY_SUCCESS("恭喜您，支付成功"),

    /**
     * default head img
     */
    DEFAULT_HEADIMG_MAN("http://static.uping.wang/default/defalut_man.jpg"),
    DEFAULT_HEADIMG_WOMAN("http://static.uping.wang/default/defalut_women.jpg");





    private Integer intValue;
    private String strValue;

    ConstantEnum(Integer intValue) {
        this.intValue = intValue;
        this.strValue = null;
    }

    ConstantEnum(String strValue) {
        this.strValue = strValue;
        this.intValue = 0;
    }

    ConstantEnum(Integer intValue, String strValue) {
        this.intValue = intValue;
        this.strValue = strValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public String getStrValue() {
        return strValue;
    }

}
