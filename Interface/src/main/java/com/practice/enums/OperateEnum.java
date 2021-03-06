package com.practice.enums;

/**
 *
 * @author Xushd  2017/12/25 20:16
 */
public enum OperateEnum {

    SUCCESS(200,"操作成功"),
    FAILE(500,"操作失败"),
    ROLE_IS_USED(500,"角色下有用户存在"),
    USER_PHONE_EXIST(500,"该手机号已经注册"),
    FILE_SIZE(500,"文件太大"),
    FILE_EMPTY(500,"文件为空"),
    PASS_NOT_EQUAIL(500,"两次密码不一致"),
    PASS_ERROR(500,"原密码错误"),
    LOGIN_ERROR_NOUSER(500,"用户不存在"),
    LOGIN_ERROR_PASSERROR(500,"密码错误"),
    LOGIN_ERROR_USERSTOP(500,"帐号被停用"),
    SERVICE_ERROR(500,"服务器错误"),
    LOGIN_TIME_OUT(500,"登录信息错误，请重新登录"),
    USE_STATUS_ERROR(500,"当前登录用户状态不可修改"),
    REPEAT(500,"数据重复，请仔细核查"),
    PHONE_ERROR(500,"手机号格式错误，请仔细核查"),
    FILE_UPLOAD_SUCCESS(200,"上传成功"),
    SOLR_ADD_ERROR(500,"SOLR 信息添加失败，请联系管理员"),
    SOLR_DEL_ERROR(500,"SOLR 信息移除失败，请联系管理员");

    private int state;

    private String stateInfo;

    OperateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
