package com.practice.service;

import com.practice.dto.KeyValueDTO;
import com.practice.dto.PageSearchParam;
import com.practice.po.School;
import com.practice.result.JsonResult;

import java.util.List;

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
     * Get school PO
     * @param id
     * @return
     */
    School getSchoolPO(Long id);

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

    /**
     * List school usable
     * @return
     */
    List<KeyValueDTO> listSchoolUsable();

    /**
     * List school usable by pid and cid and aid
     * @param pid
     * @param cid
     * @param aid
     * @return
     */
    JsonResult listSchoolUsable(Long pid, Long cid, Long aid);
}
