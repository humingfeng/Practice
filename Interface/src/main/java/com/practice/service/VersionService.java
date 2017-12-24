package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageVersion;
import com.practice.result.JsonResult;
import com.practice.vo.VersionVO;

import java.util.List;

/**
 * @author Xushd  2017/12/23 14:56
 */
public interface VersionService {

    /**
     * List version
     * @return
     */
    List<VersionVO> listVersion();

    /**
     * List vertsion by param
     * @param param
     * @return
     */
    JsonResult listVersion(PageSearchParam param);

    /**
     * Get version
     * @param id
     * @return
     */
    JsonResult getVersion(Long id);

    /**
     * Add version
     * @param token
     * @param manageVersion
     * @return
     */
    JsonResult addVersion(String token, ManageVersion manageVersion);

    /**
     * Update version
     * @param token
     * @param manageVersion
     * @return
     */
    JsonResult updateVersion(String token, ManageVersion manageVersion);

    /**
     * Delete version
     * @param id
     * @return
     */
    JsonResult deleteVersion(Long id);

    /**
     * List version item
     * @param id
     * @return
     */
    JsonResult listVersionItem(Long id);
}
