package com.practice.manage.resolver;


import com.practice.PermissonException;
import com.practice.enums.AuthEnum;
import com.practice.enums.OperateEnum;
import com.practice.manage.aspect.PermissionAspect;
import com.practice.result.JsonResult;

import com.practice.utils.ExceptionUtil;
import com.practice.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常类
 *
 * @author Xushd  2017/12/21 21:40
 */
public class GlobalExceptionResolver extends SimpleMappingExceptionResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        response.setCharacterEncoding("UTF-8");
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.isBlank(requestType)){
            LOGGER.error("Error:{}", ex);
            return getModelAndView("/404", ex, request);

        } else {//ajax

            String result = "";

            if(ex.getCause() instanceof PermissonException){

                result = JsonUtils.objectToJson(JsonResult.error(AuthEnum.NO_AUTH));

            }else{

                result = JsonUtils.objectToJson(JsonResult.error(ExceptionUtil.getStackTrace(ex)));
                LOGGER.error("Error:{}", ex);
            }

            try {
                PrintWriter writer = response.getWriter();
                writer.write(result);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ModelAndView();
        }
    }




}
