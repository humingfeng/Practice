package com.practice.service.impl;

import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.OrderPreviewDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.mapper.ManageActivityMapper;
import com.practice.po.ManageActivity;
import com.practice.po.ManageDictionary;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.DictionaryService;
import com.practice.service.OrderService;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd on 2018/2/1 14:38
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    private ActivityServiceImpl activityService;
    @Resource
    private CacheService cacheService;
    @Resource
    private DictionaryService dictionaryService;

    /**
     * Get order preview info
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult getOrderInfoPreview(Long activityId, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ActivitySolrItemDTO solrItemDTO = cacheService.getActvitySolrItemDTO(activityId);
        if(solrItemDTO == null){
            solrItemDTO = activityService.getActivitySolrItemDTO(activityId);
        }


        OrderPreviewDTO orderPreviewDTO = new OrderPreviewDTO();

        orderPreviewDTO.setActivityId(activityId);

        orderPreviewDTO.setActivityName(solrItemDTO.getName());

        orderPreviewDTO.setImgCover(solrItemDTO.getImgCover());

        orderPreviewDTO.setPrice(String.valueOf(solrItemDTO.getMoney()));

        String apply = solrItemDTO.getApply();

        String[] split = apply.split(",");

        List<String> applys = new ArrayList<>();

        for (String s : split) {

            ManageDictionary dictionaryPO = dictionaryService.getDictionaryPO(Long.valueOf(s));

            applys.add(dictionaryPO.getName());

        }
        orderPreviewDTO.setApplys(applys);

        orderPreviewDTO.setType(solrItemDTO.getTypeName());

        orderPreviewDTO.setClassify(solrItemDTO.getClassifyName());

        orderPreviewDTO.setTheme(solrItemDTO.getThemeName());

        orderPreviewDTO.setBaseName(solrItemDTO.getBaseName());

        orderPreviewDTO.setSign(solrItemDTO.getSign());

        orderPreviewDTO.setTime(solrItemDTO.getTime());

        orderPreviewDTO.setBeginAndEnd(TimeUtils.getDateStringShort(solrItemDTO.getBeginTime())+" - "+TimeUtils.getDateStringShort(solrItemDTO.getEndTime()));


        return JsonResult.success(orderPreviewDTO);
    }
}
