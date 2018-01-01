package com.practice.manage.controller;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.po.ManageActivity;
import com.practice.po.ManageActivityClassify;
import com.practice.po.ManageActivityTheme;
import com.practice.po.ManageActivityType;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.BasesService;
import com.practice.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xushd  2017/12/25 20:40
 */
@RequestMapping(value = "/auth/activity")
@Controller
public class ActivityController {

    @Resource
    private ActivityService activityService;
    @Resource
    private BasesService basesService;

    /**
     * Activity Type index
     * @return
     */
    @RequestMapping(value = "/type")
    public String indexType(){

        return "activity/type";
    }

    /**
     * Activity type list
     * @param param
     * @return
     */
    @RequestMapping(value = "/type/list")
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
    @RequestMapping(value = "/type/add")
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
    @RequestMapping(value = "/type/update")
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
    @RequestMapping(value = "/type/delete/{id}")
    @ResponseBody
    public JsonResult ajaxTypeDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteType(token,id);
    }

    /**
     * Activity type
     * @param id
     * @return
     */
    @RequestMapping(value = "/type/{id}")
    @ResponseBody
    public JsonResult ajaxType(@PathVariable Long id){
        return activityService.getType(id);
    }

    /**
     * Activity type usable list
     * @return
     */
    @RequestMapping(value = "/type/usable")
    @ResponseBody
    public JsonResult ajaxTypeUsable(){
        return activityService.listTypeUsable();
    }

    /**
     * Activity classify index
     * @return
     */
    @RequestMapping(value = "/classify")
    public String indexClassify(){

        return "activity/classify";
    }

    /**
     * Activity Classify list
     * @param param
     * @return
     */
    @RequestMapping(value = "/classify/list")
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
    @RequestMapping(value = "/classify/add")
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
    @RequestMapping(value = "/classify/update")
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
    @RequestMapping(value = "/classify/delete/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteClassify(token,id);
    }

    /**
     * Activity classify
     * @param id
     * @return
     */
    @RequestMapping(value = "/classify/{id}")
    @ResponseBody
    public JsonResult ajaxClassify(@PathVariable Long id){
        return activityService.getClassify(id);
    }

    /**
     * Activity classify usable list
     * @return
     */
    @RequestMapping(value = "/classify/usable/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyUsable(@PathVariable Long id){
        return activityService.listClassifyUsable(id);
    }



    /**
     * Activity theme
     * @return
     */
    @RequestMapping(value = "/theme")
    public String indexTheme(){

        return "activity/theme";
    }

    /**
     * Activity theme list
     * @param param
     * @return
     */
    @RequestMapping(value = "/theme/list")
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
    @RequestMapping(value = "/theme/add")
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
    @RequestMapping(value = "/theme/update")
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
    @RequestMapping(value = "/theme/delete/{id}")
    @ResponseBody
    public JsonResult ajaxThemeDelete(@RequestAttribute String token, @PathVariable Long id){
        return activityService.deleteTheme(token,id);
    }

    /**
     * Activity theme
     * @param id
     * @return
     */
    @RequestMapping(value = "/theme/{id}")
    @ResponseBody
    public JsonResult ajaxTheme(@PathVariable Long id){
        return activityService.getTheme(id);
    }

    /**
     * Activity theme usable list
     * @return
     */
    @RequestMapping(value = "/theme/usable/{id}")
    @ResponseBody
    public JsonResult ajaxThemeUsable(@PathVariable Long id){
        return activityService.listThemeUsable(id);
    }

    /**
     * Activity manage
     * @return
     */
    @RequestMapping(value = "/manage")
    public String indexActivityManage(Model model){

        JsonResult jsonResult = activityService.listTypeUsable();

        List<KeyValueDTO> keyValueDTOS = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResult.getData()), KeyValueDTO.class);

        model.addAttribute("types",keyValueDTOS);


        return "activity/manage";
    }

    /**
     * Activity manage list
     * @param param
     * @return
     */
    @RequestMapping(value = "/manage/list")
    @ResponseBody
    public JsonResult ajaxActivityManageList(PageSearchParam param){
        return activityService.listManage(param);
    }

    /**
     * Activity manage handle
     * @return
     */
    @RequestMapping(value = "/manage/base/handle")
    public String indexActivityBaseAdd(Long id,Model model){

        JsonResult jsonResultTypes = activityService.listTypeUsable();

        List<KeyValueDTO> keyValueDTOSTypes = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResultTypes.getData()), KeyValueDTO.class);

        model.addAttribute("types",keyValueDTOSTypes);

        JsonResult jsonResultBases = basesService.listBasesUsable();

        List<KeyValueDTO> keyValueDTOSBases = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResultBases.getData()), KeyValueDTO.class);

        model.addAttribute("bases",keyValueDTOSBases);

        if(id==null){
            model.addAttribute("id","");
        }else{
            model.addAttribute("id",id);
        }

        return "activity/manage_base";
    }

    /**
     * Activity manage add
     * @param manageActivity
     * @param token
     * @return
     */
    @RequestMapping(value = "/manage/base/add")
    @ResponseBody
    public JsonResult ajaxActivityBaseAdd(ManageActivity manageActivity,@RequestAttribute String token){

        return activityService.addActivityManage(manageActivity,token);

    }

    /**
     * Activity manage obj
     * @param id
     * @return
     */
    @RequestMapping(value = "/manage/obj/{id}")
    @ResponseBody
    public JsonResult ajaxActivityObj(@PathVariable Long id){

        return activityService.getActivityManage(id);
    }

    /**
     * Activity manage update
     * @param manageActivity
     * @param token
     * @return
     */
    @RequestMapping(value = "/manage/update")
    @ResponseBody
    public JsonResult ajaxActivityUpdate(ManageActivity manageActivity,@RequestAttribute String token){

        return activityService.updateActivityManage(manageActivity,token);
    }
}
