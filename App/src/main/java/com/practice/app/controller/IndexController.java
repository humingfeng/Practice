package com.practice.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xushd  2018/1/15 21:17
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){

        return "index";
    }
}
