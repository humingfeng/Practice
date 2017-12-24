package com.practice.service;

import com.practice.dto.NavDTO;
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
}
