package com.practice.service;

import com.practice.dto.AreaDTO;
import com.practice.dto.CityDTO;
import com.practice.dto.NavDTO;
import com.practice.dto.ProvinceDTO;
import com.practice.enums.DicParentEnum;
import com.practice.po.ManageDictionary;

import java.util.List;

/**
 * Cache service
 *
 * @author Xushd  2017/12/22 21:08
 */
public interface CacheService {


    /**
     * Set manage user navs
     *
     * @param userId
     * @param navList
     */
    void setManageUserNavs(List<NavDTO> navList, Long userId);

    /**
     * Get manage user navs
     *
     * @param userId
     * @return
     */
    List<NavDTO> getManageUserNavs(Long userId);

    /**
     * Del manage user navs
     * @param userId
     */
    void delManageUserNavs(Long userId);

    /**
     * Set dictionary
     * @param dicParentEnum
     * @param list
     */
    void resetDictionary(DicParentEnum dicParentEnum, List<ManageDictionary> list);

    /**
     * list dictionary by enum
     * @param dicParentEnum
     * @return
     */
    List<ManageDictionary> listMangeDictionaryByEnum(DicParentEnum dicParentEnum);

    /**
     * Get dictionary by id
     * @param id
     * @return
     */
    ManageDictionary getDictionaryById(Long id);

    /**
     * Set dictionary by id
     * @param id
     * @param dictionary
     */
    void setDicionaryById(Long id, ManageDictionary dictionary);

    /**
     * Set manage permission
     * @param permissionList
     * @param id
     */
    void setManageUserPermission(List<String> permissionList, Long id);

    /**
     * Get manage permission
     * @param id
     * @return
     */
    List<String> getManageUserPermission(Long id);

    /**
     * Get provice list
     * @return
     */
    List<ProvinceDTO> getProvinceList();

    /**
     * Set provice list
     * @param list
     */
    void setProvinceList(List<ProvinceDTO> list);

    /**
     * Get province by pid
     * @param pid
     * @return
     */
    ProvinceDTO getProvince(Long pid);

    /**
     * Set province by pid
     * @param pid
     * @param provinceDTO
     */
    void setProvince(Long pid,ProvinceDTO provinceDTO);

    /**
     * Get city list
     * @return
     */
    List<CityDTO> getCityList(Long pid);

    /**
     * Set city list
     * @param pid
     * @param list
     */
    void setCityList(Long pid,List<CityDTO> list);

    /**
     * Get city by cid
     * @param cid
     * @return
     */
    CityDTO getCity(Long cid);

    /**
     * Set city by cid
     * @param cid
     * @param cityDTO
     */
    void setCity(Long cid,CityDTO cityDTO);

    /**
     * Get area list
     * @param cid
     * @return
     */
    List<AreaDTO> getAreaList(Long cid);

    /**
     * Set area list
     * @param cid
     * @param list
     */
    void setAreaList(Long cid,List<AreaDTO> list);

    /**
     * Get area by aid
     * @param aid
     * @return
     */
    AreaDTO getArea(Long aid);

    /**
     * Set area by aid
     * @param aid
     * @param areaDTO
     */
    void setArea(Long aid,AreaDTO areaDTO);


    /**
     * Set phone verify code
     * @param phone
     * @param verifyCode
     */
    void setPhoneVerifyCode(String phone, String verifyCode);

    /**
     * is exit phone verify code
     * @param phone
     * @return
     */
    boolean isExistPhoneVerifyCode(String phone);

    /**
     * Get phone verify code
     * @param phone
     * @return
     */
    String getPhoneVerifyCode(String phone);
}
