package com.practice.service;

import com.practice.po.ManageNav;
import com.practice.result.JsonResult;

/**
 * @author Xushd  2017/12/23 20:20
 */
public interface MenuService {
    /**
     * List menu
     * @return
     */
    JsonResult listMenu();

    /**
     * Get menu
     * @param id
     * @return
     */
    JsonResult getMenu(Long id);

    /**
     * Add menu
     * @param token
     * @param manageNav
     * @return
     */
    JsonResult addMenu(String token, ManageNav manageNav);

    /**
     * Update menu
     * @param token
     * @param manageNav
     * @return
     */
    JsonResult updateMenu(String token, ManageNav manageNav);

    /**
     * List menu parent
     * @return
     */
    JsonResult listMenuParent();

    /**
     * List menu usable
     * @param roleId
     * @return
     */
    JsonResult listMenuUsable(Long roleId);

}
