package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.dto.SliderItemDTO;
import com.practice.po.AppSlider;
import com.practice.po.AppSliderContent;
import com.practice.result.JsonResult;

import java.util.List;

/**
 * @author Xushd  2018/1/20 15:04
 */
public interface RunService {
    /**
     * List app slider
     * @param param
     * @return
     */
    JsonResult listAppSlider(PageSearchParam param);

    /**
     * Save app slider
     * @param appSlider
     * @param token
     * @return
     */
    JsonResult saveAppSlider(AppSlider appSlider, String token);

    /**
     * Get app slider
     * @param id
     * @return
     */
    JsonResult getAppSlider(Long id);

    /**
     * Update app slider
     * @param appSlider
     * @param token
     * @return
     */
    JsonResult updateAppSlider(AppSlider appSlider, String token);

    /**
     * Del app slider
     * @param id
     * @param token
     * @return
     */
    JsonResult delAppSlider(Long id, String token);

    /**
     * Get app slider content
     * @param id
     * @return
     */
    JsonResult getAppSliderContent(Long id);

    /**
     * Save app slider content
     * @param content
     * @param token
     * @return
     */
    JsonResult saveAppSliderContent(AppSliderContent content, String token);

    /**
     * Update app slider content
     * @param content
     * @param token
     * @return
     */
    JsonResult updateAppSliderContent(AppSliderContent content, String token);

    /**
     * List app slider order
     * @param type
     * @return
     */
    List<SliderItemDTO> listAppSliderOrder(Integer type);

    /**
     * Save app slider order
     * @param type
     * @param list
     * @return
     */
    JsonResult saveAppSliderOrder(Integer type, String list);

    /**
     * List usable App slider
     * @param type
     * @return
     */
    JsonResult listUsableAppSlider(Integer type);

    /**
     * List app slider form cache
     * @param tag
     * @return
     */
    JsonResult listSliderCache(Integer tag);
}
