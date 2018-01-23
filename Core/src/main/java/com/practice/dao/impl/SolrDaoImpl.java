package com.practice.dao.impl;

import com.practice.dao.SolrDao;
import com.practice.dto.ActivitySolrItemDTO;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/23 21:21
 */
public class SolrDaoImpl implements SolrDao {


    @Resource
    private HttpSolrServer httpSolrServer;

    @Value("${solr.Url}${solr.collection}")
    private String solrConllectionUrl;


    /**
     * Add activity to Sorl
     *
     * @param actvitySolrItemDTO
     */
    @Override
    public Boolean addActivityItem(ActivitySolrItemDTO actvitySolrItemDTO) {

        httpSolrServer.setBaseURL(solrConllectionUrl);

        try {

            SolrInputDocument entries = new SolrInputDocument();

            entries.setField("id", actvitySolrItemDTO.getId());

            entries.setField("name", actvitySolrItemDTO.getName());

            entries.setField("type_id", actvitySolrItemDTO.getTypeId());

            entries.setField("classify_id", actvitySolrItemDTO.getClassifyId());

            entries.setField("organize_id", actvitySolrItemDTO.getOrganizeId());

            entries.setField("base_id", actvitySolrItemDTO.getBaseId());

            entries.setField("time_hour", actvitySolrItemDTO.getTimeHour());

            entries.setField("money", actvitySolrItemDTO.getMoney());

            entries.setField("self", actvitySolrItemDTO.getSelf());

            entries.setField("number", actvitySolrItemDTO.getNumber());

            entries.setField("begin_time", actvitySolrItemDTO.getBeginTime());

            entries.setField("end_time", actvitySolrItemDTO.getEndTime());

            entries.setField("close_time", actvitySolrItemDTO.getCloseTime());

            entries.setField("like", actvitySolrItemDTO.getLike());

            entries.setField("type_name", actvitySolrItemDTO.getTypeName());

            entries.setField("classify_name", actvitySolrItemDTO.getClassifyName());

            entries.setField("theme_name", actvitySolrItemDTO.getThemeName());

            entries.setField("organize_name", actvitySolrItemDTO.getOrganizeName());

            entries.setField("base_name", actvitySolrItemDTO.getBaseName());

            entries.setField("pinyin", actvitySolrItemDTO.getPinyin());

            httpSolrServer.add(entries);

            httpSolrServer.commit();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
}
