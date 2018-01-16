package com.practice.app.controller;

import com.practice.result.JsonResult;
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

    @RequestMapping(value = "/404")
    public JsonResult index404(){

        return JsonResult.error("接口调用错误");
    }
}
