package com.practice.utils;

import com.practice.result.JsonResult;

/**
 * Area service interface
 * @author Xushd on 2017/12/27 16:34
 */
public interface AreaService {

    /**
     * List provice list
     * @return
     */
    JsonResult listProvinceList();

    /**
     * List city list by pid
     * @param pid
     * @return
     */
    JsonResult listCityByPid(Long pid);

    /**
     * List area list by cid
     * @param cid
     * @return
     */
    JsonResult listAreaByCid(Long cid);
}
