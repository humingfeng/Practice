package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageBase;
import com.practice.result.JsonResult;

/**
 * Bases service interface
 * @author Xushd  2017/12/31 16:44
 */
public interface BasesService {

    /**
     * List bases
     * @param param
     * @return
     */
    JsonResult listBases(PageSearchParam param);

    /**
     * Add bases
     * @param token
     * @param base
     * @return
     */
    JsonResult addBases(String token, ManageBase base);

    /**
     * Update bases
     * @param token
     * @param base
     * @return
     */
    JsonResult updateBases(String token, ManageBase base);

    /**
     * delete bases
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteBases(String token, Long id);

    /**
     * List bases usable
     * @return
     */
    JsonResult listBasesUsable();

    /**
     * Get bases
     * @param id
     * @return
     */
    JsonResult getBases(Long id);

    /**
     * Get base PO
     * @param baseId
     * @return
     */
    ManageBase getBasesPO(Long baseId);
}
