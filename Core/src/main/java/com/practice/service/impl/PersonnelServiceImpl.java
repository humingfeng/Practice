package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.*;
import com.practice.enums.AuthEnum;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.*;
import com.practice.utils.*;
import com.practice.vo.ActivityListItemVO;
import com.practice.vo.ActivitySearchVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private ParentMapper parentMapper;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private CacheService cacheService;
    @Resource
    private ParentStudentMapper parentStudentMapper;
    @Resource
    private ParentActivityLinkMapper parentActivityLinkMapper;
    @Resource
    private ActivityService activityService;
    @Resource
    private ParentPhotosMapper parentPhotosMapper;
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

            StudentDTO studentDTO = new StudentDTO();
            ManageStudent student = manageStudents.get(0);

            studentDTO.setStudentId(student.getId());

            studentDTO.setSchoolId(student.getSchoolId());

            studentDTO.setSchoolName(schoolService.getSchoolPO(student.getSchoolId()).getName());

            studentDTO.setClassId(student.getClassId());

            studentDTO.setPeriodName(dictionaryService.getDictionaryPO(student.getPeriodId()).getName());

            studentDTO.setPeriodId(student.getPeriodId());

            studentDTO.setClassName(dictionaryService.getDictionaryPO(student.getClassId()).getName());

            studentDTO.setClassId(student.getClassId());

            studentDTO.setStudentName(student.getName());

            return JsonResult.success(studentDTO);
        }

    }

    /**
     * Register parent
     *
     * @param parent
     * @return
     */
    @Override
    public JsonResult registerParent(Parent parent) {

        ParentExample parentExample = new ParentExample();

        //step1 判断手机号是否重复注册
        parentExample.createCriteria()
                .andPhoneEqualTo(parent.getPhone())
                .andStatusEqualTo(1)
                .andDelflagEqualTo(0);

        long l1 = parentMapper.countByExample(parentExample);
        if(l1>0){
            return JsonResult.error("该手机号已注册");
        }

        //step2 判断当前学生的亲属关系是否注册
        ParentStudentExample parentStudentExample = new ParentStudentExample();

        parentStudentExample.createCriteria()
                .andStudentIdEqualTo(parent.getStudentId())
                .andRelationIdEqualTo(parent.getRelationId());

        long l2 = parentStudentMapper.countByExample(parentStudentExample);

        if(l2>0){
            return JsonResult.error("该学生的该亲属已经注册");
        }

        //step3 插入家长信息

        parent.setId(null);

        parent.setStatus(1);

        parent.setDelflag(0);

        parent.setUpdateTime(new Date());

        parent.setCreateTime(new Date());

        parent.setPassword(CommonUtils.sha256(parent.getPassword()));

        parentMapper.insertSelective(parent);

        //step4 插入家长和学生的关系
        Long parentId = parent.getId();

        ParentStudent parentStudent = new ParentStudent();

        parentStudent.setCreateTime(new Date());

        parentStudent.setStudentId(parent.getStudentId());

        parentStudent.setParentId(parentId);

        parentStudent.setRelationId(parent.getRelationId());

        parentStudent.setIsLogin(1);

        parentStudentMapper.insertSelective(parentStudent);

        return JsonResult.success("注册成功");
    }

    /**
     * Is phone parent exit
     *
     * @return
     * @param phone
     */
    @Override
    public boolean isParentPhoneExit(String phone) {

        if(!ValidatorUtils.isMobile(phone)){
            return false;
        }
        ParentExample parentExample = new ParentExample();

        parentExample.createCriteria()
                .andPhoneEqualTo(Long.valueOf(phone))
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        long l = parentMapper.countByExample(parentExample);

        if(l>0){
            return true;
        }

        return false;
    }

    /**
     * Get parent by phone
     *
     * @param phone
     * @return
     */
    @Override
    public JsonResult getParentByPhone(String phone) {
        if(!ValidatorUtils.isMobile(phone)){
            return JsonResult.error(OperateEnum.PHONE_ERROR);
        }
        ParentExample parentExample = new ParentExample();

        parentExample.createCriteria()
                .andPhoneEqualTo(Long.valueOf(phone))
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        List<Parent> parents = parentMapper.selectByExample(parentExample);

        if(parents.size()==0){
            return JsonResult.error("该手机号从未注册");
        }

        ParentDTO parentDTO = this.getParentDTO(parents.get(0).getId());

        return JsonResult.success(parentDTO);
    }

    /**
     * Reset parent pass
     *
     * @param id
     * @param pass
     * @return
     */
    @Override
    public JsonResult resetParentPass(Long id, String pass) {

        Parent parent = new Parent();

        parent.setPassword(CommonUtils.sha256(pass));

        parent.setId(id);

        parent.setUpdateTime(new Date());

        parentMapper.updateByPrimaryKeySelective(parent);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Parent login check
     *
     * @param phone
     * @param pass
     * @return
     */
    @Override
    public JsonResult checkParentLogin(String phone, String pass) {

        if(!ValidatorUtils.isMobile(phone)){
            return JsonResult.error(OperateEnum.PHONE_ERROR);
        }

        ParentExample parentExample = new ParentExample();

        parentExample.createCriteria()
                .andPhoneEqualTo(Long.valueOf(phone))
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        List<Parent> parents = parentMapper.selectByExample(parentExample);

        if(parents.size()==0){
            return JsonResult.error(AuthEnum.USER_NO_EXIST);
        }

        Parent parent = parents.get(0);

        if(!StringUtils.equals(CommonUtils.sha256(pass),parent.getPassword())){
            return JsonResult.error(AuthEnum.PASS_ERROR);
        }

        ParentDTO parentDTO = this.getParentDTO(parent.getId());

        cacheService.setParent(parentDTO);

        return JsonResult.success(parentDTO);
    }

    /**
     * Get parent DTO
     *
     * @param id
     * @return
     */
    @Override
    public ParentDTO getParentDTO(Long id) {

        Parent parent = parentMapper.selectByPrimaryKey(id);

        ParentStudentExample parentStudentExample = new ParentStudentExample();

        parentStudentExample.createCriteria().andParentIdEqualTo(id).andIsLoginEqualTo(1);

        List<ParentStudent> parentStudents = parentStudentMapper.selectByExample(parentStudentExample);

        ParentStudent parentStudent = parentStudents.get(0);

        ParentDTO parentDTO = new ParentDTO();

        parentDTO.setId(parent.getId());

        parentDTO.setName(parent.getName());

        parentDTO.setStudentId(parentStudent.getStudentId());

        parentDTO.setPhone(parent.getPhone());

        parentDTO.setHeadImg(parent.getHeadImg());

        parentDTO.setRelationId(parentStudent.getRelationId());

        parentDTO.setRelationName(dictionaryService.getDictionaryPO(parentStudent.getRelationId()).getName());

        parentDTO.setStudentName(studentMapper.selectByPrimaryKey(parentStudent.getStudentId()).getName());

        return parentDTO;
    }

    /**
     * Get student DTO
     *
     * @param studentId
     */
    @Override
    public StudentDTO getStudentDTO(Long studentId) {

        ManageStudent student = studentMapper.selectByPrimaryKey(studentId);

        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setStudentId(student.getId());

        studentDTO.setSchoolId(student.getSchoolId());

        studentDTO.setSchoolName(schoolService.getSchoolPO(student.getSchoolId()).getName());

        studentDTO.setClassId(student.getClassId());

        studentDTO.setPeriodName(dictionaryService.getDictionaryPO(student.getPeriodId()).getName());

        studentDTO.setPeriodId(student.getPeriodId());

        studentDTO.setClassName(dictionaryService.getDictionaryPO(student.getClassId()).getName());

        studentDTO.setClassId(student.getClassId());

        studentDTO.setStudentName(student.getName());

        return studentDTO;
    }

    /**
     * Update parent push id
     *
     * @param token
     * @param pushId
     * @return
     */
    @Override
    public JsonResult updateParentPushId(String token, String pushId) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        Parent parent = new Parent();

        parent.setId(tokenParent.getId());

        parent.setPushId(pushId);

        parent.setUpdateTime(new Date());

        parentMapper.updateByPrimaryKeySelective(parent);

        return JsonResult.success(OperateEnum.SUCCESS);
    }


    /**
     * List parent
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listParent(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());


        String key1="name",key2 = "phone";

        ParentExample parentExample = new ParentExample();

        ParentExample.Criteria criteria = parentExample.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(key1)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key1)));
        }
        if(param.getFiled(key2)!=null){
            criteria.andPhoneEqualTo(Long.valueOf(param.getFiled(key2)));
        }
        parentExample.setOrderByClause("create_time desc");

        List<Parent> parents = parentMapper.selectByExample(parentExample);

        PageInfo<Parent> parentPageInfo = new PageInfo<>(parents);

        PageResult<Parent> parentPageResult = new PageResult<>(parentPageInfo);

        return JsonResult.success(parentPageResult);
    }

    /**
     * Get parent
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getParent(Long id) {
        return null;
    }

    /**
     * Update parent
     *
     * @param parent
     * @param token
     * @return
     */
    @Override
    public JsonResult updateParent(Parent parent, String token) {

        parentMapper.updateByPrimaryKeySelective(parent);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del parent
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult delParent(Long id, String token) {
        return null;
    }

    /**
     * List parent student
     *
     * @param parentId
     * @return
     */
    @Override
    public JsonResult listParentStudent(Long parentId) {

        ParentStudentExample parentStudentExample = new ParentStudentExample();

        parentStudentExample.createCriteria().andParentIdEqualTo(parentId);

        List<ParentStudent> parentStudents = parentStudentMapper.selectByExample(parentStudentExample);

        Parent parent = parentMapper.selectByPrimaryKey(parentId);

        ParentStudentDTO parentStudentDTO = new ParentStudentDTO();

        parentStudentDTO.setParentId(parentId);

        parentStudentDTO.setParentName(parent.getName());

        parentStudentDTO.setPhone(parent.getPhone());

        List<ParentStudentItemDTO> list = new ArrayList<>();

        for (ParentStudent parentStudent : parentStudents) {

            ManageStudent student = studentMapper.selectByPrimaryKey(parentStudent.getStudentId());

            ParentStudentItemDTO itemDTO = new ParentStudentItemDTO();

            itemDTO.setBindTime(TimeUtils.getDateString(parentStudent.getCreateTime()));

            itemDTO.setStudentName(student.getName());

            itemDTO.setSchoolName(schoolService.getSchoolPO(student.getSchoolId()).getName());

            itemDTO.setPeriodName(dictionaryService.getDictionaryPO(student.getPeriodId()).getName());

            itemDTO.setClassName(dictionaryService.getDictionaryPO(student.getClassId()).getName());

            itemDTO.setRelation(dictionaryService.getDictionaryPO(parentStudent.getRelationId()).getName());

            list.add(itemDTO);
        }

        parentStudentDTO.setList(list);

        return JsonResult.success(parentStudentDTO);
    }

    /**
     * Get parent PO
     *
     * @param userId
     * @return
     */
    @Override
    public Parent getParentPO(Long userId) {
        return parentMapper.selectByPrimaryKey(userId);
    }

    /**
     * List parent enroll activity
     *
     * @param token
     * @param pageIndex
     * @return
     */
    @Override
    public JsonResult listParentEnrollActivity(String token, int pageIndex) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        PageHelper.startPage(pageIndex,10);

        ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

        linkExample.createCriteria()
                .andParentIdEqualTo(tokenParent.getId())
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        linkExample.setOrderByClause(" create_time desc ");

        List<ParentActivityLink> parentActivityLinks = parentActivityLinkMapper.selectByExample(linkExample);

        PageInfo<ParentActivityLink> pageInfo = new PageInfo<>(parentActivityLinks);

        List<ActivityListItemVO> list = new ArrayList<>();

        for (ParentActivityLink parentActivityLink : parentActivityLinks) {

            Long activityId = parentActivityLink.getActivityId();

            ActivityListItemVO itemVO = activityService.getActivityListItemDTO(activityId);

            list.add(itemVO);

        }

        PageResult<ActivityListItemVO> pageResult = new PageResult<>();

        pageResult.setList(list);

        pageResult.setPageNum(pageIndex);

        pageResult.setTotal((int) pageInfo.getTotal());

        pageResult.setPages(pageInfo.getPages());

        return JsonResult.success(pageResult);
    }

    /**
     * List photos
     *
     * @param token
     * @param pageIndex
     * @return
     */
    @Override
    public JsonResult listPhotos(String token, Integer pageIndex) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);


        PageHelper.startPage(pageIndex,20);

        ParentPhotosExample photosExample = new ParentPhotosExample();

        photosExample.createCriteria()
                .andUserIdEqualTo(tokenParent.getId());

        photosExample.setOrderByClause("create_time desc");

        List<ParentPhotos> parentPhotos = parentPhotosMapper.selectByExample(photosExample);

        List<PhotoDTO> list = new ArrayList<>();

        Map<String,PhotoDTO> map = new LinkedHashMap<>();

        for (ParentPhotos parentPhoto : parentPhotos) {

            String dateString = TimeUtils.getDateString(parentPhoto.getCreateTime());

            if(map.containsKey(dateString)){
                map.get(dateString).getImg().add(parentPhoto.getPhoto());
            }else{

                PhotoDTO photoDTO = new PhotoDTO();

                photoDTO.setId(parentPhoto.getId());

                photoDTO.setDesc(parentPhoto.getDescription());

                List<String> imgs = new ArrayList<>();

                imgs.add(parentPhoto.getPhoto());

                photoDTO.setImg(imgs);

                photoDTO.setTime(TimeUtils.getBeforeNowString(parentPhoto.getCreateTime()));

                map.put(dateString,photoDTO);
            }
        }

        for (PhotoDTO photoDTO : map.values()) {

            list.add(photoDTO);
        }

        PageInfo<ParentPhotos> parentPhotosPageInfo = new PageInfo<>(parentPhotos);

        PageResult<PhotoDTO> result = new PageResult<>();

        result.setList(list);

        result.setPages(parentPhotosPageInfo.getPages());

        return JsonResult.success(result);
    }
}
