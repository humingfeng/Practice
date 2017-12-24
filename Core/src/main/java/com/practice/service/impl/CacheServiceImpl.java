package com.practice.service.impl;

import com.practice.dao.JedisClient;
import com.practice.dto.NavDTO;
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
}
