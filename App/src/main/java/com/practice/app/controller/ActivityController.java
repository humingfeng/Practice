package com.practice.app.controller;

import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.result.JsonResult;
import com.practice.service.SearchService;
import com.practice.vo.ActivitySearchVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/25 22:02
 */
@RequestMapping(value = "/app/auth/activity")
@RestController
public class ActivityController {

    @Resource
    private SearchService searchService;

    /**
     * Activity list by search param
     * @param searchParam
     * @return
     */
    @RequestMapping(value = "/list/{searchParam}")
    public JsonResult listActivity(@PathVariable String searchParam,String query){

        SolrQueryDTO solrQueryDTO = new SolrQueryDTO();

        solrQueryDTO.init(searchParam,query);

        PageResult<ActivitySearchVO> searchResult = searchService.getSearchResult(solrQueryDTO);

        return JsonResult.success(searchResult);

    }

}
