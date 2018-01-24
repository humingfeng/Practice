package com.practice.dao;

import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.SolrResultDTO;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @author Xushd  2018/1/23 21:21
 */
public interface SolrDao {
    /**
     * Add activity to Sorl
     * @param actvitySolrItemDTO
     * @return
     */
    Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO);

    /**
     * Remove activity from solr
     * @param activityId
     * @return
     */
    Boolean removeActivityItem(Long activityId);

    /**
     * Get search result
     * @param query
     * @return
     */
    SolrResultDTO getSearchResult(SolrQuery query);

    /**
     * Add activity like
     * @param activityId
     * @return
     */
    Boolean addActivityLike(Long activityId);

    /**
     * Update activity enroll
     * @param activityId
     * @return
     */
    Boolean updateActivityEnroll(Long activityId,Integer enroll);
}
