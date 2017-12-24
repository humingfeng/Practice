package com.practice.enums;

/**
 * Auth Enum
 * @author Xushd  2017/11/27 23:10
 */
public enum AuthEnum {

    SUCCESS(200, "登录成功"),
    NO_AUTH(401,"没有权限"),
    TIME_OUT(403,"登录超时"),
    YES_AUTH(200,"正常"),
    USER_NO_EXIST(500, "帐号不存在"),
    PASS_ERROR(500, "密码错误"),
    VERCODE_TIME_OUT(500, "验证码过期"),
    VERCODE_ERROR(500, "验证码错误"),
    USER_NO_STATUS(500, "帐号被禁用");

    private int status;
    private String message;

    AuthEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static String getName(int index) {
        for (AuthEnum auth : AuthEnum.values()) {
            if (auth.getStatus() == index) {
                return auth.getMessage();
            }
        }
        return null;
    }
}
