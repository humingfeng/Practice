package com.practice.task;

import com.practice.service.ExecuteService;
import com.practice.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @author Xushd  2018/1/25 20:38
 */
public class HourTask {

    public static Logger LOGGER = LoggerFactory.getLogger(HourTask.class);

    @Resource
    private ExecuteService executeService;

    public void timerController(){

        LOGGER.info("HOUR_TAST {} BEGIN", TimeUtils.getNowTime());

        /**
         * Polling actvity begin
         */
        executeService.pollingActivityBegin();
        /**
         * Polling actvity end
         */
        executeService.pollingActivityEnd();
        /**
         * Polling activity close
         */
        executeService.pollingActivityEnrollClose();

        LOGGER.info("HOUR_TAST {} END", TimeUtils.getNowTime());
    }
}
