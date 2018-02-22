package com.practice.service;

import com.practice.result.JsonResult;

/**
 * @author Xushd  2018/2/10 22:05
 */
public interface MessageService {
    /**
     * Get message list item by id
     * @param msgId
     * @return
     */
    JsonResult getMessageListItemById(Long msgId);

    /**
     * List message
     * @param activityId
     * @param pageIndex
     * @param token
     * @return
     */
    JsonResult getMessageList(Long activityId, Integer pageIndex, String token);

    /**
     * Send activity end push
     * @param id
     */
    void sendActivityEndMessage(Long id);

    /**
     * Send activity begin push
     * @param id
     */
    void sendActivityBeginMessage(Long id);
}
