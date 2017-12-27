package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.EduMapper;
import com.practice.mapper.SchoolMapper;
import com.practice.po.Edu;
import com.practice.po.EduExample;
import com.practice.po.SchoolExample;
import com.practice.result.JsonResult;
import com.practice.service.EduService;
import com.practice.service.AreaService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd on 2017/12/27 15:50
 */
@Service
public class EduServiceImpl implements EduService {

    @Resource
    private EduMapper eduMapper;
    @Resource
    private SchoolMapper schoolMapper;
    @Resource
    private AreaService areaService;

    /**
     * List edu
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listEdu(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        EduExample eduExample = new EduExample();

        EduExample.Criteria criteria = eduExample.createCriteria().andDelflagEqualTo(0);

        String field1 = "name";

        if(param.getFiled(field1)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(field1)));
        }

        eduExample.setOrderByClause(" create_time desc ");

        List<Edu> edus = eduMapper.selectByExample(eduExample);
        for (Edu edu : edus) {

            edu.setProvince(areaService.getProvice(edu.getProviceId()).getName());
            edu.setCity(areaService.getCity(edu.getCityId()).getName());
            edu.setArea(areaService.getArea(edu.getAreaId()).getName());

        }

        PageInfo<Edu> eduPageInfo = new PageInfo<>(edus);

        return JsonResult.success(eduPageInfo);
    }

    /**
     * Get edu
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getEdu(Long id) {
        return JsonResult.success(eduMapper.selectByPrimaryKey(id));
    }

    /**
     * Add edu
     *
     * @param token
     * @param edu
     * @return
     */
    @Override
    public JsonResult addEdu(String token, Edu edu) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        EduExample eduExample = new EduExample();

        eduExample.createCriteria().andNameEqualTo(edu.getName())
                .andDelflagEqualTo(0)
                .andProviceIdEqualTo(edu.getProviceId())
                .andCityIdEqualTo(edu.getCityId())
                .andAreaIdEqualTo(edu.getAreaId());

        long l = eduMapper.countByExample(eduExample);

        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }

        edu.setId(null);

        Date date = new Date();

        edu.setCreateTime(date);

        edu.setUpdateTime(date);

        edu.setUpdateUser(tokeUser.getId());

        edu.setStatus(1);

        edu.setDelflag(0);

        eduMapper.insertSelective(edu);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update edu
     *
     * @param token
     * @param edu
     * @return
     */
    @Override
    public JsonResult updateEdu(String token, Edu edu) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(edu.getName())){

            EduExample eduExample = new EduExample();

            eduExample.createCriteria().andNameEqualTo(edu.getName())
                    .andDelflagEqualTo(0)
                    .andIdNotEqualTo(edu.getId())
                    .andProviceIdEqualTo(edu.getProviceId())
                    .andCityIdEqualTo(edu.getCityId())
                    .andAreaIdEqualTo(edu.getAreaId());

            long l = eduMapper.countByExample(eduExample);

            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }
        }

        edu.setUpdateTime(new Date());

        edu.setUpdateUser(tokeUser.getId());

        eduMapper.updateByPrimaryKeySelective(edu);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Delete edu
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteEdu(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        SchoolExample schoolExample = new SchoolExample();

        schoolExample.createCriteria().andDelflagEqualTo(0)
                .andEduIdEqualTo(id);

        long l = schoolMapper.countByExample(schoolExample);

        if(l>0){
            return JsonResult.error("改教育局下有学校存在，请勿删除");
        }

        Edu edu = new Edu();

        edu.setId(id);

        edu.setUpdateUser(tokeUser.getId());

        edu.setUpdateTime(new Date());

        edu.setDelflag(1);

        eduMapper.updateByPrimaryKeySelective(edu);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List usable edu
     *
     * @return
     */
    @Override
    public JsonResult listEduUsable(Long pid,Long cid,Long aid) {

        EduExample eduExample = new EduExample();

        eduExample.createCriteria().andDelflagEqualTo(0)
                .andStatusEqualTo(1)
                .andProviceIdEqualTo(pid)
                .andCityIdEqualTo(cid)
                .andAreaIdEqualTo(aid);

        List<Edu> edus = eduMapper.selectByExample(eduExample);

        List<KeyValueDTO> list = new ArrayList<>();

        for (Edu edu : edus) {
            list.add(new KeyValueDTO(edu.getId(),edu.getName()));
        }

        return JsonResult.success(list);
    }
}
