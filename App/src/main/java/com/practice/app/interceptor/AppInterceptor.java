package com.practice.app.interceptor;

import com.practice.enums.AuthEnum;
import com.practice.result.JsonResult;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Xushd  2018/1/15 21:01
 */
public class AppInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("manage-token");
        if(StringUtils.isNotBlank(token) && JwtTokenUtil.isJwtTimeOut(token)){
            request.setAttribute("token",token);
            return true;
        }
        PrintWriter writer = response.getWriter();
        writer.write(JsonUtils.objectToJson(JsonResult.error(AuthEnum.TIME_OUT)));
        writer.flush();
        return false;
    }
}
