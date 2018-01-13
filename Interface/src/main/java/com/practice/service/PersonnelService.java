package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageStudent;
import com.practice.po.ManageTeacher;
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
}
