package com.practice.listener;

import com.practice.dto.OrderPayDelayMessage;
import com.practice.service.OrderService;
import com.practice.utils.JsonUtils;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Xushd  2018/1/23 22:36
 */
public class PayDelayMessageListener implements MessageListener {

    @Resource
    private OrderService orderService;

    @Override
    public void onMessage(Message message) {
        TextMessage tm = (TextMessage) message;
        try {
            System.out.println("监听到了文本消息：\t"
                    + tm.getText());

            OrderPayDelayMessage orderPayDelayMessage = JsonUtils.jsonToPojo(((TextMessage) message).getText(), OrderPayDelayMessage.class);

            orderService.closeOrderPay(orderPayDelayMessage);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
