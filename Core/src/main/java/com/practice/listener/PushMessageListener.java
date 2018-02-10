package com.practice.listener;

import com.practice.dto.PushMessageDTO;
import com.practice.service.PushService;
import com.practice.utils.JsonUtils;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Xushd  2018/1/23 22:36
 */
public class PushMessageListener implements MessageListener{

    @Resource
    private PushService pushService;

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());

            PushMessageDTO pushMessageDTO = JsonUtils.jsonToPojo(tm.getText(), PushMessageDTO.class);

            if(pushMessageDTO.getType()==1){

                pushService.excuteAcitivityMsgPush(pushMessageDTO);

            }


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
