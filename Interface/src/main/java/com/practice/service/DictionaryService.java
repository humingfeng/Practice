package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.enums.DicParentEnum;
import com.practice.po.ManageDictionary;
import com.practice.result.JsonResult;

import java.util.List;

/**
 * @author Xushd  2017/12/23 16:02
 */
public interface DictionaryService {

    /**
     * list dictionary
     * @param param
     * @return
     */
    JsonResult listDictionary(PageSearchParam param);

    /**
     * add dictionary
     * @param token
     * @param dictionary
     * @return
     */
    JsonResult addDictionary(String token, ManageDictionary dictionary);

    /**
     * get dictionary
     * @param id
     * @return
     */
    JsonResult getDictionary(Long id);

    /**
     * update dictionary
     * @param token
     * @param dictionary
     * @return
     */
    JsonResult updateDictionary(String token, ManageDictionary dictionary);

    /**
     * reset dictionary chace
     * @return
     */
    JsonResult resetDictionaryCache();


    /**
     * list dictionary by enum
     * @param dicParentEnum
     * @return
     */
    List<ManageDictionary> listDictionaryByEnumFromCache(DicParentEnum dicParentEnum);

}
