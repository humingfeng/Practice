package com.practice.manage.controller;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageActivityClassify;
import com.practice.po.ManageActivityTheme;
import com.practice.po.ManageActivityType;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Xushd  2017/12/25 20:40
 */
@Controller
public class ActivityController {

    @Resource
    private ActivityService activityService;

    /**
     * Activity Type index
     * @return
     */
    @RequestMapping(value = "/auth/activity/type")
    public String indexType(){

        return "activity/type";
    }

    /**
     * Activity type list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/list")
    @ResponseBody
    public JsonResult ajaxTypeList(PageSearchParam param){

        return activityService.listType(param);
    }

    /**
     * Activity type add
     * @param token
     * @param activityType
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/add")
    @ResponseBody
    public JsonResult ajaxTypeAdd(@RequestAttribute String token, ManageActivityType activityType){
        return activityService.addType(token,activityType);
    }

    /**
     * Activity type update
     * @param token
     * @param activityType
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/update")
    @ResponseBody
    public JsonResult ajaxTypeUpdate(@RequestAttribute String token,ManageActivityType activityType){
        return activityService.updateType(token,activityType);
    }

    /**
     * Activity type delete
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/delete/{id}")
    @ResponseBody
    public JsonResult ajaxTypeDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteType(token,id);
    }

    /**
     * Activity type
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/{id}")
    @ResponseBody
    public JsonResult ajaxType(@PathVariable Long id){
        return activityService.getType(id);
    }

    /**
     * Activity type usable list
     * @return
     */
    @RequestMapping(value = "/auth/activity/type/usable")
    @ResponseBody
    public JsonResult ajaxTypeUsable(){
        return activityService.listTypeUsable();
    }

    /**
     * Activity classify index
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify")
    public String indexClassify(){

        return "activity/classify";
    }

    /**
     * Activity Classify list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/list")
    @ResponseBody
    public JsonResult ajaxClassifyList(PageSearchParam param){

        return activityService.listClassify(param);
    }

    /**
     * Activity classify add
     * @param token
     * @param activityClassify
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/add")
    @ResponseBody
    public JsonResult ajaxClassifyAdd(@RequestAttribute String token, ManageActivityClassify activityClassify){
        return activityService.addClassify(token,activityClassify);
    }

    /**
     * Activity classify update
     * @param token
     * @param activityClassify
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/update")
    @ResponseBody
    public JsonResult ajaxClassifyUpdate(@RequestAttribute String token,ManageActivityClassify activityClassify){
        return activityService.updateClassify(token,activityClassify);
    }

    /**
     * Activity classify delete
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/delete/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteClassify(token,id);
    }

    /**
     * Activity classify
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/{id}")
    @ResponseBody
    public JsonResult ajaxClassify(@PathVariable Long id){
        return activityService.getClassify(id);
    }

    /**
     * Activity classify usable list
     * @return
     */
    @RequestMapping(value = "/auth/activity/classify/usable/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyUsable(@PathVariable Long id){
        return activityService.listClassifyUsable(id);
    }



    /**
     * Activity theme
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme")
    public String indexTheme(){

        return "activity/theme";
    }

    /**
     * Activity theme list
     * @param param
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/list")
    @ResponseBody
    public JsonResult ajaxThemeList(PageSearchParam param){

        return activityService.listTheme(param);
    }

    /**
     * Activity theme add
     * @param token
     * @param activityTheme
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/add")
    @ResponseBody
    public JsonResult ajaxThemeAdd(@RequestAttribute String token, ManageActivityTheme activityTheme){
        return activityService.addTheme(token,activityTheme);
    }

    /**
     * Activity theme update
     * @param token
     * @param activityTheme
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/update")
    @ResponseBody
    public JsonResult ajaxThemeUpdate(@RequestAttribute String token,ManageActivityTheme activityTheme){
        return activityService.updateTheme(token,activityTheme);
    }

    /**
     * Activity theme delete
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/delete/{id}")
    @ResponseBody
    public JsonResult ajaxThemeDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteTheme(token,id);
    }

    /**
     * Activity theme
     * @param id
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/{id}")
    @ResponseBody
    public JsonResult ajaxTheme(@PathVariable Long id){
        return activityService.getTheme(id);
    }

    /**
     * Activity theme usable list
     * @return
     */
    @RequestMapping(value = "/auth/activity/theme/usable/{id}")
    @ResponseBody
    public JsonResult ajaxThemeUsable(@PathVariable Long id){
        return activityService.listThemeUsable(id);
    }

}
