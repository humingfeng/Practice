package com.practice.app.controller;

import com.practice.dto.PageResult;
import com.practice.dto.SolrQueryDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.po.ManageActivityEnroll;
import com.practice.po.ManageStudent;
import com.practice.po.School;
import com.practice.po.StudentEnrollInfo;
import com.practice.result.JsonResult;
import com.practice.service.*;
import com.practice.utils.JwtTokenUtil;
import com.practice.vo.ActivitySearchVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/25 22:02
 */
@RequestMapping(value = "/app/auth/activity")
@RestController
public class ActivityController {

    @Resource
    private SearchService searchService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private ActivityService activityService;
    @Resource
    private EnrollService enrollService;

    /**
     * Activity list by search param
     * @param searchParam
     * @return
     */
    @RequestMapping(value = "/list/{searchParam}")
    public JsonResult listActivity(@PathVariable String searchParam, String query, @RequestAttribute String token){

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        Long studentId = tokenParent.getStudentId();

        ManageStudent studentPO = personnelService.getStudentPO(studentId);

        School schoolPO = schoolService.getSchoolPO(studentPO.getSchoolId());

        SolrQueryDTO solrQueryDTO = new SolrQueryDTO();

        solrQueryDTO.init(searchParam,query);

        solrQueryDTO.setPid(schoolPO.getProviceId());

        solrQueryDTO.setCid(schoolPO.getCityId());

        PageResult<ActivitySearchVO> searchResult = searchService.getSearchResult(solrQueryDTO);

        return JsonResult.success(searchResult);

    }

    /**
     * Get activity detail
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/detail/{activityId}")
    public JsonResult getActivityDetail(@PathVariable Long activityId,@RequestAttribute String token){

        return activityService.getActivityDetail(activityId,token);
    }

    /**
     * Collect Activity
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/collect/{activityId}")
    public JsonResult collectActivity(@PathVariable Long activityId,@RequestAttribute String token){

        return activityService.collectActivity(activityId,token);
    }

    /**
     * Collect cancle
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/collect/cancle/{activityId}")
    public JsonResult collectActivityCancle(@PathVariable Long activityId,@RequestAttribute String token){
        return activityService.collectActivityCancle(activityId,token);
    }

    /**
     * Get enroll info
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/enroll/info/{activityId}")
    public JsonResult getEnrollInfo(@PathVariable Long activityId){

        ManageActivityEnroll activityEnroll = activityService.getActivityEnroll(activityId);
        return JsonResult.success(activityEnroll);
    }

}
