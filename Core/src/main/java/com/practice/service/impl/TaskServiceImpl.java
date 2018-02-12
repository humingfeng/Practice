package com.practice.service.impl;

import com.practice.dto.ActivityTaskDTO;
import com.practice.dto.KeyValueDTO;
import com.practice.dto.TaskQuestionDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.mapper.ManageActivityQuestionMapper;
import com.practice.mapper.ManageActivityQuestionOptionMapper;
import com.practice.mapper.ManageActivityTaskItemMapper;
import com.practice.mapper.ManageActivityTaskMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.TaskService;
import com.practice.utils.JwtTokenUtil;
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
    @Resource
    private ManageActivityQuestionMapper questionMapper;
    @Resource
    private ManageActivityQuestionOptionMapper questionOptionMapper;

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

    /**
     * Get activity Task question
     *
     * @param taskId
     * @param token
     * @return
     */
    @Override
    public JsonResult getActivityTaskQuestion(Long taskId, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivityTask task = taskMapper.selectByPrimaryKey(taskId);

        List<Long> questionIds = taskItemMapper.selectQuestionIds(task.getActivityId(), taskId);

        List<TaskQuestionDTO> list = new ArrayList<>();

        ManageActivityQuestionOptionExample optionExample = new ManageActivityQuestionOptionExample();

        for (Long questionId : questionIds) {

            ManageActivityQuestion question = questionMapper.selectByPrimaryKey(questionId);

            TaskQuestionDTO taskQuestionDTO = new TaskQuestionDTO();

            taskQuestionDTO.setQuestionId(questionId);

            taskQuestionDTO.setActivityId(task.getActivityId());

            taskQuestionDTO.setTaskId(taskId);

            taskQuestionDTO.setClassify(question.getClassify());

            taskQuestionDTO.setType(question.getTypeId());

            taskQuestionDTO.setQuestionStr(question.getQuestion());

            if(question.getClassify()==3){
                taskQuestionDTO.setPhotoNum(question.getPhotoNum());

                List<String> answers = new ArrayList<>();

                for (int i = 0; i < question.getPhotoNum(); i++) {
                     answers.add("");
                }
                taskQuestionDTO.setAnswers(answers);
            }

            taskQuestionDTO.setAnswer("");
            if(question.getClassify()==1){

                optionExample.clear();

                optionExample.createCriteria().andQuestionIdEqualTo(questionId);

                List<ManageActivityQuestionOption> options = questionOptionMapper.selectByExample(optionExample);

                List<KeyValueDTO> optionsList = new ArrayList<>();

                for (ManageActivityQuestionOption option : options) {

                    optionsList.add(new KeyValueDTO(option.getId(),option.getOptionMark(),option.getText()));
                }

                taskQuestionDTO.setOptions(optionsList);
            }

            list.add(taskQuestionDTO);

        }
        return JsonResult.success(list);
    }
}
