package com.practice.app.controller;

import com.practice.dto.PageResult;
import com.practice.dto.SignErcodeDTO;
import com.practice.dto.SolrQueryDTO;
import com.practice.po.ManageActivityEnroll;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.EnrollService;
import com.practice.service.SearchService;
import com.practice.service.TaskService;
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
    private ActivityService activityService;
    @Resource
    private TaskService taskService;
    @Resource
    private EnrollService enrollService;

    /**
     * Activity list by search param
     * @param searchParam
     * @return
     */
    @RequestMapping(value = "/list/{searchParam}")
    public JsonResult listActivity(@PathVariable String searchParam, String query){

        SolrQueryDTO solrQueryDTO = new SolrQueryDTO();

        solrQueryDTO.init(searchParam,query);

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

    /**
     * Get activity task by activityId
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/task/{activityId}")
    public JsonResult getActivityTask(@PathVariable Long activityId,@RequestAttribute String token){

        return taskService.getActivityTask(activityId,token);
    }

    /**
     * Get activity task question
     * @param taskId
     * @param token
     * @return
     */
    @RequestMapping(value = "/task/question/{taskId}")
    public JsonResult getActivityQuestion(@PathVariable Long taskId,@RequestAttribute String token){
        return taskService.getActivityTaskQuestion(taskId,token);
    }


    /**
     * Submit task answer
     * @param answer
     * @param token
     * @return
     */
    @RequestMapping(value = "/task/answer/submit")
    public JsonResult submitTaskAnswer(String answer,@RequestAttribute String token){
        return taskService.saveActivityTaskQuestionAnswer(answer,token);
    }

    /**
     * 签到签退
     * @param sign
     * @param token
     * @return
     */
    @RequestMapping(value = "/sign")
    public JsonResult sign(SignErcodeDTO sign,@RequestAttribute String token){
        return enrollService.appScanSign(sign,token);
    }

    /**
     * App 签到记录
     * @param activityId
     * @param token
     * @return
     */
    @RequestMapping(value = "/sign/record/{activityId}")
    public JsonResult singRecord(@PathVariable Long activityId,@RequestAttribute String token){

        return enrollService.appActivitySignRecord(activityId,token);
    }

}
