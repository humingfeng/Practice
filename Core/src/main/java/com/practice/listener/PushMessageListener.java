package com.practice.listener;

import com.practice.service.ActivityService;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Xushd  2018/1/23 22:36
 */
public class PushMessageListener implements MessageListener{



    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("QueueMessageListener监听到了文本消息：\t"
                    + tm.getText());

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
