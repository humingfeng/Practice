package com.practice.service;

import com.practice.dto.AreaDTO;
import com.practice.dto.CityDTO;
import com.practice.dto.ProvinceDTO;
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

    /**
     * Get provinceDTO by provinceId
     * @param provinceId
     * @return
     */
    ProvinceDTO getProvice(Long provinceId);

    /**
     * Get CityDTO by cityId
     * @param cityId
     * @return
     */
    CityDTO getCity(Long cityId);

    /**
     * Get AreaDTO by areaId
     * @param areaId
     * @return
     */
    AreaDTO getArea(Long areaId);

    /**
     * Get pro city
     * @return
     */
    JsonResult getProCity();
}
