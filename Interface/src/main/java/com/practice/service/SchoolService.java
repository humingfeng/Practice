package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.School;
import com.practice.result.JsonResult;

/**
 * School service interface
 * @author Xushd on 2017/12/27 15:49
 */
public interface SchoolService {

    /**
     * List school
     * @param param
     * @return
     */
    JsonResult listSchool(PageSearchParam param);

    /**
     * Get school
     * @param id
     * @return
     */
    JsonResult getSchool(Long id);

    /**
     * Add school
     * @param token
     * @param school
     * @return
     */
    JsonResult addSchool(String token, School school);

    /**
     * Update school
     * @param token
     * @param school
     * @return
     */
    JsonResult updateSchool(String token, School school);

    /**
     * Delete school
     * @param token
     * @param id
     * @return
     */
    JsonResult deleteSchool(String token, Long id);
}