package com.practice.service.impl;

import com.practice.dto.*;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.CacheService;
import com.practice.service.EnrollService;
import com.practice.service.PersonnelService;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.TimeUtils;
import org.apache.solr.common.util.Hash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.*;

/**
 * @author Xushd on 2018/1/30 15:26
 */
@Service
public class EnrollServiceImpl implements EnrollService {

    @Resource
    private StudentEnrollInfoMapper studentEnrollInfoMapper;
    @Resource
    private ManageStudentMapper studentMapper;
    @Resource
    private ManageActivityEnrollRecordMapper enrollRecordMapper;
    @Resource
    private ManageActivityMapper activityMapper;
    @Resource
    private ParentStudentMapper parentStudentMapper;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private ManageActivitySignMapper signMapper;
    @Resource
    private ManageActivitySignRecordMapper signRecordMapper;
    @Resource
    private ParentActivityLinkMapper parentActivityLinkMapper;
    /**
     * Get student enroll info
     *
     * @param studentId
     * @param token
     * @return
     */
    @Override
    public JsonResult getStudentEnrollInfo(Long studentId,String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        StudentEnrollInfoExample example = new StudentEnrollInfoExample();

        example.createCriteria().andStudentIdEqualTo(studentId);

        List<StudentEnrollInfo> studentEnrollInfos = studentEnrollInfoMapper.selectByExample(example);

        if(studentEnrollInfos.size()>0){
            return JsonResult.success(studentEnrollInfos.get(0));
        }else{
            StudentEnrollInfo studentEnrollInfo = new StudentEnrollInfo();

            studentEnrollInfo.setStudentId(studentId);

            studentEnrollInfo.setPhone(String.valueOf(tokenParent.getPhone()));

            studentEnrollInfo.setParentName(tokenParent.getName());

            ManageStudent student = studentMapper.selectByPrimaryKey(studentId);

            studentEnrollInfo.setName(student.getName());

            studentEnrollInfo.setSex(student.getSex());

            studentEnrollInfo.setBirthday(student.getBirthday());

            studentEnrollInfo.setNation(student.getNation());

            studentEnrollInfoMapper.insertSelective(studentEnrollInfo);

            return JsonResult.success(studentEnrollInfo);
        }

    }

    /**
     * Update student enroll info
     *
     * @param enrollInfo
     * @param token
     * @return
     */
    @Override
    public JsonResult updateStudentEnrollInfo(StudentEnrollInfo enrollInfo, String token) {

        TokenParentDTO tokenParentDTO = JwtTokenUtil.getTokenParent(token);

        Long studentId = tokenParentDTO.getStudentId();

        Date date = new Date();
        if(enrollInfo.getId()==0L){

            enrollInfo.setStudentId(studentId);

            enrollInfo.setCreateTime(date);

            enrollInfo.setUpdateTime(date);

            studentEnrollInfoMapper.insertSelective(enrollInfo);
        }else{

            enrollInfo.setUpdateTime(date);

            studentEnrollInfoMapper.updateByPrimaryKeySelective(enrollInfo);
        }


        return JsonResult.success(OperateEnum.SUCCESS);
    }



    /**
     * List my student
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult listMyStudent(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ParentStudentExample parentStudentExample = new ParentStudentExample();

        parentStudentExample.createCriteria().andParentIdEqualTo(tokenParent.getId());

        List<ParentStudent> parentStudents = parentStudentMapper.selectByExample(parentStudentExample);

        List<StudentDTO> list = new ArrayList<>();

        for (ParentStudent parentStudent : parentStudents) {

            StudentDTO studentDTO = personnelService.getStudentDTO(parentStudent.getStudentId());

            studentDTO.setPhone(String.valueOf(tokenParent.getPhone()));

            list.add(studentDTO);
        }

        return JsonResult.success(list);
    }

    /**
     * App scan sign
     *
     * @param sign
     * @param token
     * @return
     */
    @Override
    public JsonResult appScanSign(SignErcodeDTO sign, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);


        //判断 活动是否在进行中
        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(sign.getActivityId());

