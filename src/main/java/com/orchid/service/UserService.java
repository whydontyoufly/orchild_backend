package com.orchid.service;

import com.orchid.dto.UserDTO;
import com.orchid.entity.UserInfo;

import java.util.List;

/**
 * Created by ljg on 2018/5/4.
 */
public interface UserService {
    List<UserInfo> selectByAccount(String accountName);
    void addUserAndRole(UserDTO userDTO);
}
