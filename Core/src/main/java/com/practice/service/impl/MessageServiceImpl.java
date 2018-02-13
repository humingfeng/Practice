package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.MessageListItemDTO;
import com.practice.dto.PageResult;
import com.practice.dto.TokenParentDTO;
import com.practice.mapper.ActivityMessageMapper;
import com.practice.po.ActivityMessage;
import com.practice.po.ActivityMessageExample;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.MessageService;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

            itemDTO.setTime(TimeUtils.getDateString(activityMessage.getCreateTime()));

            itemDTO.setItemId(activityMessage.getActivityId());

            cacheService.setActivityMessageItem(itemDTO);
        }

        return JsonResult.success(itemDTO);
    }

    /**
     * List message
     *
     * @param activityId
     * @param pageIndex
     * @param token
     * @return
     */
    @Override
    public JsonResult getMessageList(Long activityId, Integer pageIndex, String token) {

        PageHelper.startPage(pageIndex,10);

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ActivityMessageExample messageExample = new ActivityMessageExample();

        messageExample.createCriteria()
                .andStatusEqualTo(1)
                .andActivityIdEqualTo(activityId)
                .andReceiverIdEqualTo(tokenParent.getId());
        messageExample.or().andStatusEqualTo(1)
                .andActivityIdEqualTo(activityId)
                .andReceiverIdEqualTo(0L);

        messageExample.setOrderByClause("create_time desc" );

        List<ActivityMessage> activityMessages = messageMapper.selectByExample(messageExample);

        PageInfo<ActivityMessage> pageInfo = new PageInfo<>(activityMessages);

        List<MessageListItemDTO> list = new ArrayList<>();

        for (ActivityMessage activityMessage : activityMessages) {

            MessageListItemDTO itemDTO = new MessageListItemDTO();

            itemDTO.setMsgId(activityMessage.getId());

            itemDTO.setTitle(activityMessage.getMsgTitle());

            itemDTO.setContent(activityMessage.getMsgContent());

            itemDTO.setImg(activityMessage.getMsgCover());

            itemDTO.setTime(TimeUtils.getDateString(activityMessage.getCreateTime()));

            itemDTO.setItemId(activityMessage.getActivityId());

            list.add(itemDTO);
        }

        PageResult<MessageListItemDTO> pageResult = new PageResult<>();


        pageResult.setList(list);

        pageResult.setPages(pageInfo.getPages());


        return JsonResult.success(pageResult);
    }
}
