package com.practice.manage.interceptor;

import com.practice.enums.AuthEnum;
import com.practice.result.JsonResult;
import com.practice.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Xushd  2017/12/21 21:20
 */
public class ManageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                token = cookie.getValue();
            }
        }
        String requestType = request.getHeader("X-Requested-With");
        String url = request.getRequestURL().toString();

        if (StringUtils.isNotBlank(token)) {
//            JsonResult result = loginService.checkManageLoginStatus(token);
//            if(result.getStatus()==200){
//                request.setAttribute("token",token);
//                return true;
//            }
        }
        if(StringUtils.isBlank(requestType)){
            request.getRequestDispatcher("/").forward(request, response);
        }else{
            //ajax
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtils.objectToJson(JsonResult.error(AuthEnum.NO_AUTH)));
            writer.flush();
        }
        return false;
    }
}
