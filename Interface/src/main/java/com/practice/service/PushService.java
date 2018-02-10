package com.practice.service;

import com.practice.dto.PushMessageDTO;

/**
 * @author Xushd  2018/2/10 12:47
 */
public interface PushService {

    /**
     * Activity message push
     * @param pushMessageDTO
     */
    void excuteAcitivityMsgPush(PushMessageDTO pushMessageDTO);
}
