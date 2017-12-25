package com.practice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageActivityClassifyMapper;
import com.practice.mapper.ManageActivityThemeMapper;
import com.practice.mapper.ManageActivityTypeMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd  2017/12/25 20:46
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ManageActivityTypeMapper typeMapper;
    @Resource
    private ManageActivityClassifyMapper classifyMapper;
    @Resource
    private ManageActivityThemeMapper themeMapper;


    /**
     * List activity type
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listType(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

        ManageActivityTypeExample.Criteria criteria = typeExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name";

        if(param.getFiled(filed)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }

        typeExample.setOrderByClause("create_time desc");

        List<ManageActivityType> manageActivityTypes = typeMapper.selectByExample(typeExample);

        PageInfo<ManageActivityType> manageActivityTypePageInfo = new PageInfo<>(manageActivityTypes);

        return JsonResult.success(manageActivityTypePageInfo);
    }

    /**
     * add activity type
     *
     * @param token
     * @param activityType
     * @return
     */
    @Override
    public JsonResult addType(String token, ManageActivityType activityType) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

        typeExample.createCriteria().andNameEqualTo(activityType.getName()).andDelflagEqualTo(0);

        long l = typeMapper.countByExample(typeExample);

        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }


        activityType.setId(null);

        Date date = new Date();

        activityType.setCreateTime(date);

        activityType.setUpdateTime(date);

        activityType.setStatus(1);

        activityType.setDelflag(0);

        activityType.setUpdateUser(tokeUser.getId());

        typeMapper.insertSelective(activityType);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * update activity type
     *
     * @param token
     * @param activityType
     * @return
     */
    @Override
    public JsonResult updateType(String token, ManageActivityType activityType) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(activityType.getName())){

            ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

            typeExample.createCriteria().andNameEqualTo(activityType.getName()).andIdNotEqualTo(activityType.getId()).andDelflagEqualTo(0);

            long l = typeMapper.countByExample(typeExample);

            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }
        }

        activityType.setUpdateTime(new Date());

        activityType.setUpdateUser(tokeUser.getId());

        typeMapper.updateByPrimaryKeySelective(activityType);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * delete activity type
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteType(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityType activityType = new ManageActivityType();

        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        classifyExample.createCriteria().andTypeIdEqualTo(id).andDelflagEqualTo(0);

        long l = classifyMapper.countByExample(classifyExample);

        if(l>0){
            return JsonResult.error("该类型下有分类，不可删除");
        }


        activityType.setId(id);

        activityType.setDelflag(1);

        activityType.setUpdateTime(new Date());

        activityType.setUpdateUser(tokeUser.getId());

        typeMapper.updateByPrimaryKeySelective(activityType);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * get activity type
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getType(Long id) {
        return JsonResult.success(typeMapper.selectByPrimaryKey(id));
    }

    /**
     * List activity type usable
     *
     * @return
     */
    @Override
    public JsonResult listTypeUsable() {

        ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

        typeExample.createCriteria().andStatusEqualTo(1).andDelflagEqualTo(0);

        return JsonResult.success(typeMapper.selectByExample(typeExample));
    }

    /**
     * List activity classify
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listClassify(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        ManageActivityClassifyExample.Criteria criteria = classifyExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name";

        if(param.getFiled(filed)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }

        classifyExample.setOrderByClause("create_time desc");

        List<ManageActivityClassify> manageActivityTypes = classifyMapper.selectByExample(classifyExample);

        for (ManageActivityClassify manageActivityType : manageActivityTypes) {

            manageActivityType.setTypeName(typeMapper.selectByPrimaryKey(manageActivityType.getTypeId()).getName());
        }

        PageInfo<ManageActivityClassify> manageActivityClassifyPageInfo = new PageInfo<>(manageActivityTypes);

        return JsonResult.success(manageActivityClassifyPageInfo);
    }

    /**
     * add activity classify
     *
     * @param token
     * @param activityClassify
     * @return
     */
    @Override
    public JsonResult addClassify(String token, ManageActivityClassify activityClassify) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        classifyExample.createCriteria().andNameEqualTo(activityClassify.getName()).andDelflagEqualTo(0);

        long l = classifyMapper.countByExample(classifyExample);

        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }

        activityClassify.setId(null);

        Date date = new Date();

        activityClassify.setDelflag(0);

        activityClassify.setStatus(1);

        activityClassify.setCreateTime(date);

        activityClassify.setUpdateTime(date);

        activityClassify.setUpdateUser(tokeUser.getId());

        classifyMapper.insertSelective(activityClassify);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * update activity classify
     *
     * @param token
     * @param activityClassify
     * @return
     */
    @Override
    public JsonResult updateClassify(String token, ManageActivityClassify activityClassify) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(activityClassify.getName())){

            ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

            classifyExample.createCriteria().andNameEqualTo(activityClassify.getName()).andDelflagEqualTo(0).andIdNotEqualTo(activityClassify.getId());

            long l = classifyMapper.countByExample(classifyExample);

            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }

        }

        activityClassify.setUpdateTime(new Date());

        activityClassify.setUpdateUser(tokeUser.getId());

        classifyMapper.updateByPrimaryKeySelective(activityClassify);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * delete activity classify
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteClassify(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        themeExample.createCriteria().andClassifyIdEqualTo(id).andDelflagEqualTo(0);

        long l = themeMapper.countByExample(themeExample);

        if(l>0){
            return JsonResult.error("该分类下有主题，不可删除");
        }

        ManageActivityClassify activityClassify = new ManageActivityClassify();

        activityClassify.setId(id);

        activityClassify.setDelflag(1);

        activityClassify.setUpdateTime(new Date());

        activityClassify.setUpdateUser(tokeUser.getId());

        classifyMapper.updateByPrimaryKeySelective(activityClassify);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * get activity classify
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getClassify(Long id) {

        return JsonResult.success(classifyMapper.selectByPrimaryKey(id));
    }

    /**
     * list activity classify usable
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult listClassifyUsable(Long id) {
        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        classifyExample.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1).andTypeIdEqualTo(id);

        return JsonResult.success(classifyMapper.selectByExample(classifyExample));
    }

    /**
     * list activity theme
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listTheme(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        String filed = "name";

        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        ManageActivityThemeExample.Criteria criteria = themeExample.createCriteria().andDelflagEqualTo(0);

        if(param.getFiled(filed)!=null){
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(filed)));
        }

        themeExample.setOrderByClause("create_time desc");

        List<ManageActivityTheme> manageActivityThemes = themeMapper.selectByExample(themeExample);

        for (ManageActivityTheme manageActivityTheme : manageActivityThemes) {

            manageActivityTheme.setTypeName(typeMapper.selectByPrimaryKey(manageActivityTheme.getTypeId()).getName());

            manageActivityTheme.setClassifyName(classifyMapper.selectByPrimaryKey(manageActivityTheme.getClassifyId()).getName());
        }

        PageInfo<ManageActivityTheme> manageActivityThemePageInfo = new PageInfo<>(manageActivityThemes);

        return JsonResult.success(manageActivityThemePageInfo);
    }

    /**
     * add activity theme
     *
     * @param token
     * @param activityTheme
     * @return
     */
    @Override
    public JsonResult addTheme(String token, ManageActivityTheme activityTheme) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        themeExample.createCriteria().andNameEqualTo(activityTheme.getName()).andDelflagEqualTo(0);

        long l = themeMapper.countByExample(themeExample);

        if(l>0){

            return JsonResult.error(OperateEnum.REPEAT);
        }


        activityTheme.setId(null);

        Date date = new Date();

        activityTheme.setStatus(1);

        activityTheme.setDelflag(0);

        activityTheme.setCreateTime(date);

        activityTheme.setUpdateTime(date);

        activityTheme.setUpdateUser(tokeUser.getId());

        themeMapper.insertSelective(activityTheme);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * update activity theme
     *
     * @param token
     * @param activityTheme
     * @return
     */
    @Override
    public JsonResult updateTheme(String token, ManageActivityTheme activityTheme) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(activityTheme.getName())){

            ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

            themeExample.createCriteria().andNameEqualTo(activityTheme.getName()).andDelflagEqualTo(0).andIdNotEqualTo(activityTheme.getId());

            long l = themeMapper.countByExample(themeExample);

            if(l>0){

                return JsonResult.error(OperateEnum.REPEAT);
            }

        }

        activityTheme.setUpdateTime(new Date());

        activityTheme.setUpdateUser(tokeUser.getId());

        themeMapper.updateByPrimaryKeySelective(activityTheme);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * delete activity theme
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult deleteTheme(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        //TODO 当期主题如果存在活动 则不可删除

        ManageActivityTheme activityTheme = new ManageActivityTheme();

        activityTheme.setId(id);

        activityTheme.setDelflag(1);

        activityTheme.setUpdateTime(new Date());

        activityTheme.setUpdateUser(tokeUser.getId());

        themeMapper.updateByPrimaryKeySelective(activityTheme);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * get activity theme
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getTheme(Long id) {
        return JsonResult.success(themeMapper.selectByPrimaryKey(id));
    }

    /**
     * list activity theme usable
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult listThemeUsable(Long id) {

        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        themeExample.createCriteria().andClassifyIdEqualTo(id).andStatusEqualTo(1).andDelflagEqualTo(0);

        return JsonResult.success(themeMapper.selectByExample(themeExample));
    }
}
