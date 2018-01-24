package com.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xushd  2018/1/24 22:47
 */

public class test {


    @Test
    public void verifyTest(){

        String REGEX = "[^(a-zA-Z0-9\\u4e00-\\u9fa5)$]";

        String text = "“九·一八”历史博物馆";


        Pattern p = Pattern.compile(REGEX);

        Matcher m = p.matcher(text);

        System.out.println( m.replaceAll("").trim());

    }
}
