package com.practice.manage.controller;

import com.practice.dto.PageSearchParam;
import com.practice.po.Edu;
import com.practice.po.ManageBase;
import com.practice.po.School;
import com.practice.result.JsonResult;
import com.practice.service.BasesService;
import com.practice.service.EduService;
import com.practice.service.SchoolService;
import com.practice.service.AreaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Base info controller
 * @author Xushd on 2017/12/27 15:46
 */
@Controller
public class BaseInfoController {

    @Resource
    private EduService eduService;
    @Resource
    private SchoolService schoolService;
    @Resource
    private AreaService areaService;
    @Resource
    private BasesService basesService;
    /**
     * Edu index
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu")
    public String indexEdu(){
        return "baseinfo/edu";
    }

    /**
     * Edu list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/list")
    @ResponseBody
    public JsonResult ajaxEduList(PageSearchParam param){
        return eduService.listEdu(param);
    }

    /**
     * Edu add
     * @param token
     * @param edu
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/add" +
            "")
    @ResponseBody
    public JsonResult ajaxEduAdd(@RequestAttribute String token, Edu edu){
        return eduService.addEdu(token,edu);
    }

    /**
     * Edu update
     * @param token
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/update")
    @ResponseBody
    public JsonResult ajaxEduUpdate(@RequestAttribute String token,Edu edu){
        return eduService.updateEdu(token,edu);
    }

    /**
     * Edu obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/{id}")
    @ResponseBody
    public JsonResult ajaxEduObj(@PathVariable Long id){
        return eduService.getEdu(id);
    }

    /**
     * Edu usable list
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/usable/list/{pid}/{cid}/{aid}")
    @ResponseBody
    public JsonResult ajaxEduUsableList(@PathVariable Long pid,
                                        @PathVariable Long cid,
                                        @PathVariable Long aid){
        return eduService.listEduUsable(pid,cid,aid);
    }

    /**
     * Edu delete
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/edu/delete/{id}")
    @ResponseBody
    public JsonResult ajaxEduDelete(@RequestAttribute String token,@PathVariable Long id){
        return eduService.deleteEdu(token,id);
    }

    /**
     * School index
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school")
    public String indexSchool(){

        return "baseinfo/school";
    }

    /**
     * School list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school/list")
    @ResponseBody
    public JsonResult ajaxSchoolList(PageSearchParam param){
        return schoolService.listSchool(param);
    }

    /**
     * School obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school/{id}")
    @ResponseBody
    public JsonResult ajaxSchoolObj(@PathVariable Long id){
        return schoolService.getSchool(id);
    }

    /**
     * School add
     * @param token
     * @param school
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school/add")
    @ResponseBody
    public JsonResult ajaxSchoolAdd(@RequestAttribute String token, School school){

        return schoolService.addSchool(token,school);
    }

    /**
     * School update
     * @param token
     * @param school
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school/update")
    @ResponseBody
    public JsonResult ajaxSchoolUpdate(@RequestAttribute String token, School school){
        return schoolService.updateSchool(token,school);
    }

    /**
     * School delete
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/school/delete/{id}")
    @ResponseBody
    public JsonResult ajaxSchoolDelete(@RequestAttribute String token,@PathVariable Long id){
        return schoolService.deleteSchool(token,id);
    }


    /**
     * Province list
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/province/list")
    @ResponseBody
    public JsonResult ajaxProvinceList(){
        return areaService.listProvinceList();
    }

    /**
     * City list by province id
     * @param pid
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/city/list/{pid}")
    @ResponseBody
    public JsonResult ajaxCityList(@PathVariable Long pid){
        return areaService.listCityByPid(pid);
    }

    /**
     * Area list by city id
     * @param cid
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/area/list/{cid}")
    @ResponseBody
    public JsonResult ajaxAreaList(@PathVariable Long cid){
        return areaService.listAreaByCid(cid);
    }


    /**
     * Bases index
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases")
    public String indexBases(){
        return "baseinfo/bases";
    }

    /**
     * Bases list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/list")
    @ResponseBody
    public JsonResult ajaxBasesList(PageSearchParam param){
        return basesService.listBases(param);
    }

    /**
     * Bases add
     * @param token
     * @param base
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/add")
    @ResponseBody
    public JsonResult ajaxBasesAdd(@RequestAttribute String token, ManageBase base){

        return basesService.addBases(token,base);
    }

    /**
     * Bases get
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/{id}")
    @ResponseBody
    public JsonResult ajaxBasesObj(@PathVariable Long id){
        return basesService.getBases(id);
    }

    /**
     * Bases update
     * @param token
     * @param base
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/update")
    @ResponseBody
    public JsonResult ajaxBasesUpdate(@RequestAttribute String token, ManageBase base){

        return basesService.updateBases(token,base);
    }

    /**
     * Bases delete
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/delete/{id}")
    @ResponseBody
    public JsonResult ajaxBasesDelete(@RequestAttribute String token, @PathVariable Long id){

        return basesService.deleteBases(token,id);
    }

    /**
     * Bases list usable
     * @return
     */
    @RequestMapping(value = "/auth/baseinfo/bases/usable")
    @ResponseBody
    public JsonResult ajaxBasesUsable(){

        return basesService.listBasesUsable();
    }
}
