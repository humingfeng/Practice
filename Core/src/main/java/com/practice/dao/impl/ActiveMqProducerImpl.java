package com.practice.dao.impl;

import com.practice.dao.ActiveMqProducer;
import com.practice.dto.OrderPayDelayMessage;
import com.practice.dto.PushMessageDTO;
import com.practice.dto.SolrUpdateMessage;
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

    @Resource(name = "jmsTemplateSolr")
    private JmsTemplate jmsTemplateSolr;

    @Resource(name="pushQueue")
    private Destination pushQueue;

    @Resource(name="payDelayQueue")
    private Destination payDelayQueue;

    @Resource(name = "solrUpdateQueue")
    private Destination solrUpdateQueue;

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

    /**
     * Send solr update message
     *
     * @param solrUpdateMessage
     */
    @Override
    public void sendSolrUpdateMessage(SolrUpdateMessage solrUpdateMessage) {

        String msg = JsonUtils.objectToJson(solrUpdateMessage);

        System.out.println("向队列" + solrUpdateQueue.toString() + "发送了消息------------" + msg);

        jmsTemplateSolr.send(solrUpdateQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }

    /**
     * Send push message
     *
     * @param pushMessageDTO
     */
    @Override
    public void sendPushMessage(PushMessageDTO pushMessageDTO) {

        String msg = JsonUtils.objectToJson(pushMessageDTO);

        System.out.println("向队列" + pushQueue.toString() + "发送了消息------------" + msg);

        jmsTemplatePush.send(pushQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });

    }
}
