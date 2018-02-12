package com.practice.service.impl;

import com.practice.dto.*;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.TaskService;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private ManageActivityTaskItemAnswerMapper taskItemAnswerMapper;
    @Resource
    private ParentPhotosMapper photosMapper;

    /**
     * Get activity Task
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult getActivityTask(Long activityId, String token) {


        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        List<ManageActivityTask> tasks = taskMapper.selectByActivityId(activityId);

        List<ActivityTaskDTO> list = new ArrayList<>();


        ManageActivityTaskItemAnswerExample itemAnswerExample = new ManageActivityTaskItemAnswerExample();


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

            itemAnswerExample.clear();

            itemAnswerExample.createCriteria()
                    .andActivityIdEqualTo(activityId)
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andTaskIdEqualTo(task.getId());

            long l1 = taskItemAnswerMapper.countByExample(itemAnswerExample);

            if(l1>0){
                taskDTO.setIsAnswer(1);
            }else{
                taskDTO.setIsAnswer(0);
            }

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

        ManageActivityTaskItemAnswerExample answerExample = new ManageActivityTaskItemAnswerExample();


        for (Long questionId : questionIds) {

            ManageActivityQuestion question = questionMapper.selectByPrimaryKey(questionId);


            TaskQuestionDTO taskQuestionDTO = new TaskQuestionDTO();

            taskQuestionDTO.setQuestionId(questionId);

            taskQuestionDTO.setActivityId(task.getActivityId());

            taskQuestionDTO.setTaskId(taskId);

            taskQuestionDTO.setClassify(question.getClassify());

            taskQuestionDTO.setType(question.getTypeId());

            taskQuestionDTO.setQuestionStr(question.getQuestion());

            answerExample.clear();

            answerExample.createCriteria()
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andActivityIdEqualTo(task.getActivityId())
                    .andTaskIdEqualTo(taskId)
                    .andQuestionIdEqualTo(questionId);

            List<ManageActivityTaskItemAnswer> itemAnswers = taskItemAnswerMapper.selectByExample(answerExample);

            if(itemAnswers.size()>0){

                if(question.getClassify()==1||question.getClassify()==2){
                    taskQuestionDTO.setAnswer(itemAnswers.get(0).getAnswerText());
                }else{

                    String photos = itemAnswers.get(0).getAnswerText();

                    String[] split = photos.split(",");

                    List<String> answers = new ArrayList<>();

                    for (String s : split) {

                        ParentPhotos parentPhotos = photosMapper.selectByPrimaryKey(Long.valueOf(s));
                        if(parentPhotos!=null){
                            answers.add(parentPhotos.getPhoto());
                        }else{
                            answers.add("");
                        }

                    }

                    int i = question.getPhotoNum() - split.length;

                    for (int j = 0; j < i; j++) {
                        answers.add("");

                    }
                    taskQuestionDTO.setAnswers(answers);
                    taskQuestionDTO.setPhotoNum(question.getPhotoNum());

                }


            }else{
                if(question.getClassify()==3){
                    taskQuestionDTO.setPhotoNum(question.getPhotoNum());

                    List<String> answers = new ArrayList<>();

                    for (int i = 0; i < question.getPhotoNum(); i++) {
                        answers.add("");
                    }
                    taskQuestionDTO.setAnswers(answers);
                }

                taskQuestionDTO.setAnswer("");
            }




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

    /**
     * Save task answer
     *
     * @param answer
     * @param token
     * @return
     */
    @Override
    public JsonResult saveActivityTaskQuestionAnswer(String answer, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        List<TaskQuestionAnswerDTO> answerDTOS = JsonUtils.jsonToList(answer, TaskQuestionAnswerDTO.class);

        ManageActivityTaskItemAnswerExample answerExample = new ManageActivityTaskItemAnswerExample();

        for (TaskQuestionAnswerDTO answerDTO : answerDTOS) {

            answerExample.clear();

            answerExample.createCriteria()
                    .andActivityIdEqualTo(answerDTO.getActivityId())
                    .andTaskIdEqualTo(answerDTO.getTaskId())
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andQuestionIdEqualTo(answerDTO.getQuestionId());

            taskItemAnswerMapper.deleteByExample(answerExample);

            ManageActivityTaskItemAnswer itemAnswer = new ManageActivityTaskItemAnswer();

            itemAnswer.setActivityId(answerDTO.getActivityId());

            itemAnswer.setTaskId(answerDTO.getTaskId());

            itemAnswer.setQuestionId(answerDTO.getQuestionId());

            itemAnswer.setType(answerDTO.getType());

            itemAnswer.setStudentId(tokenParent.getStudentId());

            if(answerDTO.getType()==1){
                //选择题
                itemAnswer.setAnswerText(answerDTO.getAnswer());

            }else if(answerDTO.getType()==2){
                //简单题
                itemAnswer.setAnswerText(answerDTO.getAnswer());
            }else {

                List<Long> photoId = new ArrayList<>();

                String phontos = answerDTO.getAnswer();

                String[] split = phontos.split(",");

                for (String s : split) {
                    ParentPhotos parentPhotos = new ParentPhotos();

                    parentPhotos.setCreateTime(new Date());

                    parentPhotos.setUserId(tokenParent.getId());

                    parentPhotos.setPhoto(s);

                    parentPhotos.setDescription("活动题目回答");

                    photosMapper.insertSelective(parentPhotos);

                    photoId.add(parentPhotos.getId());
                }

                itemAnswer.setUpdateTime(new Date());

                itemAnswer.setAnswerText(StringUtils.join(photoId,","));
            }

            taskItemAnswerMapper.insertSelective(itemAnswer);
        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }
}
