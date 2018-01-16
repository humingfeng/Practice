package com.practice.app.controller;

import com.practice.dto.KeyValueDTO;
import com.practice.enums.DicParentEnum;
import com.practice.enums.SMSTemplateEnum;
import com.practice.result.JsonResult;
import com.practice.service.AreaService;
import com.practice.service.DictionaryService;
import com.practice.service.SchoolService;
import com.practice.service.SmsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private SmsService smsService;

    /**
     * List province
     * @return
     */
    @RequestMapping(value = "/pro/list")
    public JsonResult provinceList(){
        return areaService.listProvinceList();
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
     * Send sms
     * @param phone
     * @param type
     * @return
     */
    @RequestMapping(value = "/send/sms",method = RequestMethod.POST)
    public JsonResult sendSMS(String phone,String type){
        return smsService.sendSms(phone, SMSTemplateEnum.stateOf(type));
    }

    @RequestMapping(value = "/verify/phone/code")
    public JsonResult vieryfPhoneVerifyCode(String phone,String code){
        return smsService.verifyPhoneCode(phone,code);
    }


}

