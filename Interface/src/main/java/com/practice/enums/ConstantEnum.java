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
