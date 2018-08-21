package com.orchid.mapper;

import com.orchid.entity.Role;

import java.util.List;

/**
 * Created by ljg on 2018/5/4.
 */
public interface RoleMapper {
    List<Role> getListByUserId(Long userId);
}
