package com.practice.manage.controller;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.po.ManageStudent;
import com.practice.po.ManageTeacher;
import com.practice.result.JsonResult;
import com.practice.service.PersonnelService;
import com.practice.service.SchoolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Personnel controller
 * @author Xushd  2018/1/11 21:26
 */
@RequestMapping(value = "/auth/personnel")
@Controller
public class PersonnelController {

    @Resource
    private PersonnelService personnelService;
    @Resource
    private SchoolService schoolService;

    @RequestMapping(value = "/teacher")
    public String indexTeacher(Model model){

        List<KeyValueDTO> schoolList = schoolService.listSchoolUsable();

        model.addAttribute("schoolList",schoolList);

        return "personnel/teacher";
    }

    /**
     * Teacher list
     * @param param
     * @return
     */
    @RequestMapping(value = "/teacher/list")
    @ResponseBody
    public JsonResult ajaxTeacherList(PageSearchParam param){
       return personnelService.listTeacher(param);
    }

    /**
     * Teacher PO
     * @param id
     * @return
     */
    @RequestMapping(value = "/teacher/{id}")
    @ResponseBody
    public JsonResult ajaxTeacherObj(@PathVariable Long id){

        return personnelService.getTeacher(id);
    }

    /**
     * Teacher add
     * @param token
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/teacher/add")
    @ResponseBody
    public JsonResult ajaxTeacherAdd(@RequestAttribute String token, ManageTeacher teacher){
        return personnelService.addTeacher(token,teacher);
    }

    /**
     * Teacher update
     * @param token
     * @param teacher
     * @return
     */
    @RequestMapping(value = "/teacher/update")
    @ResponseBody
    public JsonResult ajaxTeacherUpdate(@RequestAttribute String token, ManageTeacher teacher){
        return personnelService.updateTeacher(token,teacher);
    }

    /**
     * Teacher del
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/teacher/del/{id}")
    @ResponseBody
    public JsonResult ajaxTeacherDel(@RequestAttribute String token,@PathVariable Long id){
        return personnelService.delTeacher(token,id);
    }

    /**
     * Teacher export
     * @param token
     * @param schoolId
     * @param rows
     * @return
     */
    @RequestMapping(value = "/teacher/export")
    @ResponseBody
    public JsonResult ajaxTeacherExport(@RequestAttribute String token,
                                        String schoolId,String rows){
        return personnelService.addTeacherList(token,schoolId,rows);
    }

    /**
     * Student index
     * @param model
     * @return
     */
    @RequestMapping(value = "/student")
    public String indexStudent(Model model){

        List<KeyValueDTO> listSchoolUsable = schoolService.listSchoolUsable();

        model.addAttribute("schoolList",listSchoolUsable);



        return "personnel/student";
    }

    /**
     * Student list
     * @param param
     * @return
     */
    @RequestMapping(value = "/student/list")
    @ResponseBody
    public JsonResult ajaxStudentList(PageSearchParam param){

        return personnelService.listStudent(param);
    }

    /**
     * Student obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/student/{id}")
    @ResponseBody
    public JsonResult ajaxStudent(@PathVariable Long id){
        return personnelService.getStudent(id);
    }

    /**
     * Student add
     * @param token
     * @param student
     * @return
     */
    @RequestMapping(value = "/student/add")
    @ResponseBody
    public JsonResult ajaxStudentAdd(@RequestAttribute String token,
                                     ManageStudent student){
        return personnelService.addStudent(token,student);
    }

    /**
     * Student update
     * @param token
     * @param student
     * @return
     */
    @RequestMapping(value = "/student/update")
    @ResponseBody
    public JsonResult ajaxStudentUpdate(@RequestAttribute String token,
                                        ManageStudent student){
        return personnelService.updateStudent(token,student);
    }

    /**
     * Student del
     * @return
     */
    @RequestMapping(value = "/student/del/{id}")
    @ResponseBody
    public JsonResult ajaxStudentDel(@PathVariable Long id,
                                     @RequestAttribute String token){
        return personnelService.delStudent(token,id);
    }

    /**
     * Student add list
     * @param token
     * @param schoolId
     * @param periodId
     * @param classId
     * @param rows
     * @return
     */
    @RequestMapping(value = "/student/export")
    @ResponseBody
    public JsonResult ajaxStudentExport(@RequestAttribute String token,
                                        String schoolId,
                                        String periodId,
                                        String classId,
                                        String rows){
        return personnelService.addStudentList(token,schoolId,periodId,classId,rows);
    }
}
