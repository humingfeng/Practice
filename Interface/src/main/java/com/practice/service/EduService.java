package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.Edu;
import com.practice.result.JsonResult;

/**
 * Edu service interface
 * @author Xushd on 2017/12/27 15:49
 */
public interface EduService {

    /**
     * List edu
     * @param param
     * @return
     */
    JsonResult listEdu(PageSearchParam param);

    /**
     * Get edu
     * @param id
     * @return
     */
    JsonResult getEdu(Long id);

    /**
     * Add edu
     * @param token
     * @param edu
     * @return
     */
    JsonResult addEdu(String token, Edu edu);

    /**
     * Update edu
     * @param token
     * @param edu
     * @return
     */
    JsonResult updateEdu(String token, Edu edu);

    /**
     * Delete edu
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteEdu(String token, Long id);

    /**
     * List usable edu
     * @return
     */
    JsonResult listEduUsable();

}