        if(manageActivity.getStatus()!=1){
            return JsonResult.error("活动未开始");
        }

        //判断当前学生是否报名了

        ManageActivityEnrollRecordExample recordExample = new ManageActivityEnrollRecordExample();

        recordExample.createCriteria()
                .andActivityIdEqualTo(sign.getActivityId())
                .andStudentIdEqualTo(tokenParent.getStudentId())
                .andStatusEqualTo(8);

        long l = enrollRecordMapper.countByExample(recordExample);
        if(l==0){
            return JsonResult.error("您未报名该活动");
        }


        //判断当前 是否在活动有效期

        String validTime = manageActivity.getValidTime();

        String[] split = validTime.split(" - ");

        String nowTimeShort = TimeUtils.getNowTimeShort();

        String beginTimeStr = nowTimeShort+ " " +split[0],endTimeStr = nowTimeShort+ " " +split[1];

        Date beginTime = TimeUtils.getDateFromString(beginTimeStr),endTime = TimeUtils.getDateFromString(endTimeStr);

        Date nowDate = new Date();



        //签到
        if(TimeUtils.Obj1LessObj2(nowDate,beginTime)){
            return JsonResult.error("活动未到开始时间");
        }



        ManageActivitySign manageActivitySign = signMapper.selectByPrimaryKey(sign.getSignId());

