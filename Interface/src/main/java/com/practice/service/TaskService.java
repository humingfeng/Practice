package com.practice.service;

import com.practice.result.JsonResult;

/**
 * @author Xushd  2018/2/11 20:55
 */
public interface TaskService {
    /**
     * Get activity Task
     * @param activityId
     * @param token
     * @return
     */
    JsonResult getActivityTask(Long activityId, String token);

    /**
     * Get activity Task question
     * @param taskId
     * @param token
     * @return
     */
    JsonResult getActivityTaskQuestion(Long taskId, String token);
}
