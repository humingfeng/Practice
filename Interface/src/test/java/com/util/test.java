package com.util;

import com.practice.dto.SolrQueryDTO;
import com.practice.utils.TimeUtils;
import org.junit.Test;

import java.util.Date;
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
    @Test
    public void TimeUtisTest(){
        Date dateBefore = TimeUtils.getDateBeforeMinutes(10);

        System.out.println(dateBefore);

        Date dateAfterMinutes = TimeUtils.getDateAfterMinutes(10);

        System.out.println(dateAfterMinutes);

        Date dateHourFromString = TimeUtils.getDateHourFromString(TimeUtils.getNowTime());

        System.out.println(dateHourFromString);


        String dateHour = TimeUtils.getSringDateLastHour(new Date());

        System.out.println(dateHour);

    }

    @Test
    public void SolrQueryTest(){

        SolrQueryDTO solrQueryDTO = new SolrQueryDTO();

        String searchParam = "q_t1i2e3o4b5a6f7m8d9c10s11g12p13";

        solrQueryDTO.init(searchParam,"123");

        System.out.println(solrQueryDTO);



    }
}
