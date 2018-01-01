package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.ManageActivityClassifyMapper;
import com.practice.mapper.ManageActivityMapper;
import com.practice.mapper.ManageActivityThemeMapper;
import com.practice.mapper.ManageActivityTypeMapper;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.UserService;
import com.practice.utils.CommonUtils;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private ManageActivityMapper activityMapper;
    @Resource
    private UserService  userService;

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

        List<ManageActivityType> manageActivityTypes = typeMapper.selectByExample(typeExample);

        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityType manageActivityType : manageActivityTypes) {
            list.add(new KeyValueDTO(manageActivityType.getId(),manageActivityType.getName()));
        }

        return JsonResult.success(list);
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

        classifyExample.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1)
                .andTypeIdEqualTo(id);

        List<ManageActivityClassify> manageActivityClassifies = classifyMapper.selectByExample(classifyExample);


        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityClassify activityClassify : manageActivityClassifies) {
            list.add(new KeyValueDTO(activityClassify.getId(),activityClassify.getName()));
        }

        return JsonResult.success(list);
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

        ManageActivityExample manageActivityExample = new ManageActivityExample();

        manageActivityExample.createCriteria()
                .andThemeIdEqualTo(id).andDelflagEqualTo(0);

        long l = activityMapper.countByExample(manageActivityExample);

        if(l>0){
            return JsonResult.error("该主题已被应用到活动中，无法删除");
        }


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

        themeExample.createCriteria()
                .andClassifyIdEqualTo(id)
                .andStatusEqualTo(1)
                .andDelflagEqualTo(0);

        List<ManageActivityTheme> manageActivityThemes = themeMapper.selectByExample(themeExample);

        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityTheme manageActivityTheme : manageActivityThemes) {
            list.add(new KeyValueDTO(manageActivityTheme.getId(),manageActivityTheme.getName()));
        }

        return JsonResult.success(list);
    }

    /**
     * List activity manage
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listManage(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        ManageActivityExample activityExample = new ManageActivityExample();

        List<ManageActivity> manageActivities = activityMapper.selectByExample(activityExample);

        for (ManageActivity manageActivity : manageActivities) {

            manageActivity.setType(typeMapper.selectByPrimaryKey(manageActivity.getTypeId()).getName());

            manageActivity.setClassify(classifyMapper.selectByPrimaryKey(manageActivity.getClassifyId()).getName());

            manageActivity.setTheme(themeMapper.selectByPrimaryKey(manageActivity.getThemeId()).getName());

        }


        PageInfo<ManageActivity> manageActivityPageInfo = new PageInfo<>(manageActivities);

        return JsonResult.success(manageActivityPageInfo);
    }

    /**
     * Add activity manage
     *
     * @param manageActivity
     * @param token
     * @return
     */
    @Override
    public JsonResult addActivityManage(ManageActivity manageActivity, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageUser userPO = userService.getUserPO(tokeUser.getId());

        manageActivity.setId(null);

        manageActivity.setOrganizeId(userPO.getOrganizeId());

        Date date = new Date();

        if(StringUtils.isBlank(manageActivity.getCloseTimeStr())){
            manageActivity.setCloseTime(date);
        }else{
            manageActivity.setCloseTime(TimeUtils.getDateFromStringShort(manageActivity.getCloseTimeStr()));
        }

        if(manageActivity.getSign()==1){
            manageActivity.setCheckSign(3);
        }else{
            manageActivity.setCheckSign(0);
        }

        if(StringUtils.isNotBlank(manageActivity.getTimeStr())){
            String timeStr = manageActivity.getTimeStr();

            String[] split = timeStr.split(" - ");

            Date dateBegin = TimeUtils.getDateFromString(split[0]);

            Date dateEnd = TimeUtils.getDateFromString(split[1]);

            manageActivity.setBeginTime(dateBegin);

            manageActivity.setEndTime(dateEnd);

        }

        manageActivity.setCheckLeader(3);

        manageActivity.setCheckApply(3);

        manageActivity.setCheckSupervise(3);

        manageActivity.setCheckTask(3);

        manageActivity.setCheckIntroduce(3);

        manageActivity.setCheckEnroll(3);

        manageActivity.setCheckEvaluate(3);


        manageActivity.setStatus(9);

        manageActivity.setUpdateTime(date);

        manageActivity.setUpdateUser(tokeUser.getId());

        manageActivity.setDelflag(0);

        activityMapper.insertSelective(manageActivity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get activity manage
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getActivityManage(Long id) {
        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(id);
        if(manageActivity.getCloseType()==1){
            manageActivity.setCloseTimeStr(TimeUtils.getDateStringShort(manageActivity.getCloseTime()));
        }

        Date beginTime = manageActivity.getBeginTime();

        Date endTime = manageActivity.getEndTime();

        manageActivity.setTimeStr(TimeUtils.getDateString(beginTime)+" - "+TimeUtils.getDateString(endTime));

        return JsonResult.success(manageActivity);
    }

    /**
     * Update activity manage
     *
     * @param manageActivity
     * @param token
     * @return
     */
    @Override
    public JsonResult updateActivityManage(ManageActivity manageActivity, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        Date date = new Date();

        if(StringUtils.isBlank(manageActivity.getCloseTimeStr())){
            manageActivity.setCloseTime(date);
        }else{
            manageActivity.setCloseTime(TimeUtils.getDateFromStringShort(manageActivity.getCloseTimeStr()));
        }

        if(manageActivity.getSign()==1){
            manageActivity.setCheckSign(3);
        }else{
            manageActivity.setCheckSign(0);
        }

        if(StringUtils.isNotBlank(manageActivity.getTimeStr())){
            String timeStr = manageActivity.getTimeStr();

            String[] split = timeStr.split(" - ");

            Date dateBegin = TimeUtils.getDateFromString(split[0]);

            Date dateEnd = TimeUtils.getDateFromString(split[1]);

            manageActivity.setBeginTime(dateBegin);

            manageActivity.setEndTime(dateEnd);

        }

        manageActivity.setUpdateUser(tokeUser.getId());

        manageActivity.setUpdateTime(date);

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }
}
