package com.practice.result;

import com.practice.enums.AuthEnum;
import com.practice.enums.ConstantEnum;
import com.practice.enums.OperateEnum;

import java.io.Serializable;

/**
 * Json result
 * @author Xushd  2017/12/21 21:23
 */
public class JsonResult implements Serializable{



    /**
     * 响应状态
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应中的数据
     */
    private Object data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static JsonResult success() {
        return new JsonResult();
    }

    public static JsonResult success(String message) {
        return new JsonResult(ConstantEnum.JSONRESULT_STATUS_SUCCESS.getIntValue(),message,null);
    }

    public static JsonResult success(Object object) {
        return new JsonResult(ConstantEnum.JSONRESULT_STATUS_SUCCESS.getIntValue(),"success",null);
    }

    public static JsonResult success(OperateEnum operateEnum) {
        return new JsonResult(operateEnum.getState(),operateEnum.getStateInfo(),null);
    }

    public static JsonResult error() {
        return new JsonResult();
    }

    public static JsonResult error(String message) {
        return new JsonResult(ConstantEnum.JSONRESULT_STATUS_ERROR.getIntValue(),message,null);
    }

    public static JsonResult error(OperateEnum operateEnum) {
        return new JsonResult(operateEnum.getState(),operateEnum.getStateInfo(),null);
    }

    public static JsonResult error(AuthEnum authEnum) {
        return new JsonResult(authEnum.getStatus(),authEnum.getMessage(),null);
    }

}
