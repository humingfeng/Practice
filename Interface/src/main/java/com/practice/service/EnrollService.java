package com.practice.service;

import com.practice.dto.SignErcodeDTO;
import com.practice.po.StudentEnrollInfo;
import com.practice.result.JsonResult;

/**
 * @author Xushd on 2018/1/30 15:24
 */
public interface EnrollService {

    /**
     * Get student enroll info
     * @param studentId
     * @param token
     * @return
     */
    JsonResult getStudentEnrollInfo(Long studentId,String token);

    /**
     * Update student enroll info
     * @param enrollInfo
     * @param token
     * @return
     */
    JsonResult updateStudentEnrollInfo(StudentEnrollInfo enrollInfo, String token);


    /**
     * List my student
     * @param token
     * @return
     */
    JsonResult listMyStudent(String token);

    /**
     * App scan sign
     * @param sign
     * @param token
     * @return
     */
    JsonResult appScanSign(SignErcodeDTO sign, String token);

    /**
     * App sign record
     * @param activityId
     * @param token
     * @return
     */
    JsonResult appActivitySignRecord(Long activityId, String token);

    /**
     * Get push tag
     * @param token
     * @return
     */
    JsonResult getNoticeTag(String token);


    /**
     * Get Notice tag tacher manage
     * @param token
     * @return
     */
    JsonResult getNoiceTag(String token);

    /**
     * List manage activity enroll record
     * @param activityId
     * @return
     */
    JsonResult listManageActivityEnrollRecord(Long activityId);

    /**
     * Get manage activity sign record
     * @param activityId
     * @param type
     * @return
     */
    JsonResult getSignRecord(Long activityId, int type);
}
