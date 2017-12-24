package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManagePermission;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2017/12/23 20:20
 */
public interface PermissionService {
    /**
     * List permission
     * @param param
     * @return
     */
    JsonResult listPermission(PageSearchParam param);

    /**
     * Get permission
     * @param id
     * @return
     */
    JsonResult getPermission(Long id);

    /**
     * Add permissison
     * @param token
     * @param managePermission
     * @return
     */
    JsonResult addPermission(String token, ManagePermission managePermission);

    /**
     * Update permission
     * @param token
     * @param managePermission
     * @return
     */
    JsonResult updatePermission(String token, ManagePermission managePermission);

    /**
     * List permission usable
     * @param roleId
     * @return
     */
    JsonResult listPermissionUsable(Long roleId);

}
