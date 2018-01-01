package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageActivity;
import com.practice.po.ManageActivityClassify;
import com.practice.po.ManageActivityTheme;
import com.practice.po.ManageActivityType;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2017/12/25 20:46
 */
public interface ActivityService {
    /**
     * List activity type
     * @param param
     * @return
     */
    JsonResult listType(PageSearchParam param);

    /**
     * add activity type
     * @param token
     * @param activityType
     * @return
     */
    JsonResult addType(String token, ManageActivityType activityType);

    /**
     * update activity type
     * @param token
     * @param activityType
     * @return
     */
    JsonResult updateType(String token, ManageActivityType activityType);

    /**
     * delete activity type
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteType(String token, Long id);

    /**
     * get activity type
     * @param id
     * @return
     */
    JsonResult getType(Long id);

    /**
     * List activity type usable
     * @return
     */
    JsonResult listTypeUsable();

    /**
     * List activity classify
     * @param param
     * @return
     */
    JsonResult listClassify(PageSearchParam param);

    /**
     * add activity classify
     * @param token
     * @param activityClassify
     * @return
     */
    JsonResult addClassify(String token, ManageActivityClassify activityClassify);

    /**
     * update activity classify
     * @param token
     * @param activityClassify
     * @return
     */
    JsonResult updateClassify(String token, ManageActivityClassify activityClassify);

    /**
     * delete activity classify
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteClassify(String token, Long id);

    /**
     * get activity classify
     * @param id
     * @return
     */
    JsonResult getClassify(Long id);

    /**
     * list activity classify usable
     * @param id
     * @return
     */
    JsonResult listClassifyUsable(Long id);

    /**
     * list activity theme
     * @param param
     * @return
     */
    JsonResult listTheme(PageSearchParam param);

    /**
     * add activity theme
     * @param token
     * @param activityTheme
     * @return
     */
    JsonResult addTheme(String token, ManageActivityTheme activityTheme);

    /**
     * update activity theme
     * @param token
     * @param activityTheme
     * @return
     */
    JsonResult updateTheme(String token, ManageActivityTheme activityTheme);

    /**
     * delete activity theme
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteTheme(String token, Long id);

    /**
     * get activity theme
     * @param id
     * @return
     */
    JsonResult getTheme(Long id);

    /**
     * list activity theme usable
     * @param id
     * @return
     */
    JsonResult listThemeUsable(Long id);

    /**
     * List activity manage
     * @param param
     * @return
     */
    JsonResult listManage(PageSearchParam param);

    /**
     * Add activity manage
     * @param manageActivity
     * @param token
     * @return
     */
    JsonResult addActivityManage(ManageActivity manageActivity, String token);

    /**
     * Get activity manage
     * @param id
     * @return
     */
    JsonResult getActivityManage(Long id);

    /**
     * Update activity manage
     * @param manageActivity
     * @param token
     * @return
     */
    JsonResult updateActivityManage(ManageActivity manageActivity, String token);
}
