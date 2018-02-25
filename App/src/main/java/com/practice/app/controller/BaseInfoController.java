package com.practice.app.controller;

import com.practice.app.service.UploadService;
import com.practice.dto.AppBaseDataDTO;
import com.practice.dto.KeyValueDTO;
import com.practice.enums.DicParentEnum;
import com.practice.enums.SMSTemplateEnum;
import com.practice.enums.SystemParamEnum;
import com.practice.po.ManageBase;
import com.practice.result.JsonResult;
import com.practice.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xushd  2018/1/16 20:49
 */
@RequestMapping(value = "/app/base/")
@RestController
public class BaseInfoController {

    @Resource
    private AreaService areaService;
    @Resource
    private SchoolService  schoolService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private SmsService smsService;
    @Resource
    private RunService runService;
    @Resource
    private ActivityService activityService;
    @Resource
    private BasesService basesService;
    @Resource
    private UploadService uploadService;
    @Resource
    private ParamService paramService;

    /**
     * List province
     * @return
     */
    @RequestMapping(value = "/pro/list")
    public JsonResult provinceList(){

        return areaService.listProvinceList();

//        return areaService.getProCity();
    }

    /**
     * List city by pid
     * @param pid
     * @return
     */
    @RequestMapping(value = "/city/list/{pid}")
    public JsonResult cityList(@PathVariable Long pid){
        return areaService.listCityByPid(pid);
    }

    /**
     * List area by cid
     * @param cid
     * @return
     */
    @RequestMapping(value = "/area/list/{cid}")
    public JsonResult areaList(@PathVariable Long cid){
        return areaService.listAreaByCid(cid);
    }



    /**
     * List school by pid and cid and aid
     * @param pid
     * @param cid
     * @param aid
     * @return
     */
    @RequestMapping(value = "/school/list/{pid}/{cid}/{aid}")
    public JsonResult schoolList(@PathVariable Long pid,
                                 @PathVariable Long cid,
                                 @PathVariable Long aid){
        return schoolService.listSchoolUsable(pid,cid,aid);
    }

    /**
     * List period
     * @return
     */
    @RequestMapping(value = "/period/list")
    public JsonResult periodList(){
        List<KeyValueDTO> list = dictionaryService.listDicByEnumFromCache(DicParentEnum.PERIOD);
        return JsonResult.success(list);
    }

    /**
     * List class
     * @return
     */
    @RequestMapping(value = "/class/list")
    public JsonResult classList(){
        List<KeyValueDTO> list = dictionaryService.listDicByEnumFromCache(DicParentEnum.CLASS_TYPE);
        return JsonResult.success(list);
    }

    /**
     * List relations
     * @return
     */
    @RequestMapping(value = "/relations/list")
    public JsonResult relationsList(){
        List<KeyValueDTO> keyValueDTOS = dictionaryService.listDicByEnumFromCache(DicParentEnum.RELATIONS);
        return JsonResult.success(keyValueDTOS);
    }

    /**
     * List nations
     * @return
     */
    @RequestMapping(value = "/nations/list")
    public JsonResult nationsList(){
        List<KeyValueDTO> keyValueDTOS = dictionaryService.listDicByEnumFromCache(DicParentEnum.NATION_TYPE);
        return JsonResult.success(keyValueDTOS);
    }

    /**
     * Send sms
     * @param phone
     * @param type
     * @return
     */
    @RequestMapping(value = "/send/sms",method = RequestMethod.POST)
    public JsonResult sendSMS(String phone,String type){

        if(StringUtils.equals(type,SMSTemplateEnum.FORGET.getSign())){
            if(!personnelService.isParentPhoneExit(phone)){
                return JsonResult.error("该手机从未注册过，请仔细检查！");
            }
        }
        if(StringUtils.equals(type,SMSTemplateEnum.REST_PHONE.getSign())||StringUtils.equals(type,SMSTemplateEnum.REGISTER.getSign())){

            if(personnelService.isParentPhoneExit(phone)){
                return JsonResult.error("该手机已经注册过，请仔细检查！");
            }

        }

        return smsService.sendSms(phone, SMSTemplateEnum.stateOf(type));
    }

    /**
     * Phone code verify
     * @param phone
     * @param code
     * @return
     */
    @RequestMapping(value = "/verify/phone/code")
    public JsonResult vieryfPhoneVerifyCode(String phone,String code){
        return smsService.verifyPhoneCode(phone,code);
    }


    /**
     * List slider by tag
     * @param tag
     * @return
     */
    @RequestMapping(value = "/slider/list/{tag}")
    public JsonResult getSliderByType(@PathVariable Integer tag){
        return runService.listSliderCache(tag);
    }

    /**
     * Get slider content
     * @param id
     * @return
     */
    @RequestMapping(value = "/slider/content/{id}")
    public JsonResult getSliderContent(@PathVariable Long id){
        return runService.getAppSliderContent(id);
    }

    /**
     * Get Base data
     * @return
     */
    @RequestMapping(value = "/data")
    public JsonResult getBaseData(){

        AppBaseDataDTO appBaseDataDTO = new AppBaseDataDTO();

        List<KeyValueDTO> periodList = dictionaryService.listDicByEnumFromCache(DicParentEnum.PERIOD);

        appBaseDataDTO.setPeriod(periodList);

        List<KeyValueDTO> typeList = activityService.listTypeKV();

        appBaseDataDTO.setActvityType(typeList);

        return JsonResult.success(appBaseDataDTO);
    }

    /**
     * Get classify by typeId
     * @param typeId
     * @return
     */
    @RequestMapping(value = "/classify/{typeId}")
    public JsonResult getClassifyByType(@PathVariable Long typeId){
        return activityService.listClassifyCache(typeId);
    }

    /**
     * Get bases
     * @param basesId
     * @return
     */
    @RequestMapping(value = "/bases/{basesId}")
    public JsonResult getBasesIntroduce(@PathVariable Long basesId){
        ManageBase basesPO = basesService.getBasesPO(basesId);
        return JsonResult.success(basesPO);
    }

    /**
     * Get theme by classify id
     * @param classifyId
     * @return
     */
    @RequestMapping(value = "/theme/{classifyId}")
    public JsonResult getThemeByClassify(@PathVariable Long classifyId){
        return activityService.listThemeCache(classifyId);
    }

    /**
     * Get filter period
     * @return
     */
    @RequestMapping(value = "/filter/period")
    public JsonResult getFilterPeriod(){
        return dictionaryService.getFilterPeriod();
    }

    /**
     * Upload img
     * @param file
     * @return
     */
    @RequestMapping(value ="/upload/photo/appimg/")
    public JsonResult uploadPhoto(@RequestParam(value = "file", required = false) MultipartFile file){

        return uploadService.uploadImg(file,"appimg/");
    }

    /**
     * Get About content
     * @return
     */
    @RequestMapping(value = "/about")
    public JsonResult getAboutContent(){
        KeyValueDTO paramByEnumPhone = paramService.getParamByEnum(SystemParamEnum.LINK_PHONE);
        KeyValueDTO paramByEnumAbout = paramService.getParamByEnum(SystemParamEnum.APP_ABOUT);

        return JsonResult.success(paramByEnumPhone.getValue(),paramByEnumAbout.getValue());
    }

}

