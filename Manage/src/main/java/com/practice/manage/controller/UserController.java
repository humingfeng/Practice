package com.practice.manage.controller;

import com.practice.dto.TokenUserDTO;
import com.practice.po.ManageUser;
import com.practice.result.JsonResult;
import com.practice.service.UserService;
import com.practice.utils.CommonUtils;
import com.practice.utils.IDUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    /**
     * User change password
     * @return
     */
    @RequestMapping(value = "/change/pwd",method = RequestMethod.GET)
    public String changePwd(){

        return "system/changePwd";

    }

    /**
     * User change password submit
     * @param token
     * @param password1
     * @param password2
     * @return
     */
    @RequestMapping(value = "/change/pwd",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult changePwd(@RequestAttribute String token,String password1,String password2){

        if(StringUtils.equals(password1,password2)){

            TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

            ManageUser manageUser = new ManageUser();

            manageUser.setId(tokeUser.getId());

            manageUser.setPassword(CommonUtils.sha256(password1));

            return userService.updateUser(manageUser,token);

        }else{
            return JsonResult.error("两次密码输入不一致");
        }

    }

    /**
     * Change userInfo
     * @return
     */
    @RequestMapping(value = "/change/userInfo",method = RequestMethod.GET)
    public String changeUserInfo(Model model,@RequestAttribute String token){

        TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

        ManageUser userPO = userService.getUserPO(tokeUser.getId());

        model.addAttribute("userInfo",userPO);

        return "system/userInfo";
    }
}
