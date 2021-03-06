package com.practice.utils;

import java.util.Random;
import java.util.UUID;

/**
 * ID工具类
 *
 * @author Xushd  2017/12/21 23:26
 */
public class IDUtils {

    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);
        return str;
    }

    /**
     * id生成
     */
    public static long getOrderId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        Random random = new Random();
        int end2 = random.nextInt(99);
        //如果不足两位前面补0
        String str = millis + String.format("%02d", end2);
        long id = new Long(str);
        return id;
    }

    /**
     * Id生成 日期+流水号
     * @param id
     * @return
     */
    public static String getTimeOrderId(long id){
        String nowDateString = TimeUtils.getNowDateStringByFormat("yyyyMMdd");
        String str = nowDateString + String.format("%06d", id);
        return str;
    }

    /**
     * 生成6为验证码
     * @return
     */
    public static String getVerifyCode(){

        int anInt = new Random().nextInt(999999);
        return String.format("%06d",anInt);
    }

    /**
     * UUID 没有‘-’
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
        return temp;

    }




}
