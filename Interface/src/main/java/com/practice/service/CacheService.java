package com.practice.service;

import com.practice.dto.*;
import com.practice.enums.DicParentEnum;
import com.practice.po.ManageBase;
import com.practice.po.ManageDictionary;
import com.practice.vo.ActivityDetailVO;

import java.util.List;

/**
 * Cache service
 *
 * @author Xushd  2017/12/22 21:08
 */
public interface CacheService {


    /**
     * Set manage user navs
     *
     * @param userId
     * @param navList
     */
    void setManageUserNavs(List<NavDTO> navList, Long userId);

    /**
     * Get manage user navs
     *
     * @param userId
     * @return
     */
    List<NavDTO> getManageUserNavs(Long userId);

    /**
     * Del manage user navs
     * @param userId
     */
    void delManageUserNavs(Long userId);

    /**
     * Set dictionary
     * @param dicParentEnum
     * @param list
     */
    void resetDictionary(DicParentEnum dicParentEnum, List<ManageDictionary> list);

    /**
     * list dictionary by enum
     * @param dicParentEnum
     * @return
     */
    List<ManageDictionary> listMangeDictionaryByEnum(DicParentEnum dicParentEnum);

    /**
     * Get dictionary by id
     * @param id
     * @return
     */
    ManageDictionary getDictionaryById(Long id);

    /**
     * Set dictionary by id
     * @param id
     * @param dictionary
     */
    void setDicionaryById(Long id, ManageDictionary dictionary);

    /**
     * Set manage permission
     * @param permissionList
     * @param id
     */
    void setManageUserPermission(List<String> permissionList, Long id);

    /**
     * Get manage permission
     * @param id
     * @return
     */
    List<String> getManageUserPermission(Long id);

    /**
     * Get provice list
     * @return
     */
    List<ProvinceDTO> getProvinceList();

    /**
     * Set provice list
     * @param list
     */
    void setProvinceList(List<ProvinceDTO> list);

    /**
     * Get province by pid
     * @param pid
     * @return
     */
    ProvinceDTO getProvince(Long pid);

    /**
     * Set province by pid
     * @param pid
     * @param provinceDTO
     */
    void setProvince(Long pid,ProvinceDTO provinceDTO);

    /**
     * Get city list
     * @return
     */
    List<CityDTO> getCityList(Long pid);

    /**
     * Set city list
     * @param pid
     * @param list
     */
    void setCityList(Long pid,List<CityDTO> list);

    /**
     * Get city by cid
     * @param cid
     * @return
     */
    CityDTO getCity(Long cid);

    /**
     * Set city by cid
     * @param cid
     * @param cityDTO
     */
    void setCity(Long cid,CityDTO cityDTO);

    /**
     * Get area list
     * @param cid
     * @return
     */
    List<AreaDTO> getAreaList(Long cid);

    /**
     * Set area list
     * @param cid
     * @param list
     */
    void setAreaList(Long cid,List<AreaDTO> list);

    /**
     * Get area by aid
     * @param aid
     * @return
     */
    AreaDTO getArea(Long aid);

    /**
     * Set area by aid
     * @param aid
     * @param areaDTO
     */
    void setArea(Long aid,AreaDTO areaDTO);


    /**
     * Set phone verify code
     * @param phone
     * @param verifyCode
     */
    void setPhoneVerifyCode(String phone, String verifyCode);

    /**
     * is exit phone verify code
     * @param phone
     * @return
     */
    boolean isExistPhoneVerifyCode(String phone);

    /**
     * Get phone verify code
     * @param phone
     * @return
     */
    String getPhoneVerifyCode(String phone);

    /**
     * Set parent DTO
     * @param parentDTO
     */
    void setParent(ParentDTO parentDTO);

    /**
     * Get parent DTO
     * @param id
     * @return
     */
    ParentDTO getParent(Long id);

    /**
     * Set system param
     * @param systemParam
     */
    void setSystemParam(KeyValueDTO systemParam);

    /**
     * Get system param
     * @param id
     * @return
     */
    KeyValueDTO getSystemParam(Long id);

    /**
     * Get app slider
     * @param type
     * @return
     */
    List<SliderItemDTO> getAppSlider(Integer type);

    /**
     * Set app slider
     * @param type
     */
    void setAppSlider(Integer type,List<SliderItemDTO> list);

    /**
     * Set activity solr item
     * @param activitySolrItemDTO
     */
    void setActvitySolrItemDTO(ActivitySolrItemDTO activitySolrItemDTO);

    /**
     * Get activity solr item
     * @param activityId
     * @return
     */
    ActivitySolrItemDTO getActvitySolrItemDTO(Long activityId);

    /**
     * Clear activity solr item
     * @param id
     */
    void clearActivitySolrItemDTO(Long id);

    /**
     * Get classify
     * @param typeId
     * @return
     */
    List<KeyValueDTO> getClassify(Long typeId);

    /**
     * set classify
     * @param typeId
     * @param list
     */
    void setClassify(Long typeId, List<KeyValueDTO> list);

    /**
     * Get activity detail
     * @param id
     * @return
     */
    ActivityDetailVO getActivityDetail(Long id);

    /**
     * Set activity detail
     * @param id
     * @param detailVO
     */
    void setActivityDetail(Long id,ActivityDetailVO detailVO);

    /**
     * Clear activity detail
     * @param id
     */
    void clearActivityDetail(Long id);
    /**
     * Get bases
     * @param baseId
     * @return
     */
    ManageBase getBases(Long baseId);

    /**
     * Set bases
     * @param basesId
     * @param bases
     */
    void setBases(Long basesId,ManageBase bases);

    /**
     * Clear activity stock queue
     * @param id
     */
    void clearActivityStockQueue(Long id);

    /**
     * Create activity stock queue
     * @param id
     * @param queues
     */
    void createActivityStockQueue(Long id, byte[]... queues);

    /**
     * Get activity stock sku
     * @param activityId
     * @return
     */
    ActivitySkuDTO getActivitySku(Long activityId);

    /**
     * Add activity stock sku
     * @param skuDTO
     */
    void addActivitySku(ActivitySkuDTO skuDTO);

    /**
     * Set order delay message
     * @param orderPayDelayMessage
     */
    void setOrderDelayMessage(OrderPayDelayMessage orderPayDelayMessage);

    /**
     * Get order delay message
     * @param orderNum
     * @return
     */
    OrderPayDelayMessage getOrderDelayMessage(String orderNum);


    /**
     * Get themes
     * @param classifyId
     * @return
     */
    List<KeyValueDTO> getTheme(Long classifyId);

    /**
     * Set themes
     * @param classifyId
     * @param list
     */
    void setTheme(Long classifyId, List<KeyValueDTO> list);

    /**
     * Get filter period
     * @return
     */
    FilterPeriodDTO getFilterPeriod();

    /**
     * Set filter period
     * @param filterPeriodDTO
     */
    void setFilterPeriod(FilterPeriodDTO filterPeriodDTO);

    /**
     * Add activity like
     * @param id
     * @param score
     * @return
     */
    void setActivityLike(Long id,String score);

    /**
     * Get activity like
     * @param activityId
     * @return
     */
    String getActivityScore(Long activityId);

    /**
     * Set solr update message
     * @param solrUpdateMessage
     */
    void setSolrUpdateMessage(SolrUpdateMessage solrUpdateMessage);

    /**
     * Set activity message DTO
     * @param itemDTO
     */
    void setActivityMessageItem(MessageListItemDTO itemDTO);

    /**
     * Get activity message DTO
     * @param id
     * @return
     */
    MessageListItemDTO getActivityMessageItem(Long id);
}
