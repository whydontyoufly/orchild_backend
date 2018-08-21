package com.orchid.controller;

import com.orchid.dto.Result;
import com.orchid.dto.UserDTO;
import com.orchid.entity.UserInfo;
import com.orchid.service.UserService;
import com.orchid.tools.annotation.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by ljg on 2018/5/2.
 */
@RestController
@RequestMapping("/userManager")
@Log(title = "用户管理")
public class UserManagerController {
    @Resource
    private UserService userService;

    /**
     * 新增用户信息
     * @param userDTO
     * @return
     */
    @RequestMapping("/addUser")
    @RequiresPermissions("user")
    public Result addUser(@RequestBody UserDTO userDTO){
        Result result = new Result();
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        userDTO.setCreateUser(userInfo.getId());
        if(userService.selectByAccount(userDTO.getAccountName()).size()==0){
            userService.addUserAndRole(userDTO);
            result.setMsg("创建成功");
            result.setStatus(1);
        }else {
            result.setMsg("创建失败，用户名重复");
            result.setStatus(0);
        }
        return result;
    }
}
