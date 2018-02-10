package com.practice.service.impl;

import com.TestBase;
import com.practice.dto.PushMessageDTO;
import com.practice.service.PushService;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Xushd  2018/2/10 18:35
 */
public class PushServiceImplTest extends TestBase {

    @Resource
    private PushService pushService;


    @Test
    public void sendJpush() throws Exception {

        PushMessageDTO pushMessageDTO = new PushMessageDTO();

        pushMessageDTO.setMsgId(2L);

        pushMessageDTO.setType(1);

        pushService.excuteAcitivityMsgPush(pushMessageDTO);
    }

}