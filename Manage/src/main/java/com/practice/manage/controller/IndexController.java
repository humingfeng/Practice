package com.practice.manage.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Index controller
 *
 * @author Xushd  2017/12/21 21:44
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(@CookieValue(required = false) String token) {

        if (StringUtils.isNotBlank(token)) {

        } else {
            return "login";
        }

        return "index";
    }

    @RequestMapping(value = "/404")
    public String index404() {

        return "404";
    }
}
