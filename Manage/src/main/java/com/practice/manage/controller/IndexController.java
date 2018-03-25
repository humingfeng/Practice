package com.practice.manage.controller;

import com.practice.dto.CountDTO;
import com.practice.dto.KeyValueDTO;
import com.practice.enums.SystemParamEnum;
import com.practice.service.ParamService;
import com.practice.service.StatisticsService;
import com.practice.service.VersionService;
import com.practice.vo.SystemInfoVO;
import com.practice.dto.TokenUserDTO;
import com.practice.utils.JwtTokenUtil;
import com.practice.vo.VersionVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

/**
 * Index controller
 *
 * @author Xushd  2017/12/21 21:44
 */
@Controller
public class IndexController {


    @Resource
    private VersionService versionService;
    @Resource
    private ParamService paramService;
    @Resource
    private StatisticsService statisticsService;

    @RequestMapping(value = "/")
    public String index(@CookieValue(required = false,value = "manage_token") String token,Model model) {

        if (StringUtils.isNotBlank(token)) {

            TokenUserDTO tokeUser = JwtTokenUtil.getTokeUser(token);

            if(tokeUser==null){
                return "login";
            }

            model.addAttribute("userName",tokeUser.getAccount());
            model.addAttribute("headImg",tokeUser.getHeadImg());

        } else {
            return "login";
        }



        return "index";
    }

    @RequestMapping(value = "/404")
    public String index404() {

        return "404";
    }

    @RequestMapping(value = "/head")
    public String indexHead(Model model){


        KeyValueDTO paramICON = paramService.getParamByEnum(SystemParamEnum.ICONFONT_CSS);

        model.addAttribute("iconfoncss",paramICON.getValue());

        return "head";
    }


    /**
     * Welcome
     * @return
     */
    @RequestMapping(value = "/auth/welcome")
    public String indexWelcome(Model model){

        Properties properties = System.getProperties();

        SystemInfoVO dto = new SystemInfoVO();
        dto.setJavaVersion(properties.getProperty("java.version"));
        dto.setJavaVendor(properties.getProperty("java.vendor"));
        dto.setOsName(properties.getProperty("os.name"));
        dto.setOsArch(properties.getProperty("os.arch"));
        dto.setOsVersion(properties.getProperty("os.version"));
        dto.setCurVersion("v0.0.1");
        dto.setMysqlVersion("v5.6.21");
        model.addAttribute("system",dto);

        List<VersionVO> versionLog = versionService.listVersion();

        model.addAttribute("versionLog",versionLog);


        CountDTO countDTO = statisticsService.getCountDTO();

        model.addAttribute("count",countDTO);


        return "welcome";
    }

    /**
     * jump common
     * @param module
     * @param page
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/auth/jump/{module}/{page}")
    public String indexJump(@PathVariable String module,
                            @PathVariable String page,
                            String id, Model model){

        if(StringUtils.isNotBlank(id)){
            model.addAttribute("id",id);
        }
        return module+"/"+page;
    }

}
