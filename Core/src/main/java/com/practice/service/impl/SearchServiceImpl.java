package com.practice.service.impl;

import com.practice.dao.SolrDao;
import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.dto.SolrResultDTO;
import com.practice.service.SearchService;
import com.practice.utils.IkAnalyzerUtils;
import com.practice.vo.ActivitySearchVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author Xushd  2018/1/23 22:56
 */
@Service
public class SearchServiceImpl implements SearchService {


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

    /**
     * Remove activity from solr
     *
     * @param solrItemId
     * @return
     */
    @Override
    public Boolean removeActivityItem(Long solrItemId) {
        return solrDao.removeActivityItem(solrItemId);
    }

    /**
     * Get searche result
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageResult<ActivitySearchVO> getSearchResult(SolrQueryDTO queryDTO) {

        SolrQuery query = new SolrQuery();

        query.setStart(queryDTO.getPageIndex()-1);

        query.setRows(queryDTO.getPageSize());

        if (StringUtils.isBlank(queryDTO.getQuery())) {
            query.setQuery("*:*");
        } else {
            String queryStr = queryDTO.getQuery();
            int key = 2;
            try {
                List<String> words = IkAnalyzerUtils.splitWord(queryStr);

                String join = StringUtils.join(words, " ");

                query.setQuery("*"+join+"*");

                query.set("defType", "edismax");

                int i = words.size();
                if(i>key){
                    query.set("mm",i-1);
                }else{
                    query.set("mm",key);
                }


            } catch (IOException e) {

                query.setQuery("*"+queryStr+"*");

                query.set("defType", "edismax");

                query.set("mm",key);
            }

        }
        if(queryDTO.getPid()!=0L){
            query.addFilterQuery("pid:"+queryDTO.getPid());
        }
        if(queryDTO.getCid()!=0L){
            query.addFilterQuery("cid:"+queryDTO.getCid());
        }
        if(queryDTO.getAid()!=0L){
            query.addFilterQuery("aid:"+queryDTO.getAid());
        }

        if(queryDTO.getTypeId()!=0L){
            query.addFilterQuery("type_id:"+queryDTO.getTypeId());
        }

        if(queryDTO.getClassifyId()!=0L){
            query.addFilterQuery("classify_id:"+queryDTO.getClassifyId());
        }

        if(queryDTO.getThemeId()!=0L){
            query.addFilterQuery("theme_id:"+queryDTO.getThemeId());
        }

        if(queryDTO.getOrganizeId()!=0L){
            query.addFilterQuery("organized_id:"+queryDTO.getOrganizeId());
        }

        if(queryDTO.getBaseId()!=0L){
            query.addFilterQuery("base_id:{ 0 TO *]");
        }

        if(queryDTO.getFree()==1){
            query.addFilterQuery("money:0");
        }

        if(queryDTO.getSelf()!=0){
            query.addFilterQuery("self:1");
        }

        if(queryDTO.getApply()!=0L){
            query.addFilterQuery("apply:*"+queryDTO.getApply()+"*");
        }

        //1 活动时间大于1D  2活动时间一天'
        if(queryDTO.getDuration()!=0){
            query.addFilterQuery("duration_type:"+queryDTO.getDuration());
        }

        if(queryDTO.getClose()!=0){
            query.addFilterQuery("close_type:"+queryDTO.getClose());
        }
        //是否开启 签到模式  1 是 2否
        if(queryDTO.getSign()!=0){
            query.addFilterQuery("sign:1");
        }

        //1 关注度 2开始时间最近 3价格低到高 4 活动时短到长
        Integer soft = queryDTO.getSoft();
        switch (soft){
            case 0:
                query.addSort("publish_time", SolrQuery.ORDER.desc);
                break;
            case 1:
                query.addSort("score", SolrQuery.ORDER.desc);
                break;
            case 2:
                query.addSort("begin_time", SolrQuery.ORDER.asc);
                break;
            case 3:
                query.addSort("money", SolrQuery.ORDER.asc);
                break;
            case 4:
                query.addSort("duration", SolrQuery.ORDER.asc);
                break;
            default:
                break;
        }
        query.set("df", "item_keywords");

        SolrResultDTO solrResultDTO = solrDao.getSearchResult(query);

        PageResult<ActivitySearchVO> activitySearchVOPageResult = new PageResult<>();

        Long total = solrResultDTO.getCount();

        long pageCount = total/queryDTO.getPageSize();

        if(total % queryDTO.getPageSize() >0 ){
            pageCount++;
        }
        activitySearchVOPageResult.setPageNum(queryDTO.getPageIndex());

        activitySearchVOPageResult.setPages((int)pageCount);

        activitySearchVOPageResult.setTotal(Integer.valueOf(String.valueOf(total)));

        activitySearchVOPageResult.setList(solrResultDTO.getList());

        return activitySearchVOPageResult;
    }

    /**
     * Update solr collect
     *
     * @param id
     * @param collectCount
     * @return
     */
    @Override
    public boolean updateCollectCount(Long id, long collectCount) {
        return solrDao.updateActivityCollect(id,(int) collectCount);
    }

    /**
     * Update solr enroll
     *
     * @param id
     * @param enrolledCount
     * @return
     */
    @Override
    public boolean updateEnrollCount(Long id, long enrolledCount) {

        return solrDao.updateActivityEnroll(id, (int) enrolledCount);
    }

    /**
     * Update solr status
     *
     * @param id
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(Long id, Integer status) {
        return solrDao.updateActivityStatus(id,status);
    }
}
