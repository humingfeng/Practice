package com.practice.service.impl;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageParams;
import com.practice.dao.ActiveMqProducer;
import com.practice.dto.*;
import com.practice.enums.ConstantEnum;
import com.practice.enums.OperateEnum;
import com.practice.mapper.*;
import com.practice.po.*;
import com.practice.result.JsonResult;
import com.practice.service.*;
import com.practice.utils.JwtTokenUtil;
import com.practice.utils.OrderNumUtil;
import com.practice.utils.TimeUtils;
import com.practice.vo.ActivityDetailVO;
import com.practice.weixinpay.sdk.XmlUtil;
import org.apache.commons.lang3.StringUtils;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Xushd on 2018/2/1 14:38
 */
@Service
public class OrderServiceImpl implements OrderService {

    Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

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
    @Resource
    private SchoolService schoolService;
    @Resource
    private AlipayRecordMapper alipayRecordMapper;
    @Resource
    private PersonnelService personnelService;
    @Resource
    private ActivityMessageMapper activityMessageMapper;

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

        ManageActivity activity = activityMapper.selectByPrimaryKey(activityId);

        Integer key1 = 2,key2 = 5,key3 = 1;
        if(activity.getStatus().equals(key1)||activity.getStatus().equals(key2)||activity.getStatus().equals(key3)){

            return JsonResult.error("报名已结束");
        }


