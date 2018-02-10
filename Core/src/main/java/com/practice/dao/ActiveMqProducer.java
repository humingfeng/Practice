package com.practice.dao;

import com.practice.dto.OrderPayDelayMessage;
import com.practice.dto.PushMessageDTO;
import com.practice.dto.SolrUpdateMessage;

/**
 * @author Xushd  2018/1/23 22:35
 */
public interface ActiveMqProducer {

    /**
     * Send order pay delay message
     * @param orderPayDelayMessage
     * @return
     */
    boolean sendOrderPayDelayMessage(OrderPayDelayMessage orderPayDelayMessage);

    /**
     * Send solr update message
     * @param solrUpdateMessage
     */
    void sendSolrUpdateMessage(SolrUpdateMessage solrUpdateMessage);

    /**
     * Send push message
     * @param pushMessageDTO
     */
    void sendPushMessage(PushMessageDTO pushMessageDTO);
}
