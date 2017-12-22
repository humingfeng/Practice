package com.practice.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception Util class
 *
 * @author Xushd  2017/12/21 23:26
 */
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     * @author Xushd
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
