package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageResult;
import com.practice.dto.PageSearchParam;
import com.practice.dto.SliderItemDTO;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.AppSliderContentMapper;
import com.practice.mapper.AppSliderMapper;
import com.practice.po.AppSlider;
import com.practice.po.AppSliderContent;
import com.practice.po.AppSliderContentExample;
import com.practice.po.AppSliderExample;
import com.practice.result.JsonResult;
import com.practice.service.CacheService;
import com.practice.service.RunService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2018/1/20 15:04
 */
@Service
public class RunServiceImpl implements RunService {

    @Resource
    private AppSliderMapper appSliderMapper;

    @Resource
    private AppSliderContentMapper appSliderContentMapper;

    @Resource
    private CacheService cacheService;

    /**
     * List app slider
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listAppSlider(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        String key = "name",key1 = "type";

        AppSliderExample example = new AppSliderExample();

        AppSliderExample.Criteria criteria = example.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(key)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key)));
        }
        if(param.getFiled(key1)!=null){
            criteria.andTypeEqualTo(Integer.valueOf(param.getFiled(key1)));
        }

        example.setOrderByClause("create_time desc");

        List<AppSlider> appSliders = appSliderMapper.selectByExample(example);

        PageInfo<AppSlider> appSliderPageInfo = new PageInfo<>(appSliders);

        PageResult<AppSlider> appSliderPageResult = new PageResult<>(appSliderPageInfo);

        return JsonResult.success(appSliderPageResult);
    }

    /**
     * Save app slider
     *
     * @param appSlider
     * @param token
     * @return
     */
    @Override
    public JsonResult saveAppSlider(AppSlider appSlider, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        appSlider.setId(null);

        Date date = new Date();

        appSlider.setCreateTime(date);

        appSlider.setUpdateTime(date);

        appSlider.setStatus(0);

        appSlider.setDelflag(0);

        appSlider.setCreateUser(tokeUser.getId());

        appSlider.setUpdateUser(tokeUser.getId());

        appSliderMapper.insertSelective(appSlider);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get app slider
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getAppSlider(Long id) {

        return JsonResult.success(appSliderMapper.selectByPrimaryKey(id));
    }

    /**
     * Update app slider
     *
     * @param appSlider
     * @param token
     * @return
     */
    @Override
    public JsonResult updateAppSlider(AppSlider appSlider, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        appSlider.setUpdateUser(tokeUser.getId());

        appSlider.setUpdateTime(new Date());

        appSliderMapper.updateByPrimaryKeySelective(appSlider);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del app slider
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult delAppSlider(Long id, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        AppSlider appSlider = new AppSlider();

        appSlider.setId(id);

        appSlider.setUpdateTime(new Date());

        appSlider.setUpdateUser(tokeUser.getId());

        appSlider.setDelflag(1);

        appSliderMapper.updateByPrimaryKeySelective(appSlider);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get app slider content
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getAppSliderContent(Long id) {

        AppSliderContentExample appSliderContentExample = new AppSliderContentExample();

        appSliderContentExample.createCriteria().andSliderIdEqualTo(id);

        List<AppSliderContent> appSliderContents = appSliderContentMapper.selectByExample(appSliderContentExample);

        if(appSliderContents.size()>0){
            return JsonResult.success(appSliderContents.get(0));
        }
        return JsonResult.success(new AppSliderContent());
    }

    /**
     * Save app slider content
     *
     * @param content
     * @param token
     * @return
     */
    @Override
    public JsonResult saveAppSliderContent(AppSliderContent content, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        Date date = new Date();

        content.setCreateTime(date);

        content.setUpdateTime(date);

        content.setUpdateUser(tokeUser.getId());

        content.setCreateUser(tokeUser.getId());

        content.setId(null);

        appSliderContentMapper.insertSelective(content);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update app slider content
     *
     * @param content
     * @param token
     * @return
     */
    @Override
    public JsonResult updateAppSliderContent(AppSliderContent content, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        content.setUpdateUser(tokeUser.getId());

        content.setUpdateTime(new Date());

        appSliderContentMapper.updateByPrimaryKeySelective(content);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List app slider order
     *
     * @param type
     * @return
     */
    @Override
    public List<SliderItemDTO> listAppSliderOrder(Integer type) {

        List<SliderItemDTO> list = cacheService.getAppSlider(type);
        if(list==null){
            list = new ArrayList<>();
        }
        return list;
    }

    /**
     * Save app slider order
     *
     * @param type
     * @param list
     * @return
     */
    @Override
    public JsonResult saveAppSliderOrder(Integer type, String list) {

        List<SliderItemDTO> sliderItemDTOS = JsonUtils.jsonToList(list, SliderItemDTO.class);

        cacheService.setAppSlider(type,sliderItemDTOS);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List usable App slider
     *
     * @param type
     * @return
     */
    @Override
    public JsonResult listUsableAppSlider(Integer type) {

        AppSliderExample appSliderExample = new AppSliderExample();

        appSliderExample.createCriteria().andTagEqualTo(type).andStatusEqualTo(1).andDelflagEqualTo(0);

        List<AppSlider> appSliders = appSliderMapper.selectByExample(appSliderExample);

        return JsonResult.success(appSliders);
    }
}
