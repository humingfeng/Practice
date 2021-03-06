package com.practice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.practice.dao.ActiveMqProducer;
import com.practice.dto.*;
import com.practice.enums.OperateEnum;
import com.practice.exception.ServiceException;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.*;
import com.practice.utils.*;
import com.practice.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author Xushd  2017/12/25 20:46
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ActivityServiceImpl.class);

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
    @Resource
    private ManageActivityQuestionOptionMapper questionOptionMapper;
    @Resource
    private AreaService areaService;
    @Resource
    private ManageActivityEnrollRecordMapper enrollRecordMapper;
    @Resource
    private SchoolService schoolService;
    @Resource
    private BasesService basesService;
    @Resource
    private CacheService cacheService;
    @Resource
    private SearchService searchService;
    @Resource
    private ManageActivityCollectMapper collectMapper;

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

    @Value("${LIKE.BASE}")
    private String LIKEBASE;


    @Resource
    private ActiveMqProducer activeMqProducer;

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
            list.add(new KeyValueDTO(activityClassify.getId(), activityClassify.getName(),activityClassify.getIcon()));
        }

        return JsonResult.success(list);
    }

    private List<KeyValueDTO> listClassifyUsablePO(Long id){

        ManageActivityClassifyExample classifyExample = new ManageActivityClassifyExample();

        classifyExample.createCriteria()
                .andDelflagEqualTo(0)
                .andStatusEqualTo(1)
                .andTypeIdEqualTo(id);

        List<ManageActivityClassify> manageActivityClassifies = classifyMapper.selectByExample(classifyExample);


        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityClassify activityClassify : manageActivityClassifies) {
            list.add(new KeyValueDTO(activityClassify.getId(), activityClassify.getName(),activityClassify.getIcon()));
        }
        return list;
    }
    /**
     * List classify from cache
     *
     * @param typeId
     * @return
     */
    @Override
    public JsonResult listClassifyCache(Long typeId) {

        List<KeyValueDTO> list = cacheService.getClassify(typeId);

        if(list==null){

            list = this.listClassifyUsablePO(typeId);

            cacheService.setClassify(typeId,list);

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
     * List theme usable  by classifyid
     *
     * @param classifyId
     * @return
     */
    @Override
    public List<KeyValueDTO> listThemeUsablePO(Long classifyId) {
        ManageActivityThemeExample themeExample = new ManageActivityThemeExample();

        themeExample.createCriteria()
                .andClassifyIdEqualTo(classifyId)
                .andStatusEqualTo(1)
                .andDelflagEqualTo(0);

        List<ManageActivityTheme> manageActivityThemes = themeMapper.selectByExample(themeExample);

        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityTheme manageActivityTheme : manageActivityThemes) {
            list.add(new KeyValueDTO(manageActivityTheme.getId(), manageActivityTheme.getName()));
        }

        return list;
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

        ManageActivityExample.Criteria criteria = activityExample.createCriteria();

        String key1 = "typeId", key2 = "classifyId", key3 = "themeId";

        if (param.getFiled(key1) != null) {
            criteria.andTypeIdEqualTo(Long.valueOf(param.getFiled(key1)));
        }
        if (param.getFiled(key2) != null) {
            criteria.andClassifyIdEqualTo(Long.valueOf(param.getFiled(key2)));
        }
        if (param.getFiled(key3) != null) {
            criteria.andThemeIdEqualTo(Long.valueOf(param.getFiled(key3)));
        }

        activityExample.setOrderByClause("update_time desc");


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

            Date closeDate = TimeUtils.getDateHourFromString(manageActivity.getCloseTimeStr());

            if (!TimeUtils.greaterThanNow(closeDate)) {
                return JsonResult.error("报名截止时间小于当前时间");
            }

            manageActivity.setCloseTime(closeDate);

        }

        if(manageActivity.getNumber()!=0&&manageActivity.getMinNum()!=0){
            if(manageActivity.getNumber()<=manageActivity.getMinNum()){
                return JsonResult.error("报名人数限制输入不正确");
            }
        }

        if (manageActivity.getSign() == 1) {
            manageActivity.setCheckSign(3);
        } else {
            manageActivity.setCheckSign(0);
        }

        if (StringUtils.isNotBlank(manageActivity.getTimeStr())
                && StringUtils.isNotBlank(manageActivity.getValidTime())
                && StringUtils.isNotBlank(manageActivity.getTaskCloseTimeStr())) {
            String dateStr = manageActivity.getTimeStr();
            String timeStr = manageActivity.getValidTime();

            String taskCloseTimeStr = manageActivity.getTaskCloseTimeStr();

            Date dateTaskCloseTime = TimeUtils.getDateFromString(taskCloseTimeStr);

            manageActivity.setTaskCloseTime(dateTaskCloseTime);


            String[] split = dateStr.split(" - ");

            String[] split1 = timeStr.split(" - ");

            if(!split1[0].split(":")[1].equals("00")
                    ||!split1[0].split(":")[2].equals("00")
                    ||!split1[1].split(":")[1].equals("00")
                    ||!split1[1].split(":")[2].equals("00")){
                return JsonResult.error("活动开始时间和结束时间请精确到小时，分秒请置0");
            }


            if(split1[0].equals(split1[1])){
                return JsonResult.error("活动时间为零");
            }

            Date dateBegin = TimeUtils.getDateFromStringShort(split[0]);

            if (!TimeUtils.greaterThanNow(dateBegin)) {
                return JsonResult.error("活动开始时间小于当前时间");
            }

            Date dateEnd = TimeUtils.getDateFromStringShort(split[1]);

            int day = TimeUtils.getDateDayDiff(dateEnd, dateBegin);

            if (day > 1) {
                manageActivity.setDurationType(1);
            } else {
                manageActivity.setDurationType(2);
            }

            String nowTimeShort = TimeUtils.getNowTimeShort();

            Date timeBegin = TimeUtils.getDateFromString(nowTimeShort + " " + split1[0]);
            Date timeEnd = TimeUtils.getDateFromString(nowTimeShort + " " + split1[1]);

            int minutes = TimeUtils.getDateMinuteDiff(timeEnd, timeBegin);

            manageActivity.setDuration(minutes);

            manageActivity.setBeginTime(dateBegin);

            manageActivity.setEndTime(dateEnd);

        } else {
            return JsonResult.error("信息录入不完整，请仔细检查");
        }

        /**
         * 费用 单位分
         * @author Xushd on 2018/2/2 14:43
         */
        String price = manageActivity.getPrice();
        if(StringUtils.isBlank(price)){
            manageActivity.setMoney(0);
        }else{
            BigDecimal bigDecimal = new BigDecimal(price);

            if(bigDecimal.compareTo(new BigDecimal(0))==0){
                manageActivity.setMoney(0);
            }else{
                BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100));

                manageActivity.setMoney(multiply.intValue());
            }
        }

        /**
         * 库存
         * @author Xushd on 2018/2/2 14:45
         */
        manageActivity.setStock(manageActivity.getNumber());

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
            manageActivity.setCloseTimeStr(TimeUtils.getDateString(manageActivity.getCloseTime()));
        }
        String price = new BigDecimal(manageActivity.getMoney()).divide(new BigDecimal(100)).toPlainString();

        manageActivity.setPrice(price);

        Date beginTime = manageActivity.getBeginTime();

        Date endTime = manageActivity.getEndTime();

        manageActivity.setTimeStr(TimeUtils.getDateStringShort(beginTime) + " - " + TimeUtils.getDateStringShort(endTime));

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
            Date closeDate = TimeUtils.getDateHourFromString(manageActivity.getCloseTimeStr());

            if (!TimeUtils.greaterThanNow(closeDate)) {
                return JsonResult.error("报名截止时间小于当前时间");
            }

            manageActivity.setCloseTime(closeDate);
        }

        if(manageActivity.getNumber()!=0&&manageActivity.getMinNum()!=0){
            if(manageActivity.getNumber()<=manageActivity.getMinNum()){
                return JsonResult.error("报名人数限制输入不正确");
            }
        }

        if (manageActivity.getSign() == 1) {
            manageActivity.setCheckSign(3);
        } else {
            manageActivity.setCheckSign(0);
        }

        if (StringUtils.isNotBlank(manageActivity.getTimeStr())
                && StringUtils.isNotBlank(manageActivity.getValidTime())
                && StringUtils.isNotBlank(manageActivity.getTaskCloseTimeStr())) {
            String dateStr = manageActivity.getTimeStr();
            String timeStr = manageActivity.getValidTime();

            String taskCloseTimeStr = manageActivity.getTaskCloseTimeStr();

            Date dateTaskCloseTime = TimeUtils.getDateFromString(taskCloseTimeStr);

            manageActivity.setTaskCloseTime(dateTaskCloseTime);




            String[] split = dateStr.split(" - ");

            String[] split1 = timeStr.split(" - ");


            Date dateEnd1 = TimeUtils.getDateFromString(split[1] + " " + split1[1]);


            if(TimeUtils.Obj1LessObj2(dateTaskCloseTime,dateEnd1)){
                return JsonResult.error("任务结束时间应该大于活动的结束时间");
            }

            Date dateBegin = TimeUtils.getDateFromStringShort(split[0]);

            if (!TimeUtils.greaterThanNow(dateBegin)) {
                return JsonResult.error("活动开始时间小于当前时间");
            }

            Date dateEnd = TimeUtils.getDateFromStringShort(split[1]);

            int day = TimeUtils.getDateDayDiff(dateEnd, dateBegin);

            if (day > 1) {
                manageActivity.setDurationType(1);
            } else {
                manageActivity.setDurationType(2);
            }

            String nowTimeShort = TimeUtils.getNowTimeShort();

            Date timeBegin = TimeUtils.getDateFromString(nowTimeShort + " " + split1[0]);
            Date timeEnd = TimeUtils.getDateFromString(nowTimeShort + " " + split1[1]);

            int minutes = TimeUtils.getDateMinuteDiff(timeEnd, timeBegin);

            manageActivity.setDuration(minutes);

            manageActivity.setBeginTime(dateBegin);

            manageActivity.setEndTime(dateEnd);

        }

        /**
         * 费用 单位分
         * @author Xushd on 2018/2/2 14:43
         */
        String price = manageActivity.getPrice();

        if(StringUtils.isNotBlank(price)){
            BigDecimal bigDecimal = new BigDecimal(price);

            if(bigDecimal.compareTo(new BigDecimal(0))==0){
                manageActivity.setMoney(0);
            }else{
                BigDecimal multiply = bigDecimal.multiply(new BigDecimal(100));

                manageActivity.setMoney(multiply.intValue());
            }
        }else{
            manageActivity.setMoney(0);
        }

        /**
         * 库存
         * @author Xushd on 2018/2/2 14:45
         */
        manageActivity.setStock(manageActivity.getNumber());

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

            return JsonResult.success(OperateEnum.SUCCESS.getStateInfo(),introduce.getId());
        } else {

            introduceMapper.updateByPrimaryKeySelective(introduce);

            return JsonResult.success(OperateEnum.SUCCESS);

        }



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

        return JsonResult.success(OperateEnum.SUCCESS.getStateInfo(),attention.getId());
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
     * Update activity sign out Ercode
     *
     * @param id
     * @param s
     * @param diff
     */
    @Override
    public void updateActivitySingOutErcode(Long id, String s, int diff) {

        ManageActivitySign sign = new ManageActivitySign();

        sign.setId(id);

        sign.setSignOutErcode(s);

        sign.setSignOutTime((long) diff);

        signMapper.updateByPrimaryKeySelective(sign);

    }

    /**
     * Update activity sign in Ercode
     *
     * @param id
     * @param s
     */
    @Override
    public void updateActivitySignInErcode(Long id, String s) {


        ManageActivitySign sign = new ManageActivitySign();

        sign.setId(id);

        sign.setSignInErcode(s);

        signMapper.updateByPrimaryKeySelective(sign);
    }

    /**
     * Reset classify cache
     *
     * @return
     */
    @Override
    public JsonResult resetClassifyCache() {

        cacheService.delClassify();

        return JsonResult.success(OperateEnum.SUCCESS);
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
     * Get ManageAttention PO
     *
     * @param activityId
     * @return
     */
    @Override
    public ManageActivityAttention getActivityAttentionPO(Long activityId) {

        List<ManageActivityAttention> attentions = attentionMapper.selectByActivityId(activityId);

        if(attentions.size()>0){

            return attentions.get(0);
        }

        return null;
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
    @Transactional
    @Override
    public JsonResult delTask(String token, Long activityId, Long id) {


        ManageActivityTaskExample taskExample = new ManageActivityTaskExample();

        taskExample.createCriteria().andActivityIdEqualTo(activityId);

        long l = taskMapper.countByExample(taskExample);

        if (l <= 1) {

            return JsonResult.error("活动任务一个不设置，这样子不好");
        }


        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria().andActivityIdEqualTo(activityId).andTaskIdEqualTo(id);

        taskItemMapper.deleteByExample(itemExample);

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

        if (param.getFiled(filed2) != null) {
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
    public JsonResult addQuestion(String token, QuestionDTO question) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

        questionExample.createCriteria()
                .andDelflagEqualTo(0)
                .andQuestionEqualTo(question.getQuestion());

        long l = questionMapper.countByExample(questionExample);

        if (l > 0) {
            return JsonResult.error(OperateEnum.REPEAT);
        }

        ManageActivityQuestion activityQuestion = new ManageActivityQuestion();

        activityQuestion.setId(null);

        activityQuestion.setTypeId(question.getTypeId());

        activityQuestion.setQuestion(question.getQuestion());

        activityQuestion.setAnswerText(question.getAnswerText());

        activityQuestion.setClassify(question.getClassify());

        if(question.getClassify()== 3){
            activityQuestion.setPhotoNum(question.getPhotoNum());
        }

        activityQuestion.setStatus(1);

        activityQuestion.setDelflag(0);

        activityQuestion.setUpdateTime(new Date());

        activityQuestion.setUpdateUser(tokeUser.getId());

        questionMapper.insertSelective(activityQuestion);


        if (StringUtils.isNotBlank(question.getOptions())) {

            String options = question.getOptions();

            List<QuestionItemDTO> questionItemDTOS = JsonUtils.jsonToList(options, QuestionItemDTO.class);

            for (QuestionItemDTO questionItemDTO : questionItemDTOS) {

                ManageActivityQuestionOption option = new ManageActivityQuestionOption();

                option.setQuestionId(activityQuestion.getId());

                option.setText(questionItemDTO.getText());

                option.setOptionMark(questionItemDTO.getOptionMark());

                option.setId(null);

                option.setUpdateTime(new Date());

                option.setUpdateUser(tokeUser.getId());

                questionOptionMapper.insertSelective(option);

                if (questionItemDTO.getCorrect() == 1) {
                    activityQuestion.setAnswerId(option.getId());
                }

            }

            ManageActivityQuestion question1 = new ManageActivityQuestion();

            question1.setId(activityQuestion.getId());

            question1.setAnswerId(activityQuestion.getAnswerId());

            questionMapper.updateByPrimaryKeySelective(question1);

        }

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
    public JsonResult updateQuestion(String token, QuestionDTO question) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        if (StringUtils.isNotBlank(question.getQuestion())) {

            ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

            questionExample.createCriteria()
                    .andDelflagEqualTo(0)
                    .andQuestionEqualTo(question.getQuestion())
                    .andIdNotEqualTo(question.getId());

            long l = questionMapper.countByExample(questionExample);

            if (l > 0) {
                return JsonResult.error(OperateEnum.REPEAT);
            }
        }


        ManageActivityQuestion activityQuestion = new ManageActivityQuestion();

        activityQuestion.setId(question.getId());

        activityQuestion.setTypeId(question.getTypeId());

        activityQuestion.setUpdateUser(tokeUser.getId());

        activityQuestion.setUpdateTime(new Date());

        activityQuestion.setQuestion(question.getQuestion());

        activityQuestion.setClassify(question.getClassify());

        if(question.getClassify()== 3){
            activityQuestion.setPhotoNum(question.getPhotoNum());
        }

        if (question.getClassify() == 1) {

            String options = question.getOptions();

            List<QuestionItemDTO> questionItemDTOS = JsonUtils.jsonToList(options, QuestionItemDTO.class);


            ManageActivityQuestionOptionExample optionExample = new ManageActivityQuestionOptionExample();

            optionExample.createCriteria().andQuestionIdEqualTo(question.getId());

            List<ManageActivityQuestionOption> questionOptions = questionOptionMapper.selectByExample(optionExample);


            List<Long> hasId = new ArrayList<>();

            for (QuestionItemDTO questionItemDTO : questionItemDTOS) {

                ManageActivityQuestionOption option = new ManageActivityQuestionOption();

                if (questionItemDTO.getId() != 0) {

                    hasId.add(questionItemDTO.getId());

                    option.setId(questionItemDTO.getId());

                    option.setOptionMark(questionItemDTO.getOptionMark());

                    option.setText(questionItemDTO.getText());

                    option.setUpdateUser(tokeUser.getId());

                    option.setUpdateTime(new Date());

                    questionOptionMapper.updateByPrimaryKeySelective(option);

                    if (questionItemDTO.getCorrect() == 1) {
                        activityQuestion.setAnswerId(questionItemDTO.getId());
                    }

                } else {

                    option.setQuestionId(activityQuestion.getId());

                    option.setText(questionItemDTO.getText());

                    option.setOptionMark(questionItemDTO.getOptionMark());

                    option.setId(null);

                    option.setUpdateTime(new Date());

                    option.setUpdateUser(tokeUser.getId());

                    questionOptionMapper.insertSelective(option);

                    if (questionItemDTO.getCorrect() == 1) {
                        activityQuestion.setAnswerId(option.getId());
                    }
                }
            }

            questionMapper.updateByPrimaryKeySelective(activityQuestion);


            for (ManageActivityQuestionOption questionOption : questionOptions) {

                if (!hasId.contains(questionOption.getId())) {
                    questionOptionMapper.deleteByPrimaryKey(questionOption.getId());
                }
            }

        } else {

            ManageActivityQuestionOptionExample optionExample = new ManageActivityQuestionOptionExample();

            optionExample.createCriteria()
                    .andQuestionIdEqualTo(question.getId());

            questionOptionMapper.deleteByExample(optionExample);

            activityQuestion.setAnswerText(question.getAnswerText());

            activityQuestion.setAnswerId(null);

            questionMapper.updateByPrimaryKeySelective(activityQuestion);

        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Del question
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult delQuestion(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria().andQuestionIdEqualTo(id);

        long l = taskItemMapper.countByExample(itemExample);

        if (l > 0) {
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
        ManageActivityQuestion question = questionMapper.selectByPrimaryKey(id);

        Integer classify = question.getClassify();

        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setId(question.getId());

        questionDTO.setClassify(question.getClassify());

        questionDTO.setTypeId(question.getTypeId());

        questionDTO.setQuestion(question.getQuestion());

        if (classify == 1) {

            ManageActivityQuestionOptionExample optionExample = new ManageActivityQuestionOptionExample();

            optionExample.createCriteria().andQuestionIdEqualTo(id);

            List<ManageActivityQuestionOption> questionOptions = questionOptionMapper.selectByExample(optionExample);

            List<QuestionItemDTO> list = new ArrayList<>();

            for (ManageActivityQuestionOption questionOption : questionOptions) {

                QuestionItemDTO questionItemDTO = new QuestionItemDTO();

                questionItemDTO.setId(questionOption.getId());

                if (questionOption.getId().equals(question.getAnswerId())) {
                    questionItemDTO.setCorrect(1);
                } else {
                    questionItemDTO.setCorrect(0);
                }

                questionItemDTO.setOptionMark(questionOption.getOptionMark());

                questionItemDTO.setText(questionOption.getText());

                list.add(questionItemDTO);
            }

            questionDTO.setList(list);

        } else if(classify == 2){

            questionDTO.setAnswerText(question.getAnswerText());

        } else{

            questionDTO.setPhotoNum(question.getPhotoNum());
        }

        return JsonResult.success(questionDTO);

    }

    /**
     * Update question status
     *
     * @param id
     * @param status
     * @param token
     * @return
     */
    @Override
    public JsonResult updateQuestionStatus(Long id, int status, String token) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);


        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria().andQuestionIdEqualTo(id);

        long l = taskItemMapper.countByExample(itemExample);

        if (l > 0) {
            return JsonResult.error("该问题已经被活动应用，暂不能更改状态");
        }


        ManageActivityQuestion activityQuestion = new ManageActivityQuestion();

        activityQuestion.setId(id);

        activityQuestion.setStatus(status);

        activityQuestion.setUpdateUser(tokeUser.getId());

        activityQuestion.setUpdateTime(new Date());

        questionMapper.updateByPrimaryKeySelective(activityQuestion);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List question usable
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listQuestionUsable(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());


        ManageActivityQuestionExample questionExample = new ManageActivityQuestionExample();

        ManageActivityQuestionExample.Criteria criteria = questionExample.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1);

        String key1 = "type", key2 = "name";

        if (param.getFiled(key1) != null) {
            criteria.andTypeIdEqualTo(Long.valueOf(param.getFiled(key1)));
        }

        if (param.getFiled(key2) != null) {
            criteria.andQuestionLike(CommonUtils.getLikeSql(param.getFiled(key2)));
        }

        questionExample.setOrderByClause("update_time desc");

        List<ManageActivityQuestion> manageActivityQuestions = questionMapper.selectByExample(questionExample);

        PageInfo<ManageActivityQuestion> pageInfo = new PageInfo<>(manageActivityQuestions);

        List<QuestionVO> list = new ArrayList<>();

        for (ManageActivityQuestion manageActivityQuestion : manageActivityQuestions) {
            QuestionVO questionVO = new QuestionVO();

            questionVO.setId(manageActivityQuestion.getId());

            questionVO.setClassify(manageActivityQuestion.getClassify() == 1 ? "客观题" : "主观题");

            questionVO.setType(dictionaryService.getDictionaryPO(manageActivityQuestion.getTypeId()).getName());

            questionVO.setQuestion(manageActivityQuestion.getQuestion());

            list.add(questionVO);

        }

        PageResult<QuestionVO> questionVOPageResult = new PageResult<>(pageInfo, list);

        return JsonResult.success(questionVOPageResult);
    }

    /**
     * List task question
     *
     * @param activityId
     * @param taskId
     * @return
     */
    @Override
    public JsonResult listActivityTaskQuestion(Long activityId, Long taskId) {


        ManageActivityTaskItemExample itemExample = new ManageActivityTaskItemExample();

        itemExample.createCriteria().andTaskIdEqualTo(taskId).andActivityIdEqualTo(activityId);

        List<ManageActivityTaskItem> manageActivityTaskItems = taskItemMapper.selectByExample(itemExample);

        List<TaskQuestionVO> list = new ArrayList<>();

        for (ManageActivityTaskItem manageActivityTaskItem : manageActivityTaskItems) {

            ManageActivityQuestion question = questionMapper.selectByPrimaryKey(manageActivityTaskItem.getQuestionId());

            TaskQuestionVO questionVO = new TaskQuestionVO();

            questionVO.setQuestionId(question.getId());

            questionVO.setActivityId(activityId);

            questionVO.setTaskId(taskId);

            questionVO.setClassify(question.getClassify() == 1 ? "客观题" : "主观题");

            questionVO.setType(dictionaryService.getDictionaryPO(question.getTypeId()).getName());

            questionVO.setQuestion(question.getQuestion());

            questionVO.setId(manageActivityTaskItem.getId());

            list.add(questionVO);
        }


        return JsonResult.success(list);
    }

    /**
     * Del task question
     *
     * @param token
     * @param activityId
     * @param taskId
     * @param id
     * @return
     */
    @Override
    public JsonResult delTaskQuestion(String token, Long activityId, Long taskId, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        List<Long> longs = taskItemMapper.selectQuestionIds(activityId, taskId);

        if (longs.size() <= 1) {
            return JsonResult.error("任务不能一个问题都没有");
        }

        taskItemMapper.deleteByPrimaryKey(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Add task question
     *
     * @param activityId
     * @param taskId
     * @param checkeds
     * @param token
     * @return
     */
    @Override
    public JsonResult addTaskQuestion(String token, Long activityId, Long taskId, String checkeds) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        List<Long> ids = taskItemMapper.selectQuestionIds(activityId, taskId);

        String[] questions = checkeds.split(",");

        for (String questionId : questions) {

            if (!ids.contains(Long.valueOf(questionId))) {
                ManageActivityTaskItem manageActivityTaskItem = new ManageActivityTaskItem();

                manageActivityTaskItem.setId(null);

                manageActivityTaskItem.setActivityId(activityId);

                manageActivityTaskItem.setQuestionId(Long.valueOf(questionId));

                manageActivityTaskItem.setTaskId(taskId);

                manageActivityTaskItem.setUpdateUser(tokeUser.getId());

                manageActivityTaskItem.setUpdateTime(new Date());

                taskItemMapper.insertSelective(manageActivityTaskItem);
            }

        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }


    /**
     * Update activity sign status
     *
     * @param token
     * @param activityId
     * @param status
     * @return
     */
    @Override
    public JsonResult updateActivitySingStatus(String token, Long activityId, int status) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivity manageActivity = new ManageActivity();

        manageActivity.setId(activityId);

        manageActivity.setSign(status);

        if (status == 2) {
            manageActivity.setCheckSign(0);
        } else {
            manageActivity.setCheckSign(3);
        }

        manageActivity.setUpdateUser(tokeUser.getId());

        manageActivity.setUpdateTime(new Date());

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Check over activity
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult checkOverActivity(Long id) {

        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(id);

        int checkStatus = 3;

        List<String> errors = new ArrayList<>();

        Date beginTime = manageActivity.getBeginTime();

        Date endTime = manageActivity.getEndTime();

        if (TimeUtils.lessThanNow(beginTime)) {
            errors.add("活动开始时间小于当期时间");
        }

        if (TimeUtils.lessThanNow(endTime)) {
            errors.add("活动结束时间小于当期时间");
        }

        if (manageActivity.getCloseType() == 1) {
            Date closeTime = manageActivity.getCloseTime();
            if (TimeUtils.Obj1LessObj2(beginTime, closeTime)) {
                errors.add("活动报名截至时间小于活动开始时间");
            }
        }

        //introduce
        if (manageActivity.getCheckIntroduce() == checkStatus) {

            List<ManageActivityIntroduce> introduces = introduceMapper.selectByActivityId(id);

            if (introduces.size() == 0) {
                errors.add("未设置活动介绍");
            }
        }

        //apply
        if (manageActivity.getCheckApply() == checkStatus) {

            List<ManageActivityApply> applies = applyMapper.selectByActivityId(id);

            if (applies.size() == 0) {
                errors.add("未设置活动适用年段");
            }
        }


        //leader
        if (manageActivity.getCheckLeader() == checkStatus) {

            List<ManageActivityLeader> leaders = leaderMapper.selectByActivityId(id);

            if (leaders.size() == 0) {
                errors.add("未设置活动负责人");
            }

            int i = 0;

            for (ManageActivityLeader leader : leaders) {

                if (leader.getMain() == 1) {
                    i = 1;
                    break;
                }
            }

            if (i == 0) {
                errors.add("未设置活动主负责人");
            }


        }

        //sign
        if (manageActivity.getCheckSign() == checkStatus) {

            List<ManageActivitySign> signs = signMapper.selectByActivityId(id);

            if (signs.size() == 0) {
                errors.add("未设置签到信息");
            }

            ManageActivitySign sign = signs.get(0);

            if (sign.getSignIn() == 0 && sign.getSignOut() == 0) {
                errors.add("未开启签到、签退");
            }
            if (sign.getSignIn() == 1 && StringUtils.isBlank(sign.getSignInErcode())) {
                errors.add("开启签到但未设置签到二维码");
            }
            if (sign.getSignOut() == 1 && StringUtils.isBlank(sign.getSignOutErcode())) {
                errors.add("开启签退但未设置签退二维码");
            }


        }

        //supervise
        if (manageActivity.getCheckSupervise() == checkStatus) {

            List<ManageActivitySupervise> supervises = superviseMapper.selectByActivityId(id);

            if (supervises.size() == 0) {
                errors.add("未设置监督人员");
            }
        }

        //enroll
        if (manageActivity.getCheckEnroll() == checkStatus) {

            List<ManageActivityEnroll> enrolls = enrollMapper.selectByActivityId(id);

            if (enrolls.size() == 0) {
                errors.add("未设置报名信息");
            }

            ManageActivityEnroll enroll = enrolls.get(0);

            if (enroll.getName() == 0) {

                errors.add("报名采集信息设置有误");
            }

        }

        //task
        if (manageActivity.getCheckTask() == checkStatus) {

            List<ManageActivityTask> tasks = taskMapper.selectByActivityId(id);

            if (tasks.size() == 0) {

                errors.add("未设置活动任务");

            } else {

                for (ManageActivityTask task : tasks) {
                    Integer limitNum = task.getLimitNum();

                    List<Long> questionIds = taskItemMapper.selectQuestionIds(id, task.getId());

                    if (questionIds.size() == 0) {
                        errors.add("[" + task.getTitle() + "] 任务没有设置题目");
                    }
                    if (questionIds.size() < limitNum) {
                        errors.add("[" + task.getTitle() + "] 任务题目数小于任务最低完成个数");
                    }

                }

            }

        }

        if (errors.size() == 0) {
            manageActivity.setStatus(8);
            activityMapper.updateByPrimaryKeySelective(manageActivity);

            return JsonResult.success("活动信息完整，可以提交啦");
        }


        return JsonResult.error(StringUtils.join(errors, "</br>"));
    }

    /**
     * To verfiy activity
     *
     * @param token
     * @param id
     * @return
     */
    @Override
    public JsonResult toVerfiy(String token, Long id) {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivity manageActivity = new ManageActivity();

        manageActivity.setId(id);

        manageActivity.setStatus(3);

        manageActivity.setUpdateTime(new Date());

        manageActivity.setUpdateUser(tokeUser.getId());

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List activity verity
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listVerifyActivity(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityExample example = new ManageActivityExample();

        ManageActivityExample.Criteria criteria = example.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(3);

        String key1 = "name", key2 = "id";
        if (param.getFiled(key1) != null) {
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key1)));
        }
        if (param.getFiled(key2) != null) {
            criteria.andIdEqualTo(Long.valueOf(param.getFiled(key2)));
        }

        example.setOrderByClause("update_time desc");

        List<ManageActivity> manageActivities = activityMapper.selectByExample(example);

        for (ManageActivity manageActivity : manageActivities) {

            manageActivity.setType(typeMapper.selectByPrimaryKey(manageActivity.getTypeId()).getName());

            manageActivity.setClassify(classifyMapper.selectByPrimaryKey(manageActivity.getClassifyId()).getName());

            manageActivity.setTheme(themeMapper.selectByPrimaryKey(manageActivity.getThemeId()).getName());
        }

        PageInfo<ManageActivity> manageActivityPageInfo = new PageInfo<>(manageActivities);

        return JsonResult.success(manageActivityPageInfo);
    }

    /**
     * List online activity
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listOnlineActivity(PageSearchParam param) {
        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityExample example = new ManageActivityExample();

        ManageActivityExample.Criteria criteria = example.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(6);

        String key1 = "name", key2 = "id";

        if (param.getFiled(key1) != null) {
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key1)));
        }
        if (param.getFiled(key2) != null) {
            criteria.andIdEqualTo(Long.valueOf(param.getFiled(key2)));
        }

        example.setOrderByClause("update_time desc");

        List<ManageActivity> manageActivities = activityMapper.selectByExample(example);

        for (ManageActivity manageActivity : manageActivities) {

            manageActivity.setType(typeMapper.selectByPrimaryKey(manageActivity.getTypeId()).getName());

            manageActivity.setClassify(classifyMapper.selectByPrimaryKey(manageActivity.getClassifyId()).getName());

            manageActivity.setTheme(themeMapper.selectByPrimaryKey(manageActivity.getThemeId()).getName());
        }

        PageInfo<ManageActivity> manageActivityPageInfo = new PageInfo<>(manageActivities);

        return JsonResult.success(manageActivityPageInfo);
    }

    /**
     * Pass activity
     *
     * @param id
     * @return
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public JsonResult passActivity(Long id) throws ServiceException {

        ManageActivity manageActivity = new ManageActivity();

        manageActivity.setId(id);

        manageActivity.setStatus(6);

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        ActivitySolrItemDTO activitySolrItemDTO = this.getActivitySolrItemDTO(id);

        cacheService.setActvitySolrItemDTO(activitySolrItemDTO);

        Boolean aBoolean = searchService.addActivityItem(activitySolrItemDTO);

        if (!aBoolean) {

            cacheService.clearActivitySolrItemDTO(id);

            throw new ServiceException(OperateEnum.SOLR_ADD_ERROR.getStateInfo());
        }

        manageActivity = activityMapper.selectByPrimaryKey(id);

        if(manageActivity.getStock()>0){
            //创建库存队列

            cacheService.clearActivityStockQueue(id);

            byte[][] queues = new byte[manageActivity.getStock()][];

            for (int i = 0; i < manageActivity.getStock(); ++i) {
                byte[] serializer = SerializeUtils.serializer(new ActivitySkuDTO(id,manageActivity.getName(),manageActivity.getMoney()));
                queues[i] = serializer;
            }

            cacheService.createActivityStockQueue(id,queues);

        }

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Reject activity
     *
     * @param id
     * @param reason
     * @return
     */
    @Override
    public JsonResult rejectActivity(Long id, String reason) {

        ManageActivity manageActivity = new ManageActivity();

        manageActivity.setId(id);

        manageActivity.setStatus(7);

        manageActivity.setReason(reason);

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get activity view
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getActivityView(Long id) {

        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(id);


        manageActivity.setType(typeMapper.selectByPrimaryKey(manageActivity.getTypeId()).getName());

        manageActivity.setClassify(classifyMapper.selectByPrimaryKey(manageActivity.getClassifyId()).getName());

        manageActivity.setTheme(themeMapper.selectByPrimaryKey(manageActivity.getThemeId()).getName());

        if (manageActivity.getCloseType() == 1) {
            manageActivity.setCloseTimeStr(TimeUtils.getDateStringShort(manageActivity.getCloseTime()));
        }

        Date beginTime = manageActivity.getBeginTime();

        Date endTime = manageActivity.getEndTime();

        manageActivity.setTimeStr(TimeUtils.getDateString(beginTime) + " - " + TimeUtils.getDateString(endTime));

        if (manageActivity.getBaseId() != 0L) {
            String name = basesService.getBasesPO(manageActivity.getBaseId()).getName();
            manageActivity.setBaseName(name);
        }


        ActivityVerifyVO verifyVO = new ActivityVerifyVO();

        verifyVO.setBase(manageActivity);

        List<ManageActivityIntroduce> introduces = introduceMapper.selectByActivityId(id);

        ManageActivityIntroduce introduce = introduces.get(0);

        introduce.setProName(areaService.getProvice(introduce.getPid()).getName());

        introduce.setCityName(areaService.getCity(introduce.getCid()).getName());

        introduce.setAreaName(areaService.getArea(introduce.getAid()).getName());

        verifyVO.setIntroduce(introduce);

        List<ManageActivityApply> applies = applyMapper.selectByActivityId(id);

        for (ManageActivityApply apply : applies) {
            apply.setGradeName(dictionaryService.getDictionaryPO(apply.getGradeId()).getName());
        }

        verifyVO.setActivityApplyList(applies);

        List<ManageActivityLeader> leaders = leaderMapper.selectByActivityId(id);

        for (ManageActivityLeader leader : leaders) {

            leader.setUserName(userService.getUserPO(leader.getUserId()).getNickName());
        }

        verifyVO.setLeaderList(leaders);

        List<ManageActivityAttention> attentions = attentionMapper.selectByActivityId(id);

        for (ManageActivityAttention attention : attentions) {

            attention.setTypeStr(dictionaryService.getDictionaryPO(attention.getType()).getName());
        }

        verifyVO.setAttentionList(attentions);

        if (manageActivity.getCheckSupervise() == 0) {
            verifyVO.setSuperviseList(null);
        } else {

            List<ManageActivitySupervise> supervises = superviseMapper.selectByActivityId(id);

            for (ManageActivitySupervise supervise : supervises) {

                supervise.setUserName(userService.getUserPO(supervise.getUserId()).getNickName());

            }
            verifyVO.setSuperviseList(supervises);

        }

        List<ManageActivityEnroll> enrolls = enrollMapper.selectByActivityId(id);

        ManageActivityEnroll enroll = enrolls.get(0);

        verifyVO.setEnroll(enroll);

        if (manageActivity.getCheckSign() == 0) {
            verifyVO.setSign(null);
        } else {
            List<ManageActivitySign> signs = signMapper.selectByActivityId(id);

            verifyVO.setSign(signs.get(0));
        }

        List<TaskDTO> list = new ArrayList<>();

        List<ManageActivityTask> tasks = taskMapper.selectByActivityId(id);

        ManageActivityQuestionExample example = new ManageActivityQuestionExample();

        for (ManageActivityTask task : tasks) {

            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setTask(task);


            List<Long> questionIds = taskItemMapper.selectQuestionIds(id, task.getId());

            example.clear();

            example.createCriteria().andIdIn(questionIds);

            List<ManageActivityQuestion> manageActivityQuestions = questionMapper.selectByExample(example);

            taskDTO.setQuestionList(manageActivityQuestions);

            list.add(taskDTO);


        }

        verifyVO.setTaskList(list);


        return JsonResult.success(verifyVO);
    }

    /**
     * Offline activity
     *
     * @param token
     * @param id
     * @return
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public JsonResult offline(String token, Long id) throws ServiceException {

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(id);


        ManageActivityEnrollRecordExample recordExample = new ManageActivityEnrollRecordExample();

        recordExample.createCriteria().andActivityIdEqualTo(id).andStatusGreaterThanOrEqualTo(8);

        long l = enrollRecordMapper.countByExample(recordExample);

        if (l > 0) {
            return JsonResult.error("已经有报名学生，不可下线");
        }

        manageActivity.setStatus(9);

        manageActivity.setUpdateTime(new Date());

        manageActivity.setUpdateUser(tokeUser.getId());

        activityMapper.updateByPrimaryKeySelective(manageActivity);

        Boolean aBoolean = searchService.removeActivityItem(id);

        if (!aBoolean) {
            throw new ServiceException(OperateEnum.SOLR_DEL_ERROR.getStateInfo());
        }

        if(manageActivity.getStock()>0){
            cacheService.clearActivityStockQueue(id);
        }
        cacheService.clearActivitySolrItemDTO(id);

        cacheService.clearActivityDetail(id);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List enroll record
     *
     * @param param
     * @param activityId
     * @return
     */
    @Override
    public JsonResult listEnrollRecordList(PageSearchParam param, Long activityId) {

        PageHelper.startPage(param.getPageIndex(), param.getPageSize());

        ManageActivityEnrollRecordExample recordExample = new ManageActivityEnrollRecordExample();

        ManageActivityEnrollRecordExample.Criteria criteria = recordExample.createCriteria().andActivityIdEqualTo(activityId).andStatusNotEqualTo(0);

        String key1 = "schoolId", key2 = "name", key3 = "status";

        if (param.getFiled(key1) != null) {
            criteria.andSchoolIdEqualTo(Long.valueOf(param.getFiled(key1)));
        }
        if (param.getFiled(key2) != null) {
            criteria.andNameLike(CommonUtils.getLikeSql(param.getFiled(key2)));
        }
        if (param.getFiled(key3) != null) {
            criteria.andStatusEqualTo(Integer.valueOf(param.getFiled(key3)));
        }

        List<ManageActivityEnrollRecord> manageActivityEnrollRecords = enrollRecordMapper.selectByExample(recordExample);

        for (ManageActivityEnrollRecord record : manageActivityEnrollRecords) {

            record.setSchoolName(schoolService.getSchoolPO(record.getSchoolId()).getName());

            record.setPeriodName(dictionaryService.getDictionaryPO(record.getPeriodId()).getName());

            record.setClassName(dictionaryService.getDictionaryPO(record.getClassId()).getName());

            if (record.getNation() != null) {
                record.setNationName(dictionaryService.getDictionaryPO(record.getNation()).getName());
            }

            record.setTimeStr(TimeUtils.getDateString(record.getUpdateTime()));
        }


        PageInfo<ManageActivityEnrollRecord> pageInfo = new PageInfo<>(manageActivityEnrollRecords);


        return JsonResult.success(pageInfo);
    }


    /**
     * Get activitySolrItemDTO
     *
     * @param activityId
     * @return
     */
    @Override
    public ActivitySolrItemDTO getActivitySolrItemDTO(Long activityId) {

        ActivitySolrItemDTO solrItemDTO = new ActivitySolrItemDTO();

        ManageActivity activity = activityMapper.selectByPrimaryKey(activityId);

        solrItemDTO.setId(activityId);

        solrItemDTO.setTypeId(activity.getTypeId());

        solrItemDTO.setClassifyId(activity.getClassifyId());

        solrItemDTO.setThemeId(activity.getThemeId());

        solrItemDTO.setName(activity.getName());

        solrItemDTO.setOrganizeId(activity.getOrganizeId());

        solrItemDTO.setBaseId(activity.getBaseId());

        solrItemDTO.setDuration(activity.getDuration());

        solrItemDTO.setDurationType(activity.getDurationType());

        solrItemDTO.setSelf(activity.getSelf());

        Integer money = activity.getMoney();

        BigDecimal divide = new BigDecimal(money).divide(new BigDecimal(100));

        solrItemDTO.setMoney(divide.floatValue());

        solrItemDTO.setBeginTime(activity.getBeginTime());

        solrItemDTO.setEndTime(activity.getEndTime());

        solrItemDTO.setCloseTime(activity.getCloseTime());

        /**
         * 默认 5分好评
         */
        String activityScore = cacheService.getActivityScore(activityId);

        solrItemDTO.setScore(Float.valueOf(activityScore));

        long enrolledCount = enrollRecordMapper.getEnrolledCount(activityId);

        solrItemDTO.setEnroll((int) enrolledCount);

        long collectCount = collectMapper.getCollectCount(activityId);

        solrItemDTO.setCollect((int) collectCount);

        solrItemDTO.setStatus(activity.getStatus());

        solrItemDTO.setSupervise(activity.getCheckSupervise());

        solrItemDTO.setNumber(activity.getNumber());

        String typeName = typeMapper.selectByPrimaryKey(activity.getTypeId()).getName();

        solrItemDTO.setTypeName(typeName);

        String classifyName = classifyMapper.selectByPrimaryKey(activity.getClassifyId()).getName();

        solrItemDTO.setClassifyName(classifyName);

        String themeName = themeMapper.selectByPrimaryKey(activity.getThemeId()).getName();

        solrItemDTO.setThemeName(themeName);

        String organizeName = dictionaryService.getDictionaryPO(activity.getOrganizeId()).getName();

        solrItemDTO.setOrganizeName(organizeName);

        String baseName = "";
        if (activity.getBaseId() == 0L) {
            solrItemDTO.setBaseName("");
        } else {
            baseName = basesService.getBasesPO(activity.getBaseId()).getName();
            solrItemDTO.setBaseName(baseName);
        }

        String name = activity.getName();

        List<String> pinyinList = new ArrayList<>();

        pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(name)));

        pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(typeName)));

        pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(classifyName)));

        pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(themeName)));

        pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(organizeName)));

        if (StringUtils.isNotBlank(baseName)) {
            pinyinList.add(PinYinUtils.cnToPinYin(ValidatorUtils.eliminate(baseName)));
        }

        solrItemDTO.setPinyin(StringUtils.join(pinyinList, " "));

        List<ManageActivityIntroduce> manageActivityIntroduces = introduceMapper.selectByActivityId(activityId);

        if (manageActivityIntroduces.size() > 0) {

            solrItemDTO.setImgCover(manageActivityIntroduces.get(0).getImgCover());
            solrItemDTO.setPid(manageActivityIntroduces.get(0).getPid());
            solrItemDTO.setCid(manageActivityIntroduces.get(0).getCid());
            solrItemDTO.setAid(manageActivityIntroduces.get(0).getAid());

        } else {

            solrItemDTO.setImgCover("");
            solrItemDTO.setPid(0L);
            solrItemDTO.setCid(0L);
            solrItemDTO.setAid(0L);
        }

        List<ManageActivityApply> manageActivityApplies = applyMapper.selectByActivityId(activityId);

        List<Long> apply = new ArrayList<>();

        for (ManageActivityApply manageActivityApply : manageActivityApplies) {

            apply.add(manageActivityApply.getGradeId());
        }

        solrItemDTO.setApply(StringUtils.join(apply, ","));

        solrItemDTO.setSign(activity.getSign());

        solrItemDTO.setCloseType(activity.getCloseType());

        String validTime = activity.getValidTime();

        if (StringUtils.isNotBlank(validTime)) {
            String[] split = validTime.split(" - ");
            String time1 = split[0].substring(0, 5);
            String time2 = split[1].substring(0, 5);
            solrItemDTO.setTime(time1 + " - " + time2);

        } else {
            solrItemDTO.setTime("");
        }


        return solrItemDTO;
    }


    /**
     * List actvity type
     *
     * @return
     */
    @Override
    public List<KeyValueDTO> listTypeKV() {

        ManageActivityTypeExample example = new ManageActivityTypeExample();

        example.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1);

        List<ManageActivityType> manageActivityTypes = typeMapper.selectByExample(example);

        List<KeyValueDTO> list = new ArrayList<>();

        for (ManageActivityType type : manageActivityTypes) {

            list.add(new KeyValueDTO(type.getId(), type.getName()));

        }

        return list;
    }

    /**
     * Get activity detail
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult getActivityDetail(Long id, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ActivityDetailVO detailVO = cacheService.getActivityDetail(id);

        if(detailVO == null){
            detailVO = new ActivityDetailVO();

            ManageActivity activity = activityMapper.selectByPrimaryKey(id);

            List<ManageActivityIntroduce> introduces = introduceMapper.selectByActivityId(id);

            ManageActivityIntroduce introduce = introduces.get(0);

            detailVO.setId(id);

            detailVO.setImgCover(introduce.getImgCover());

            detailVO.setName(activity.getName());

            detailVO.setType(typeMapper.selectByPrimaryKey(activity.getTypeId()).getName());

            detailVO.setClassify(classifyMapper.selectByPrimaryKey(activity.getClassifyId()).getName());

            detailVO.setTheme(themeMapper.selectByPrimaryKey(activity.getThemeId()).getName());

            List<ManageActivityTask> tasks = taskMapper.selectByActivityId(id);

            detailVO.setTaskNum(tasks.size());

            detailVO.setNumber(activity.getNumber());

            detailVO.setEnroll(0);

            detailVO.setCollect(0);

            detailVO.setCloseType(activity.getCloseType());

            detailVO.setCloseTime(TimeUtils.getDateStringShort(activity.getCloseTime()));

            detailVO.setBeginTime(TimeUtils.getDateStringShort(activity.getBeginTime()));

            detailVO.setEndTime(TimeUtils.getDateStringShort(activity.getEndTime()));

            detailVO.setBaseId(activity.getBaseId());

            if(activity.getBaseId()!=0L){

                ManageBase basesPO = basesService.getBasesPO(activity.getBaseId());
                detailVO.setBaseName( basesPO.getName());
                detailVO.setBaseCover(basesPO.getImgCover());

            }else{
                detailVO.setBaseName("");
                detailVO.setBaseCover("");
            }

            BigDecimal minutes = new BigDecimal(activity.getDuration());

            BigDecimal hour = minutes.divide(new BigDecimal(60), 1, BigDecimal.ROUND_DOWN);

            detailVO.setDuration(hour.toString());

            detailVO.setDurationType(activity.getDurationType());

            detailVO.setAddress(introduce.getAddress());

            detailVO.setIntroduce(introduce.getDetail());

            String price = new BigDecimal(activity.getMoney()).divide(new BigDecimal(100)).toPlainString();
            detailVO.setPrice(price);

            detailVO.setSign(activity.getSign());

            detailVO.setSelf(activity.getSelf());

            if (StringUtils.isNotBlank(activity.getValidTime())) {
                String[] split = activity.getValidTime().split(" - ");
                String time1 = split[0].substring(0, 5);
                String time2 = split[1].substring(0, 5);
                detailVO.setTime(time1 + " - " + time2);

            } else {
                detailVO.setTime("");
            }
            List<String> list = new ArrayList<>();
            List<ManageActivityAttention> attentions = attentionMapper.selectByActivityId(id);
            for (ManageActivityAttention attention : attentions) {

                list.add(attention.getDoc());
            }
            detailVO.setAttentions(list);

            List<String> listApply = new ArrayList<>();
            List<ManageActivityApply> applies = applyMapper.selectByActivityId(id);
            for (ManageActivityApply apply : applies) {
                listApply.add(dictionaryService.getDictionaryPO(apply.getGradeId()).getName());
            }
            detailVO.setApplys(listApply);



            cacheService.setActivityDetail(id,detailVO);
        }

        ManageActivity manageActivity = activityMapper.selectByPrimaryKey(id);

        detailVO.setStatus(manageActivity.getStatus());

        detailVO.setMoneyDesc(manageActivity.getMoneyDesc());


        //报名数

        long l = enrollRecordMapper.getEnrolledCount(id);

        detailVO.setEnroll((int) l);

        /**
         * 我是否报名不做判断，因为 用户可能有多个孩子
         */
        detailVO.setMyEnroll(0);




        long l1 = collectMapper.getCollectCount(id);

        detailVO.setCollect((int) l1);

        ManageActivityCollectExample collectExample = new ManageActivityCollectExample();

        collectExample.createCriteria().andActivityIdEqualTo(id);

        collectExample.clear();

        collectExample.createCriteria().andActivityIdEqualTo(id).andParentIdEqualTo(tokenParent.getId());

        long l2 = collectMapper.countByExample(collectExample);

        if(l2==0L){
            detailVO.setMyConllect(0);
        }else{
            detailVO.setMyConllect(1);
        }



        return JsonResult.success(detailVO);
    }

    /**
     * Collect activity
     *
     * @param id
     * @param token
     * @return
     */
    @Override
    public JsonResult collectActivity(Long id, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivityCollectExample example = new ManageActivityCollectExample();

        example.createCriteria().andActivityIdEqualTo(id).andParentIdEqualTo(tokenParent.getId());

        long l = collectMapper.countByExample(example);

        if(l>0){
            return JsonResult.error("您已经收藏，请勿重复收藏");
        }else{
            ManageActivityCollect collect = new ManageActivityCollect();

            collect.setActivityId(id);
            collect.setParentId(tokenParent.getId());

            collectMapper.insertSelective(collect);

            //发送消息 更新solr
            SolrUpdateMessage solrUpdateMessage = new SolrUpdateMessage();

            solrUpdateMessage.setType(2);

            solrUpdateMessage.setId(id);

            solrUpdateMessage.setStatus(1);

            solrUpdateMessage.setCreateTime(new Date());

            activeMqProducer.sendSolrUpdateMessage(solrUpdateMessage);

            cacheService.setSolrUpdateMessage(solrUpdateMessage);


        }


        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * List collect
     *
     * @param token
     * @return
     */
    @Override
    public JsonResult listCollect(String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        List<Long> activityIds = collectMapper.listActivityIdsByParentId(tokenParent.getId());

        List<ActivityListItemVO> list = new ArrayList<>();

        for (Long activityId : activityIds) {

            ActivityListItemVO activityListItemDTO = this.getActivityListItemDTO(activityId);

            list.add(activityListItemDTO);

        }

        return JsonResult.success(list);
    }

    /**
     * collect cancle
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult collectActivityCancle(Long activityId, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivityCollectExample example = new ManageActivityCollectExample();

        example.createCriteria().andParentIdEqualTo(tokenParent.getId()).andActivityIdEqualTo(activityId);

        collectMapper.deleteByExample(example);

        return JsonResult.success(OperateEnum.SUCCESS);
    }

    /**
     * Get Activity
     *
     * @param activityId
     * @return
     */
    @Override
    public ManageActivity getActivity(Long activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    /**
     * List theme by classifyId
     *
     * @param classifyId
     * @return
     */
    @Override
    public JsonResult listThemeCache(Long classifyId) {

        List<KeyValueDTO> list = cacheService.getTheme(classifyId);

        if(list==null){

            list = this.listThemeUsablePO(classifyId);

            cacheService.setTheme(classifyId,list);

        }
        return JsonResult.success(list);

    }


    /**
     * Get Activity list item VO
     *
     * @param activityId
     * @return
     */
    @Override
    public ActivityListItemVO getActivityListItemDTO(Long activityId) {

        ManageActivity activity = activityMapper.selectByPrimaryKey(activityId);

        ActivityListItemVO itemVO = new ActivityListItemVO();

        itemVO.setId(activityId);

        itemVO.setName(activity.getName());

        List<ManageActivityIntroduce> manageActivityIntroduces = introduceMapper.selectByActivityId(activityId);

        if(manageActivityIntroduces.size()>0){
            itemVO.setImgCover(manageActivityIntroduces.get(0).getImgCover());
        }else{
            itemVO.setImgCover("");
        }


        String price = new BigDecimal(activity.getMoney()).divide(new BigDecimal(100)).toPlainString();

        itemVO.setPrice(price);

        itemVO.setBeginTime(TimeUtils.getDateStringShort(activity.getBeginTime()));

        itemVO.setEndTime(TimeUtils.getDateStringShort(activity.getEndTime()));

        itemVO.setNumber(activity.getNumber());

        String ducation = new BigDecimal(activity.getDuration()).divide(new BigDecimal(60)).toPlainString();

        itemVO.setDuration(ducation);

        long l = enrollRecordMapper.getEnrolledCount(activityId);

        itemVO.setEnrolled((int) l);

        long collectCount = collectMapper.getCollectCount(activityId);

        itemVO.setCollect((int) collectCount);

        itemVO.setSelf(activity.getSelf());

        String score = cacheService.getActivityScore(activityId);

        itemVO.setScore(score);

        itemVO.setSign(activity.getSign());

        itemVO.setDurationType(activity.getDurationType());

        itemVO.setCloseType(activity.getCloseType());

        itemVO.setCloseTime(TimeUtils.getDateStringShort(activity.getCloseTime()));

        String validTime = activity.getValidTime();

        if (StringUtils.isNotBlank(validTime)) {
            String[] split = validTime.split(" - ");
            String time1 = split[0].substring(0, 5);
            String time2 = split[1].substring(0, 5);
            itemVO.setTime(time1 + " - " + time2);

        } else {
            itemVO.setTime("");
        }

        itemVO.setStatus(activity.getStatus());

        itemVO.setSupervise(activity.getCheckSupervise());

        return itemVO;
    }


    /**
     * Update Solr
     *
     * @param solrUpdateMessage
     */
    @Override
    public void excuteSolrUpdate(SolrUpdateMessage solrUpdateMessage) {

        Integer keyLike = 1,keyCollect = 2,keyEnroll = 3,keyStatus = 4;
        /**
         * 更新like
         */
        if(solrUpdateMessage.getType().equals(keyLike)){
            //TODO 评分
        }
        /**
         * 更新collect
         */
        if(solrUpdateMessage.getType().equals(keyCollect)){

            long collectCount = collectMapper.getCollectCount(solrUpdateMessage.getId());

            if(searchService.updateCollectCount(solrUpdateMessage.getId(),collectCount)){

                solrUpdateMessage.setUpdateTime(new Date());
                solrUpdateMessage.setStatus(2);
                cacheService.setSolrUpdateMessage(solrUpdateMessage);
            }

        }
        /**
         * 更新enroll
         */
        if(solrUpdateMessage.getType().equals(keyEnroll)){
            long enrolledCount = enrollRecordMapper.getEnrolledCount(solrUpdateMessage.getId());

            if(searchService.updateEnrollCount(solrUpdateMessage.getId(),enrolledCount)){
                solrUpdateMessage.setUpdateTime(new Date());
                solrUpdateMessage.setStatus(2);
                cacheService.setSolrUpdateMessage(solrUpdateMessage);
            }
        }

        /**
         * 更新status
         */
        if(solrUpdateMessage.getType().equals(keyStatus)){

            ManageActivity activity = activityMapper.selectByPrimaryKey(solrUpdateMessage.getId());

            if(searchService.updateStatus(solrUpdateMessage.getId(),activity.getStatus())){
                solrUpdateMessage.setUpdateTime(new Date());
                solrUpdateMessage.setStatus(2);
                cacheService.setSolrUpdateMessage(solrUpdateMessage);
            }
        }
    }


}
