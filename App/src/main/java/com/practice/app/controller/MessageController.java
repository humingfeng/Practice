package com.practice.app.controller;

import com.practice.result.JsonResult;
import com.practice.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/2/10 22:02
 */
@RequestMapping(value = "/app/auth/msg")
@RestController
public class MessageController {

    @Resource
    private MessageService messageService;

    /**
     * Get message list item by id
     * @param msgId
     * @return
     */
    @RequestMapping(value = "/list/item/{msgId}")
    public JsonResult getMessageListItemById(@PathVariable Long msgId){
        return messageService.getMessageListItemById(msgId);
    }

}
