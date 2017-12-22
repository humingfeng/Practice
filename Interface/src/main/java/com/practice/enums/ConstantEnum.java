package com.practice.enums;

/**
 * Constant enums Class
 * @author Xushd  2017/11/27 21:55
 */
public enum ConstantEnum {
    /**
     * json result status error
     */
    JSONRESULT_STATUS_ERROR(500),
    /**
     * json result status success
     */
    JSONRESULT_STATUS_SUCCESS(200),
    /**
     * json result status auth
     */
    JSONRESULT_STATUS_AUTH(403);

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
