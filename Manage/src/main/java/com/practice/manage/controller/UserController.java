package com.practice.manage.controller;

import com.practice.result.JsonResult;
import com.practice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * User controller
 * @author Xushd on 2017/12/22 15:59
 */
@RequestMapping("/auth/user")
@Controller
public class UserController {


    @Resource
    private UserService userService;

    /**
     * Get user nav by token
     * @param token
     * @return
     */
    @RequestMapping(value = "/nav")
    @ResponseBody
    public JsonResult ajaxUserNav(@RequestAttribute String token){

        return userService.listUserNav(token);
    }

    /**
     * User Logout
     * @param token
     * @return
     */
    @RequestMapping(value = "/logout")
    @ResponseBody
    public JsonResult ajaxUserLogout(@RequestAttribute String token){

        return userService.logoutUser(token);
    }
}
