package com.orchid.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.orchid.entity.Role;
import com.orchid.entity.UserInfo;
import com.orchid.tools.annotation.Log;

/**
 * Created by ljg on 2018/5/4.
 */
public class UserDTO extends UserInfo {
    @Log(title = "角色")
    @JsonSerialize(using=ToStringSerializer.class)
    private Long[] roleList;

    public Long[] getRoleList() {
        return roleList;
    }

    public void setRoleList(Long[] roleList) {
        this.roleList = roleList;
    }
}
