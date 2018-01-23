package com.practice.service.impl;

import com.practice.dao.SolrDao;
import com.practice.dto.ActivitySolrItemDTO;
import com.practice.service.SolrService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/23 22:56
 */
@Service
public class SolrServiceImpl implements SolrService {


    @Resource
    private SolrDao solrDao;

    /**
     * Add activity to solr
     *
     * @param actvitySolrItemDTO
     */
    @Override
    public Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO) {
        return solrDao.addActivityItem(actvitySolrItemDTO);
    }
}
