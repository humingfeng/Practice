package com.practice.manage.controller;

import com.practice.dto.PageSearchParam;
import com.practice.dto.SliderItemDTO;
import com.practice.po.AppSlider;
import com.practice.po.AppSliderContent;
import com.practice.result.JsonResult;
import com.practice.service.RunService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Xushd  2018/1/20 14:43
 */
@RequestMapping(value = "/auth/run")
@Controller
public class RunCenterController {

    @Resource
    private RunService runService;
    /**
     * Index app slider
     * @return
     */
    @RequestMapping(value = "/slider/app")
    public String indexAppSilder(){

        return "run/slider_app";
    }

    /**
     * List app slider
     * @param param
     * @return
     */
    @RequestMapping(value = "/slider/app/list")
    @ResponseBody
    public JsonResult ajaxAppSliderList(PageSearchParam param){
        return runService.listAppSlider(param);
    }

    /**
     * Add app slider
     * @param appSlider
     * @param token
     * @return
     */
    @RequestMapping(value = "/slider/app/add")
    @ResponseBody
    public JsonResult ajaxAppSliderSave(AppSlider appSlider, @RequestAttribute String token){
        return runService.saveAppSlider(appSlider,token);
    }

    /**
     * Get App slider
     * @param id
     * @return
     */
    @RequestMapping(value = "/slider/app/{id}")
    @ResponseBody
    public JsonResult ajaxAppSliderObj(@PathVariable Long id){
        return runService.getAppSlider(id);
    }

    /**
     * Update app slider
     * @param appSlider
     * @param token
     * @return
     */
    @RequestMapping(value = "/slider/app/update")
    @ResponseBody
    public JsonResult ajaxAppSliderUpdate(AppSlider appSlider,@RequestAttribute String token){
        return runService.updateAppSlider(appSlider,token);
    }

    /**
     * Del app slider
     * @param id
     * @param token
     * @return
     */
    @RequestMapping(value = "/slider/app/del")
    @ResponseBody
    public JsonResult ajaxAppSliderDel(@PathVariable Long id,@RequestAttribute String token){
        return runService.delAppSlider(id,token);
    }

    /**
     * Get app slider content
     * @param id
     * @return
     */
    @RequestMapping(value = "/slider/app/content/{id}")
    @ResponseBody
    public JsonResult ajaxAppSliderContent(@PathVariable Long id){
        return runService.getAppSliderContent(id);
    }

    /**
     * Save app slider content
     * @param content
     * @param token
     * @return
     */
    @RequestMapping(value = "/slider/app/content/save")
    @ResponseBody
    public JsonResult ajaxAppSliderContentSave(AppSliderContent content,@RequestAttribute String token){
        return runService.saveAppSliderContent(content,token);
    }

    /**
     * Update app slider content
     * @param content
     * @param token
     * @return
     */
    @RequestMapping(value = "/slider/app/content/update")
    @ResponseBody
    public JsonResult ajaxAppSliderContentUpdate(AppSliderContent content,@RequestAttribute String token){
        return runService.updateAppSliderContent(content,token);
    }

    /**
     * App slider order
     * @return
     */
    @RequestMapping(value = "/slider/app/order")
    public String indexAppSliderOrder(){
        return "run/slider_app_order";
    }

    /**
     * App slider order drag
     * @param type
     * @return
     */
    @RequestMapping(value = "/slider/app/order/drag/{type}")
    public String indexAppSliderOrderDrag(@PathVariable Integer type, Model model){


        model.addAttribute("type",type);

        return "run/slider_app_order_drag";
    }

    /**
     * List app slider order
     * @param type
     * @return
     */
    @RequestMapping(value = "/slider/app/order/list/{type}")
    @ResponseBody
    public JsonResult ajaxListAppSliderOrder(@PathVariable Integer type){
        List<SliderItemDTO> list = runService.listAppSliderOrder(type);

        return JsonResult.success(list);
    }

    /**
     * Save app slider order
     * @param type
     * @param list
     * @return
     */
    @RequestMapping(value = "/slider/app/order/save/{type}")
    @ResponseBody
    public JsonResult ajaxAppSliderOrderSave(@PathVariable Integer type,String list){
        return runService.saveAppSliderOrder(type,list);
    }

    /**
     * List app slider usable
     * @param type
     * @return
     */
    @RequestMapping(value = "/slider/app/usable/list/{type}")
    @ResponseBody
    public JsonResult ajaxAppSliderUsable(@PathVariable Integer type){
        return runService.listUsableAppSlider(type);
    }
}
