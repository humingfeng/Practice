package com.practice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dto.ApplyDTO;
import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.dto.TokenUserDTO;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.DictionaryService;
import com.practice.service.UserService;
import com.practice.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.*;

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
    private UserService userService;
    @Resource
    private ManageActivityIntroduceMapper introduceMapper;
    @Resource
    private ManageActivityApplyMapper applyMapper;
    @Resource
    private ManageActivityAttentionMapper attentionMapper;
    @Resource
    private ManageActivityLeaderMapper leaderMapper;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private ManageActivitySuperviseMapper superviseMapper;
    @Resource
    private ManageActivityEnrollMapper enrollMapper;
    @Resource
    private ManageActivitySignMapper signMapper;
    @Resource
    private ManageActivityTaskMapper taskMapper;
    @Resource
    private ManageActivityTaskItemMapper taskItemMapper;
    @Resource
    private ManageActivityQuestionMapper questionMapper;


    @Value("${ENDPOINT}")
    private String ENDPOINT;

    @Value("${ACCESSKEYID}")
    private String ACCESSKEYID;

    @Value("${ACCESSKEYSECRET}")
    private String ACCESSKEYSECRET;

    @Value("${BUCKETNAME}")
    private String BUCKETNAME;

    @Value("${IMGURL}")
    private String IMGURL;

    @Value("${PARENET_DIR}")
    private String PARENET_DIR;

    /**
     * 获取OSS对象参数
     *
     * @return
     */
    private Map<String, String> getParam() {

        Map<String, String> param = new HashMap<>();
        param.put("endpoint", ENDPOINT);
        param.put("accessKeyId", ACCESSKEYID);
        param.put("accessKeySecret", ACCESSKEYSECRET);
        param.put("bucketName", BUCKETNAME);

        return param;
    }

    /**
     * List activity type
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listType(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

        ManageActivityTypeExample.Criteria criteria = typeExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name";

        if (param.getFiled(filed) != null) {
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

        if (l > 0) {
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

        if (StringUtils.isNotBlank(activityType.getName())) {

            ManageActivityTypeExample typeExample = new ManageActivityTypeExample();

            typeExample.createCriteria().andNameEqualTo(activityType.getName()).andIdNotEqualTo(activityType.getId()).andDelflagEqualTo(0);

            long l = typeMapper.countByExample(typeExample);

            if (l > 0) {
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

        if (l > 0) {
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
            list.add(new KeyValueDTO(manageActivityType.getId(), manageActivityType.getName()));
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

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        ManageActivityClassifyExample.Criteria criteria = classifyExample.createCriteria().andDelflagEqualTo(0);

        String filed = "name";

        if (param.getFiled(filed) != null) {
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

        if (l > 0) {
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

        if (StringUtils.isNotBlank(activityClassify.getName())) {

            ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

            classifyExample.createCriteria().andNameEqualTo(activityClassify.getName()).andDelflagEqualTo(0).andIdNotEqualTo(activityClassify.getId());

            long l = classifyMapper.countByExample(classifyExample);

            if (l > 0) {
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

        if (l > 0) {
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
            list.add(new KeyValueDTO(activityClassify.getId(), activityClassify.getName()));
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

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        String filed = "name";

        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        ManageActivityThemeExample.Criteria criteria = themeExample.createCriteria().andDelflagEqualTo(0);

        if (param.getFiled(filed) != null) {
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

        if (l > 0) {

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

        if (StringUtils.isNotBlank(activityTheme.getName())) {

            ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

            themeExample.createCriteria().andNameEqualTo(activityTheme.getName()).andDelflagEqualTo(0).andIdNotEqualTo(activityTheme.getId());

            long l = themeMapper.countByExample(themeExample);

            if (l > 0) {

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

        if (l > 0) {
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
            list.add(new KeyValueDTO(manageActivityTheme.getId(), manageActivityTheme.getName()));
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

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

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

        manageActivity.setId(null);

        manageActivity.setOrganizeId(tokeUser.getOid());

        Date date = new Date();

        if (StringUtils.isBlank(manageActivity.getCloseTimeStr())) {
            manageActivity.setCloseTime(date);
        } else {
            manageActivity.setCloseTime(TimeUtils.getDateFromStringShort(manageActivity.getCloseTimeStr()));
        }

        if (manageActivity.getSign() == 1) {
            manageActivity.setCheckSign(3);
        } else {
            manageActivity.setCheckSign(0);
        }

        if (StringUtils.isNotBlank(manageActivity.getTimeStr())) {
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
        if (manageActivity.getCloseType() == 1) {
            manageActivity.setCloseTimeStr(TimeUtils.getDateStringShort(manageActivity.getCloseTime()));
        }

        Date beginTime = manageActivity.getBeginTime();

        Date endTime = manageActivity.getEndTime();

        manageActivity.setTimeStr(TimeUtils.getDateString(beginTime) + " - " + TimeUtils.getDateString(endTime));

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

        if (StringUtils.isBlank(manageActivity.getCloseTimeStr())) {
            manageActivity.setCloseTime(date);
        } else {
            manageActivity.setCloseTime(TimeUtils.getDateFromStringShort(manageActivity.getCloseTimeStr()));
        }

        if (manageActivity.getSign() == 1) {
            manageActivity.setCheckSign(3);
        } else {
            manageActivity.setCheckSign(0);
        }

        if (StringUtils.isNotBlank(manageActivity.getTimeStr())) {
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

    /**
     * Get activity manage PO
     *
     * @param id
     * @return
     */
    @Override
    public ManageActivity getActivityManagePO(Long id) {
        return activityMapper.selectByPrimaryKey(id);
    }

    /**
     * Get introduce
     *
     * @param activityId
     * @return
     */
    @Override
    public ManageActivityIntroduce getActivityIntroduce(Long activityId) {

        ManageActivityIntroduceExample example = new ManageActivityIntroduceExample();

        example.createCriteria()
                .andActivityIdEqualTo(activityId);

        List<ManageActivityIntroduce> introduces = introduceMapper.selectByExample(example);

        if (introduces.size() == 0) {
            return new ManageActivityIntroduce();
        }

        return introduces.get(0);
    }

    /**
     * Update introduce
     *
     * @param token
     * @param introduce
     * @return
     */
    @Override
    public JsonResult updateActivityIntroduce(String token, ManageActivityIntroduce introduce) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);


        introduce.setUpdateUser(tokeUser.getId());

        introduce.setUpdateTime(new Date());

        if (introduce.getId() == 0L) {

            introduce.setId(null);

            introduceMapper.insertSelective(introduce);
        } else {

            introduceMapper.updateByPrimaryKeySelective(introduce);

        }


        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List apply
     *
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listApply(Long activityId) {

        ManageActivityApplyExample applyExample = new ManageActivityApplyExample();

        applyExample.createCriteria()
                .andActivityIdEqualTo(activityId);

        List<ManageActivityApply> activityApplies = applyMapper.selectByExample(applyExample);

        List<ApplyDTO> list = new ArrayList<>();
        for (ManageActivityApply activityApply : activityApplies) {

            ApplyDTO applyDTO = new ApplyDTO();

            applyDTO.setId(activityApply.getId());

            applyDTO.setGradeId(activityApply.getGradeId());

            ManageDictionary dictionary = dictionaryService.getDictionaryPO(activityApply.getGradeId());

            applyDTO.setGrade(dictionary.getName());

            applyDTO.setPeriod(dictionary.getParam());

            list.add(applyDTO);

        }

        return JsonResult.success(list);
    }

    /**
     * Add apply
     *
     * @param token
     * @param activityId
     * @param gradeIds
     * @return
     */
    @Override
    public JsonResult addApply(String token, Long activityId, String gradeIds) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        List<Long> dicIds = new ArrayList<>();

        dicIds.add(26L);
        dicIds.add(27L);
        dicIds.add(28L);

        List<ManageActivityApply> list = new ArrayList<>();

        if (dicIds.contains(Long.valueOf(gradeIds))) {

            ManageDictionary dictionary = dictionaryService.getDictionaryPO(Long.valueOf(gradeIds));

            String param = dictionary.getParam();

            String[] split = param.split("\\|");

            for (String s : split) {
                Long gradeId = Long.valueOf(s);

                ManageActivityApply activityApply = new ManageActivityApply();

                activityApply.setId(null);

                activityApply.setActivityId(activityId);

                activityApply.setUpdateTime(new Date());

                activityApply.setUpdateUser(tokeUser.getId());

                activityApply.setGradeId(gradeId);

                list.add(activityApply);
            }
        } else {
            ManageActivityApply activityApply = new ManageActivityApply();

            activityApply.setId(null);

            activityApply.setActivityId(activityId);

            activityApply.setUpdateTime(new Date());

            activityApply.setUpdateUser(tokeUser.getId());

            activityApply.setGradeId(Long.valueOf(gradeIds));

            list.add(activityApply);

        }

        ManageActivityApplyExample example = new ManageActivityApplyExample();

        for (ManageActivityApply manageActivityApply : list) {

            example.clear();

            example.createCriteria().andGradeIdEqualTo(manageActivityApply.getGradeId()).andActivityIdEqualTo(activityId);

            long l = applyMapper.countByExample(example);

            if (l == 0L) {
                applyMapper.insertSelective(manageActivityApply);
            }


        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del apply
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delApply(Long id) {

        ManageActivityApply manageActivityApply = applyMapper.selectByPrimaryKey(id);

        ManageActivityApplyExample example = new ManageActivityApplyExample();

        example.createCriteria().andActivityIdEqualTo(manageActivityApply.getActivityId());

        long l = applyMapper.countByExample(example);

        if (l == 1) {

            return JsonResult.error("活动必须有一个或多个适用年级");
        }

        applyMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List attention
     *
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listAttention(Long activityId) {

        ManageActivityAttentionExample example = new ManageActivityAttentionExample();

        example.createCriteria()
                .andActivityIdEqualTo(activityId);

        List<ManageActivityAttention> attentions = attentionMapper.selectByExample(example);

        for (ManageActivityAttention attention : attentions) {

            attention.setTypeStr(dictionaryService.getDictionaryPO(attention.getType()).getName());

        }

        return JsonResult.success(attentions);
    }

    /**
     * Add attention
     *
     * @param token
     * @param attention
     * @return
     */
    @Override
    public JsonResult addAttention(String token, ManageActivityAttention attention) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        attention.setId(null);

        attention.setUpdateTime(new Date());

        attention.setUpdateUser(tokeUser.getId());

        attentionMapper.insertSelective(attention);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update attention
     *
     * @param token
     * @param attention
     * @return
     */
    @Override
    public JsonResult updateAttention(String token, ManageActivityAttention attention) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);


        attention.setUpdateTime(new Date());

        attention.setUpdateUser(tokeUser.getId());

        attentionMapper.updateByPrimaryKeySelective(attention);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del attention
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delAttention(Long id) {

        attentionMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List leader
     *
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listLeader(Long activityId) {

        ManageActivityLeaderExample leaderExample = new ManageActivityLeaderExample();

        leaderExample.createCriteria()
                .andActivityIdEqualTo(activityId);

        List<ManageActivityLeader> leaders = leaderMapper.selectByExample(leaderExample);

        for (ManageActivityLeader leader : leaders) {

            ManageUser userPO = userService.getUserPO(leader.getUserId());

            leader.setUserName(userPO.getNickName());
        }

        return JsonResult.success(leaders);
    }

    /**
     * Add leader
     *
     * @param token
     * @param activityId
     * @param userIds
     * @return
     */
    @Override
    public JsonResult addLeader(String token, Long activityId, String userIds) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        String[] split = userIds.split(",");

        Date date = new Date();

        ManageActivityLeaderExample example = new ManageActivityLeaderExample();
        for (String s : split) {


            example.clear();

            example.createCriteria().andUserIdEqualTo(Long.valueOf(s)).andActivityIdEqualTo(activityId);

            long l = leaderMapper.countByExample(example);

            if (l == 0L) {

                example.clear();

                example.createCriteria().andActivityIdEqualTo(activityId);

                long l1 = leaderMapper.countByExample(example);

                ManageActivityLeader leader = new ManageActivityLeader();

                if (l1 == 0L) {
                    leader.setMain(1);
                } else {
                    leader.setMain(0);
                }

                leader.setActivityId(activityId);

                leader.setUserId(Long.valueOf(s));

                leader.setUpdateUser(tokeUser.getId());

                leader.setUpdateTime(date);

                leaderMapper.insertSelective(leader);
            }


        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * update leader
     *
     * @param token
     * @param id
     * @param activityId
     * @param main
     * @return
     */
    @Override
    public JsonResult updateLeader(String token, Long id, Long activityId, int main) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityLeader leader = new ManageActivityLeader();

        if (main == 0) {

            ManageActivityLeaderExample example = new ManageActivityLeaderExample();

            example.createCriteria()
                    .andActivityIdEqualTo(activityId)
                    .andMainEqualTo(1)
                    .andIdNotEqualTo(id);
            long l = leaderMapper.countByExample(example);

            if (l == 0) {
                return JsonResult.error("只剩这个主负责人了！");
            }
        }

        leader.setId(id);

        leader.setActivityId(activityId);

        leader.setMain(main);

        leader.setUpdateTime(new Date());

        leader.setUpdateUser(tokeUser.getId());

        leaderMapper.updateByPrimaryKeySelective(leader);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * del leader
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delLeader(Long id) {

        ManageActivityLeader manageActivityLeader = leaderMapper.selectByPrimaryKey(id);

        if (manageActivityLeader.getMain() == 1) {
            return JsonResult.error("这是主负责人，要想删除先免去主负责人");
        }

        ManageActivityLeaderExample example = new ManageActivityLeaderExample();

        example.createCriteria().andActivityIdEqualTo(manageActivityLeader.getActivityId());

        long l = leaderMapper.countByExample(example);

        if (l == 1) {

            return JsonResult.error("活动必须有一个或多个负责人");
        }


        leaderMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List supervise
     *
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listSupervise(Long activityId) {

        ManageActivitySuperviseExample example = new ManageActivitySuperviseExample();

        example.createCriteria().andActivityIdEqualTo(activityId);

        List<ManageActivitySupervise> manageActivitySupervises = superviseMapper.selectByExample(example);

        for (ManageActivitySupervise supervise : manageActivitySupervises) {

            ManageUser user = userService.getUserPO(supervise.getUserId());

            supervise.setUserName(user.getNickName());

        }

        return JsonResult.success(manageActivitySupervises);
    }

    /**
     * Add supervise
     *
     * @param activityId
     * @param token
     * @param userId
     * @return
     */
    @Override
    public JsonResult addSupervise(Long activityId, String token, Long userId) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivitySuperviseExample example = new ManageActivitySuperviseExample();

        example.createCriteria().andActivityIdEqualTo(activityId).andUserIdEqualTo(userId);

        long l = superviseMapper.countByExample(example);

        if (l > 0) {
            return JsonResult.error(OperateEnum.REPEAT);
        }

        ManageActivitySupervise supervise = new ManageActivitySupervise();

        supervise.setId(null);

        supervise.setActivityId(activityId);

        supervise.setUserId(userId);

        supervise.setUpdateTime(new Date());

        supervise.setUpdateUser(tokeUser.getId());

        superviseMapper.insertSelective(supervise);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del supervise
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delSupervise(Long id) {

        ManageActivitySupervise supervise = superviseMapper.selectByPrimaryKey(id);

        ManageActivitySuperviseExample example = new ManageActivitySuperviseExample();

        example.createCriteria().andActivityIdEqualTo(supervise.getActivityId());

        long l = superviseMapper.countByExample(example);

        if (l == 1) {
            return JsonResult.error("活动监督人不能一个都没有，除非关闭这个功能");
        }


        superviseMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);

    }

    /**
     * Close supervise
     *
     * @param activityId
     * @param status
     * @return
     */
    @Override
    public JsonResult statusSupervise(Long activityId, int status) {

        ManageActivity activity = new ManageActivity();

        activity.setId(activityId);

        activity.setCheckSupervise(status);

        activityMapper.updateByPrimaryKeySelective(activity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get Enroll
     *
     * @param activityId
     * @return
     */
    @Override
    public ManageActivityEnroll getActivityEnroll(Long activityId) {

        ManageActivityEnrollExample example = new ManageActivityEnrollExample();

        example.createCriteria().andActivityIdEqualTo(activityId);

        List<ManageActivityEnroll> enrolls = enrollMapper.selectByExample(example);

        if (enrolls.size() == 0) {

            ManageActivityEnroll enroll = new ManageActivityEnroll();

            enroll.setActivityId(activityId);

            enroll.setUpdateTime(new Date());

            enrollMapper.insertSelective(enroll);

            return enroll;
        }

        return enrolls.get(0);
    }

    /**
     * Update Enroll
     *
     * @param token
     * @param enroll
     * @return
     */
    @Override
    public JsonResult updateActivityEnroll(String token, ManageActivityEnroll enroll) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        enroll.setUpdateTime(new Date());

        enroll.setUpdateUser(tokeUser.getId());

        enrollMapper.updateByPrimaryKeySelective(enroll);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get Sign
     *
     * @param activityId
     * @return
     */
    @Override
    public ManageActivitySign getActivitySign(Long activityId) {

        ManageActivitySignExample example = new ManageActivitySignExample();

        example.createCriteria().andActivityIdEqualTo(activityId);

        List<ManageActivitySign> manageActivitySigns = signMapper.selectByExample(example);

        if (manageActivitySigns.size() == 0) {

            ManageActivitySign sign = new ManageActivitySign();

            sign.setActivityId(activityId);

            sign.setUpdateTime(new Date());

            signMapper.insertSelective(sign);

            return sign;
        }

        return manageActivitySigns.get(0);
    }

    /**
     * Update Sign
     *
     * @param token
     * @param sign
     * @return
     */
    @Override
    public JsonResult updateActivitySign(String token, ManageActivitySign sign) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        sign.setUpdateTime(new Date());

        sign.setUpdateUser(tokeUser.getId());

        signMapper.updateByPrimaryKeySelective(sign);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Create Sign in ercode
     *
     * @param activityId
     * @param id
     * @return
     */
    @Override
    public JsonResult createActivitySignInErcode(Long activityId, Long id) {

        OutputStream out = new ByteArrayOutputStream();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("activityId", activityId);
        jsonObject.put("id", id);

        ErCodeUtils.createErCode(out, JsonUtils.objectToJson(jsonObject));

        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;

        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());

        Map<String, String> param = this.getParam();

        param.put("key", PARENET_DIR + "ercode/");

        String newFileName = FileUploadUtils.UploadStreamOSS(param, swapStream);

        ManageActivitySign sign = new ManageActivitySign();

        String url = IMGURL + PARENET_DIR + "ercode/" + newFileName;

        sign.setId(id);

        sign.setSignInErcode(url);

        signMapper.updateByPrimaryKeySelective(sign);

        return JsonResult.success(OperateEnum.FILE_UPLOAD_SUCCESS.getStateInfo(), url);

    }


    /**
     * Create Sign out ercode
     *
     * @param activityId
     * @param id
     * @param diff
     * @return
     */
    @Override
    public JsonResult createActivitySignOutErcode(Long activityId, Long id, int diff) {

        OutputStream out = new ByteArrayOutputStream();

        JSONObject jsonObject = new JSONObject();

        jsonObject.put("activityId", activityId);
        jsonObject.put("id", id);
        jsonObject.put("diff", diff);

        ErCodeUtils.createErCode(out, JsonUtils.objectToJson(jsonObject));

        ByteArrayOutputStream baos = (ByteArrayOutputStream) out;

        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());

        Map<String, String> param = this.getParam();

        param.put("key", PARENET_DIR + "ercode/");

        String newFileName = FileUploadUtils.UploadStreamOSS(param, swapStream);

        ManageActivitySign sign = new ManageActivitySign();

        String url = IMGURL + PARENET_DIR + "ercode/" + newFileName;

        sign.setId(id);

        sign.setSignOutErcode(url);

        sign.setSignOutTime(Long.valueOf(diff));

        signMapper.updateByPrimaryKeySelective(sign);

        return JsonResult.success(OperateEnum.FILE_UPLOAD_SUCCESS.getStateInfo(), url);
    }

    /**
     * List task
     *
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listTask(Long activityId) {

        ManageActivityTaskExample example = new ManageActivityTaskExample();

        example.createCriteria().andActivityIdEqualTo(activityId);

        List<ManageActivityTask> manageActivityTasks = taskMapper.selectByExample(example);

        return JsonResult.success(manageActivityTasks);
    }

    /**
     * Add task
     *
     * @param token
     * @param task
     * @return
     */
    @Override
    public JsonResult addTask(String token, ManageActivityTask task) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        task.setId(null);

        task.setUpdateTime(new Date());

        task.setUpdateUser(tokeUser.getId());

        taskMapper.insertSelective(task);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update task
     *
     * @param token
     * @param task
     * @return
     */
    @Override
    public JsonResult updateTask(String token, ManageActivityTask task) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        task.setUpdateTime(new Date());

        task.setUpdateUser(tokeUser.getId());

        taskMapper.updateByPrimaryKeySelective(task);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del task
     *
     * @param token
     * @param activityId
     * @param id
     * @return
     */
    @Override
    public JsonResult delTask(String token, Long activityId, Long id) {


        ManageActivityTaskExample taskExample = new ManageActivityTaskExample();

        taskExample.createCriteria().andActivityIdEqualTo(activityId);

        long l = taskMapper.countByExample(taskExample);

        if (l <= 1) {

            return JsonResult.error("活动任务一个不设置，这样子不好");
        }

        //todo 判断是否有子任务

        taskMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List task item
     *
     * @param activityId
     * @param taskId
     * @return
     */
    @Override
    public JsonResult listTaskItem(Long activityId, Long taskId) {

        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria()
                .andActivityIdEqualTo(activityId)
                .andTaskIdEqualTo(taskId);

        List<ManageActivityTaskItem> manageActivityTaskItems = taskItemMapper.selectByExample(itemExample);

        return JsonResult.success(manageActivityTaskItems);
    }

    /**
     * Add task item
     *
     * @param token
     * @param taskItem
     * @return
     */
    @Override
    public JsonResult addTaskItem(String token, ManageActivityTaskItem taskItem) {


        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        taskItem.setId(null);

        taskItem.setUpdateTime(new Date());

        taskItem.setUpdateUser(tokeUser.getId());

        taskItemMapper.insertSelective(taskItem);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update task item
     *
     * @param token
     * @param taskItem
     * @return
     */
    @Override
    public JsonResult updateTaskItem(String token, ManageActivityTaskItem taskItem) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        taskItem.setUpdateTime(new Date());

        taskItem.setUpdateUser(tokeUser.getId());

        taskItemMapper.updateByPrimaryKeySelective(taskItem);

        return JsonResult.success(OperateEnum.SUCCESS);

    }

    /**
     * Del task item
     *
     * @param taskId
     * @param id
     * @return
     */
    @Override
    public JsonResult delTaskItem(Long taskId, Long id) {

        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria()
                .andTaskIdEqualTo(taskId);

        long l = taskItemMapper.countByExample(itemExample);

        if (l <= 1) {

            return JsonResult.error("任务不能一个题目都没有");
        }

        taskItemMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }


    /**
     * List question
     *
     * @param
     * @return
     */
    @Override
    public JsonResult listQuestion(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

        ManageActivityQuestionExample.Criteria criteria = questionExample.createCriteria().andDelflagEqualTo(0);


        String filed1 = "type", filed2 = "name";

        if (param.getFiled(filed1) != null) {
            criteria.andTypeIdEqualTo(Long.valueOf(param.getFiled(filed1)));
        }

        if(param.getFiled(filed2) !=null ){
            criteria.andQuestionLike(CommonUtils.getLikeSql(param.getFiled(filed2)));
        }

        List<ManageActivityQuestion> manageActivityQuestions = questionMapper.selectByExample(questionExample);

        for (ManageActivityQuestion manageActivityQuestion : manageActivityQuestions) {

            manageActivityQuestion.setTypeName(dictionaryService.getDictionaryPO(manageActivityQuestion.getTypeId()).getName());
        }

        PageInfo<ManageActivityQuestion> manageActivityQuestionPageInfo = new PageInfo<>(manageActivityQuestions);

        return JsonResult.success(manageActivityQuestionPageInfo);
    }

    /**
     * Add question
     *
     * @param token
     * @param question
     * @return
     */
    @Override
    public JsonResult addQuestion(String token, ManageActivityQuestion question) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

        questionExample.createCriteria()
                .andDelflagEqualTo(0)
                .andQuestionEqualTo(question.getQuestion());

        long l = questionMapper.countByExample(questionExample);

        if(l>0){
            return JsonResult.error(OperateEnum.REPEAT);
        }

        question.setId(null);

        question.setStatus(1);

        question.setDelflag(0);

        question.setUpdateTime(new Date());

        question.setUpdateUser(tokeUser.getId());

        questionMapper.insertSelective(question);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Update question
     *
     * @param token
     * @param question
     * @return
     */
    @Override
    public JsonResult updateQuestion(String token, ManageActivityQuestion question) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if(StringUtils.isNotBlank(question.getQuestion())){

            ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

            questionExample.createCriteria()
                    .andDelflagEqualTo(0)
                    .andQuestionEqualTo(question.getQuestion())
                    .andIdNotEqualTo(question.getId());

            long l = questionMapper.countByExample(questionExample);

            if(l>0){
                return JsonResult.error(OperateEnum.REPEAT);
            }


        }

        question.setUpdateTime(new Date());

        question.setUpdateUser(tokeUser.getId());

        questionMapper.updateByPrimaryKeySelective(question);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del question
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delQuestion(String token,Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria().andQuestionIdEqualTo(id);

        long l = taskItemMapper.countByExample(itemExample);

        if(l>0){
            return JsonResult.error("该问题已经被活动应用，暂不能删除");
        }

        ManageActivityQuestion question = new ManageActivityQuestion();

        question.setId(id);

        question.setDelflag(1);

        question.setUpdateTime(new Date());

        question.setUpdateUser(tokeUser.getId());

        questionMapper.updateByPrimaryKeySelective(question);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get question
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getQuestion(Long id) {
        return JsonResult.success(questionMapper.selectByPrimaryKey(id));
    }
}
