package com.practice.manage.interceptor;

import com.practice.enums.AuthEnum;
import com.practice.result.JsonResult;
import com.practice.utils.JsonUtils;
import com.practice.utils.JwtTokenUtil;
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

        String token=null;
        String requestType = request.getHeader("X-Requested-With");
        if(StringUtils.isBlank(requestType)){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("manage_token")){
                    token = cookie.getValue();
                    break;
                }
            }
            if (StringUtils.isNotBlank(token) && JwtTokenUtil.isJwtTimeOut(token)) {
                request.setAttribute("token",token);
                return true;
            }

            java.io.PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<script>");
            out.println("window.open ('/login','_top')");
            out.println("</script>");
            out.println("</html>");
        }else{
            //ajax
            token = request.getHeader("manage-token");
            if(StringUtils.isNotBlank(token) && JwtTokenUtil.isJwtTimeOut(token)){
                request.setAttribute("token",token);
                return true;
            }
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtils.objectToJson(JsonResult.error(AuthEnum.TIME_OUT)));
            writer.flush();
        }
        return false;
    }
}
