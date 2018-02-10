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
     * @param enroll
     * @return
     */
    Boolean updateActivityEnroll(Long activityId,Integer enroll);

    /**
     * Update activity collect
     * @param id
     * @param collectCount
     * @return
     */
    Boolean updateActivityCollect(Long id, int collectCount);

    /**
     * Update activity status
     * @param id
     * @param status
     * @return
     */
    boolean updateActivityStatus(Long id, Integer status);
}
