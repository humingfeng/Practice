package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.*;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageStudentMapper;
import com.practice.mapper.ManageTeacherMapper;
import com.practice.po.ManageStudent;
import com.practice.po.ManageStudentExample;
import com.practice.po.ManageTeacher;
import com.practice.po.ManageTeacherExample;
import com.practice.result.JsonResult;
import com.practice.service.DictionaryService;
import com.practice.service.PersonnelService;
import com.practice.service.SchoolService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Personnel service
 * @author Xushd  2018/1/11 21:29
 */
@Service
public class PersonnelServiceImpl implements PersonnelService {

    @Resource
    private ManageTeacherMapper teacherMapper;
    @Resource
    private ManageStudentMapper studentMapper;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private SchoolService schoolService;

    /**
     * List Teacher
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listTeacher(PageSearchParam param) {

        String key1 = "schoolId",key2 = "periodId",key3 = "name";

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        ManageTeacherExample example = new ManageTeacherExample();

        ManageTeacherExample.Criteria criteria = example.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(key1)!=null){
            criteria.andSchoolIdEqualTo(Long.valueOf(param.getFiled(key1)));
        }

        if(param.getFiled(key2)!=null){
            criteria.andPeriodIdEqualTo(Long.valueOf(param.getFiled(key2)));
        }
        if(param.getFiled(key3)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key3)));
        }
        example.setOrderByClause("id desc");

        List<ManageTeacher> manageTeachers = teacherMapper.selectByExample(example);
        for (ManageTeacher manageTeacher : manageTeachers) {

            manageTeacher.setSchoolName(schoolService.getSchoolPO(manageTeacher.getSchoolId()).getName());

            if(manageTeacher.getPeriodId()!=0L){
                manageTeacher.setPeriodName(dictionaryService.getDictionaryPO(manageTeacher.getPeriodId()).getName());
            }

            if(manageTeacher.getClassId()!=0L){
                manageTeacher.setClassName(dictionaryService.getDictionaryPO(manageTeacher.getClassId()).getName());
            }

            manageTeacher.setType(dictionaryService.getDictionaryPO(manageTeacher.getClassifyId()).getName());

        }

        PageInfo<ManageTeacher> manageTeacherPageInfo = new PageInfo<>(manageTeachers);

        return JsonResult.success(manageTeacherPageInfo);
    }

    /**
     * Get Teacher PO
     *
     * @param id
     * @return
     */
    @Override
    public ManageTeacher getTeacherPO(Long id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    /**
     * Get Teacher
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getTeacher(Long id) {

        ManageTeacher manageTeacher = getTeacherPO(id);

        return JsonResult.success(manageTeacher);
    }

    /**
     * Add Teacher
     *
     * @param token
     * @param teacher
     * @return
     */
    @Override
    public JsonResult addTeacher(String token, ManageTeacher teacher) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageTeacherExample example = new ManageTeacherExample();

        example.createCriteria().andDelflagEqualTo(0).andPhoneEqualTo(teacher.getPhone());

        long l = teacherMapper.countByExample(example);

        if(l>0){
            return JsonResult.error("手机号重复，请检查");
        }


        teacher.setId(null);

        teacher.setStatus(1);

        teacher.setDelflag(0);

        teacher.setUpdateTime(new Date());

        teacher.setUpdateUser(tokeUser.getId());

        teacherMapper.insertSelective(teacher);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update Teacher
     *
     * @param token
     * @param teacher
     * @return
     */
    @Override
    public JsonResult updateTeacher(String token, ManageTeacher teacher) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(teacher.getPhone()!=null){
            ManageTeacherExample example = new ManageTeacherExample();

            example.createCriteria()
                    .andDelflagEqualTo(0)
                    .andPhoneEqualTo(teacher.getPhone())
                    .andIdNotEqualTo(teacher.getId());

            long l = teacherMapper.countByExample(example);

            if(l>0){
                return JsonResult.error("手机号重复，请检查");
            }
        }

        teacher.setUpdateUser(tokeUser.getId());

        teacher.setUpdateTime(new Date());

        teacherMapper.updateByPrimaryKeySelective(teacher);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del Teacher
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult delTeacher(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageTeacher manageTeacher = new ManageTeacher();

        manageTeacher.setId(id);

        manageTeacher.setDelflag(1);

        manageTeacher.setUpdateTime(new Date());

        manageTeacher.setUpdateUser(tokeUser.getId());

        teacherMapper.updateByPrimaryKeySelective(manageTeacher);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Add teacher list
     *
     * @param token
     * @param schoolId
     * @param rows
     * @return
     */
    @Override
    public JsonResult addTeacherList(String token, String schoolId, String rows) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        List<TeacherExcelDTO> teacherExcelDTOS = JsonUtils.jsonToList(rows, TeacherExcelDTO.class);

        ManageTeacher teacher = new ManageTeacher();


        ManageTeacherExample teacherExample = new ManageTeacherExample();

        int count = 0;
        for (TeacherExcelDTO teacherExcelDTO : teacherExcelDTOS) {

            teacher.setId(null);

            teacher.setName(teacherExcelDTO.getName());

            teacher.setSchoolId(Long.valueOf(schoolId));

            switch (teacherExcelDTO.getSex()){
                case "男":
                    teacher.setSex(1);
                    break;
                case "女":
                    teacher.setSex(2);
                    break;
                default:
                    teacher.setSex(1);
                    break;
            }

            if(StringUtils.isNotBlank(teacherExcelDTO.getIdNum())){
                teacher.setIdNum(teacherExcelDTO.getIdNum());
            }

            if(StringUtils.isNotBlank(teacherExcelDTO.getPhone())){

                try{
                    Long phone = Long.valueOf(teacherExcelDTO.getPhone());

                    teacherExample.clear();

                    teacherExample.createCriteria().andPhoneEqualTo(phone).andDelflagEqualTo(0);

                    long l = teacherMapper.countByExample(teacherExample);

                    if(l>0){
                        continue;
                    }

                    teacher.setPhone(phone);

                }catch (Exception e){
                    continue;
                }

            }else{
                continue;
            }

            teacher.setClassId(0L);

            teacher.setPeriodId(0L);

            teacher.setClassifyId(83L);

            teacher.setStatus(1);

            teacher.setDelflag(0);

            teacher.setUpdateTime(new Date());

            teacher.setUpdateUser(tokeUser.getId());

            int i = teacherMapper.insertSelective(teacher);

            if(i>0){
                count++;
            }

            teacher = new ManageTeacher();
        }


        return JsonResult.success("成功导入"+count+"条数据");
    }

    /**
     * List student
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listStudent(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        String key1="schoolId",key2="periodId",key3="classId",key4="name",key5="enuNum";

        ManageStudentExample example = new ManageStudentExample();

        ManageStudentExample.Criteria criteria = example.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(key1)!=null){
            criteria.andSchoolIdEqualTo(Long.valueOf(param.getFiled(key1)));
        }

        if(param.getFiled(key2)!=null){
            criteria.andPeriodIdEqualTo(Long.valueOf(param.getFiled(key2)));
        }

        if(param.getFiled(key3)!=null){
            criteria.andClassIdEqualTo(Long.valueOf(param.getFiled(key3)));
        }

        if(param.getFiled(key4)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key4)));
        }

        if(param.getFiled(key5)!=null){
            criteria.andEnuNumEqualTo(param.getFiled(key5));
        }

        example.setOrderByClause("id desc");

        List<ManageStudent> manageStudents = studentMapper.selectByExample(example);

        for (ManageStudent manageStudent : manageStudents) {

            String schoolName = schoolService.getSchoolPO(manageStudent.getSchoolId()).getName();
            manageStudent.setSchoolName(schoolName);

            String periodName = dictionaryService.getDictionaryPO(manageStudent.getPeriodId()).getName();
            manageStudent.setPeriodName(periodName);

            String className = dictionaryService.getDictionaryPO(manageStudent.getClassId()).getName();
            manageStudent.setClassName(className);

        }

        PageInfo<ManageStudent> manageStudentPageInfo = new PageInfo<>(manageStudents);

        return JsonResult.success(manageStudentPageInfo);
    }

    /**
     * Get student
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getStudent(Long id) {

        ManageStudent studentPO = getStudentPO(id);

        return JsonResult.success(studentPO);
    }

    /**
     * Get student PO
     *
     * @param id
     * @return
     */
    @Override
    public ManageStudent getStudentPO(Long id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    /**
     * Add student
     *
     * @param token
     * @param student
     * @return
     */
    @Override
    public JsonResult addStudent(String token, ManageStudent student) {
        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageStudentExample example = new ManageStudentExample();

        example.createCriteria().andDelflagEqualTo(0).andEnuNumEqualTo(student.getEnuNum());

        long l = studentMapper.countByExample(example);

        if(l>0){
            return JsonResult.error("学籍号重复，请检查");
        }

        student.setId(null);

        student.setUpdateTime(new Date());
        student.setStatus(1);
        student.setDelflag(0);
        student.setUpdateUser(tokeUser.getId());

        studentMapper.insertSelective(student);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update student
     *
     * @param token
     * @param student
     * @return
     */
    @Override
    public JsonResult updateStudent(String token, ManageStudent student) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(student.getEnuNum()!=null){
            ManageStudentExample example = new ManageStudentExample();

            example.createCriteria()
                    .andDelflagEqualTo(0)
                    .andEnuNumEqualTo(student.getEnuNum())
                    .andIdNotEqualTo(student.getId());

            long l = studentMapper.countByExample(example);

            if(l>0){
                return JsonResult.error("学籍号重复，请检查");
            }
        }

        student.setUpdateUser(tokeUser.getId());
        student.setUpdateTime(new Date());

        studentMapper.updateByPrimaryKeySelective(student);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del student
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult delStudent(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageStudent manageStudent = new ManageStudent();

        manageStudent.setId(id);

        manageStudent.setDelflag(1);

        manageStudent.setUpdateTime(new Date());

        manageStudent.setUpdateUser(tokeUser.getId());

        studentMapper.updateByPrimaryKeySelective(manageStudent);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Add student list
     *
     * @param token
     * @param schoolId
     * @param periodId
     * @param classId
     * @param rows
     * @return
     */
    @Override
    public JsonResult addStudentList(String token, String schoolId, String periodId, String classId, String rows) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);


        List<StudentExcelDTO> studentExcelDTOS = JsonUtils.jsonToList(rows, StudentExcelDTO.class);

        ManageStudent student = new ManageStudent();

        ManageStudentExample example = new ManageStudentExample();

        int count = 0;

        for (StudentExcelDTO studentExcelDTO : studentExcelDTOS) {

            String enuNum = studentExcelDTO.getEnuNum();

            if(StringUtils.isNotBlank(enuNum)){

                example.clear();
                example.createCriteria().andEnuNumEqualTo(enuNum).andDelflagEqualTo(0);

                long l = studentMapper.countByExample(example);

                if(l>0){
                    continue;
                }

            }else{

                continue;
            }

            student.setId(null);

            student.setName(studentExcelDTO.getName());

            student.setSchoolId(Long.valueOf(schoolId));

            student.setClassId(Long.valueOf(classId));

            student.setPeriodId(Long.valueOf(periodId));

            student.setEnuNum(studentExcelDTO.getEnuNum());

            switch (studentExcelDTO.getSex()){
                case "男":
                    student.setSex(1);
                    break;
                case "女":
                    student.setSex(2);
                    break;
                default:
                    student.setSex(1);
                    break;
            }

            if(StringUtils.isNotBlank(studentExcelDTO.getIdNum())){
                student.setIdNum(studentExcelDTO.getIdNum());
            }

            if(StringUtils.isNotBlank(studentExcelDTO.getBirthday())&& TimeUtils.isDataFormat(studentExcelDTO.getBirthday())){
                student.setBirthday(studentExcelDTO.getBirthday());
            }


            student.setUpdateUser(tokeUser.getId());

            student.setUpdateTime(new Date());

            int i = studentMapper.insertSelective(student);

            if(i>0){
                count++;
            }
            student = new ManageStudent();
        }

        return JsonResult.success("成功导入"+count+"条数据");
    }

    /**
     * Verify student
     *
     * @param verifyStudentDTO
     * @return
     */
    @Override
    public JsonResult verifyStudent(VerifyStudentDTO verifyStudentDTO) {


        ManageStudentExample studentExample = new ManageStudentExample();

        studentExample.createCriteria()
                .andSchoolIdEqualTo(verifyStudentDTO.getSid())
                .andPeriodIdEqualTo(verifyStudentDTO.getPeriodId())
                .andClassIdEqualTo(verifyStudentDTO.getClassId())
                .andNameEqualTo(verifyStudentDTO.getName())
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        List<ManageStudent> manageStudents = studentMapper.selectByExample(studentExample);

        if(manageStudents.size()==0){
            return JsonResult.error("无此学生，请核对信息");
        }else{
            return JsonResult.success(manageStudents.get(0).getId());
        }

    }
}