        ActivitySolrItemDTO solrItemDTO = cacheService.getActvitySolrItemDTO(activityId);
        if (solrItemDTO == null) {
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

        orderPreviewDTO.setBeginAndEnd(TimeUtils.getDateStringShort(solrItemDTO.getBeginTime()) + " - " + TimeUtils.getDateStringShort(solrItemDTO.getEndTime()));

        orderPreviewDTO.setMoneyDesc(activity.getMoneyDesc());

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
                .andParentIdEqualTo(tokenParent.getId())
                .andStatusEqualTo(0)
                .andDelflagEqualTo(0);

        long l1 = parentActivityLinkMapper.countByExample(linkExample);
        if (l1 > 0) {
            return JsonResult.error("您有未支付订单，请先支付后，再报名");
        }


        //STEP1 判断活动是否报名结束 或则 名额

        ActivitySkuDTO skuDTO = null;

        Integer closeType = activity.getCloseType();
        if (closeType == 1) {
            //时间
            Date closeTime = activity.getCloseTime();
            if (TimeUtils.lessThanNow(closeTime)) {
                return JsonResult.error("报名时间已过");
            }

        } else {
            //名额

            skuDTO = cacheService.getActivitySku(activityId);

            if (skuDTO == null) {
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
                    .andDelflagEqualTo(0)
                    .andStatusEqualTo(1);

            long l = parentActivityLinkMapper.countByExample(linkExample);
            if (l > 0) {
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


            if (activity.getMoney() == 0) {
                //免费
                record.setStatus(8);

            } else {
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
            if (mark == 0) {
                order.setStatus(2);
                order.setPayTime(date);
            } else {
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
            if (mark == 0) {
                parentActivityLink.setStatus(1);
            } else {
                parentActivityLink.setStatus(0);
            }

            parentActivityLink.setUpdateTime(date);

            parentActivityLink.setCreateTime(date);

            parentActivityLinkMapper.insertSelective(parentActivityLink);

            if (skuDTO != null) {
                //更新 activity 的 库存
                int i = activityMapper.minusStock(skuDTO.getId());
                if (i == 0) {
                    cacheService.addActivitySku(skuDTO);
                    throw new Exception("库存异常");
                }
            }

            if (mark == 1) {

                // 发送一个 15min 延迟 的消息

                OrderPayDelayMessage orderPayDelayMessage = new OrderPayDelayMessage();

                orderPayDelayMessage.setActivityId(activityId);

                orderPayDelayMessage.setOrderNum(String.valueOf(orderNum));

                orderPayDelayMessage.setStatus(0);

                orderPayDelayMessage.setCreateDate(date);

                activeMqProducer.sendOrderPayDelayMessage(orderPayDelayMessage);

                cacheService.setOrderDelayMessage(orderPayDelayMessage);

            }else{
                // 发送报名成功推送消息

                ActivityMessage activityMessage = new ActivityMessage();

                activityMessage.setMsgTitle(activity.getName());

                activityMessage.setMsgContent(ConstantEnum.ENROLL_SUCCESS.getStrValue());

                ActivityDetailVO activityDetail = cacheService.getActivityDetail(activityId);

                activityMessage.setMsgCover(activityDetail.getImgCover());

                activityMessage.setPushStatus(1);

                activityMessage.setStatus(1);

                activityMessage.setType(1);

                activityMessage.setActivityId(activityId);

                activityMessage.setCreateTime(new Date());

                activityMessage.setCreateUser(0L);

                activityMessage.setReceiverId(tokenParent.getId());

                activityMessage.setReceiverType(1);

                activityMessageMapper.insertSelective(activityMessage);


                PushMessageDTO pushMessageDTO = new PushMessageDTO();

                pushMessageDTO.setMsgId(activityMessage.getId());

                pushMessageDTO.setCreateTime(TimeUtils.getNowTime());

                pushMessageDTO.setStatus(1);

                pushMessageDTO.setType(1);

                activeMqProducer.sendPushMessage(pushMessageDTO);
            }

            return JsonResult.success(String.valueOf(orderNum), mark);

        } catch (Exception e) {
            e.printStackTrace();

            //还回库存
            if (skuDTO != null) {
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

        if (orderInfo.getStatus() == 1) {
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

            link.setUpdateTime(new Date());

            parentActivityLinkMapper.updateByExampleSelective(link, linkExample);


            OrderPayDelayMessage orderDelayMessage = cacheService.getOrderDelayMessage(orderInfo.getOrderNum());

            orderDelayMessage.setStatus(1);

            orderDelayMessage.setUpdateDate(new Date());

            cacheService.setOrderDelayMessage(orderDelayMessage);

            //判断是否需要还回库存
            ManageActivity manageActivity = activityMapper.selectByPrimaryKey(activityId);

            if (manageActivity.getNumber() != 0) {

                activityMapper.addStock(activityId);

                ActivitySkuDTO skuDTO = new ActivitySkuDTO();

                skuDTO.setId(activityId);

                skuDTO.setName(manageActivity.getName());

                skuDTO.setPrice(manageActivity.getMoney());

                cacheService.addActivitySku(skuDTO);

            }

            LOGGER.info("订单15分钟 支付检测 {}", orderInfo.getOrderNum() + TimeUtils.getNowTime());

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

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

        linkExample.createCriteria()
                .andOrderNumEqualTo(orderNum)
                .andParentIdEqualTo(tokenParent.getId())
                .andDelflagEqualTo(0)
                .andStatusEqualTo(0);

        long l = parentActivityLinkMapper.countByExample(linkExample);

        if (l == 0) {
            return JsonResult.error("订单状态异常");
        }


        OrderInfoExample orderInfoExample = new OrderInfoExample();

        orderInfoExample.createCriteria().andOrderNumEqualTo(orderNum);

        List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

        OrderInfo orderInfo = orderInfos.get(0);

        OrderPayInfoDTO orderPayInfoDTO = new OrderPayInfoDTO();

        orderPayInfoDTO.setOrderNum(orderNum);

        orderPayInfoDTO.setOrderName(orderInfo.getOrderName());

        Integer price = orderInfo.getPrice();

        String priceStr = new BigDecimal(price).divide(new BigDecimal(100)).toPlainString();

        orderPayInfoDTO.setPrice(priceStr);

        Long enrollId = orderInfo.getEnrollId();

        ManageActivityEnrollRecord record = enrollRecordMapper.selectByPrimaryKey(enrollId);

        orderPayInfoDTO.setStudentName(record.getName());

        orderPayInfoDTO.setSchoolName(schoolService.getSchoolPO(record.getSchoolId()).getName());

        orderPayInfoDTO.setPeriodName(dictionaryService.getDictionaryPO(record.getPeriodId()).getName());

        orderPayInfoDTO.setClassName(dictionaryService.getDictionaryPO(record.getClassId()).getName());

        orderPayInfoDTO.setActiviyId(orderInfo.getActivityId());

        orderPayInfoDTO.setCreateTime(TimeUtils.getDateString(orderInfo.getCreateTime()));

        Date dateAfterMinutes = TimeUtils.getDateAfterMinutes(orderInfo.getCreateTime(), 15);

        orderPayInfoDTO.setOverTime(TimeUtils.getDateString(dateAfterMinutes));

        orderPayInfoDTO.setPhone(String.valueOf(tokenParent.getPhone()));

        return JsonResult.success(orderPayInfoDTO);
    }

    /**
     * List my order
     *
     * @param token
     * @param pageIndex
     * @return
     */
    @Override
    public JsonResult listMyOrder(String token, Integer pageIndex) {

        TokenParentDTO tokenParent = JwtTokenUtil.getTokenParent(token);

        PageHelper.startPage(pageIndex,10);

        OrderInfoExample example = new OrderInfoExample();

        example.createCriteria().andUserIdEqualTo(tokenParent.getId());

        example.setOrderByClause("create_time desc");

        List<OrderInfo> orderInfos = orderMapper.selectByExample(example);

        List<OrderListItemDTO> list = new ArrayList<>();

        for (OrderInfo orderInfo : orderInfos) {

            OrderListItemDTO itemDTO = new OrderListItemDTO();

            itemDTO.setOrderNum(orderInfo.getOrderNum());

            itemDTO.setOrderName(orderInfo.getOrderName());

            Integer price = orderInfo.getPrice();

            String priceStr = new BigDecimal(price).divide(new BigDecimal(100)).toPlainString();
            itemDTO.setPrice(priceStr);

            String beforeNowString = TimeUtils.getBeforeNowString(orderInfo.getCreateTime());

            itemDTO.setCreateTime(beforeNowString);

            itemDTO.setStatus(orderInfo.getStatus());

            ManageActivityEnrollRecord record = enrollRecordMapper.selectByPrimaryKey(orderInfo.getEnrollId());

            String name = record.getName();

            String schoolName = schoolService.getSchoolPO(record.getSchoolId()).getName();

            String periodName = dictionaryService.getDictionaryPO(record.getPeriodId()).getName();

            String className = dictionaryService.getDictionaryPO(record.getClassId()).getName();

            List<String> des = new ArrayList<>();

            des.add(name);

            des.add(schoolName);

            des.add(periodName);

            des.add(className);

            itemDTO.setDescription(StringUtils.join(des,"-"));

            ActivitySolrItemDTO solrItemDTO = activityService.getActivitySolrItemDTO(orderInfo.getActivityId());

            itemDTO.setImgCover(solrItemDTO.getImgCover());

            list.add(itemDTO);

        }

        PageInfo<OrderInfo> orderInfoPageInfo = new PageInfo<>(orderInfos);

        PageResult<OrderListItemDTO> result = new PageResult<>();

        result.setPages(orderInfoPageInfo.getPages());

        result.setList(list);

        result.setTotal((int) orderInfoPageInfo.getTotal());

        result.setPageNum(pageIndex);

        return JsonResult.success(result);
    }

    /**
     * Get order pay status
     *
     * @param orderNum
     * @return
     */
    @Override
    public JsonResult getOrderPayStatus(String orderNum) {

        Integer orderStatusOk = 2;
        OrderInfoExample orderInfoExample = new OrderInfoExample();

        orderInfoExample.createCriteria().andOrderNumEqualTo(orderNum);

        List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

        OrderInfo orderInfo = orderInfos.get(0);

        if(orderInfo.getStatus().equals(orderStatusOk)){

            return JsonResult.success("支付成功");

        }else{
            /**
             * 2s 后重试
             */
            try {
                TimeUnit.SECONDS.sleep(2);

                List<OrderInfo> list = orderMapper.selectByExample(orderInfoExample);

                OrderInfo info = list.get(0);

                if(info.getStatus().equals(orderStatusOk)){

                    return JsonResult.success("支付成功");

                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return JsonResult.error("订单未支付");

        }
    }

    /**
     * Update order and insert record
     *
     * @param params
     */
    @Override
    public void updateAndRecordPayInfo(Map<String, String> params) {

        String key = "trade_status",KEY_SUCCESS = "TRADE_SUCCESS";
        String orderNumKey = "out_trade_no";
        if(params.get(key).equals(KEY_SUCCESS)){

            String orderNum = params.get(orderNumKey);

            OrderInfoExample orderInfoExample = new OrderInfoExample();

            orderInfoExample.createCriteria().andOrderNumEqualTo(orderNum);

            List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

            if(orderInfos.size()>0){

                OrderInfo orderInfo = orderInfos.get(0);

                OrderInfo info = new OrderInfo();
                info.setPayMethod(1);
                info.setPayTime(new Date());
                info.setStatus(2);
                info.setId(orderInfo.getId());

                orderMapper.updateByPrimaryKeySelective(info);

                ManageActivityEnrollRecord record = new ManageActivityEnrollRecord();

                record.setId(orderInfo.getEnrollId());

                record.setStatus(8);

                record.setUpdateTime(new Date());

                enrollRecordMapper.updateByPrimaryKeySelective(record);

                ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

                linkExample.createCriteria()
                        .andOrderNumEqualTo(orderInfo.getOrderNum());

                ParentActivityLink link = new ParentActivityLink();

                link.setStatus(1);

                link.setUpdateTime(new Date());

                parentActivityLinkMapper.updateByExampleSelective(link, linkExample);


                AlipayRecord alipayRecord = new AlipayRecord();

                alipayRecord.setGmCreate(params.get("gmt_create"));

                alipayRecord.setCharset(params.get("charset"));

                alipayRecord.setSellerEmail(params.get("seller_email"));

                alipayRecord.setSubject(params.get("subject"));

                alipayRecord.setSign(params.get("sign"));

                alipayRecord.setBody(params.get("body"));

                alipayRecord.setBuyerId(params.get("buyer_id"));

                alipayRecord.setInvoiceAmount(params.get("invoice_amount"));

                alipayRecord.setNotifyId(params.get("notify_id"));

                alipayRecord.setNotifyType(params.get("notify_type"));

                alipayRecord.setTradeStatus(params.get("trade_status"));

                alipayRecord.setReceiptAmount(params.get("receipt_amount"));

                alipayRecord.setAppId(params.get("app_id"));

                alipayRecord.setBuyerPayAmount(params.get("buyer_pay_amount"));

                alipayRecord.setSignType(params.get("sign_type"));

                alipayRecord.setSellerId(params.get("seller_id"));

                alipayRecord.setGmtPayment(params.get("gmt_payment"));

                alipayRecord.setNotifyTime(params.get("notify_time"));

                alipayRecord.setVersion(params.get("version"));

                alipayRecord.setOutTradeNo(params.get("out_trade_no"));

                alipayRecord.setTotalAmount(params.get("total_amount"));

                alipayRecord.setTradeNo(params.get("trade_no"));

                alipayRecord.setAuthAppId(params.get("auth_app_id"));

                alipayRecord.setBuyerLogonId(params.get("buyer_logon_id"));

                alipayRecord.setPointAmount(params.get("point_amount"));

                alipayRecord.setId(null);

                alipayRecord.setCreateTime(new Date());

                alipayRecordMapper.insertSelective(alipayRecord);


                ActivityMessage activityMessage = new ActivityMessage();

                ActivitySolrItemDTO actvitySolrItemDTO = cacheService.getActvitySolrItemDTO(orderInfo.getActivityId());

                activityMessage.setMsgTitle(actvitySolrItemDTO.getName());

                activityMessage.setMsgContent(ConstantEnum.ENROLL_SUCCESS.getStrValue());

                activityMessage.setMsgCover(actvitySolrItemDTO.getImgCover());

                activityMessage.setPushStatus(1);

                activityMessage.setStatus(1);

                activityMessage.setType(1);

                activityMessage.setActivityId(orderInfo.getActivityId());

                activityMessage.setCreateTime(new Date());

                activityMessage.setCreateUser(0L);

                activityMessage.setReceiverId(orderInfo.getUserId());

                activityMessage.setReceiverType(1);

                activityMessageMapper.insertSelective(activityMessage);


                PushMessageDTO pushMessageDTO = new PushMessageDTO();

                pushMessageDTO.setMsgId(activityMessage.getId());

                pushMessageDTO.setCreateTime(TimeUtils.getNowTime());

                pushMessageDTO.setStatus(1);

                pushMessageDTO.setType(1);

                activeMqProducer.sendPushMessage(pushMessageDTO);


            }

        }

    }

    /**
     * Get order Info
     *
     * @param orderNum
     * @return
     */
    @Override
    public OrderInfo getOderInfoByOrderNum(String orderNum) {

        OrderInfoExample orderInfoExample = new OrderInfoExample();

        orderInfoExample.createCriteria().andOrderNumEqualTo(orderNum);

        List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

        if(orderInfos.size()>0){
            return orderInfos.get(0);
        }

        return null;
    }

    /**
     * List order
     *
     * @param param
     * @return
     */
    @Override
    public JsonResult listOrder(PageSearchParam param) {

        PageHelper.startPage(param.getPageIndex(),param.getPageSize());

        String key1="timeDiff",key2="orderNum",key3="userId",key4="status";

        OrderInfoExample example = new OrderInfoExample();

        OrderInfoExample.Criteria criteria = example.createCriteria();

        if(param.getFiled(key1)!=null){

            String[] split = param.getFiled(key1).split(" - ");

            criteria.andCreateTimeBetween(TimeUtils.getDateFromString(split[0]),TimeUtils.getDateFromString(split[1]));

        }

        if(param.getFiled(key2)!=null){
            criteria.andOrderNumEqualTo(param.getFiled(key2));
        }

        if(param.getFiled(key3)!=null){
            criteria.andUserIdEqualTo(Long.valueOf(param.getFiled(key3)));
        }

        if(param.getFiled(key4)!=null){
            criteria.andStatusEqualTo(Integer.valueOf(param.getFiled(key4)));
        }

        example.setOrderByClause(" create_time desc ");

        List<OrderInfo> orderInfos = orderMapper.selectByExample(example);

        for (OrderInfo orderInfo : orderInfos) {

            Parent parentPO = personnelService.getParentPO(orderInfo.getUserId());

            orderInfo.setUserName(parentPO.getName());

            String price = new BigDecimal(orderInfo.getPrice()).divide(new BigDecimal(100)).toPlainString();

            if(price.equals("0")){
                orderInfo.setPriceStr("免费");
            }else{
                orderInfo.setPriceStr(price);
            }

            orderInfo.setMethodName("");

            if(orderInfo.getPayMethod()!=null){

                if(orderInfo.getPayMethod()==1){
                    orderInfo.setMethodName("支付宝");
                }
                if(orderInfo.getPayMethod()==2){
                    orderInfo.setMethodName("微信");
                }
            }



        }

        PageInfo<OrderInfo> orderInfoPageInfo = new PageInfo<>(orderInfos);

        PageResult<OrderInfo> orderInfoPageResult = new PageResult<>(orderInfoPageInfo);

        return JsonResult.success(orderInfoPageResult);
    }

    /**
     * Get order detail
     *
     * @param id
     * @return
     */
    @Override
    public JsonResult getOrderInfoDetail(Long id) {

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        OrderInfo orderInfo = orderMapper.selectByPrimaryKey(id);

        orderDetailDTO.setOrderName(orderInfo.getOrderName());

        orderDetailDTO.setOrderNum(orderInfo.getOrderNum());
        String price = new BigDecimal(orderInfo.getPrice()).divide(new BigDecimal(100)).toPlainString();

        if(price.equals("0")){
            orderDetailDTO.setPrice("免费");
        }else{
            orderDetailDTO.setPrice(price);
        }

        orderDetailDTO.setCreateTime(TimeUtils.getDateString(orderInfo.getCreateTime()));

        if(orderInfo.getPayTime()!=null){
            orderDetailDTO.setPayTime(TimeUtils.getBeforeNowString(orderInfo.getPayTime()));
        }

        if(orderInfo.getPayMethod()!=null){

            if(orderInfo.getPayMethod()==1){
                orderDetailDTO.setMethodName("支付宝");
            }
            if(orderInfo.getPayMethod()==2){
                orderDetailDTO.setMethodName("微信");
            }
        }else{
            orderDetailDTO.setMethodName("");
        }

        switch (orderInfo.getStatus()){
            case 0:
                orderDetailDTO.setStatus("不可用");
                break;
            case 1:
                orderDetailDTO.setStatus("等待支付");
                break;
            case 2:
                orderDetailDTO.setStatus("已支付");
                break;
            case 3:
                orderDetailDTO.setStatus("用户取消");
                break;
            case 4:
                orderDetailDTO.setStatus("支付超时取消");
                break;
        }


        Parent parentPO = personnelService.getParentPO(orderInfo.getUserId());

        orderDetailDTO.setParentName(parentPO.getName());

        orderDetailDTO.setPhone(String.valueOf(parentPO.getPhone()));

        ManageActivityEnrollRecord record = enrollRecordMapper.selectByPrimaryKey(orderInfo.getEnrollId());


        StudentDTO studentDTO = personnelService.getStudentDTO(record.getStudentId());


        orderDetailDTO.setStudentName(studentDTO.getStudentName());

        orderDetailDTO.setSchoolName(studentDTO.getSchoolName());

        orderDetailDTO.setPeriodName(studentDTO.getPeriodName());

        orderDetailDTO.setClassName(studentDTO.getClassName());


        ActivitySolrItemDTO activitySolrItemDTO = activityService.getActivitySolrItemDTO(orderInfo.getActivityId());

        orderDetailDTO.setActivityName(activitySolrItemDTO.getName());

        orderDetailDTO.setType(activitySolrItemDTO.getTypeName());

        orderDetailDTO.setClassify(activitySolrItemDTO.getClassifyName());

        orderDetailDTO.setTheme(activitySolrItemDTO.getThemeName());

        orderDetailDTO.setBeginEndTime(TimeUtils.getDateString(activitySolrItemDTO.getBeginTime())+" - "+TimeUtils.getDateString(activitySolrItemDTO.getEndTime()));

        orderDetailDTO.setImgCover(activitySolrItemDTO.getImgCover());
        List<String> applys = new ArrayList<>();

        String apply = activitySolrItemDTO.getApply();

        String[] split = apply.split(",");

        for (String s : split) {

            ManageDictionary dictionaryPO = dictionaryService.getDictionaryPO(Long.valueOf(s));

            applys.add(dictionaryPO.getName());

        }
        orderDetailDTO.setApplys(applys);



        return JsonResult.success(orderDetailDTO);
    }

    /**
     * Update orderinfo status weixin
     *
     * @param xmlString
     */
    @Override
    public void updateAndRecordPayInfoWeiXin(String xmlString) throws Exception {


        Map<String, Object> map = XmlUtil.doXMLParse(xmlString);

        String result_code = map.get("result_code").toString();
        String out_trade_no = map.get("out_trade_no").toString();
        String return_code = map.get("return_code").toString();

        if(result_code.equals("SUCCESS")&&result_code.equals("SUCCESS")){

            String orderNum = out_trade_no;

            OrderInfoExample orderInfoExample = new OrderInfoExample();

            orderInfoExample.createCriteria().andOrderNumEqualTo(orderNum);

            List<OrderInfo> orderInfos = orderMapper.selectByExample(orderInfoExample);

            if(orderInfos.size()>0){

                OrderInfo orderInfo = orderInfos.get(0);

                OrderInfo info = new OrderInfo();
                info.setPayMethod(2);
                info.setPayTime(new Date());
                info.setStatus(2);
                info.setId(orderInfo.getId());

                orderMapper.updateByPrimaryKeySelective(info);

                ManageActivityEnrollRecord record = new ManageActivityEnrollRecord();

                record.setId(orderInfo.getEnrollId());

                record.setStatus(8);

                record.setUpdateTime(new Date());

                enrollRecordMapper.updateByPrimaryKeySelective(record);

                ParentActivityLinkExample linkExample = new ParentActivityLinkExample();

                linkExample.createCriteria()
                        .andOrderNumEqualTo(orderInfo.getOrderNum());

                ParentActivityLink link = new ParentActivityLink();

                link.setStatus(1);

                link.setUpdateTime(new Date());

                parentActivityLinkMapper.updateByExampleSelective(link, linkExample);


                ActivityMessage activityMessage = new ActivityMessage();

                ActivitySolrItemDTO actvitySolrItemDTO = cacheService.getActvitySolrItemDTO(orderInfo.getActivityId());

                activityMessage.setMsgTitle(actvitySolrItemDTO.getName());

                activityMessage.setMsgContent(ConstantEnum.ENROLL_SUCCESS.getStrValue());

                activityMessage.setMsgCover(actvitySolrItemDTO.getImgCover());

                activityMessage.setPushStatus(1);

                activityMessage.setStatus(1);

                activityMessage.setType(1);

                activityMessage.setActivityId(orderInfo.getActivityId());

                activityMessage.setCreateTime(new Date());

                activityMessage.setCreateUser(0L);

                activityMessage.setReceiverId(orderInfo.getUserId());

                activityMessage.setReceiverType(1);

                activityMessageMapper.insertSelective(activityMessage);


                PushMessageDTO pushMessageDTO = new PushMessageDTO();

                pushMessageDTO.setMsgId(activityMessage.getId());

                pushMessageDTO.setCreateTime(TimeUtils.getNowTime());

                pushMessageDTO.setStatus(1);

                pushMessageDTO.setType(1);

                activeMqProducer.sendPushMessage(pushMessageDTO);

            }

        }
    }
}
