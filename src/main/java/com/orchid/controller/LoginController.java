package com.orchid.controller;

import com.google.common.base.Strings;
import com.orchid.dto.Result;
import com.orchid.entity.LoginUserInfo;
import com.orchid.entity.UserInfo;
import com.orchid.tools.annotation.Log;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ljg on 2018/5/8.
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/do")
    @Log(title = "用户登陆")
    public Result login(@RequestBody LoginUserInfo loginUserInfo){
        Result result = new Result();
        String accountName = loginUserInfo.getAccountName();
        String password = loginUserInfo.getPassword();

        if(Strings.isNullOrEmpty(accountName)||Strings.isNullOrEmpty(password)){
            result.setMsg("用户名或密码为空");
            result.setStatus(0);
            return result;
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(accountName,password);

        UserInfo userInfo = null;
        try {
            subject.login(token);
            userInfo = (UserInfo) subject.getPrincipal();
            subject.getSession().setAttribute("userInfo",userInfo);
        }catch (UnknownAccountException e){
            result.setMsg("用户不存在");
            result.setStatus(0);
            return result;
        }catch (IncorrectCredentialsException e){
            result.setMsg("密码错误");
            result.setStatus(0);
            return result;
        }

        result.setStatus(1);
        result.setInfo(userInfo.getAccountName());
        result.setMsg("登陆成功");
        return result;
    }
}
