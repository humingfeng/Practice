package com.practice.service.impl;

import com.practice.dao.JedisClient;
import com.practice.dto.AreaDTO;
import com.practice.dto.CityDTO;
import com.practice.dto.NavDTO;
import com.practice.dto.ProvinceDTO;
import com.practice.enums.ConstantEnum;
import com.practice.enums.DicParentEnum;
import com.practice.po.ManageDictionary;
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
}
