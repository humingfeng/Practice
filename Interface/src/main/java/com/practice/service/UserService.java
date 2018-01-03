package com.practice.service;

import com.practice.dto.NavDTO;
import com.practice.dto.PageSearchParam;
import com.practice.po.ManageUser;
import com.practice.result.JsonResult;

import java.util.List;

/**
 * User service
 * @author Xushd on 2017/12/22 16:24
 */
public interface UserService {

    /**
     * List  user nav
     * @param token
     * @return
     */
    JsonResult listUserNav(String token);

    /**
     * List user
     * @param param
     * @return
     */
    JsonResult listUser(PageSearchParam param);

    /**
     * Add user
     * @param manageUser
     * @param token
     * @return
     */
    JsonResult addUser(ManageUser manageUser, String token);

    /**
     * Update user
     * @param manageUser
     * @param token
     * @return
     */
    JsonResult updateUser(ManageUser manageUser, String token);

    /**
     * Get user
     * @param id
     * @return
     */
    JsonResult getUser(Long id);

    /**
     * Get user
     * @param id
     * @return
     */
    ManageUser getUserPO(Long id);
    /**
     * User logout
     * @param token
     * @return
     */
    JsonResult logoutUser(String token);

    /**
     * List user role
     * @param id
     * @return
     */
    JsonResult listRoleById(Long id);

    /**
     * Save user role
     * @param token
     * @param id
     * @param roles
     * @return
     */
    JsonResult saveUserRole(String token, Long id, String roles);

    /**
     * create user navs and permission cache
     * @param id
     * @return
     */
    void createUserNavsAndPermissionCache(Long id);

    /**
     * List user permission
     * @param token
     * @return
     */
    List<String> listUserPermission(String token);

    /**
     * List users by organize
     * @param token
     * @return
     */
    List<ManageUser> listUserByOrganize(String token);
}
