package com.practice.manage.aspect;

import com.practice.PermissonException;
import com.practice.enums.AuthEnum;
import com.practice.manage.target.ControllerPermisson;
import com.practice.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AOP permission
 * @author Xushd  2017/12/24 23:15
 */
@Aspect
@Component
public class PermissionAspect {


    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionAspect.class);

    @Resource
    private UserService userService;



    /**
     * Controller层切点
     */
    @Pointcut("@annotation(com.practice.manage.target.ControllerPermisson)")
    public void ControllerAspect(){

    }

    @Before("ControllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws PermissonException, ClassNotFoundException {

        Map<String, String> controllerDescription = getControllerDescription(joinPoint);

        String value = controllerDescription.get("value");

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String token = (String) request.getAttribute("token");

        List<String> permissionList =  userService.listUserPermission(token);

        if(permissionList==null || !permissionList.contains(value)){

            throw new PermissonException(AuthEnum.NO_AUTH.getMessage());
        }

    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint
     * @return
     * @throws ClassNotFoundException
     */
    private Map<String, String> getControllerDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        Map<String,String> map = new HashMap<>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("value",method.getAnnotation(ControllerPermisson.class).value());
                    break;
                }
            }
        }
        return map;
    }


}
