package com.orchid.aspectj;

import com.orchid.entity.UserInfo;
import com.orchid.tools.IpUtil;
import com.orchid.tools.annotation.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ljg on 2018/5/11.
 */
public class UserLogAspect {
    @Autowired
    private HttpServletRequest request;

    public void addUserLog(JoinPoint joinPoint,Object retValue){
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //方法注解
        Log methodLog = method.getAnnotation(Log.class);
        if(methodLog==null){
            return;
        }

        //用户ID
        Long userId = 0l;
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            UserInfo userInfo = (UserInfo)subject.getPrincipal();
            userId = userInfo.getId();
        }

        //获取IP地址
        String ip = IpUtil.getIpAddr(request);

        Class targetClass = joinPoint.getTarget().getClass();
        String log_type = targetClass.getSimpleName();
        String log_type_name = "";
        Log classLog = (Log) targetClass.getAnnotation(Log.class);
        if(classLog!=null){
            log_type_name = classLog.title();
        }

        String action = method.getName();
        String action_name = methodLog.title();

        //请求参数信息
        List<Map<String,String>> fieldList = new ArrayList<Map<String, String>>();
        Map<Integer,Object> logParams = new HashMap<Integer, Object>();

        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            arg.getClass();
        }
    }
}
