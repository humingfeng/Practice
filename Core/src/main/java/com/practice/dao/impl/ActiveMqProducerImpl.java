package com.practice.dao.impl;

import com.practice.dao.ActiveMqProducer;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author Xushd  2018/1/23 22:35
 */
public class ActiveMqProducerImpl implements ActiveMqProducer {

    @Resource(name="jmsTemplateActivity")
    private JmsTemplate jmsTemplateActivity;

    @Resource(name="jmsTemplatePush")
    private JmsTemplate jmsTemplatePush;

    /**
     * Send to add activity solr queue
     *
     * @param destination
     * @param msg
     */
    @Override
    public void sendAddActivitySolrItemMessage(Destination destination, String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsTemplateActivity.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }

    /**
     * Send to push message queue
     *
     * @param destination
     * @param msg
     */
    @Override
    public void sendPushMessage(Destination destination, String msg) {
        System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
        jmsTemplatePush.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(msg);
            }
        });
    }
}
