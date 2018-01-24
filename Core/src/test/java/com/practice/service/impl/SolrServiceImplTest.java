package com.practice.service.impl;

import com.TestBase;
import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.service.SolrService;
import com.practice.vo.ActivitySearchVO;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * @author Xushd  2018/1/24 23:44
 */
public class SolrServiceImplTest extends TestBase{

    @Resource
    private SolrService solrService;


    public void getSearchResult() throws Exception {

        SolrQueryDTO solrQueryDTO = new SolrQueryDTO();

        solrQueryDTO.setQuery("daxue");

        PageResult<ActivitySearchVO> searchResult = solrService.getSearchResult(solrQueryDTO);

        System.out.println(searchResult.getPageNum());
        System.out.println(searchResult.getTotal());

        for (ActivitySearchVO activitySearchVO : searchResult.getList()) {
            System.out.println(activitySearchVO);
        }


    }

}