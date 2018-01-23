package com.practice.service.impl;

import com.practice.dao.JedisClient;
import com.practice.dto.*;
import com.practice.enums.ConstantEnum;
import com.practice.enums.DicParentEnum;
import com.practice.po.ManageDictionary;
import com.practice.po.SystemParam;
import com.practice.service.CacheService;
import com.practice.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xushd  2017/12/22 21:08
 */
@Service
public class CacheServiceImpl implements CacheService {


    @Resource
    JedisClient jedisClient;

    /**
     * Set manage user navs
     *
     * @param navList
     */
    @Override
    public void setManageUserNavs(List<NavDTO> navList,Long userId) {

        jedisClient.set(ConstantEnum.CACHE_MANGE_USER_NAVS.getStrValue()+":KEY:"+userId, JsonUtils.objectToJson(navList));
        jedisClient.expire(ConstantEnum.CACHE_MANGE_USER_NAVS.getStrValue()+":KEY:"+userId,60*31);
    }

    /**
     * Get manage user navs
     *
     * @param userId
     * @return
     */
    @Override
    public List<NavDTO> getManageUserNavs(Long userId) {

        String s = jedisClient.get(ConstantEnum.CACHE_MANGE_USER_NAVS.getStrValue() + ":KEY:" + userId);
        if(StringUtils.isNotBlank(s)){
            return JsonUtils.jsonToList(s,NavDTO.class);
        }
        return null;
    }

    /**
     * Del manage user navs
     *
     * @param userId
     */
    @Override
    public void delManageUserNavs(Long userId) {
        jedisClient.del(ConstantEnum.CACHE_MANGE_USER_NAVS.getStrValue() + ":KEY:" + userId);
    }

    /**
     * Set dictionary
     *
     * @param dicParentEnum
     * @param list
     */
    @Override
    public void resetDictionary(DicParentEnum dicParentEnum, List<ManageDictionary> list) {

        jedisClient.hset(ConstantEnum.CACHE_DICTIONARY.getStrValue(),dicParentEnum.getKey(),JsonUtils.objectToJson(list));

        for (ManageDictionary manageDictionary : list) {

            jedisClient.hset(ConstantEnum.CACHE_DICTIONARY.getStrValue(),"KEY:"+manageDictionary.getId(),JsonUtils.objectToJson(manageDictionary));
        }

    }

