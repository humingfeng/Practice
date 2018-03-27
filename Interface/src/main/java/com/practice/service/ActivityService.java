package com.practice.service;

import com.practice.dto.*;
import com.practice.exception.ServiceException;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.vo.ActivityListItemVO;

import java.util.List;

/**
 * @author Xushd  2017/12/25 20:46
 */
public interface ActivityService {
    /**
     * List activity type
     * @param param
     * @return
     */
    JsonResult listType(PageSearchParam param);

    /**
     * add activity type
     * @param token
     * @param activityType
     * @return
     */
    JsonResult addType(String token, ManageActivityType activityType);

    /**
     * update activity type
     * @param token
     * @param activityType
     * @return
     */
    JsonResult updateType(String token, ManageActivityType activityType);

    /**
     * delete activity type
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteType(String token, Long id);

    /**
     * get activity type
     * @param id
     * @return
     */
    JsonResult getType(Long id);

    /**
     * List activity type usable
     * @return
     */
    JsonResult listTypeUsable();

    /**
     * List activity classify
     * @param param
     * @return
     */
    JsonResult listClassify(PageSearchParam param);

    /**
     * add activity classify
     * @param token
     * @param activityClassify
     * @return
     */
    JsonResult addClassify(String token, ManageActivityClassify activityClassify);

    /**
     * update activity classify
     * @param token
     * @param activityClassify
     * @return
     */
    JsonResult updateClassify(String token, ManageActivityClassify activityClassify);

    /**
     * delete activity classify
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteClassify(String token, Long id);

    /**
     * get activity classify
     * @param id
     * @return
     */
    JsonResult getClassify(Long id);

    /**
     * list activity classify usable
     * @param id
     * @return
     */
    JsonResult listClassifyUsable(Long id);

    /**
     * list activity theme
     * @param param
     * @return
     */
    JsonResult listTheme(PageSearchParam param);

    /**
     * add activity theme
     * @param token
     * @param activityTheme
     * @return
     */
    JsonResult addTheme(String token, ManageActivityTheme activityTheme);

    /**
     * update activity theme
     * @param token
     * @param activityTheme
     * @return
     */
    JsonResult updateTheme(String token, ManageActivityTheme activityTheme);

    /**
     * delete activity theme
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteTheme(String token, Long id);

    /**
     * get activity theme
     * @param id
     * @return
     */
    JsonResult getTheme(Long id);

    /**
     * list activity theme usable
     * @param id
     * @return
     */
    JsonResult listThemeUsable(Long id);

    /**
     * List activity manage
     * @param param
     * @return
     */
    JsonResult listManage(PageSearchParam param);

    /**
     * Add activity manage
     * @param manageActivity
     * @param token
     * @return
     */
    JsonResult addActivityManage(ManageActivity manageActivity, String token);

    /**
     * Get activity manage
     * @param id
     * @return
     */
    JsonResult getActivityManage(Long id);

    /**
     * Update activity manage
     * @param manageActivity
     * @param token
     * @return
     */
    JsonResult updateActivityManage(ManageActivity manageActivity, String token);

    /**
     * Get activity manage PO
     * @param id
     * @return
     */
    ManageActivity getActivityManagePO(Long id);

    /**
     * Get introduce
     * @param activityId
     * @return
     */
    ManageActivityIntroduce getActivityIntroduce(Long activityId);

    /**
     * Update introduce
     * @param token
     * @param introduce
     * @return
     */
    JsonResult updateActivityIntroduce(String token, ManageActivityIntroduce introduce);

    /**
     * List apply
     * @param activityId
     * @return
     */
    JsonResult listApply(Long activityId);

    /**
     * Add apply
     * @param token
     * @param activityId
     * @param gradeIds
     * @return
     */
    JsonResult addApply(String token,Long activityId, String gradeIds);

    /**
     * Del apply
     * @param id
     * @return
     */
    JsonResult delApply(Long id);

    /**
     * List attention
     * @param activityId
     * @return
     */
    JsonResult listAttention(Long activityId);

