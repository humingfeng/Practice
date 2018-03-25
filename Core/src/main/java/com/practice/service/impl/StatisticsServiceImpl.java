package com.practice.service.impl;

import com.practice.dto.*;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.DictionaryService;
import com.practice.service.SchoolService;
import com.practice.service.StatisticsService;
import com.practice.utils.JwtTokenUtil;
import com.practice.vo.ActivityListItemVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Key;
import java.util.ArrayList;
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
    @Resource
    private ManageActivityLeaderMapper leaderMapper;
    @Resource
    private ActivityService activityService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private ManageActivityThemeMapper themeMapper;
    @Resource
    private ManageActivityClassifyMapper classifyMapper;
    @Resource
    private ManageBaseMapper baseMapper;
    @Resource
    private ParentMapper parentMapper;
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

    /**
     * Get tongji by activity user
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult getTongjiByActivity(String token) {

        TokenTeacherManageDTO tokenTeacherManage = JwtTokenUtil.getTokenTeacherManage(token);


        ManageActivityLeaderExample leaderExample = new ManageActivityLeaderExample();

        leaderExample.createCriteria()
                .andUserIdEqualTo(tokenTeacherManage.getId());

        List<ManageActivityLeader> leaders = leaderMapper.selectByExample(leaderExample);

        TongJiByActivityDTO tongJiByActivityDTO = new TongJiByActivityDTO(0);

        tongJiByActivityDTO.setActivityTotal(leaders.size());

        List<Long> themeIds = new ArrayList<>();
        List<Long> typeIds = new ArrayList<>();

        List<ActivityListItemVO> list = new ArrayList<>();

        for (ManageActivityLeader leader : leaders) {

            if(leader.getMain()==1){
                tongJiByActivityDTO.setMainTotal(tongJiByActivityDTO.getMainTotal()+1);
            }

            ManageActivity activity = activityMapper.selectByPrimaryKey(leader.getActivityId());

            if(!themeIds.contains(activity.getThemeId())){
                tongJiByActivityDTO.setThememTotal(tongJiByActivityDTO.getThememTotal()+1);
            }
            if(!typeIds.contains(activity.getClassifyId())){
                tongJiByActivityDTO.setClassifyTotal(tongJiByActivityDTO.getClassifyTotal()+1);
            }
            ActivityListItemVO activityListItemDTO = activityService.getActivityListItemDTO(leader.getActivityId());

            list.add(activityListItemDTO);


        }

        tongJiByActivityDTO.setList(list);


        return JsonResult.success(tongJiByActivityDTO);
    }

    /**
     * Get tongji by activity
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getTongjiByActivityInfo(Long id) {

        ManageActivityEnrollRecordExample enrollRecordExample = new ManageActivityEnrollRecordExample();

        enrollRecordExample.createCriteria()
                .andActivityIdEqualTo(id)
                .andStatusEqualTo(8);

        TongjiActivityDTO tongjiActivityDTO = new TongjiActivityDTO();

        List<ManageActivityEnrollRecord> manageActivityEnrollRecords = enrollRecordMapper.selectByExample(enrollRecordExample);

        tongjiActivityDTO.setEnrollCount(manageActivityEnrollRecords.size());

        Map<Long,Integer> schools = new HashMap<>();
        Map<Long,Integer> periods = new HashMap<>();

        for (ManageActivityEnrollRecord record : manageActivityEnrollRecords) {

            if(!schools.containsKey(record.getSchoolId())){
               schools.put(record.getSchoolId(),1);
            }else{
                schools.put(record.getSchoolId(),schools.get(record.getSchoolId())+1);
            }
            if(!periods.containsKey(record.getPeriodId())){
                periods.put(record.getPeriodId(),1);
            }else{
                periods.put(record.getPeriodId(),periods.get(record.getPeriodId())+1);
            }
        }

        List<KeyValueDTO> schoolTongji = new ArrayList<>();

        List<KeyValueDTO> periodTongji = new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : schools.entrySet()) {

            KeyValueDTO keyValueDTO = new KeyValueDTO(entry.getKey(), schoolService.getSchoolPO(entry.getKey()).getName(), entry.getValue() + "");

            schoolTongji.add(keyValueDTO);
        }

        for (Map.Entry<Long, Integer> entry : periods.entrySet()) {
            KeyValueDTO keyValueDTO = new KeyValueDTO(entry.getKey(),dictionaryService.getDictionaryPO(entry.getKey()).getName(), entry.getValue() + "");

            periodTongji.add(keyValueDTO);
        }

        tongjiActivityDTO.setPeriod(periodTongji);

        tongjiActivityDTO.setSchool(schoolTongji);

        tongjiActivityDTO.setSchoolCount(schools.size());
        tongjiActivityDTO.setPeriodCount(periods.size());

        return JsonResult.success(tongjiActivityDTO);
    }

    /**
     * Get index count
     *
     * @return
     */
    @Override
    public CountDTO getCountDTO() {

        CountDTO countDTO = new CountDTO();


        long l1 = activityMapper.countByExample(null);

        countDTO.setActivityCount(l1);

        long l2 = themeMapper.countByExample(null);

        countDTO.setThemeCount(l2);

        long l3 = classifyMapper.countByExample(null);

        countDTO.setClassifyCount(l3);

        long l4 = parentMapper.countByExample(null);

        countDTO.setParentCount(l4);

        long l5 = baseMapper.countByExample(null);

        countDTO.setBasesCount(l5);

        ManageActivityExample example = new ManageActivityExample();

        example.createCriteria().andStatusEqualTo(3);

        long l6 = activityMapper.countByExample(example);

        countDTO.setWaitVerifyCount(l6);


        return countDTO;
    }
}
