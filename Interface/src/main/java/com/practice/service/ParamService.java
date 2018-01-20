package com.practice.service;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.enums.SystemParamEnum;
import com.practice.po.SystemParam;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2018/1/20 11:25
 */
public interface ParamService {
    /**
     * List param
     * @param param
     * @return
     */
    JsonResult listParam(PageSearchParam param);

    /**
     * Save param
     * @param param
     * @param token
     * @return
     */
    JsonResult saveParam(SystemParam param, String token);

    /**
     * Update param
     * @param param
     * @param token
     * @return
     */
    JsonResult updateParam(SystemParam param, String token);

    /**
     * Del param
     * @param id
     * @param token
     * @return
     */
    JsonResult delParam(Long id, String token);

    /**
     * Sync param cache
     * @return
     */
    JsonResult syncParamCache();

    /**
     * Get param
     * @param id
     * @return
     */
    JsonResult getParam(Long id);

    /**
     * Get param by enum
     * @param systemParamEnum
     * @return
     */
    KeyValueDTO getParamByEnum(SystemParamEnum systemParamEnum);
}
