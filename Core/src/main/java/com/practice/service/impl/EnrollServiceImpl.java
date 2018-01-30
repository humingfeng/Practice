package com.practice.service.impl;

import com.practice.dto.TokenParentDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageStudentMapper;
import com.practice.mapper.StudentEnrollInfoMapper;
import com.practice.po.ManageStudent;
import com.practice.po.StudentEnrollInfo;
import com.practice.po.StudentEnrollInfoExample;
import com.practice.result.JsonResult;
import com.practice.service.EnrollService;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
