package com.practice.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.practice.result.JsonResult;

import java.util.List;
import java.util.Map;

/**
 * Fast JSON Utils
 * @author Xushd  2017/12/21 21:22
 */
public class JsonUtils {

    /**
     * 对象转化成json字符串
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        return JSON.toJSONString(object);
    }

    /**
     *  将json结果集转化为对象
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> clazz) {
        return JSON.parseObject(jsonData, clazz);
    }

    /**
     * json字符串转LIST
     * @param jsonData
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> clazz){

        return JSON.parseArray(jsonData, clazz);

    }

    public static List<Map<String,String>> jsonResultToList(String jsonResult){
        try {
            JsonResult result = jsonToPojo(jsonResult,JsonResult.class);

            return JSON.parseObject(result.getData().toString(),
                    new TypeReference<List<Map<String, String>>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
