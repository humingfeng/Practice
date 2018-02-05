package com.practice.app.controller;

import com.practice.po.StudentEnrollInfo;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.EnrollService;
import com.practice.service.PersonnelService;
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
}
