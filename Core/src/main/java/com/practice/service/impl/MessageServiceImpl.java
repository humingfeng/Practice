package com.practice.service.impl;

import com.practice.dto.MessageListItemDTO;
import com.practice.mapper.ActivityMessageMapper;
import com.practice.po.ActivityMessage;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.MessageService;
import com.practice.utils.JsonUtils;
import com.practice.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/2/10 22:05
 */
@Service
public class MessageServiceImpl implements MessageService {


    @Resource
    private ActivityMessageMapper messageMapper;
    @Resource
    private CacheService cacheService;

    /**
     * Get message list item by id
     *
     * @param msgId
     * @return
     */
    @Override
    public JsonResult getMessageListItemById(Long msgId) {


        MessageListItemDTO itemDTO = cacheService.getActivityMessageItem(msgId);

        if(itemDTO==null){
            ActivityMessage activityMessage = messageMapper.selectByPrimaryKey(msgId);

            itemDTO = new MessageListItemDTO();

            itemDTO.setMsgId(msgId);

            itemDTO.setTitle(activityMessage.getMsgTitle());

            itemDTO.setContent(activityMessage.getMsgContent());

            itemDTO.setImg(activityMessage.getMsgCover());

            itemDTO.setTime(TimeUtils.getBeforeNowString(activityMessage.getCreateTime()));

            itemDTO.setItemId(activityMessage.getActivityId());

            cacheService.setActivityMessageItem(itemDTO);
        }

        return JsonResult.success(itemDTO);
    }
}
