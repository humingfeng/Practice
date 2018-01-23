package com.practice.dao;

import javax.jms.Destination;

/**
 * @author Xushd  2018/1/23 22:35
 */
public interface ActiveMqProducer {

    /**
     * Send to add activity solr queue
     * @param destination
     * @param msg
     */
    void sendAddActivitySolrItemMessage(Destination destination, String msg);

    /**
     * Send to push message queue
     * @param destination
     * @param msg
     */
    void sendPushMessage(Destination destination, String msg);
}
