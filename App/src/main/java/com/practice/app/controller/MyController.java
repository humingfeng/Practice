package com.practice.app.controller;

import com.practice.dto.VerifyStudentDTO;
import com.practice.po.StudentEnrollInfo;
import com.practice.result.JsonResult;
import com.practice.service.*;
import com.practice.utils.JsonUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd on 2018/1/29 14:12
 */
@RequestMapping(value = "/app/auth/my")
@RestController
public class MyController {

    @Resource
    private ActivityService activityService;
    @Resource
    private EnrollService enrollService;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private StatisticsService statisticsService;
    @Resource
    private NewsService newsService;
    /**
     * List my collect
     * @param token
     * @return
     */
    @RequestMapping(value = "/collect/list")
    public JsonResult listCollect(@RequestAttribute String token){

        return activityService.listCollect(token);

    }

    /**
     * Update student enroll info
     * @param token
     * @param enrollInfo
     * @return
     */
    @RequestMapping(value = "/update/student/enroll/info")
    public JsonResult updateStudentEnrollInfo(@RequestAttribute String token, StudentEnrollInfo enrollInfo){
        return enrollService.updateStudentEnrollInfo(enrollInfo,token);
    }

    /**
     * List my students
     * @param token
     * @return
     */
    @RequestMapping(value = "/student/list")
    public JsonResult listMyStudent(@RequestAttribute String token){
        return enrollService.listMyStudent(token);
    }

    /**
     * Get student enroll info
     * @param id
     * @return
     */
    @RequestMapping(value = "/student/enroll/info/{id}")
    public JsonResult getStudentEnrollInfo(@PathVariable Long id,@RequestAttribute String token){
        return enrollService.getStudentEnrollInfo(id,token);
    }

    /**
     * Bind push id
     * @param token
     * @param pushId
     * @return
     */
    @RequestMapping(value = "/bind/push/{pushId}")
    public JsonResult bindPushId(@RequestAttribute String token,@PathVariable String pushId){
        return personnelService.updateParentPushId(token,pushId);
    }

    /**
     * List my enroll activity
     * @return
     */
    @RequestMapping(value = "/list/enroll/activity/{pageIndex}")
    public JsonResult listMyEnrollActivity(@PathVariable int pageIndex,@RequestAttribute String token){
        return personnelService.listParentEnrollActivity(token,pageIndex);
    }

    /**
     * Get my statistics
     * @param token
     * @return
     */
    @RequestMapping(value = "/statistics")
    public JsonResult getStatistics(@RequestAttribute String token){
        return statisticsService.getStatistics(token);
    }


    /**
     * List news
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/news/{pageIndex}")
    public JsonResult listNews(@PathVariable Integer pageIndex){
        return newsService.listNews(pageIndex);
    }


    /**
     * Get push tag
     * @param token
     * @return
     */
    @RequestMapping(value = "/notice/tag")
    public JsonResult getMyNoticeTag(@RequestAttribute String token){

        return enrollService.getNoticeTag(token);
    }

    /**
     * List photos
     * @param token
     * @return
     */
    @RequestMapping(value = "/photos/{pageIndex}")
    public JsonResult getMyPhoto(@RequestAttribute String token,@PathVariable Integer pageIndex){

        return personnelService.listPhotos(token,pageIndex);
    }

    /**
     * Add child
     * @param token
     * @param verifyStudentDTO
     * @return
     */
    @RequestMapping(value = "/add/child")
    public JsonResult addChild(@RequestAttribute String token,VerifyStudentDTO verifyStudentDTO){

        return personnelService.addParentChild(token,verifyStudentDTO);
    }

    /**
     * Change child
     * @param token
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/change/child/{studentId}")
    public JsonResult changeChild(@RequestAttribute String token,@PathVariable Long studentId){
        return personnelService.changeChild(token,studentId);
    }
}
