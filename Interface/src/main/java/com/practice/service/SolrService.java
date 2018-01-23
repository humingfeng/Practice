package com.practice.service;

import com.practice.dto.ActivitySolrItemDTO;

/**
 * @author Xushd  2018/1/23 21:24
 */
public interface SolrService {

    /**
     * Add activity to solr
     * @param actvitySolrItemDTO
     */
    Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO);
}
