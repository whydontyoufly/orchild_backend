package com.orchid.mapper;

import com.orchid.entity.UserInfo;

import java.util.List;

/**
 * Created by ljg on 2018/5/2.
 */
public interface UserInfoMapper {
    List<UserInfo> selectByAccount(String accountName);
    void insertSelective(UserInfo userInfo);
}
