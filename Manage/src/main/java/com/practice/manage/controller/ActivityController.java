package com.practice.manage.controller;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.enums.DicParentEnum;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.BasesService;
import com.practice.service.DictionaryService;
import com.practice.utils.JsonUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private DictionaryService dictionaryService;

    /**
     * Activity Type index
     *
     * @return
     */
    @RequestMapping(value = "/type")
    public String indexType() {

        return "activity/type";
    }

    /**
     * Activity type list
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/type/list")
    @ResponseBody
    public JsonResult ajaxTypeList(PageSearchParam param) {

        return activityService.listType(param);
    }

    /**
     * Activity type add
     *
     * @param token
     * @param activityType
     * @return
     */
    @RequestMapping(value = "/type/add")
    @ResponseBody
    public JsonResult ajaxTypeAdd(@RequestAttribute String token, ManageActivityType activityType) {
        return activityService.addType(token, activityType);
    }

    /**
     * Activity type update
     *
     * @param token
     * @param activityType
     * @return
     */
    @RequestMapping(value = "/type/update")
    @ResponseBody
    public JsonResult ajaxTypeUpdate(@RequestAttribute String token, ManageActivityType activityType) {
        return activityService.updateType(token, activityType);
    }

    /**
     * Activity type delete
     *
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/type/delete/{id}")
    @ResponseBody
    public JsonResult ajaxTypeDelete(@RequestAttribute String token, @PathVariable Long id) {
        return activityService.deleteType(token, id);
    }

    /**
     * Activity type
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/type/{id}")
    @ResponseBody
    public JsonResult ajaxType(@PathVariable Long id) {
        return activityService.getType(id);
    }

    /**
     * Activity type usable list
     *
     * @return
     */
    @RequestMapping(value = "/type/usable")
    @ResponseBody
    public JsonResult ajaxTypeUsable() {
        return activityService.listTypeUsable();
    }

    /**
     * Activity classify index
     *
     * @return
     */
    @RequestMapping(value = "/classify")
    public String indexClassify() {

        return "activity/classify";
    }

    /**
     * Activity Classify list
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/classify/list")
    @ResponseBody
    public JsonResult ajaxClassifyList(PageSearchParam param) {

        return activityService.listClassify(param);
    }

    /**
     * Activity classify add
     *
     * @param token
     * @param activityClassify
     * @return
     */
    @RequestMapping(value = "/classify/add")
    @ResponseBody
    public JsonResult ajaxClassifyAdd(@RequestAttribute String token, ManageActivityClassify activityClassify) {
        return activityService.addClassify(token, activityClassify);
    }

    /**
     * Activity classify update
     *
     * @param token
     * @param activityClassify
     * @return
     */
    @RequestMapping(value = "/classify/update")
    @ResponseBody
    public JsonResult ajaxClassifyUpdate(@RequestAttribute String token, ManageActivityClassify activityClassify) {
        return activityService.updateClassify(token, activityClassify);
    }

    /**
     * Activity classify delete
     *
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/classify/delete/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyDelete(@RequestAttribute String token, @PathVariable Long id) {
        return activityService.deleteClassify(token, id);
    }

    /**
     * Activity classify
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/classify/{id}")
    @ResponseBody
    public JsonResult ajaxClassify(@PathVariable Long id) {
        return activityService.getClassify(id);
    }

    /**
     * Activity classify usable list
     *
     * @return
     */
    @RequestMapping(value = "/classify/usable/{id}")
    @ResponseBody
    public JsonResult ajaxClassifyUsable(@PathVariable Long id) {
        return activityService.listClassifyUsable(id);
    }


    /**
     * Activity theme
     *
     * @return
     */
    @RequestMapping(value = "/theme")
    public String indexTheme() {

        return "activity/theme";
    }

    /**
     * Activity theme list
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/theme/list")
    @ResponseBody
    public JsonResult ajaxThemeList(PageSearchParam param) {

        return activityService.listTheme(param);
    }

    /**
     * Activity theme add
     *
     * @param token
     * @param activityTheme
     * @return
     */
    @RequestMapping(value = "/theme/add")
    @ResponseBody
    public JsonResult ajaxThemeAdd(@RequestAttribute String token, ManageActivityTheme activityTheme) {
        return activityService.addTheme(token, activityTheme);
    }

    /**
     * Activity theme update
     *
     * @param token
     * @param activityTheme
     * @return
     */
    @RequestMapping(value = "/theme/update")
    @ResponseBody
    public JsonResult ajaxThemeUpdate(@RequestAttribute String token, ManageActivityTheme activityTheme) {
        return activityService.updateTheme(token, activityTheme);
    }

    /**
     * Activity theme delete
     *
     * @param token
     * @param id
     * @return
     */
    @RequestMapping(value = "/theme/delete/{id}")
    @ResponseBody
    public JsonResult ajaxThemeDelete(@RequestAttribute String token, @PathVariable Long id) {
        return activityService.deleteTheme(token, id);
    }

    /**
     * Activity theme
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/theme/{id}")
    @ResponseBody
    public JsonResult ajaxTheme(@PathVariable Long id) {
        return activityService.getTheme(id);
    }

    /**
     * Activity theme usable list
     *
     * @return
     */
    @RequestMapping(value = "/theme/usable/{id}")
    @ResponseBody
    public JsonResult ajaxThemeUsable(@PathVariable Long id) {
        return activityService.listThemeUsable(id);
    }

    /**
     * Activity manage
     *
     * @return
     */
    @RequestMapping(value = "/manage")
    public String indexActivityManage(Model model) {

        JsonResult jsonResult = activityService.listTypeUsable();

        List<KeyValueDTO> keyValueDTOS = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResult.getData()), KeyValueDTO.class);

        model.addAttribute("types", keyValueDTOS);


        return "activity/manage";
    }

    /**
     * Activity manage list
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/manage/list")
    @ResponseBody
    public JsonResult ajaxActivityManageList(PageSearchParam param) {
        return activityService.listManage(param);
    }

    /**
     * Activity manage handle
     *
     * @return
     */
    @RequestMapping(value = "/manage/base/handle")
    public String indexActivityBaseAdd(Long id, Model model) {

        JsonResult jsonResultTypes = activityService.listTypeUsable();

        List<KeyValueDTO> keyValueDTOSTypes = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResultTypes.getData()), KeyValueDTO.class);

        model.addAttribute("types", keyValueDTOSTypes);

        JsonResult jsonResultBases = basesService.listBasesUsable();

        List<KeyValueDTO> keyValueDTOSBases = JsonUtils.jsonToList(JsonUtils.objectToJson(jsonResultBases.getData()), KeyValueDTO.class);

        model.addAttribute("bases", keyValueDTOSBases);

        if (id == null) {
            model.addAttribute("id", "");
        } else {
            model.addAttribute("id", id);
        }

        return "activity/manage_base";
    }

    /**
     * Activity manage add
     *
     * @param manageActivity
     * @param token
     * @return
     */
    @RequestMapping(value = "/manage/base/add")
    @ResponseBody
    public JsonResult ajaxActivityBaseAdd(ManageActivity manageActivity, @RequestAttribute String token) {

        return activityService.addActivityManage(manageActivity, token);

    }

    /**
     * Activity manage obj
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/manage/obj/{id}")
    @ResponseBody
    public JsonResult ajaxActivityObj(@PathVariable Long id) {

        return activityService.getActivityManage(id);
    }

    /**
     * Activity manage update
     *
     * @param manageActivity
     * @param token
     * @return
     */
    @RequestMapping(value = "/manage/update")
    @ResponseBody
    public JsonResult ajaxActivityUpdate(ManageActivity manageActivity, @RequestAttribute String token) {

        return activityService.updateActivityManage(manageActivity, token);
    }

    /**
     * Activity manage set
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/manage/set/{id}")
    public String indexActivitySet(Model model, @PathVariable Long id, String index) {

        ManageActivity manageActivity = activityService.getActivityManagePO(id);

        List<KeyValueDTO> tabList = new ArrayList<>();

        int checkStatus = 3;

        if (manageActivity.getCheckIntroduce() == checkStatus) {
            tabList.add(new KeyValueDTO("活动介绍", "/auth/activity/introduce/" + id));
        }
        if (manageActivity.getCheckApply() == checkStatus) {
            tabList.add(new KeyValueDTO("适用学段", "/auth/activity/apply/" + id));
        }
        if (manageActivity.getCheckLeader() == checkStatus) {
            tabList.add(new KeyValueDTO("负责人", "/auth/activity/leader/" + id));
        }
        if (manageActivity.getCheckSign() == checkStatus) {
            tabList.add(new KeyValueDTO("签到", "/auth/activity/sign/" + id));
        }
        if (manageActivity.getCheckSupervise() == checkStatus) {
            tabList.add(new KeyValueDTO("监督人员", "/auth/activity/supervise/" + id));
        }
        if (manageActivity.getCheckTask() == checkStatus) {
            tabList.add(new KeyValueDTO("任务设置", "/auth/activity/task/" + id));
        }
        if (manageActivity.getCheckEnroll() == checkStatus) {
            tabList.add(new KeyValueDTO("报名设置", "/auth/activity/enroll/" + id));
        }
        if (manageActivity.getCheckEvaluate() == checkStatus) {
            tabList.add(new KeyValueDTO("评价设置", "/auth/activity/evaluate/" + id));
        }

        tabList.add(new KeyValueDTO("注意事项", "/auth/activity/attention/" + id));


        model.addAttribute("tabList", tabList);

        if (StringUtils.isBlank(index)) {
            model.addAttribute("index", 0);
        } else {
            model.addAttribute("index", Integer.valueOf(index));
        }

        return "activity/manage_tab";
    }

    /**
     * Activity introduce set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/introduce/{activityId}")
    public String indexActivityIntroduce(@PathVariable Long activityId, Model model) {

        ManageActivityIntroduce introduce = activityService.getActivityIntroduce(activityId);

        if (introduce.getId() == null) {
            introduce.setId(0L);
            introduce.setActivityId(activityId);
        }
        model.addAttribute("introduce", introduce);


        return "activity/introduce";
    }

    /**
     * Activity introduce add
     *
     * @param introduce
     * @param token
     * @return
     */
    @RequestMapping(value = "/introduce/update")
    @ResponseBody
    public JsonResult ajaxActivityIntroduceUpdate(ManageActivityIntroduce introduce, @RequestAttribute String token) {

        return activityService.updateActivityIntroduce(token, introduce);
    }


    /**
     * Activity apply set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/apply/{activityId}")
    public String indexActivityApply(@PathVariable Long activityId, Model model) {

        List<ManageDictionary> manageDictionaries = dictionaryService.listDictionaryByEnumFromCache(DicParentEnum.PERIOD);

        model.addAttribute("periods", manageDictionaries);

        model.addAttribute("activityId", activityId);

        return "activity/apply";
    }

    /**
     * Activity apply list
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/apply/list/{activityId}")
    @ResponseBody
    public JsonResult ajaxActivityApplyList(@PathVariable Long activityId) {

        return activityService.listApply(activityId);
    }

    /**
     * Activity apply add
     *
     * @param token
     * @param activityId
     * @param gradeIds
     * @return
     */
    @RequestMapping(value = "/apply/add/{activityId}")
    @ResponseBody
    public JsonResult ajaxActivityApplyAdd(@RequestAttribute String token,@PathVariable Long activityId, String gradeIds) {

        return activityService.addApply(token, activityId,gradeIds);
    }

    /**
     * Activity apply del
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/apply/del/{id}")
    @ResponseBody
    public JsonResult ajaxActivityApplyDel(@PathVariable Long id) {

        return activityService.delApply(id);
    }


    /**
     * Activity leader set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/leader/{activityId}")
    public String indexActivityLeader(@PathVariable Long activityId, Model model) {

        model.addAttribute("activityId",activityId);

        return "activity/leader";
    }

    /**
     * Activity Leader list
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/leader/list/{activityId}")
    @ResponseBody
    public JsonResult ajaxActivityLeaderList(@PathVariable Long activityId){

        return activityService.listLeader(activityId);
    }

    /**
     * Activity leader add
     * @param activityId
     * @param token
     * @param userIds
     * @return
     */
    @RequestMapping(value = "/leader/add/activityId")
    @ResponseBody
    public JsonResult ajaxActivityLeaderAdd(@PathVariable Long activityId,
                                            @RequestAttribute String token,
                                            String userIds){

        return activityService.addLeader(token,activityId,userIds);

    }

    /**
     * Activity leader update main
     * @param id
     * @param activityId
     * @param token
     * @param main
     * @return
     */
    @RequestMapping(value = "/leader/main/{id}/{activityId}/{main}")
    @ResponseBody
    public JsonResult ajaxActivityLeaderMain(@PathVariable Long id,
                                             @PathVariable Long activityId,
                                             @RequestAttribute String token,
                                             @PathVariable int main){
        return activityService.updateLeader(token,id,activityId,main);
    }

    /**
     * Activity leader del
     * @param id
     * @return
     */
    @RequestMapping(value = "/leader/del/{id}")
    @ResponseBody
    public JsonResult ajaxActivityLeaderDel(@PathVariable Long id){
        return activityService.delLeader(id);
    }

    /**
     * Activity sign set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/sign/{activityId}")
    public String indexActivitySign(@PathVariable Long activityId) {

        return "activity/sign";
    }

    /**
     * Activity supervise set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/supervise/{activityId}")
    public String indexActivitySupervise(@PathVariable Long activityId) {

        return "activity/supervise";
    }

    /**
     * Activity task set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/task/{activityId}")
    public String indexActivityTask(@PathVariable Long activityId) {

        return "activity/task";
    }

    /**
     * Activity enroll set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/enroll/{activityId}")
    public String indexActivityEnroll(@PathVariable Long activityId) {

        return "activity/enroll";
    }

    /**
     * Activity evaluate set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/evaluate/{activityId}")
    public String indexActivityEvaluate(@PathVariable Long activityId) {

        return "activity/evaluate";
    }

    /**
     * Activity attention set
     *
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/attention/{activityId}")
    public String indexActivityAttention(@PathVariable Long activityId, Model model) {

        model.addAttribute("activityId",activityId);

        return "activity/attention";
    }

    /**
     * Activity attention list
     * @param activityId
     * @return
     */
    @RequestMapping(value = "/attention/list/{activityId}")
    @ResponseBody
    public JsonResult ajaxActivityAttentionList(@PathVariable Long activityId) {

        return activityService.listAttention(activityId);
    }

    /**
     * Activity attention add
     * @param token
     * @param attention
     * @return
     */
    @RequestMapping(value = "/attention/add")
    @ResponseBody
    public JsonResult ajaxActivityAttentionAdd(@RequestAttribute String token,ManageActivityAttention attention){

        return activityService.addAttention(token,attention);
    }

    /**
     * Activity attention update
     * @param token
     * @param attention
     * @return
     */
    @RequestMapping(value = "/attention/update")
    @ResponseBody
    public JsonResult ajaxActivityAttentionUpdate(@RequestAttribute String token,ManageActivityAttention attention){

        return activityService.updateAttention(token,attention);
    }

    /**
     * Activity attention del
     * @param id
     * @return
     */
    @RequestMapping(value = "/attention/del/{id}")
    @ResponseBody
    public JsonResult ajaxActivityAttentionDel(@PathVariable Long id){

        return activityService.delAttention(id);
    }
}