    /**
     * Add attention
     * @param token
     * @param attention
     * @return
     */
    JsonResult addAttention(String token, ManageActivityAttention attention);

    /**
     * Update attention
     * @param token
     * @param attention
     * @return
     */
    JsonResult updateAttention(String token, ManageActivityAttention attention);

    /**
     * Del attention
     * @param id
     * @return
     */
    JsonResult delAttention(Long id);

    /**
     * List leader
     * @param activityId
     * @return
     */
    JsonResult listLeader(Long activityId);

    /**
     * Add leader
     * @param token
     * @param activityId
     * @param userIds
     * @return
     */
    JsonResult addLeader(String token, Long activityId, String userIds);

    /**
     * update leader
     * @param token
     * @param id
     * @param activityId
     * @param main
     * @return
     */
    JsonResult updateLeader(String token, Long id, Long activityId, int main);

    /**
     * del leader
     * @param id
     * @return
     */
    JsonResult delLeader(Long id);

    /**
     * List supervise
     * @param activityId
     * @return
     */
    JsonResult listSupervise(Long activityId);

    /**
     * Add supervise
     * @param activityId
     * @param token
     * @param userId
     * @return
     */
    JsonResult addSupervise(Long activityId, String token, Long userId);

    /**
     * Del supervise
     * @param id
     * @return
     */
    JsonResult delSupervise(Long id);

    /**
     * Close supervise
     * @param activityId
     * @param status
     * @return
     */
    JsonResult statusSupervise(Long activityId,int status);

    /**
     * Get Enroll
     * @param activityId
     * @return
     */
    ManageActivityEnroll getActivityEnroll(Long activityId);

    /**
     * Update Enroll
     *
     * @param token
     * @param enroll
     * @return
     */
    JsonResult updateActivityEnroll(String token, ManageActivityEnroll enroll);

    /**
     * Get Sign
     * @param activityId
     * @return
     */
    ManageActivitySign getActivitySign(Long activityId);

    /**
     * Update Sign
     * @param token
     * @param sign
     * @return
     */
    JsonResult updateActivitySign(String token, ManageActivitySign sign);


    /**
     * List task
     * @param activityId
     * @return
     */
    JsonResult listTask(Long activityId);

    /**
     * Add task
     * @param token
     * @param task
     * @return
     */
    JsonResult addTask(String token, ManageActivityTask task);

    /**
     * Update task
     * @param token
     * @param task
     * @return
     */
    JsonResult updateTask(String token, ManageActivityTask task);

    /**
     * Del task
     * @param token
     * @param activityId
     * @param id
     * @return
     */
    JsonResult delTask(String token, Long activityId, Long id);

    /**
     * List task item
     * @param activityId
     * @param taskId
     * @return
     */
    JsonResult listTaskItem(Long activityId, Long taskId);

    /**
     * Add task item
     * @param token
     * @param taskItem
     * @return
     */
    JsonResult addTaskItem(String token, ManageActivityTaskItem taskItem);

    /**
     * Update task item
     * @param token
     * @param taskItem
     * @return
     */
    JsonResult updateTaskItem(String token, ManageActivityTaskItem taskItem);

    /**
     * Del task item
     * @param taskId
     * @param id
     * @return
     */
    JsonResult delTaskItem(Long taskId, Long id);

    /**
     * List question
     * @param
     * @return
     */
    JsonResult listQuestion(PageSearchParam param);

    /**
     * Add question
     * @param token
     * @param question
     * @return
     */
    JsonResult addQuestion(String token, QuestionDTO question);

    /**
     * Update question
     * @param token
     * @param question
     * @return
     */
    JsonResult updateQuestion(String token, QuestionDTO question);

    /**
     * Del question
     * @param id
     * @param token
     * @return
     */
    JsonResult delQuestion(String token,Long id);

    /**
     * Get question
     * @param id
     * @return
     */
    JsonResult getQuestion(Long id);

    /**
     * Update question status
     * @param id
     * @param status
     * @param token
     * @return
     */
    JsonResult updateQuestionStatus(Long id, int status, String token);

    /**
     * List question usable
     * @param param
     * @return
     */
    JsonResult listQuestionUsable(PageSearchParam param);

