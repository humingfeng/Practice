package com.practice.service.impl;

import com.practice.mapper.ManageActivityMapper;
import com.practice.po.ManageActivity;
import com.practice.po.ManageActivityExample;
import com.practice.service.ExecuteService;
import com.practice.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd  2018/1/25 20:47
 */
@Service
public class ExecuteServiceImpl implements ExecuteService {

    public static Logger LOGGER = LoggerFactory.getLogger(ExecuteServiceImpl.class);

    @Resource
    private ManageActivityMapper activityMapper;

    private static Integer INTERVAL = 1;
    /**
     * Polling activity begin
     */
    @Override
    public void pollingActivityBegin() {

        ManageActivityExample example = new ManageActivityExample();

        /**
         * Get current time before 10m and after 10m
         */
        example.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(6)
                .andBeginTimeBetween(TimeUtils.getDateBeforeMinutes(INTERVAL), TimeUtils.getDateAfterMinutes(INTERVAL ));

        List<ManageActivity> manageActivities = activityMapper.selectByExample(example);

        List<Long> ids = new ArrayList<>();
        for (ManageActivity manageActivity : manageActivities) {

            ids.add(manageActivity.getId());

            LOGGER.info("ACTIVITY STATUS BEGIN {}", manageActivity.getId());
        }
        if (ids.size() > 0) {
            int i = activityMapper.updateStatusByPrimaryKeyList(ids, 1);
            if (i > 0) {
                LOGGER.info("UPDATE STATUS BEGIN {} SUCCESS", TimeUtils.getNowTime());
            } else {
                LOGGER.info("UPDATE STATUS BEGIN {} ERROR", TimeUtils.getNowTime());
            }
        } else {
            LOGGER.info("UPDATE STATUS BEGIN NO ITEMS {}", TimeUtils.getNowTime());
        }
    }

    /**
     * Polling actvity end
     */
    @Override
    public void pollingActivityEnd() {
        ManageActivityExample example = new ManageActivityExample();

        /**
         * Get current time before INTERVAL and after INTERVAL
         */
        example.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1)
                .andEndTimeBetween(TimeUtils.getDateBeforeMinutes(INTERVAL), TimeUtils.getDateAfterMinutes(INTERVAL));

        List<ManageActivity> manageActivities = activityMapper.selectByExample(example);

        List<Long> ids = new ArrayList<>();
        for (ManageActivity manageActivity : manageActivities) {

            ids.add(manageActivity.getId());

            LOGGER.info("ACTIVITY STATUS END {}", manageActivity.getId());
        }
        if (ids.size() > 0) {
            int i = activityMapper.updateStatusByPrimaryKeyList(ids, 5);
            if (i > 0) {
                LOGGER.info("UPDATE STATUS END {} SUCCESS", TimeUtils.getNowTime());
            } else {
                LOGGER.info("UPDATE STATUS END {} ERROR", TimeUtils.getNowTime());
            }
        } else {
            LOGGER.info("UPDATE STATUS END NO ITEMS {}", TimeUtils.getNowTime());
        }
    }

    /**
     * Polling activity close
     */
    @Override
    public void pollingActivityEnrollClose() {
        ManageActivityExample example = new ManageActivityExample();

        /**
         * Get current time before INTERVAL and after INTERVAL
         */
        example.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(6)
                .andCloseTypeEqualTo(1)
                .andCloseTimeBetween(TimeUtils.getDateBeforeMinutes(INTERVAL), TimeUtils.getDateAfterMinutes(INTERVAL));

        List<ManageActivity> manageActivities = activityMapper.selectByExample(example);

        List<Long> ids = new ArrayList<>();
        for (ManageActivity manageActivity : manageActivities) {

            ids.add(manageActivity.getId());

            LOGGER.info("ACTIVITY STATUS ENROLL CLOSE {}", manageActivity.getId());
        }
        if (ids.size() > 0) {
            int i = activityMapper.updateStatusByPrimaryKeyList(ids, 2);
            if (i > 0) {
                LOGGER.info("UPDATE STATUS ENROLL CLOSE {} SUCCESS", TimeUtils.getNowTime());
            } else {
                LOGGER.info("UPDATE STATUS ENROLL CLOSE {} ERROR", TimeUtils.getNowTime());
            }
        } else {
            LOGGER.info("UPDATE STATUS ENROLL CLOSE NO ITEMS {}", TimeUtils.getNowTime());
        }
    }
}
