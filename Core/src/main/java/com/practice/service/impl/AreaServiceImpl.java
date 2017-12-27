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
                list.add(new CityDTO(city.getCityId(), city.getCity()));
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
}