        if(sign.getEvent()==1){
            if(manageActivitySign.getSignIn()==0){
                return JsonResult.error("该活动未开启签到功能！");
            }
            if(TimeUtils.Obj1LessObj2(endTime,nowDate)){
                return JsonResult.error("已过活动签到时间");
            }

            //是否已经签到

            ManageActivitySignRecordExample signRecordExample = new ManageActivitySignRecordExample();

            signRecordExample.createCriteria()
                    .andActivityIdEqualTo(sign.getActivityId())
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andSignIdEqualTo(sign.getSignId())
                    .andTypeEqualTo(1)
                    .andGroupDateEqualTo(nowTimeShort);

            long l1 = signRecordMapper.countByExample(signRecordExample);

            if(l1>0){
                return JsonResult.error("您已经签到了,请请勿重复签到哦！");

            }


            ManageActivitySignRecord signRecord = new ManageActivitySignRecord();

            signRecord.setActivityId(sign.getActivityId());

            signRecord.setSignId(sign.getSignId());

            signRecord.setStudentId(tokenParent.getStudentId());

            signRecord.setGroupDate(nowTimeShort);

            signRecord.setSignTime(nowDate);

            signRecord.setType(1);

            signRecordMapper.insertSelective(signRecord);



            return JsonResult.success("签到成功");


        }else{

            if(manageActivitySign.getSignOut()==0){
                return JsonResult.error("该活动未开启签到功能！");
            }

            long signOutTime = manageActivitySign.getSignOutTime();

            Date dateSignOutTime = TimeUtils.getDateAfterMinutes(beginTime, (int) signOutTime);

            if(TimeUtils.Obj1LessObj2(nowDate,dateSignOutTime)){
                return JsonResult.error("未到签退时间！");
            }

            Date dateEndTimeAfter = TimeUtils.getDateAfterMinutes(endTime, 10);

            if(TimeUtils.Obj1LessObj2(dateEndTimeAfter,nowDate)){
                return JsonResult.error("活动已经结束10分钟了，签退不成功啊！");
            }

            //是否已经签退

            ManageActivitySignRecordExample signRecordExample = new ManageActivitySignRecordExample();

            signRecordExample.createCriteria()
                    .andActivityIdEqualTo(sign.getActivityId())
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andSignIdEqualTo(sign.getSignId())
                    .andTypeEqualTo(2)
                    .andGroupDateEqualTo(nowTimeShort);

            long l1 = signRecordMapper.countByExample(signRecordExample);

            if(l1>0){
                return JsonResult.error("您已经签退了,请请勿重复签到哦！");
            }
            ManageActivitySignRecord signRecord = new ManageActivitySignRecord();

            signRecord.setActivityId(sign.getActivityId());

            signRecord.setSignId(sign.getSignId());

            signRecord.setStudentId(tokenParent.getStudentId());

            signRecord.setGroupDate(nowTimeShort);

            signRecord.setSignTime(nowDate);

            signRecord.setType(2);

            signRecordMapper.insertSelective(signRecord);




            return JsonResult.success("签退成功");

        }





    }

    /**
     * App sign record
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult appActivitySignRecord(Long activityId, String token) {


        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);


        SignResultDTO signResultDTO = new SignResultDTO();

        ManageActivity activity = activityMapper.selectByPrimaryKey(activityId);



        signResultDTO.setActivityName(activity.getName());

        String validTime = activity.getValidTime();

        String[] split = validTime.split(" - ");

        String beginHour = split[0].substring(0, 5),endHour = split[1].substring(0, 5);

        signResultDTO.setValidTime(beginHour+" - "+endHour);

        signResultDTO.setDurationTime(TimeUtils.getDateStringShort(activity.getBeginTime())+" - "+TimeUtils.getDateStringShort(activity.getEndTime()));

        StudentDTO studentDTO = personnelService.getStudentDTO(tokenParent.getStudentId());

        signResultDTO.setStudentName(studentDTO.getStudentName());

        signResultDTO.setStudentDesc(studentDTO.getSchoolName()+" - "+studentDTO.getPeriodName()+" - "+studentDTO.getClassName());

        ManageActivitySignRecordExample signRecordExample = new ManageActivitySignRecordExample();

        signRecordExample.createCriteria()
                .andActivityIdEqualTo(activityId)
                .andStudentIdEqualTo(tokenParent.getStudentId());

        signRecordExample.setOrderByClause(" sign_time desc ");

        List<ManageActivitySignRecord> signRecords = signRecordMapper.selectByExample(signRecordExample);

        //处理一下

        Map<String,List<ManageActivitySignRecord>> map = new HashMap<>();

        for (ManageActivitySignRecord signRecord : signRecords) {

            if(map.containsKey(signRecord.getGroupDate())){
                map.get(signRecord.getGroupDate()).add(signRecord);
            }else{
                List<ManageActivitySignRecord> values = new ArrayList<>();
                values.add(signRecord);
                map.put(signRecord.getGroupDate(),values);
            }
        }
        List<SignRecordDTO> list = new ArrayList<>();
        for (String s : map.keySet()) {

            SignRecordDTO signRecordDTO = new SignRecordDTO();

            List<ManageActivitySignRecord> manageActivitySignRecords = map.get(s);

            for (ManageActivitySignRecord manageActivitySignRecord : manageActivitySignRecords) {
                String dateString = TimeUtils.getDateString(manageActivitySignRecord.getSignTime());
                if(manageActivitySignRecord.getType()==1){

                    signRecordDTO.setSignIn(dateString.substring(10, 16));

                }else{
                    signRecordDTO.setSignOut(dateString.substring(10,16));
                }

            }
            ManageActivitySign sign = signMapper.selectByPrimaryKey(manageActivitySignRecords.get(0).getSignId());

            signRecordDTO.setSignMark(sign.getSignIn());

            signRecordDTO.setSignOutMark(sign.getSignOut());

            signRecordDTO.setGroupTime(s);

            list.add(signRecordDTO);

        }

        signResultDTO.setList(list);

        return JsonResult.success(signResultDTO);

    }

    /**
     * Get push tag
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult getNoticeTag(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);


        ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

        linkExample.createCriteria()
                .andParentIdEqualTo(tokenParent.getId())
                .andStatusEqualTo(1)
                .andDelflagEqualTo(0);

        List<ParentActivityLink> activityLinks = parentActivityLinkMapper.selectByExample(linkExample);

        List<Long> activityIds = new ArrayList<>();
        for (ParentActivityLink activityLink : activityLinks) {

            Long activityId = activityLink.getActivityId();

            ManageActivity activity = activityMapper.selectByPrimaryKey(activityId);

            if(activity.getStatus()==6||activity.getStatus()==1||activity.getStatus()==2){
                activityIds.add(activityId);
            }
        }


        return JsonResult.success(activityIds);
    }
}
