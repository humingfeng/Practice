package com.practice.service.impl;

import com.practice.dto.StudentDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.EnrollService;
import com.practice.service.PersonnelService;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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

    /**
     * Get student enroll info
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult getStudentEnrollInfo(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        StudentEnrollInfoExample example = new StudentEnrollInfoExample();

        example.createCriteria().andStudentIdEqualTo(tokenParent.getStudentId());

        List<StudentEnrollInfo> studentEnrollInfos = studentEnrollInfoMapper.selectByExample(example);

        if(studentEnrollInfos.size()>0){
            return JsonResult.success(studentEnrollInfos.get(0));
        }else{
            StudentEnrollInfo studentEnrollInfo = new StudentEnrollInfo();

            studentEnrollInfo.setStudentId(tokenParent.getStudentId());

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
     * Save activity enroll info
     *
     * @param enrollInfo
     * @param token
     * @param activityId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult saveActivityEnrollInfo(StudentEnrollInfo enrollInfo, String token, Long activityId) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivityEnrollRecordExample recordExample = new ManageActivityEnrollRecordExample();

        recordExample.createCriteria().andActivityIdEqualTo(activityId).andStudentIdEqualTo(tokenParent.getStudentId());

        long l = enrollRecordMapper.countByExample(recordExample);

        if(l>0){
            return JsonResult.error("您已经报名，请勿重复报名");
        }

        ManageStudent manageStudent = studentMapper.selectByPrimaryKey(tokenParent.getStudentId());

        ManageActivityEnrollRecord enrollRecord = new ManageActivityEnrollRecord();

        enrollRecord.setActivityId(activityId);

        enrollRecord.setSchoolId(manageStudent.getSchoolId());

        enrollRecord.setPeriodId(manageStudent.getPeriodId());

        enrollRecord.setClassId(manageStudent.getClassId());

        enrollRecord.setStudentId(tokenParent.getStudentId());

        enrollRecord.setName(enrollInfo.getName());

        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(activityId);

        Date date = new Date();
        int mark = 0;
        if(manageActivity.getMoney().compareTo(new BigDecimal(0))==0){
            //免费
            enrollRecord.setStatus(8);

        }else{
            enrollRecord.setStatus(9);
            mark = 1;
            //TODO 创建订单信息
            //创建订单信息
        }
        enrollRecord.setUpdateTime(date);

        enrollRecord.setUpdateUser(tokenParent.getId());

        enrollRecordMapper.insertSelective(enrollRecord);


        //记录



        ParentActivityLink parentActivityLink = new ParentActivityLink();

        parentActivityLink.setActivityId(activityId);
        parentActivityLink.setParentId(tokenParent.getId());
        parentActivityLink.setStudentId(tokenParent.getStudentId());

        parentActivityLink.setUpdateTime(date);
        parentActivityLink.setCreateTime(date);

        parentActivityLink.setDelflag(0);

        parentActivityLinkMapper.insertSelective(parentActivityLink);

        //更新报名信息

        enrollInfo.setUpdateTime(date);
        studentEnrollInfoMapper.updateByPrimaryKeySelective(enrollInfo);


        return JsonResult.success(mark);
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

            list.add(studentDTO);
        }

        return JsonResult.success(list);
    }
}
