package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageBaseMapper;
import com.practice.po.ManageBase;
import com.practice.po.ManageBaseExample;
import com.practice.result.JsonResult;
import com.practice.service.AreaService;
import com.practice.service.BasesService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/31 16:44
 */
@Service
public class BasesServiceImpl implements BasesService {

    @Resource
    private ManageBaseMapper baseMapper;
    @Resource
    private AreaService areaService;


    /**
     * List bases
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listBases(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        ManageBaseExample manageBaseExample = new ManageBaseExample();

        ManageBaseExample.Criteria criteria = manageBaseExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name",pid="pid",cid="cid",aid="aid";

        if(param.getFiled(filed)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }
        if(param.getFiled(pid)!=null){
            criteria.andPidEqualTo(Long.valueOf(param.getFiled(pid)));
        }
        if(param.getFiled(cid)!=null){
            criteria.andCidEqualTo(Long.valueOf(param.getFiled(cid)));
        }
        if(param.getFiled(aid)!=null){
            criteria.andAidEqualTo(Long.valueOf(param.getFiled(aid)));
        }

        List<ManageBase> manageBases = baseMapper.selectByExample(manageBaseExample);

        for (ManageBase manageBase : manageBases) {

            manageBase.setArea(areaService.getArea(manageBase.getAid()).getName());

            manageBase.setCity(areaService.getCity(manageBase.getCid()).getName());

            manageBase.setProv(areaService.getProvice(manageBase.getPid()).getName());
        }

        manageBaseExample.setOrderByClause("update_time desc");

        PageInfo<ManageBase> manageBasePageInfo = new PageInfo<>(manageBases);

        return JsonResult.success(manageBasePageInfo);
    }

    /**
     * Add bases
     *
     * @param token
     * @param base
     * @return
     */
    @Override
    public JsonResult addBases(String token, ManageBase base) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageBaseExample baseExample = new ManageBaseExample();

        baseExample.createCriteria()
                .andDelflagEqualTo(0)
                .andPidEqualTo(base.getPid())
                .andCidEqualTo(base.getCid())
                .andAidEqualTo(base.getAid())
                .andNameEqualTo(base.getName());

        long l = baseMapper.countByExample(baseExample);

        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }


        base.setId(null);

        base.setStatus(1);

        base.setDelflag(0);

        base.setUpdateTime(new Date());

        base.setUpdateUser(tokeUser.getId());

        baseMapper.insertSelective(base);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update bases
     *
     * @param token
     * @param base
     * @return
     */
    @Override
    public JsonResult updateBases(String token, ManageBase base) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(base.getName())){
            ManageBaseExample manageBaseExample = new ManageBaseExample();

            manageBaseExample.createCriteria()
                    .andDelflagEqualTo(0)
                    .andPidEqualTo(base.getPid())
                    .andCidEqualTo(base.getCid())
                    .andAidEqualTo(base.getAid())
                    .andNameEqualTo(base.getName())
                    .andIdNotEqualTo(base.getId());

            long l = baseMapper.countByExample(manageBaseExample);

            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }
        }

        base.setUpdateUser(tokeUser.getId());

        base.setUpdateTime(new Date());

        baseMapper.updateByPrimaryKeySelective(base);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * delete bases
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteBases(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        //TODO  判断 当前基地 是否有活动


        ManageBase manageBase = new ManageBase();

        manageBase.setId(id);

        manageBase.setDelflag(1);

        manageBase.setUpdateTime(new Date());

        manageBase.setUpdateUser(tokeUser.getId());

        baseMapper.updateByPrimaryKeySelective(manageBase);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List bases usable
     *
     * @return
     */
    @Override
    public JsonResult listBasesUsable() {

        ManageBaseExample example = new ManageBaseExample();

        example.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1);

        List<ManageBase> manageBases = baseMapper.selectByExample(example);

        return JsonResult.success(manageBases);
    }

    /**
     * Get bases
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getBases(Long id) {
        return JsonResult.success(baseMapper.selectByPrimaryKey(id));
    }
}
