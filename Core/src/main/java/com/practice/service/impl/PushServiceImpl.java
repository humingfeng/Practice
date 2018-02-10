package com.practice.service.impl;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.practice.dto.JpushMessageDTO;
import com.practice.dto.PushMessageDTO;
import com.practice.mapper.ActivityMessageMapper;
import com.practice.mapper.ParentMapper;
import com.practice.po.ActivityMessage;
import com.practice.po.Parent;
import com.practice.service.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/2/10 12:48
 */
@Service
public class PushServiceImpl implements PushService {


    static Logger LOGGER = LoggerFactory.getLogger(PushServiceImpl.class);

    @Value("${PUSH.MASTER.SECRET}")
    private String MASTERSECRET;
    @Value("${PUSH.KEY}")
    private String APPKEY;

    @Resource
    private ActivityMessageMapper activityMessageMapper;
    @Resource
    private ParentMapper parentMapper;

    /**
     * Activity message push
     *
     * @param pushMessageDTO
     */
    @Override
    public void excuteAcitivityMsgPush(PushMessageDTO pushMessageDTO) {

        Long msgId = pushMessageDTO.getMsgId();

        ActivityMessage message = activityMessageMapper.selectByPrimaryKey(msgId);


        //判断是单体推送还是群体推送

        if(message.getReceiverId()==0L){
            //群体

            JpushMessageDTO jpushMessageDTO = new JpushMessageDTO();

            jpushMessageDTO.setActivityId(message.getActivityId());

            jpushMessageDTO.setMsgId(msgId);

            jpushMessageDTO.setTag("tag_"+message.getActivityId());

            //1活动 2其他
            jpushMessageDTO.setType(1);

            jpushMessageDTO.setTitle(message.getMsgTitle());

            jpushMessageDTO.setContent(message.getMsgContent());

            PushPayload pushPayload = buildPushTagPayload(jpushMessageDTO);

            this.sendJpush(pushPayload);


        }else{
            //单体

            JpushMessageDTO jpushMessageDTO = new JpushMessageDTO();

            jpushMessageDTO.setActivityId(message.getActivityId());

            jpushMessageDTO.setMsgId(msgId);
            //1活动 2其他
            jpushMessageDTO.setType(1);

            jpushMessageDTO.setTitle(message.getMsgTitle());

            jpushMessageDTO.setContent(message.getMsgContent());

            //获取cid
            //1家长 2教师
            if(message.getReceiverType()==1){
                Parent parent = parentMapper.selectByPrimaryKey(message.getReceiverId());

                jpushMessageDTO.setCid(parent.getPushId());

            }else{
                //TODO
            }

            PushPayload pushPayload = buildPushCidPayload(jpushMessageDTO);

            this.sendJpush(pushPayload);

        }

        //更新消息表的状态

        ActivityMessage activityMessage = new ActivityMessage();

        activityMessage.setId(msgId);

        activityMessage.setPushStatus(3);

        activityMessageMapper.updateByPrimaryKeySelective(activityMessage);


    }

    /**
     * 推送方法
     * @param payload
     */
    protected void sendJpush(PushPayload payload){
        JPushClient jpushClient = new JPushClient(MASTERSECRET, APPKEY, null, ClientConfig.getInstance());
        try {
            PushResult result = jpushClient.sendPush(payload);
            LOGGER.info("Got result - " + result);
            System.out.println(result);

        } catch (APIConnectionException e) {
            LOGGER.error("Connection error. Should retry later. ", e);
            LOGGER.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOGGER.error("Error response from JPush server. Should review and fix it. ", e);
            LOGGER.info("HTTP Status: " + e.getStatus());
            LOGGER.info("Error Code: " + e.getErrorCode());
            LOGGER.info("Error Message: " + e.getErrorMessage());
            LOGGER.info("Msg ID: " + e.getMsgId());
            LOGGER.error("Sendno: " + payload.getSendno());
        }
    }





    /**
     * 生成单体推送的推送体
     * @param dto
     * @return
     */
    public static PushPayload buildPushCidPayload(JpushMessageDTO dto){
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(dto.getCid()))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(dto.getTitle())
                                .setSound("happy")
                                .addExtra("id", dto.getMsgId())
                                .addExtra("msg",dto.getContent())
                                .addExtra("type",dto.getType())
                                .addExtra("activityId",dto.getActivityId())
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(dto.getTitle())
                                .addExtra("id", dto.getMsgId())
                                .addExtra("msg",dto.getContent())
                                .addExtra("type",dto.getType())
                                .addExtra("activityId",dto.getActivityId())
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setTitle(dto.getTitle())
                        .setMsgContent(dto.getContent())
                        .addExtra("id",dto.getMsgId())
                        .addExtra("msg",dto.getContent())
                        .addExtra("type",dto.getType())
                        .addExtra("activityId",dto.getActivityId())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    /**
     * 生成标签推送的推送体
     * @return
     */
    public static PushPayload buildPushTagPayload(JpushMessageDTO dto) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all()).setAudience(Audience.tag(dto.getTag()))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(dto.getTitle())
                                .setSound("happy")
                                .addExtra("id", dto.getMsgId())
                                .addExtra("msg",dto.getContent())
                                .addExtra("type",dto.getType())
                                .addExtra("activityId",dto.getActivityId())
                                .build())
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(dto.getTitle())
                                .addExtra("id", dto.getMsgId())
                                .addExtra("msg",dto.getContent())
                                .addExtra("type",dto.getType())
                                .addExtra("activityId",dto.getActivityId())
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setTitle(dto.getTitle())
                        .setMsgContent(dto.getContent())
                        .addExtra("id",dto.getMsgId())
                        .addExtra("msg",dto.getContent())
                        .addExtra("type",dto.getType())
                        .addExtra("activityId",dto.getActivityId())
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }
}
