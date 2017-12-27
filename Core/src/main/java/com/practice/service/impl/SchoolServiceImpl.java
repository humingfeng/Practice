package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.EduMapper;
import com.practice.mapper.SchoolMapper;
import com.practice.po.School;
import com.practice.po.SchoolExample;
import com.practice.result.JsonResult;
import com.practice.service.SchoolService;
import com.practice.utils.AreaService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd on 2017/12/27 15:50
 */
@Service
public class SchoolServiceImpl implements SchoolService {


    @Resource
    private EduMapper eduMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private AreaService areaService;

    /**
     * List school
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listSchool(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        SchoolExample schoolExample = new SchoolExample();

        SchoolExample.Criteria criteria = schoolExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name", pid = "pid", cid = "cid", aid = "aid";

        if (param.getFiled(filed) != null) {
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }
        if (param.getFiled(pid) != null) {
            criteria.andProviceIdEqualTo(Long.valueOf(param.getFiled(pid)));
        }
        if (param.getFiled(cid) != null) {
            criteria.andCityIdEqualTo(Long.valueOf(param.getFiled(cid)));
        }
        if (param.getFiled(aid) != null) {
            criteria.andAreaIdEqualTo(Long.valueOf(param.getFiled(aid)));
        }

        schoolExample.setOrderByClause(" create_time desc ");

        List<School> schools = schoolMapper.selectByExample(schoolExample);

        for (School school : schools) {

            school.setProvince(areaService.getProvice(school.getProviceId()).getName());
            school.setCity(areaService.getCity(school.getCityId()).getName());
            school.setArea(areaService.getArea(school.getAreaId()).getName());
        }

        PageInfo<School> schoolPageInfo = new PageInfo<>(schools);

        return JsonResult.success(schoolPageInfo);
    }

    /**
     * Get school
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getSchool(Long id) {
        return JsonResult.success(schoolMapper.selectByPrimaryKey(id));
    }

    /**
     * Add school
     *
     * @param token
     * @param school
     * @return
     */
    @Override
    public JsonResult addSchool(String token, School school) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        SchoolExample schoolExample = new SchoolExample();

        schoolExample.createCriteria().andDelflagEqualTo(0)
                .andNameEqualTo(school.getName())
                .andProviceIdEqualTo(school.getProviceId())
                .andCityIdEqualTo(school.getCityId())
                .andAreaIdEqualTo(school.getAreaId());

        long l = schoolMapper.countByExample(schoolExample);
        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }

        school.setId(null);

        Date date = new Date();

        school.setUpdateTime(date);

        school.setCreateTime(date);

        school.setStatus(1);

        school.setDelflag(0);

        school.setUpdateUser(tokeUser.getId());

        schoolMapper.insertSelective(school);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update school
     *
     * @param token
     * @param school
     * @return
     */
    @Override
    public JsonResult updateSchool(String token, School school) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(school.getName())){
            SchoolExample schoolExample = new SchoolExample();

            schoolExample.createCriteria().andDelflagEqualTo(0)
                    .andNameEqualTo(school.getName())
                    .andIdNotEqualTo(school.getId())
                    .andProviceIdEqualTo(school.getProviceId())
                    .andCityIdEqualTo(school.getCityId())
                    .andAreaIdEqualTo(school.getAreaId());

            long l = schoolMapper.countByExample(schoolExample);
            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }
        }

        school.setUpdateUser(tokeUser.getId());

        school.setUpdateTime(new Date());

        schoolMapper.updateByPrimaryKeySelective(school);


        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Delete school
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteSchool(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        //TODO 学校中有学生不可删除

        School school = new School();

        school.setId(id);

        school.setDelflag(1);

        school.setUpdateTime(new Date());

        school.setUpdateUser(tokeUser.getId());

        schoolMapper.updateByPrimaryKey(school);

        return JsonResult.success(OperateEnum.SUCCESS);
    }
}
