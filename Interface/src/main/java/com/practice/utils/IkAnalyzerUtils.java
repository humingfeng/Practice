package com.practice.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd on 2018/1/5 13:21
 */
public class IkAnalyzerUtils {



     public static List<String> splitWord(String text) throws IOException {

        List<String> keywordList = new ArrayList<String>();
        try {
            byte[] bt = text.getBytes();
            InputStream ip = new ByteArrayInputStream(bt);
            Reader read = new InputStreamReader(ip);
            //true开启只能分词模式，如果不设置默认为false，也就是细粒度分割
            IKSegmenter iks = new IKSegmenter(read,false);
            Lexeme t;
            while ((t = iks.next()) != null) {
                keywordList.add(t.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keywordList;
    }
}
