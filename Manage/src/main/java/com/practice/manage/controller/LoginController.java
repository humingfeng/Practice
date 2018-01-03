package com.practice.manage.controller;

import com.practice.enums.AuthEnum;
import com.practice.result.JsonResult;
import com.practice.service.LoginService;
import com.practice.utils.VerifyCodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Xushd  2017/12/21 22:07
 */
@Controller
public class LoginController {

    @Resource
    LoginService loginService;

    /**
     * Login Index
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    /**
     * Manage Login check
     * @param vercode
     * @param account
     * @param pass
     * @param code
     * @return
     */
    @RequestMapping(value = "/login/check")
    @ResponseBody
    public JsonResult loginCheck(@CookieValue(required = false) String vercode, String account, String pass, String code) {

        if (StringUtils.isBlank(vercode)) {
            return JsonResult.error(AuthEnum.VERCODE_TIME_OUT);
        }

        if (!StringUtils.equals(code.toLowerCase(), vercode)) {
            return JsonResult.error(AuthEnum.VERCODE_ERROR);
        }

        return loginService.manageLoginCheck(account, pass);

    }



    /**
     * 生成验证码
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
    public void verifyCode(HttpServletResponse response) throws IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session

        Cookie cookie = new Cookie("vercode", verifyCode.toLowerCase());
        // 设置为60min*24
        cookie.setMaxAge(60);
        cookie.setPath("/");
        response.addCookie(cookie);
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
}