    /**
     * Add task question
     * @param activityId
     * @param taskId
     * @param checkeds
     * @param token
     * @return
     */
    JsonResult addTaskQuestion(String token,Long activityId, Long taskId, String checkeds);

    /**
     * List task question
     * @param activityId
     * @param taskId
     * @return
     */
    JsonResult listActivityTaskQuestion(Long activityId, Long taskId);

    /**
     * Del task question
     * @param token
     * @param activityId
     * @param taskId
     * @param id
     * @return
     */
    JsonResult delTaskQuestion(String token, Long activityId, Long taskId, Long id);

    /**
     * Check over activity
     * @param id
     * @return
     */
    JsonResult checkOverActivity(Long id);

    /**
     * To verfiy activity
     * @param token
     * @param id
     * @return
     */
    JsonResult toVerfiy(String token, Long id);

    /**
     * List activity verity
     * @param param
     * @return
     */
    JsonResult listVerifyActivity(PageSearchParam param);

    /**
     * Pass activity
     * @param id
     * @return
     */
    JsonResult passActivity(Long id) throws ServiceException;

    /**
     * Reject activity
     * @param id
     * @param reason
     * @return
     */
    JsonResult rejectActivity(Long id, String reason);

    /**
     * Get activity view
     * @param id
     * @return
     */
    JsonResult getActivityView(Long id);

    /**
     * Update activity sign status
     * @param token
     * @param activityId
     * @param status
     * @return
     */
    JsonResult updateActivitySingStatus(String token, Long activityId, int status);

    /**
     * Offline activity
     * @param token
     * @param id
     * @return
     * @throws ServiceException
     */
    JsonResult offline(String token, Long id) throws ServiceException;

    /**
     * List enroll record
     * @param param
     * @param activityId
     * @return
     */
    JsonResult listEnrollRecordList(PageSearchParam param, Long activityId);

    /**
     * Get activitySolrItemDTO
     * @param activityId
     * @return
     */
    ActivitySolrItemDTO getActivitySolrItemDTO(Long activityId);


    /**
     * List online activity
     * @param param
     * @return
     */
    JsonResult listOnlineActivity(PageSearchParam param);

    /**
     * List actvity type
     * @return
     */
    List<KeyValueDTO> listTypeKV();

    /**
     * List classify from cache
     * @param typeId
     * @return
     */
    JsonResult listClassifyCache(Long typeId);

    /**
     * Get activity detail
     * @param id
     * @param token
     * @return
     */
    JsonResult getActivityDetail(Long id, String token);

    /**
     * Collect activity
     * @param id
     * @param token
     * @return
     */
    JsonResult collectActivity(Long id, String token);

    /**
     * List collect
     * @param token
     * @return
     */
    JsonResult listCollect(String token);

    /**
     * collect cancle
     * @param activityId
     * @param token
     * @return
     */
    JsonResult collectActivityCancle(Long activityId, String token);

    /**
     * Get Activity
     * @param activityId
     * @return
     */
    ManageActivity getActivity(Long activityId);

    /**
     * List theme by classifyId
     * @param classifyId
     * @return
     */
    JsonResult listThemeCache(Long classifyId);

    /**
     * List theme usable  by classifyid
     * @param classifyId
     * @return
     */
    List<KeyValueDTO> listThemeUsablePO(Long classifyId);

    /**
     * Get Activity list item VO
     * @param activityId
     * @return
     */
    ActivityListItemVO getActivityListItemDTO(Long activityId);

    /**
     * Update Solr
     * @param solrUpdateMessage
     */
    void excuteSolrUpdate(SolrUpdateMessage solrUpdateMessage);

    /**
     * Update activity sign out Ercode
     * @param id
     * @param s
     * @param diff
     */
    void updateActivitySingOutErcode(Long id, String s, int diff);

    /**
     * Update activity sign in Ercode
     * @param id
     * @param s
     */
    void updateActivitySignInErcode(Long id, String s);

    /**
     * Reset classify cache
     * @return
     */
    JsonResult resetClassifyCache();


}
