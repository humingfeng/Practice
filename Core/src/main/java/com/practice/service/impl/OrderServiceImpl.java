package com.practice.service.impl;

import com.practice.dao.ActiveMqProducer;
import com.practice.dto.*;
import com.practice.enums.OperateEnum;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger LOGGER  = LoggerFactory.getLogger(OrderServiceImpl.class);

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
    private ManageActivityMapper activityMapper;
    @Resource
    private ParentActivityLinkMapper parentActivityLinkMapper;
    @Resource
    private ActiveMqProducer activeMqProducer;

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
    public JsonResult createOrder(Long activityId, Long studentId, String token) throws Exception {



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


            //STEP1 判断活动是否报名结束 或则 名额

            ActivitySkuDTO skuDTO = new ActivitySkuDTO();

            Integer closeType = activity.getCloseType();
            if(closeType==1){
                //时间
                Date closeTime = activity.getCloseTime();
                if(TimeUtils.lessThanNow(closeTime)){
                    return JsonResult.error("报名时间已过");
                }
            }else{
                //名额

                skuDTO =  cacheService.getActivitySku(activityId);

                if(skuDTO==null){
                    return JsonResult.error("名额已满");
                }

            }
        int mark = 0;

        try {

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


            if(activity.getMoney()==0){
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

            order.setPrice(activity.getMoney());

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

            if(skuDTO!=null){
                //更新 activity 的 库存
                int i = activityMapper.minusStock(skuDTO.getId());
                if(i==0){
                    cacheService.addActivitySku(skuDTO);
                    throw new Exception("库存异常");
                }
            }

            if(mark==1){

                // 发送一个 15min 延迟 的消息

                OrderPayDelayMessage orderPayDelayMessage = new OrderPayDelayMessage();

                orderPayDelayMessage.setActivityId(activityId);

                orderPayDelayMessage.setOrderNum(String.valueOf(orderNum));

                orderPayDelayMessage.setStatus(0);

                orderPayDelayMessage.setCreateDate(date);

                activeMqProducer.sendOrderPayDelayMessage(orderPayDelayMessage);

                cacheService.setOrderDelayMessage(orderPayDelayMessage);

            }

            return JsonResult.success(String.valueOf(orderNum),mark);

        } catch (Exception e){
            e.printStackTrace();

            //还回库存
            if(skuDTO!=null){
                cacheService.addActivitySku(skuDTO);
            }

            throw new Exception(e.getMessage());

        }

    }

    /**
     * Order 15min over
     *
     * @param orderPayDelayMessage
     */
    @Override
    public void closeOrderPay(OrderPayDelayMessage orderPayDelayMessage) {


        OrderInfoExample orderInfoExample = new OrderInfoExample();

        orderInfoExample.createCriteria().andOrderNumEqualTo(orderPayDelayMessage.getOrderNum());

        List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

        OrderInfo orderInfo = orderInfos.get(0);

        Long activityId = orderPayDelayMessage.getActivityId();

        if(orderInfo.getStatus()==1){
            //如果 订单的状态 还是等待支付 关闭订单
            OrderInfo order = new OrderInfo();

            order.setId(orderInfo.getId());

            order.setStatus(4);

            orderMapper.updateByPrimaryKeySelective(order);
            //报名记录取消

            ManageActivityEnrollRecord enrollRecord = new ManageActivityEnrollRecord();

            enrollRecord.setId(orderInfo.getEnrollId());

            enrollRecord.setStatus(5);

            enrollRecord.setUpdateTime(new Date());

            enrollRecord.setUpdateUser(0L);

            enrollRecordMapper.updateByPrimaryKeySelective(enrollRecord);

            //家长活动订单 记录 取消

            ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

            linkExample.createCriteria()
                    .andOrderNumEqualTo(orderInfo.getOrderNum());

            ParentActivityLink link = new ParentActivityLink();

            link.setStatus(3);

            parentActivityLinkMapper.updateByExampleSelective(link,linkExample);


            OrderPayDelayMessage orderDelayMessage = cacheService.getOrderDelayMessage(orderInfo.getOrderNum());

            orderDelayMessage.setStatus(1);

            orderDelayMessage.setUpdateDate(new Date());

            cacheService.setOrderDelayMessage(orderDelayMessage);

            //判断是否需要还回库存
            ManageActivity manageActivity = activityMapper.selectByPrimaryKey(activityId);

            if(manageActivity.getNumber()!=0){

                activityMapper.addStock(activityId);

                ActivitySkuDTO skuDTO = new ActivitySkuDTO();

                skuDTO.setId(activityId);

                skuDTO.setName(manageActivity.getName());

                skuDTO.setPrice(manageActivity.getMoney());

                cacheService.addActivitySku(skuDTO);

            }

            LOGGER.info("订单15分钟 支付检测 {}",orderInfo.getOrderNum() + TimeUtils.getNowTime());

        }


    }

    /**
     * Get order pay Info
     *
     * @param orderNum
     * @param token
     * @return
     */
    @Override
    public JsonResult getOrderPayInfo(String orderNum, String token) {
        return null;
    }
}
