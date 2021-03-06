package com.practice.dao.impl;

import com.practice.dao.SolrDao;
import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.dto.SolrResultDTO;
import com.practice.utils.TimeUtils;
import com.practice.vo.ActivitySearchVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

            entries.setField("theme_id",actvitySolrItemDTO.getThemeId());

            entries.setField("img_cover",actvitySolrItemDTO.getImgCover());

            entries.setField("organize_id", actvitySolrItemDTO.getOrganizeId());

            entries.setField("base_id", actvitySolrItemDTO.getBaseId());

            entries.setField("duration", actvitySolrItemDTO.getDuration());

            entries.setField("duration_type", actvitySolrItemDTO.getDurationType());

            entries.setField("money", actvitySolrItemDTO.getMoney());

            entries.setField("self", actvitySolrItemDTO.getSelf());

            entries.setField("number", actvitySolrItemDTO.getNumber());

            entries.setField("enroll", actvitySolrItemDTO.getEnroll());

            entries.setField("apply", actvitySolrItemDTO.getApply());

            entries.setField("begin_time", actvitySolrItemDTO.getBeginTime());

            entries.setField("end_time", actvitySolrItemDTO.getEndTime());

            entries.setField("close_time", actvitySolrItemDTO.getCloseTime());

            entries.setField("score", actvitySolrItemDTO.getScore());

            entries.setField("type_name", actvitySolrItemDTO.getTypeName());

            entries.setField("classify_name", actvitySolrItemDTO.getClassifyName());

            entries.setField("theme_name", actvitySolrItemDTO.getThemeName());

            entries.setField("organize_name", actvitySolrItemDTO.getOrganizeName());

            entries.setField("base_name", actvitySolrItemDTO.getBaseName());

            entries.setField("pinyin", actvitySolrItemDTO.getPinyin());

            entries.setField("sign", actvitySolrItemDTO.getSign());

            entries.setField("time",actvitySolrItemDTO.getTime());

            entries.setField("pid",actvitySolrItemDTO.getPid());

            entries.setField("cid",actvitySolrItemDTO.getCid());

            entries.setField("aid",actvitySolrItemDTO.getAid());

            entries.setField("close_type", actvitySolrItemDTO.getCloseType());

            entries.setField("status", actvitySolrItemDTO.getStatus());

            entries.setField("supervise", actvitySolrItemDTO.getSupervise());

            entries.setField("collect", actvitySolrItemDTO.getCollect());

            entries.setField("publish_time",new Date());

            httpSolrServer.add(entries);

            httpSolrServer.commit();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    /**
     * Remove activity from solr
     *
     * @param solrItemId
     * @return
     */
    @Override
    public Boolean removeActivityItem(Long solrItemId) {

        try {

            httpSolrServer.setBaseURL(solrConllectionUrl);

            httpSolrServer.deleteById(String.valueOf(solrItemId));

            httpSolrServer.commit();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;
    }

    /**
     * Get search result
     *
     * @param query
     * @return
     */
    @Override
    public SolrResultDTO getSearchResult(SolrQuery query) {

        httpSolrServer.setBaseURL(solrConllectionUrl);

        SolrResultDTO solrResultDTO = new SolrResultDTO();
        try {
            QueryResponse queryResponse = httpSolrServer.query(query);

            SolrDocumentList results = queryResponse.getResults();

            solrResultDTO.setCount(results.getNumFound());

            List<ActivitySearchVO> rows = new ArrayList<>();

            for (SolrDocument result : results) {

                ActivitySearchVO activitySearchVO = new ActivitySearchVO();

                activitySearchVO.setId(Long.valueOf(String.valueOf(result.get("id"))));

                activitySearchVO.setImgCover(String.valueOf(result.get("img_cover")));

                activitySearchVO.setName(String.valueOf(result.get("name")));

                BigDecimal minutes = new BigDecimal(String.valueOf(result.get("duration")));

                BigDecimal hour = minutes.divide(new BigDecimal(60), 1, BigDecimal.ROUND_DOWN);

                activitySearchVO.setDuration(hour.toString());

                activitySearchVO.setTime(String.valueOf(result.get("time")));

                activitySearchVO.setDurationType(Integer.valueOf(String.valueOf(result.get("duration_type"))));

                activitySearchVO.setPrice(String.valueOf(result.get("money")));

                activitySearchVO.setBeginTime(TimeUtils.getDateStringShort((Date) result.get("begin_time")));

                activitySearchVO.setEndTime(TimeUtils.getDateStringShort((Date) result.get("end_time")));

                activitySearchVO.setCloseType(Integer.valueOf(String.valueOf(result.get("close_type"))));

                activitySearchVO.setCloseTime(TimeUtils.getDateStringShort((Date) result.get("close_time")));

                activitySearchVO.setSign(Integer.valueOf(String.valueOf(result.get("sign"))));

                activitySearchVO.setNumber(Integer.valueOf(String.valueOf(result.get("number"))));

                activitySearchVO.setEnrolled(Integer.valueOf(String.valueOf(result.get("enroll"))));

                activitySearchVO.setScore(String.valueOf(result.get("score")));

                activitySearchVO.setStatus(Integer.valueOf(String.valueOf(result.get("status"))));

                activitySearchVO.setSupervise(Integer.valueOf(String.valueOf(result.get("supervise"))));

                activitySearchVO.setSelf(Integer.valueOf(String.valueOf(result.get("self"))));

                activitySearchVO.setCollect(Integer.valueOf(String.valueOf(result.get("collect"))));

                rows.add(activitySearchVO);

            }
            solrResultDTO.setList(rows);

        } catch (SolrServerException e) {
            e.printStackTrace();

        }

        return solrResultDTO;
    }

    /**
     * Add activity like
     *
     * @param activityId
     * @return
     */
    @Override
    public Boolean addActivityLike(Long activityId) {

        httpSolrServer.setBaseURL(solrConllectionUrl);

        try {

            Map<String, Integer> inc = new HashMap<>(1);
            inc.put("inc", 1);
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", activityId);
            doc.addField("like", inc);
            httpSolrServer.add(doc);
            httpSolrServer.commit();

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Update activity enroll
     *
     * @param activityId
     * @param enroll
     * @return
     */
    @Override
    public Boolean updateActivityEnroll(Long activityId, Integer enroll) {

        httpSolrServer.setBaseURL(solrConllectionUrl);

        try {

            Map<String, Integer> update = new HashMap<>(1);
            update.put("set", enroll);
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", activityId);
            doc.addField("enroll", update);
            httpSolrServer.add(doc);
            httpSolrServer.commit();

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Update activity collect
     *
     * @param id
     * @param collectCount
     * @return
     */
    @Override
    public Boolean updateActivityCollect(Long id, int collectCount) {
        httpSolrServer.setBaseURL(solrConllectionUrl);

        try {

            Map<String, Integer> update = new HashMap<>(1);
            update.put("set", collectCount);
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", id);
            doc.addField("collect", update);
            httpSolrServer.add(doc);
            httpSolrServer.commit();

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Update activity status
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateActivityStatus(Long id, Integer status) {
        httpSolrServer.setBaseURL(solrConllectionUrl);

        try {

            Map<String, Integer> update = new HashMap<>(1);
            update.put("set", status);
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id", id);
            doc.addField("status", update);
            httpSolrServer.add(doc);
            httpSolrServer.commit();

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
