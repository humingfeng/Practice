package com.practice.service.impl;

import com.practice.dto.ParentStatisticsDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.mapper.ManageActivityEnrollRecordMapper;
import com.practice.mapper.ManageActivityMapper;
import com.practice.mapper.ManageActivityTaskItemAnswerMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.StatisticsService;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xushd on 2018/2/14 10:08
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private ManageActivityEnrollRecordMapper enrollRecordMapper;
    @Resource
    private ManageActivityMapper activityMapper;
    @Resource
    private ManageActivityTaskItemAnswerMapper taskItemAnswerMapper;

    /**
     * Get my statistics
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult getStatistics(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivityEnrollRecordExample enrollRecordExample = new ManageActivityEnrollRecordExample();

        enrollRecordExample.createCriteria()
                .andStudentIdEqualTo(tokenParent.getStudentId())
                .andStatusEqualTo(8);

        List<ManageActivityEnrollRecord> records = enrollRecordMapper.selectByExample(enrollRecordExample);

        ParentStatisticsDTO statisticsDTO = new ParentStatisticsDTO();

        statisticsDTO.setActivityCount(records.size());

        Map<Long,Integer> activityIds = new HashMap<>();

        for (ManageActivityEnrollRecord record : records) {
            if(activityIds.containsKey(record.getActivityId())){
                Integer count = activityIds.get(record.getActivityId());

                activityIds.put(record.getActivityId(),count+1);

            }else{
                activityIds.put(record.getActivityId(),1);
            }
        }

        Map<Long,Integer> themeIds = new HashMap<>();
        Integer themeCount  = 0;
        for (Map.Entry<Long, Integer> entry : activityIds.entrySet()) {

            ManageActivity activity = activityMapper.selectByPrimaryKey(entry.getKey());

            if(themeIds.containsKey(activity.getThemeId())){
                Integer integer = themeIds.get(activity.getThemeId());

                themeIds.put(activity.getThemeId(),integer+1);
            }else{
                themeIds.put(activity.getThemeId(),1);

                themeCount++;
            }

        }

        statisticsDTO.setThemeCount(themeCount);


        long taskCount = taskItemAnswerMapper.countTaskOver(tokenParent.getStudentId());

        statisticsDTO.setTaskCount((int) taskCount);

        return JsonResult.success(statisticsDTO);
    }
}
