package com.practice.service.impl;

import com.practice.dto.ActivityTaskDTO;
import com.practice.mapper.ManageActivityTaskItemMapper;
import com.practice.mapper.ManageActivityTaskMapper;
import com.practice.po.ManageActivityTask;
import com.practice.po.ManageActivityTaskItemExample;
import com.practice.result.JsonResult;
import com.practice.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd  2018/2/11 20:55
 */
@Service
public class TaskServiceImpl implements TaskService {


    @Resource
    private ManageActivityTaskMapper taskMapper;
    @Resource
    private ManageActivityTaskItemMapper taskItemMapper;

    /**
     * Get activity Task
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult getActivityTask(Long activityId, String token) {

        List<ManageActivityTask> tasks = taskMapper.selectByActivityId(activityId);

        List<ActivityTaskDTO> list = new ArrayList<>();

        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        for (ManageActivityTask task : tasks) {
            ActivityTaskDTO taskDTO = new ActivityTaskDTO();

            taskDTO.setAcivityId(activityId);

            taskDTO.setQuestionLimit(task.getLimitNum());

            taskDTO.setTaskName(task.getTitle());

            taskDTO.setTaskDescritption(task.getDescription());

            taskDTO.setTaskId(task.getId());

            itemExample.clear();

            itemExample.createCriteria().andTaskIdEqualTo(task.getId());

            long l = taskItemMapper.countByExample(itemExample);

            taskDTO.setQuestionNum((int) l);

            taskDTO.setTaskLimit(tasks.size());

            taskDTO.setImgCover("http://static-uping.oss-cn-beijing.aliyuncs.com/practice/activity_cover/1447202244619.jpg");

            list.add(taskDTO);
        }

        return JsonResult.success(list);
    }
}
