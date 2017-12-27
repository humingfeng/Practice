package com.practice.service.impl;

import com.practice.dto.AreaDTO;
import com.practice.dto.CityDTO;
import com.practice.dto.ProvinceDTO;
import com.practice.mapper.AreaMapper;
import com.practice.mapper.CityMapper;
import com.practice.mapper.ProvinceMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.utils.AreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xushd on 2017/12/27 16:34
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private CacheService cacheService;
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private AreaMapper areaMapper;

    /**
     * List provice list
     *
     * @return
     */
    @Override
    public JsonResult listProvinceList() {

        List<ProvinceDTO> list = cacheService.getProvinceList();

        if (list == null) {
            List<Province> provinces = provinceMapper.selectByExample(null);

            list = new ArrayList<>();

            for (Province province : provinces) {
                list.add(new ProvinceDTO(province.getProvinceId(), province.getProvince()));
            }

            cacheService.setProvinceList(list);
        }

        return JsonResult.success(list);
    }

    /**
     * List city list by pid
     *
     * @param pid
     * @return
     */
    @Override
    public JsonResult listCityByPid(Long pid) {

        List<CityDTO> list = cacheService.getCityList(pid);

        if (list == null) {

            CityExample cityExample = new CityExample();
            cityExample.createCriteria().andProvinceIdEqualTo(pid);

            List<City> cities = cityMapper.selectByExample(cityExample);

            list = new ArrayList<>();

            for (City city : cities) {
                list.add(new CityDTO(city.getCityId(), city.getCity())) ;
            }

            cacheService.setCityList(pid,list);
        }

        return JsonResult.success(list);

    }

    /**
     * List area list by cid
     *
     * @param cid
     * @return
     */
    @Override
    public JsonResult listAreaByCid(Long cid) {

        List<AreaDTO> areaList = cacheService.getAreaList(cid);
        if(areaList==null){


            AreaExample areaExample = new AreaExample();

            areaExample.createCriteria().andCityIdEqualTo(cid);

            List<Area> areas = areaMapper.selectByExample(areaExample);

            areaList = new ArrayList<>();

            for (Area area : areas) {
                areaList.add(new AreaDTO(area.getAreaId(),area.getArea()));
            }

            cacheService.setAreaList(cid,areaList);

        }

        return JsonResult.success(areaList);
    }

    /**
     * Get provinceDTO by provinceId
     *
     * @param provinceId
     * @return
     */
    @Override
    public ProvinceDTO getProvice(Long provinceId) {

        ProvinceDTO provinceDTO = cacheService.getProvince(provinceId);

        if(provinceDTO==null){

            ProvinceExample provinceExample = new ProvinceExample();

            provinceExample.createCriteria().andProvinceIdEqualTo(provinceId);

            List<Province> provinces = provinceMapper.selectByExample(provinceExample);

            if(provinces.size()>0){
                Province province = provinces.get(0);

                provinceDTO = new ProvinceDTO(provinceId, province.getProvince());

                cacheService.setProvince(provinceId,provinceDTO);

            }
        }

        return provinceDTO;
    }

    /**
     * Get CityDTO by cityId
     *
     * @param cityId
     * @return
     */
    @Override
    public CityDTO getCity(Long cityId) {

        CityDTO cityDTO = cacheService.getCity(cityId);

        if(cityDTO==null){

            CityExample cityExample = new CityExample();

            cityExample.createCriteria().andCityIdEqualTo(cityId);

            List<City> cities = cityMapper.selectByExample(cityExample);

            if(cities.size()>0){

                City city = cities.get(0);

                cityDTO = new CityDTO(cityId, city.getCity());

                cacheService.setCity(cityId,cityDTO);

            }
        }

        return cityDTO;
    }

    /**
     * Get AreaDTO by areaId
     *
     * @param areaId
     * @return
     */
    @Override
    public AreaDTO getArea(Long areaId) {

        AreaDTO areaDTO = cacheService.getArea(areaId);

        if(areaDTO==null){

            AreaExample areaExample = new AreaExample();

            areaExample.createCriteria().andAreaIdEqualTo(areaId);

            List<Area> areas = areaMapper.selectByExample(areaExample);

            if(areas.size()>0){

                Area area = areas.get(0);

                areaDTO = new AreaDTO(areaId, area.getArea());

                cacheService.setArea(areaId,areaDTO);

            }
        }

        return areaDTO;
    }
}
