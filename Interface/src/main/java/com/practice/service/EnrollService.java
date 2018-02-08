package com.practice.service;

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
}