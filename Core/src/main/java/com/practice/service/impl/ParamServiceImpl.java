package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageResult;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.enums.SystemParamEnum;
import com.practice.mapper.SystemParamMapper;
import com.practice.po.SystemParam;
import com.practice.po.SystemParamExample;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.ParamService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2018/1/20 11:25
 */
@Service
public class ParamServiceImpl implements ParamService {

    @Resource
    private SystemParamMapper paramMapper;
    @Resource
    private CacheService  cacheService;

    /**
     * List param
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listParam(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        String key="name";

        SystemParamExample systemParamExample = new SystemParamExample();

        SystemParamExample.Criteria criteria = systemParamExample.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(key)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key)));
        }

        systemParamExample.setOrderByClause("create_time desc");

        List<SystemParam> systemParams = paramMapper.selectByExample(systemParamExample);

        PageInfo<SystemParam> systemParamPageInfo = new PageInfo<>(systemParams);

        PageResult<SystemParam> paramPageResult = new PageResult<>(systemParamPageInfo);

        return JsonResult.success(paramPageResult);
    }

    /**
     * Save param
     *
     * @param param
     * @param token
     * @return
     */
    @Override
    public JsonResult saveParam(SystemParam param, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        param.setId(null);

        param.setStatus(1);

        param.setDelflag(0);

        param.setCreateUser(tokeUser.getId());

        param.setUpdateUser(tokeUser.getId());

        Date date = new Date();

        param.setCreateTime(date);

        param.setUpdateTime(date);

        paramMapper.insertSelective(param);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update param
     *
     * @param param
     * @param token
     * @return
     */
    @Override
    public JsonResult updateParam(SystemParam param, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        param.setUpdateTime(new Date());

        param.setUpdateUser(tokeUser.getId());

        paramMapper.updateByPrimaryKeySelective(param);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del param
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult delParam(Long id, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        SystemParam param = new SystemParam();

        param.setId(id);

        param.setUpdateUser(tokeUser.getId());

        param.setUpdateTime(new Date());

        param.setDelflag(1);

        paramMapper.updateByPrimaryKeySelective(param);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Sync param cache
     *
     * @return
     */
    @Override
    public JsonResult syncParamCache() {

        SystemParamExample systemParamExample = new SystemParamExample();

        systemParamExample.createCriteria()
                .andStatusEqualTo(1)
                .andDelflagEqualTo(0);

        List<SystemParam> systemParams = paramMapper.selectByExample(systemParamExample);

        for (SystemParam systemParam : systemParams) {

            cacheService.setSystemParam(new KeyValueDTO(systemParam.getId(),systemParam.getParam()));
        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get param
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getParam(Long id) {
        return JsonResult.success(paramMapper.selectByPrimaryKey(id));
    }

    /**
     * Get param by enum
     *
     * @param systemParamEnum
     * @return
     */
    @Override
    public KeyValueDTO getParamByEnum(SystemParamEnum systemParamEnum) {

        KeyValueDTO systemParam = cacheService.getSystemParam(systemParamEnum.getId());

        if(systemParam==null){

            SystemParam param = paramMapper.selectByPrimaryKey(systemParamEnum.getId());

            systemParam = new KeyValueDTO(param.getId(),param.getParam());
        }

        return systemParam;
    }
}
