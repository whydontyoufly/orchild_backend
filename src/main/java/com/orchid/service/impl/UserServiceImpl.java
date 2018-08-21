package com.orchid.service.impl;

import com.orchid.dto.UserDTO;
import com.orchid.entity.Role;
import com.orchid.entity.UserInfo;
import com.orchid.entity.UserRole;
import com.orchid.mapper.RoleMapper;
import com.orchid.mapper.UserInfoMapper;
import com.orchid.mapper.UserRoleMapper;
import com.orchid.service.UserService;
import com.orchid.tools.PasswordUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljg on 2018/5/4.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    public List<UserInfo> selectByAccount(String accountName) {
        return userInfoMapper.selectByAccount(accountName);
    }

    @Transactional
    public void addUserAndRole(UserDTO userDTO) {
        UserInfo user = new UserInfo();
        if("123456".equals(userDTO.getPassword())){
            user.setPwdStatus((short) 0);
        }else {
            user.setPwdStatus((short) 1);
        }
        user.setAccountName(userDTO.getAccountName());
        user.setCreateUser(userDTO.getCreateUser());
        user.setDataAreaCode(userDTO.getDataAreaCode());
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setDeptId(userDTO.getDeptId());
        user.setRemark(userDTO.getRemark());
        user.setStatus((short)1);
        user.setTelephone(userDTO.getTelephone());
        user.setUserPosition(userDTO.getUserPosition());
        user.setStaffNo(userDTO.getStaffNo());
        user.setPassword(PasswordUtils.encrypt(userDTO.getAccountName(),userDTO.getPassword()));
        userInfoMapper.insertSelective(user);

        Long userId = user.getId();
        List<UserRole> userRoles = new ArrayList<UserRole>();
        if(userDTO.getRoleList().length!=0){
            for (Long roleId:userDTO.getRoleList()){
                UserRole userRole = new UserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(userId);
                userRole.setStatus((short) 1);
                userRoles.add(userRole);
            }
            userRoleMapper.insertList(userRoles);
        }
    }
}
