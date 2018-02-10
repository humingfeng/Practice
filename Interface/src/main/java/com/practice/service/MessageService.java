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
}