    /**
     * list dictionary by enum
     *
     * @param dicParentEnum
     * @return
     */
    @Override
    public List<ManageDictionary> listMangeDictionaryByEnum(DicParentEnum dicParentEnum) {

        String hget = jedisClient.hget(ConstantEnum.CACHE_DICTIONARY.getStrValue(), dicParentEnum.getKey());
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToList(hget,ManageDictionary.class);
        }
        return null;
    }

    /**
     * Get dictionary by id
     *
     * @param id
     * @return
     */
    @Override
    public ManageDictionary getDictionaryById(Long id) {
        String hget = jedisClient.hget(ConstantEnum.CACHE_DICTIONARY.getStrValue(), "KEY:"+id);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,ManageDictionary.class);
        }
        return null;
    }

    /**
     * Set dictionary by id
     *
     * @param id
     * @param dictionary
     */
    @Override
    public void setDicionaryById(Long id, ManageDictionary dictionary) {

        jedisClient.hset(ConstantEnum.CACHE_DICTIONARY.getStrValue(),"KEY:"+id,JsonUtils.objectToJson(dictionary));

    }

    /**
     * Set manage permission
     *
     * @param permissionList
     * @param id
     */
    @Override
    public void setManageUserPermission(List<String> permissionList, Long id) {

        jedisClient.set(ConstantEnum.CACHE_MANGE_USER_PERMISSION.getStrValue()+"KEY:"+id, JsonUtils.objectToJson(permissionList));
        jedisClient.expire(ConstantEnum.CACHE_MANGE_USER_PERMISSION.getStrValue()+":KEY:"+id,60*31);
    }

    /**
     * Get manage permission
     *
     * @param id
     * @return
     */
    @Override
    public List<String> getManageUserPermission(Long id) {

        String s = jedisClient.get(ConstantEnum.CACHE_MANGE_USER_PERMISSION.getStrValue() + "KEY:" + id);
        if(StringUtils.isNotBlank(s)){
            return JsonUtils.jsonToList(s,String.class);
        }
        return null;
    }

    /**
     * Get provice list
     *
     * @return
     */
    @Override
    public List<ProvinceDTO> getProvinceList() {

        String hget = jedisClient.hget(ConstantEnum.CACHE_PROVINCE.getStrValue(), "KEY:LIST");
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToList(hget,ProvinceDTO.class);
        }

        return null;
    }

    /**
     * Set provice list
     *
     * @param list
     */
    @Override
    public void setProvinceList(List<ProvinceDTO> list) {

        jedisClient.hset(ConstantEnum.CACHE_PROVINCE.getStrValue(),"KEY:LIST",JsonUtils.objectToJson(list));

    }

    /**
     * Get province by pid
     *
     * @param pid
     * @return
     */
    @Override
    public ProvinceDTO getProvince(Long pid) {

        String hget = jedisClient.hget(ConstantEnum.CACHE_PROVINCE.getStrValue(), "KEY:" + pid);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,ProvinceDTO.class);
        }

        return null;
    }

    /**
     * Set province by pid
     *
     * @param pid
     * @param provinceDTO
     */
    @Override
    public void setProvince(Long pid, ProvinceDTO provinceDTO) {

        jedisClient.hset(ConstantEnum.CACHE_PROVINCE.getStrValue(),"KEY:"+pid,JsonUtils.objectToJson(provinceDTO));
    }

    /**
     * Get city list
     *
     * @return
     */
    @Override
    public List<CityDTO> getCityList(Long pid) {
        String hget = jedisClient.hget(ConstantEnum.CACHE_CITY.getStrValue(), "KEY:LIST:"+pid);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToList(hget,CityDTO.class);
        }

        return null;
    }

    /**
     * Set city list
     *
     * @param list
     */
    @Override
    public void setCityList(Long pid, List<CityDTO> list) {
        jedisClient.hset(ConstantEnum.CACHE_CITY.getStrValue(), "KEY:LIST:"+pid,JsonUtils.objectToJson(list));
    }

    /**
     * Get city by cid
     *
     * @param cid
     * @return
     */
    @Override
    public CityDTO getCity(Long cid) {
        String hget = jedisClient.hget(ConstantEnum.CACHE_CITY.getStrValue(), "KEY:" + cid);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,CityDTO.class);
        }

        return null;
    }

    /**
     * Set city by cid
     *
     * @param cid
     * @param cityDTO
     */
    @Override
    public void setCity(Long cid, CityDTO cityDTO) {
        jedisClient.hset(ConstantEnum.CACHE_CITY.getStrValue(), "KEY:" + cid,JsonUtils.objectToJson(cityDTO));
    }

    /**
     * Get area list
     *
     * @return
     */
    @Override
    public List<AreaDTO> getAreaList(Long cid) {
        String hget = jedisClient.hget(ConstantEnum.CACHE_AREA.getStrValue(), "KEY:LIST:"+cid);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToList(hget,AreaDTO.class);
        }

        return null;
    }

    /**
     * Set area list
     *
     * @param list
     */
    @Override
    public void setAreaList(Long cid,List<AreaDTO> list) {
        jedisClient.hset(ConstantEnum.CACHE_AREA.getStrValue(), "KEY:LIST:"+cid,JsonUtils.objectToJson(list));
    }

    /**
     * Get area by aid
     *
     * @param aid
     * @return
     */
    @Override
    public AreaDTO getArea(Long aid) {
        String hget = jedisClient.hget(ConstantEnum.CACHE_AREA.getStrValue(), "KEY:" + aid);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,AreaDTO.class);
        }

        return null;
    }

    /**
     * Set area by aid
     *
     * @param aid
     * @param areaDTO
     */
    @Override
    public void setArea(Long aid, AreaDTO areaDTO) {
        jedisClient.hset(ConstantEnum.CACHE_AREA.getStrValue(), "KEY:" + aid,JsonUtils.objectToJson(areaDTO));
    }

    /**
     * Set phone verify code
     *
     * @param phone
     * @param verifyCode
     */
    @Override
    public void setPhoneVerifyCode(String phone, String verifyCode) {
        jedisClient.set(ConstantEnum.PHONE_VERIFY_CODE.getStrValue()+":KEY:"+phone,verifyCode);
        jedisClient.expire(ConstantEnum.PHONE_VERIFY_CODE.getStrValue()+":KEY:"+phone,60*5);
    }

    /**
     * is exit phone verify code
     *
     * @param phone
     * @return
     */
    @Override
    public boolean isExistPhoneVerifyCode(String phone) {
        return jedisClient.isExit(ConstantEnum.PHONE_VERIFY_CODE.getStrValue()+":KEY:"+phone);
    }

    /**
     * Get phone verify code
     *
     * @param phone
     * @return
     */
    @Override
    public String getPhoneVerifyCode(String phone) {
        return jedisClient.get(ConstantEnum.PHONE_VERIFY_CODE.getStrValue()+":KEY:"+phone);
    }

    /**
     * Set parent DTO
     *
     * @param parentDTO
     */
    @Override
    public void setParent(ParentDTO parentDTO) {
        jedisClient.hset(ConstantEnum.PARENT_DTO.getStrValue(),"KEY:"+parentDTO.getId(),JsonUtils.objectToJson(parentDTO));
    }

    /**
     * Get parent DTO
     *
     * @param id
     * @return
     */
    @Override
    public ParentDTO getParent(Long id) {
        String hget = jedisClient.hget(ConstantEnum.PARENT_DTO.getStrValue(), "KEY:" + id);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,ParentDTO.class);
        }
        return null;
    }

    /**
     * Set system param
     *
     * @param systemParam
     */
    @Override
    public void setSystemParam(KeyValueDTO systemParam) {
        jedisClient.hset(ConstantEnum.SYSTEM_PARAM.getStrValue(),"KEY:"+systemParam.getId(),JsonUtils.objectToJson(systemParam));
    }

    /**
     * Get system param
     *
     * @param id
     * @return
     */
    @Override
    public KeyValueDTO getSystemParam(Long id) {
        String hget = jedisClient.hget(ConstantEnum.SYSTEM_PARAM.getStrValue(), "KEY:" + id);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,KeyValueDTO.class);
        }
        return null;
    }

    /**
     * Get app slider
     *
     * @param type
     * @return
     */
    @Override
    public List<SliderItemDTO> getAppSlider(Integer type) {

        String hget = jedisClient.hget(ConstantEnum.APP_SLIDER.getStrValue(), "KEY:" + type);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToList(hget,SliderItemDTO.class);
        }

        return null;
    }

    /**
     * Set app slider
     *
     * @param type
     * @param list
     */
    @Override
    public void setAppSlider(Integer type, List<SliderItemDTO> list) {
        jedisClient.hset(ConstantEnum.APP_SLIDER.getStrValue(),"KEY:"+type,JsonUtils.objectToJson(list));
    }

    /**
     * Set activity solr item
     *
     * @param activitySolrItemDTO
     */
    @Override
    public void setActvitySolrItemDTO(ActivitySolrItemDTO activitySolrItemDTO) {
        jedisClient.hset(ConstantEnum.ACTIVITY_SOLR_ITEM.getStrValue(),"KEY:"+activitySolrItemDTO.getId(),JsonUtils.objectToJson(activitySolrItemDTO));
    }

    /**
     * Get activity solr item
     *
     * @param activityId
     * @return
     */
    @Override
    public ActivitySolrItemDTO getActvitySolrItemDTO(Long activityId) {

        String hget = jedisClient.hget(ConstantEnum.ACTIVITY_SOLR_ITEM.getStrValue(), "KEY:" + activityId);
        if(StringUtils.isNotBlank(hget)){
            return JsonUtils.jsonToPojo(hget,ActivitySolrItemDTO.class);
        }

        return null;
    }
}
