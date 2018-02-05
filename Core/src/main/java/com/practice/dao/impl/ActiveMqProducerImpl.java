package com.practice.dao.impl;

import com.practice.dao.ActiveMqProducer;
import com.practice.dto.OrderPayDelayMessage;
import com.practice.utils.JsonUtils;
import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * @author Xushd  2018/1/23 22:35
 */
public class ActiveMqProducerImpl implements ActiveMqProducer {

    @Resource(name="jmsTemplatePayDelay")
    private JmsTemplate jmsTemplatePayDelay;

    @Resource(name="jmsTemplatePush")
    private JmsTemplate jmsTemplatePush;

    @Resource(name="pushQueue")
    private Destination pushQueue;

    @Resource(name="payDelayQueue")
    private Destination payDelayQueue;

    @Override
    public boolean sendOrderPayDelayMessage(OrderPayDelayMessage orderPayDelayMessage) {

        final long time = 15 * 60 * 1000;

        String msg = JsonUtils.objectToJson(orderPayDelayMessage);

        System.out.println("向队列" + payDelayQueue.toString() + "发送了消息------------" + msg);

        jmsTemplatePayDelay.send(payDelayQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message =  session.createTextMessage(msg);
                //设置延迟时间
                message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
                return message;
            }
        });

        return true;
    }

}
