package com.practice.service.impl;

import com.practice.dto.ActivitySolrItemDTO;
import com.practice.dto.OrderPreviewDTO;
import com.practice.dto.TokenParentDTO;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.ActivityService;
import com.practice.service.CacheService;
import com.practice.service.DictionaryService;
import com.practice.service.OrderService;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.OrderNumUtil;
import com.practice.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xushd on 2018/2/1 14:38
 */
@Service
public class OrderServiceImpl implements OrderService {


    @Resource
    private ActivityService activityService;
    @Resource
    private CacheService cacheService;
    @Resource
    private DictionaryService dictionaryService;
    @Resource
    private ManageActivityEnrollRecordMapper enrollRecordMapper;
    @Resource
    private ManageStudentMapper studentMapper;
    @Resource
    private OrderInfoMapper orderMapper;
    @Resource
    private ParentActivityLinkMapper parentActivityLinkMapper;

    @Value("${SERVER.ID}")
    private String SERVERID;
    /**
     * Get order preview info
     *
     * @param activityId
     * @param token
     * @return
     */
    @Override
    public JsonResult getOrderInfoPreview(Long activityId, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ActivitySolrItemDTO solrItemDTO = cacheService.getActvitySolrItemDTO(activityId);
        if(solrItemDTO == null){
            solrItemDTO = activityService.getActivitySolrItemDTO(activityId);
        }

        OrderPreviewDTO orderPreviewDTO = new OrderPreviewDTO();

        orderPreviewDTO.setActivityId(activityId);

        orderPreviewDTO.setActivityName(solrItemDTO.getName());

        orderPreviewDTO.setImgCover(solrItemDTO.getImgCover());

        orderPreviewDTO.setPrice(String.valueOf(solrItemDTO.getMoney()));

        String apply = solrItemDTO.getApply();

        orderPreviewDTO.setApply(apply);

        String[] split = apply.split(",");

        List<String> applys = new ArrayList<>();

        for (String s : split) {

            ManageDictionary dictionaryPO = dictionaryService.getDictionaryPO(Long.valueOf(s));

            applys.add(dictionaryPO.getName());

        }
        orderPreviewDTO.setApplys(applys);

        orderPreviewDTO.setType(solrItemDTO.getTypeName());

        orderPreviewDTO.setClassify(solrItemDTO.getClassifyName());

        orderPreviewDTO.setTheme(solrItemDTO.getThemeName());

        orderPreviewDTO.setBaseName(solrItemDTO.getBaseName());

        orderPreviewDTO.setSign(solrItemDTO.getSign());

        orderPreviewDTO.setTime(solrItemDTO.getTime());

        orderPreviewDTO.setBeginAndEnd(TimeUtils.getDateStringShort(solrItemDTO.getBeginTime())+" - "+TimeUtils.getDateStringShort(solrItemDTO.getEndTime()));


        return JsonResult.success(orderPreviewDTO);
    }

    /**
     * Create order
     *
     * @param activityId
     * @param studentId
     * @param token
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public JsonResult createOrder(Long activityId, Long studentId, String token) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ManageActivity activity = activityService.getActivity(activityId);

        ManageStudent student = studentMapper.selectByPrimaryKey(studentId);

        //是否有未支付订单
        ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

        linkExample.createCriteria()
                .andActivityIdEqualTo(activityId)
                .andParentIdEqualTo(tokenParent.getId())
                .andStatusEqualTo(0)
                .andDelflagEqualTo(0);

        long l1 = parentActivityLinkMapper.countByExample(linkExample);
        if(l1>0){
            return JsonResult.error("您有未支付订单，请先支付后，再报名");
        }


        ManageActivityEnrollRecordExample recordExample = new ManageActivityEnrollRecordExample();

        //STEP1 判断活动是否结束
        Integer closeType = activity.getCloseType();
        if(closeType==1){
            //时间
            Date closeTime = activity.getCloseTime();
            if(TimeUtils.lessThanNow(closeTime)){
                return JsonResult.error("报名时间已过");
            }
        }else{
            //名额

            recordExample.createCriteria()
                    .andActivityIdEqualTo(activityId)
                    .andStatusGreaterThanOrEqualTo(8);

            long l = enrollRecordMapper.countByExample(recordExample);

            if(activity.getNumber()-l==0){
                return JsonResult.error("名额已满");
            }
        }


        //STEP2 判断是否重复报名
        linkExample.clear();
        linkExample.createCriteria()
                .andStudentIdEqualTo(studentId)
                .andActivityIdEqualTo(activityId)
                .andDelflagEqualTo(0);

        long l = parentActivityLinkMapper.countByExample(linkExample);
        if(l>0){
            return JsonResult.error("已经报过名了，请勿充重复报名");
        }

        //STEP3 插入报名记录
        ManageActivityEnrollRecord record = new ManageActivityEnrollRecord();

        record.setActivityId(activityId);

        record.setStudentId(studentId);

        record.setSchoolId(student.getSchoolId());

        record.setPeriodId(student.getPeriodId());

        record.setClassId(student.getClassId());

        record.setName(student.getName());

        record.setParentName(tokenParent.getName());

        int mark = 0;
        if(activity.getMoney().compareTo(new BigDecimal(0))==0){
            //免费
            record.setStatus(8);

        }else{
            record.setStatus(9);
            mark = 1;
        }

        record.setUpdateTime(new Date());

        record.setUpdateUser(tokenParent.getId());

        enrollRecordMapper.insertSelective(record);

        //STEP4创建订单
        OrderInfo order = new OrderInfo();

        order.setActivityId(activityId);

        order.setEnrollId(record.getId());

        order.setOrderName(activity.getName());

        //TODO 钱单位是分
        order.setPrice(activity.getMoney().toBigInteger().intValue());

        Date date = new Date();
        if(mark==0){
            order.setStatus(2);
            order.setPayTime(date);
        }else{
            order.setStatus(1);
        }

        long orderNum = OrderNumUtil.getId(Integer.valueOf(SERVERID));

        order.setOrderNum(String.valueOf(orderNum));

        order.setCreateTime(date);

        order.setUserId(tokenParent.getId());

        orderMapper.insertSelective(order);

        //STEP5 插入 家长 报名记录
        ParentActivityLink parentActivityLink = new ParentActivityLink();

        parentActivityLink.setStudentId(studentId);

        parentActivityLink.setParentId(tokenParent.getId());

        parentActivityLink.setActivityId(activityId);

        parentActivityLink.setDelflag(0);

        parentActivityLink.setCreateTime(new Date());

        parentActivityLink.setOrderNum(String.valueOf(orderNum));
        if(mark==0){
            parentActivityLink.setStatus(1);
        }else{
            parentActivityLink.setStatus(0);
        }

        parentActivityLink.setUpdateTime(date);

        parentActivityLink.setCreateTime(date);

        parentActivityLinkMapper.insertSelective(parentActivityLink);

        if(mark==1){
            //TODO 创建一个 15分钟的 延迟消息


        }

        return JsonResult.success(String.valueOf(orderNum),mark);

    }
}
