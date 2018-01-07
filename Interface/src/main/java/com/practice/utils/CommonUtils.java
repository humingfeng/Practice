package com.practice.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common utils
 * @author Xushd  2017/12/23 0:27
 */
public class CommonUtils {

    /**
     * 拼装 sql 查询
     * @param filed
     * @return
     */
    public static String getLikeSql(String filed){

        return "%"+replaceSpecStr(filed)+"%";
    }

    /**
     * sha256 加密
     * @param pass
     * @return
     */
    public static String sha256(String pass){
        MessageDigest messageDigest;
        String encdeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(pass.getBytes("UTF-8"));
            encdeStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encdeStr;
    }

    /**
     * 正则替换所有特殊字符
     * @param orgStr
     * @return
     */
    public static String replaceSpecStr(String orgStr){
        if (null!=orgStr&&!"".equals(orgStr.trim())) {
            String regEx="[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(orgStr);
            return m.replaceAll("");
        }
        return null;
    }

    /**
     * 获取URL图片流
     * @param urlString
     * @return
     */
    public static InputStream createPicInputStream(String urlString){
        InputStream is = null;
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }
}
