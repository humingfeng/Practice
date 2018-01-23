package com.practice.dao;

import com.practice.dto.ActivitySolrItemDTO;

/**
 * @author Xushd  2018/1/23 21:21
 */
public interface SolrDao {
    /**
     * Add activity to Sorl
     * @param actvitySolrItemDTO
     */
    Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO);
}
