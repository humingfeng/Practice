package com.practice.service;

import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.vo.ActivitySearchVO;

/**
 * @author Xushd  2018/1/23 21:24
 */
public interface SearchService {

    /**
     * Add activity to solr
     * @param actvitySolrItemDTO
     * @return
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

    /**
     * Update solr collect
     * @param id
     * @param collectCount
     * @return
     */
    boolean updateCollectCount(Long id, long collectCount);

    /**
     * Update solr enroll
     * @param id
     * @param enrolledCount
     * @return
     */
    boolean updateEnrollCount(Long id, long enrolledCount);

    /**
     * Update solr status
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id, Integer status);
}
