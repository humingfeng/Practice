package com.practice.service;

import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.vo.ActivitySearchVO;

/**
 * @author Xushd  2018/1/23 21:24
 */
public interface SolrService {

    /**
     * Add activity to solr
     * @param actvitySolrItemDTO
     */
    Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO);

    /**
     * Remove activity from solr
     * @param solrItemId
     * @return
     */
    Boolean removeActivityItem(Long solrItemId);

    /**
     * Get searche result
     * @param queryDTO
     * @return
     */
    PageResult<ActivitySearchVO> getSearchResult(SolrQueryDTO queryDTO);
}
