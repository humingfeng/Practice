package com.practice.service;

import com.practice.dto.PageSearchParam;
import com.practice.po.ManageRole;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2017/12/23 20:19
 */
public interface RoleService {

    /**
     * List role
     * @param param
     * @return
     */
    JsonResult listRole(PageSearchParam param);

    /**
     * Get role
     * @param id
     * @return
     */
    JsonResult getRole(Long id);

    /**
     * Add role
     * @param token
     * @param manageRole
     * @return
     */
    JsonResult addRole(String token, ManageRole manageRole);

    /**
     * Update role
     * @param token
     * @param manageRole
     * @return
     */
    JsonResult updateRole(String token, ManageRole manageRole);

    /**
     * List role usable
     * @param userId
     * @return
     */
    JsonResult listRoleUsable(Long userId);


    /**
     * List role menu
     * @param id
     * @return
     */
    JsonResult listRoleMenuId(Long id);

    /**
     * List role permission
     * @param id
     * @return
     */
    JsonResult listRolePermissionId(Long id);

    /**
     * Save role nva and permission
     * @param token
     * @param id
     * @param navs
     * @param pers
     * @return
     */
    JsonResult saveRoleNavAndPermission(String token, Long id, String navs, String pers);
}
