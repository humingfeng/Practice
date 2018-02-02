package com.practice.dao;

import com.practice.dto.OrderPayDelayMessage;

/**
 * @author Xushd  2018/1/23 22:35
 */
public interface ActiveMqProducer {

    boolean sendOrderPayDelayMessage(OrderPayDelayMessage orderPayDelayMessage);
}
