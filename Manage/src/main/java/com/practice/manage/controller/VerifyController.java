package com.practice.manage.controller;

import com.practice.dto.PageSearchParam;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/13 20:14
 */
@RequestMapping(value = "/auth/verify")
@Controller
public class VerifyController {

    @Resource
    private ActivityService activityService;

    /**
     * Activity verify index
     * @return
     */
    @RequestMapping(value = "/activity")
    public String indexActivity(){
        return "verify/activity";
    }

    /**
     * Activity verify list
     * @return
     */
    @RequestMapping(value = "/activity/list")
    @ResponseBody
    public JsonResult ajaxActivityList(PageSearchParam param){
        return activityService.listVerifyActivity(param);
    }

    /**
     * Activity verify pass
     * @param id
     * @return
     */
    @RequestMapping(value = "/activity/pass/{id}")
    @ResponseBody
    public JsonResult ajaxActivityPass(@PathVariable Long id){
        return activityService.passActivity(id);
    }

    /**
     * Activity verify reject
     * @param id
     * @return
     */
    @RequestMapping(value = "/activity/reject/{id}")
    @ResponseBody
    public JsonResult ajaxActivityReject(@PathVariable Long id,String reason){
        return activityService.rejectActivity(id,reason);
    }

    /**
     * Activity view
     * @param id
     * @return
     */
    @RequestMapping(value = "/activity/view/{id}")
    @ResponseBody
    public JsonResult ajaxActivityView(@PathVariable Long id){

        return activityService.getActivityView(id);
    }
}
