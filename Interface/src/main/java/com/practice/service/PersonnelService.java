package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.dto.ParentDTO;
import com.practice.dto.StudentDTO;
import com.practice.dto.VerifyStudentDTO;
import com.practice.po.ManageStudent;
import com.practice.po.ManageTeacher;
import com.practice.po.Parent;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2018/1/11 21:29
 */
public interface PersonnelService {
    /**
     * List Teacher
     * @param param
     * @return
     */
    JsonResult listTeacher(PageSearchParam param);

    /**
     * Get Teacher
     * @param id
     * @return
     */
    JsonResult getTeacher(Long id);

    /**
     * Get Teacher PO
     * @param id
     * @return
     */
    ManageTeacher getTeacherPO(Long id);

    /**
     * Add Teacher
     * @param token
     * @param teacher
     * @return
     */
    JsonResult addTeacher(String token, ManageTeacher teacher);

    /**
     * Update Teacher
     * @param token
     * @param teacher
     * @return
     */
    JsonResult updateTeacher(String token, ManageTeacher teacher);

    /**
     * Del Teacher
     * @param token
     * @param id
     * @return
     */
    JsonResult delTeacher(String token, Long id);

    /**
     * Add teacher list
     * @param token
     * @param schoolId
     * @param rows
     * @return
     */
    JsonResult addTeacherList(String token, String schoolId, String rows);

    /**
     * List student
     * @param param
     * @return
     */
    JsonResult listStudent(PageSearchParam param);

    /**
     * Get student
     * @param id
     * @return
     */
    JsonResult getStudent(Long id);

    /**
     * Get student PO
     * @param id
     * @return
     */
    ManageStudent getStudentPO(Long id);
    /**
     * Add student
     * @param token
     * @param student
     * @return
     */
    JsonResult addStudent(String token, ManageStudent student);

    /**
     * Update student
     * @param token
     * @param student
     * @return
     */
    JsonResult updateStudent(String token, ManageStudent student);

    /**
     * Del student
     * @param token
     * @param id
     * @return
     */
    JsonResult delStudent(String token, Long id);

    /**
     * Add student list
     * @param token
     * @param schoolId
     * @param periodId
     * @param classId
     * @param rows
     * @return
     */
    JsonResult addStudentList(String token, String schoolId, String periodId, String classId, String rows);

    /**
     * Verify student
     * @param verifyStudentDTO
     * @return
     */
    JsonResult verifyStudent(VerifyStudentDTO verifyStudentDTO);

    /**
     * Register parent
     * @param parent
     * @return
     */
    JsonResult registerParent(Parent parent);

    /**
     * Is phone parent exit
     * @return
     * @param phone
     */
    boolean isParentPhoneExit(String phone);

    /**
     * Get parent by phone
     * @param phone
     * @return
     */
    JsonResult getParentByPhone(String phone);

    /**
     * Reset parent pass
     * @param id
     * @param pass
     * @return
     */
    JsonResult resetParentPass(Long id, String pass);

    /**
     * Parent login check
     * @param phone
     * @param pass
     * @return
     */
    JsonResult checkParentLogin(String phone, String pass);

    /**
     * Get parent DTO
     * @param id
     * @return
     */
    ParentDTO getParentDTO(Long id);

    /**
     * Get student DTO
     * @param studentId
     */
    StudentDTO getStudentDTO(Long studentId);

    /**
     * Update parent push id
     * @param token
     * @param pushId
     * @return
     */
    JsonResult updateParentPushId(String token, String pushId);

    /**
     * List parent
     * @param param
     * @return
     */
    JsonResult listParent(PageSearchParam param);

    /**
     * Get parent
     * @param id
     * @return
     */
    JsonResult getParent(Long id);

    /**
     * Update parent
     * @param parent
     * @param token
     * @return
     */
    JsonResult updateParent(Parent parent, String token);

    /**
     * Del parent
     * @param id
     * @param token
     * @return
     */
    JsonResult delParent(Long id, String token);

    /**
     * List parent student
     * @param parentId
     * @return
     */
    JsonResult listParentStudent(Long parentId);

    /**
     * Get parent PO
     * @param userId
     * @return
     */
    Parent getParentPO(Long userId);

    /**
     * List parent enroll activity
     * @param token
     * @param pageIndex
     * @return
     */
    JsonResult listParentEnrollActivity(String token, int pageIndex);
}
