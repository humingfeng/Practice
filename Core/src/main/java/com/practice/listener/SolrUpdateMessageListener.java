package com.practice.listener;

import com.practice.dto.OrderPayDelayMessage;
import com.practice.dto.SolrUpdateMessage;
import com.practice.service.ActivityService;
import com.practice.utils.JsonUtils;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Xushd on 2018/2/9 16:15
 */
public class SolrUpdateMessageListener implements MessageListener {

    @Resource
    private ActivityService activityService;

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("监听到了文本消息：\t"
                    + tm.getText());

            SolrUpdateMessage solrUpdateMessage = JsonUtils.jsonToPojo(((TextMessage) message).getText(), SolrUpdateMessage.class);

            activityService.excuteSolrUpdate(solrUpdateMessage);


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
