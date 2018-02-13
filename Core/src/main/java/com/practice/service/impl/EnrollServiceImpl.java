package com.practice.service.impl;

import com.practice.dto.SignErcodeDTO;
import com.practice.dto.SignResultDTO;
import com.practice.dto.StudentDTO;
import com.practice.dto.TokenParentDTO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private ParentActivityLinkMapper parentActivityLinkMapper;
    @Resource
    private ParentStudentMapper parentStudentMapper;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private ManageActivitySignMapper signMapper;
    @Resource
    private ManageActivitySignRecordMapper signRecordMapper;
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

            ManageStudent student = studentMapper.selectByPrimaryKey(tokenParent.getStudentId());

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

        ManageActivitySign manageActivitySign = signMapper.selectByPrimaryKey(sign.getSignId());

        if(sign.getEvent()==1){
            if(manageActivitySign.getSignIn()==0){
                return JsonResult.error("该活动未开启签到功能！");
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

            if(l1==0){

                //签到
                if(!TimeUtils.Obj1LessObj2(beginTime,nowDate)){
                    return JsonResult.error("活动未到开始时间");
                }

                ManageActivitySignRecord signRecord = new ManageActivitySignRecord();

                signRecord.setActivityId(sign.getActivityId());

                signRecord.setSignId(sign.getSignId());

                signRecord.setStudentId(tokenParent.getStudentId());

                signRecord.setGroupDate(nowTimeShort);

                signRecord.setSignTime(nowDate);

                signRecord.setType(1);

                signRecordMapper.insertSelective(signRecord);


            }



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

            if(l1==0){
                ManageActivitySignRecord signRecord = new ManageActivitySignRecord();

                signRecord.setActivityId(sign.getActivityId());

                signRecord.setSignId(sign.getSignId());

                signRecord.setStudentId(tokenParent.getStudentId());

                signRecord.setGroupDate(nowTimeShort);

                signRecord.setSignTime(nowDate);

                signRecord.setType(2);

                signRecordMapper.insertSelective(signRecord);
            }




        }

        SignResultDTO signResultDTO = new SignResultDTO();

        signResultDTO.setActivityName(manageActivity.getName());

        signResultDTO.setBeginTime(split[0]);

        signResultDTO.setEndTime(split[1]);

        signResultDTO.setGroupTime(nowTimeShort);

        if(sign.getEvent()==1){
            signResultDTO.setSignTime(TimeUtils.getDateString(nowDate));
            signResultDTO.setSignOutTime("");
        }else{

            ManageActivitySignRecordExample signRecordExample = new ManageActivitySignRecordExample();

            signRecordExample.createCriteria()
                    .andActivityIdEqualTo(sign.getActivityId())
                    .andStudentIdEqualTo(tokenParent.getStudentId())
                    .andSignIdEqualTo(sign.getSignId())
                    .andTypeEqualTo(1)
                    .andGroupDateEqualTo(nowTimeShort);

            List<ManageActivitySignRecord> signRecordList = signRecordMapper.selectByExample(signRecordExample);

            if(signRecordList.size()>0){
                ManageActivitySignRecord manageActivitySignRecord = signRecordList.get(0);

                signResultDTO.setSignTime(TimeUtils.getDateString(manageActivitySignRecord.getSignTime()));
            }else{
                signResultDTO.setSignTime("");
            }
            signResultDTO.setSignOutTime(TimeUtils.getDateString(nowDate));
        }


        StudentDTO studentDTO = personnelService.getStudentDTO(tokenParent.getStudentId());

        signResultDTO.setStudentName(studentDTO.getStudentName());

        signResultDTO.setStudentDesc(studentDTO.getSchoolName()+"|"+studentDTO.getPeriodName()+"|"+studentDTO.getClassName());


        return JsonResult.success(signResultDTO);
    }
}
