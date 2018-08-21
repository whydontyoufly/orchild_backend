package com.orchid.mapper;

import com.orchid.entity.Menu;

import java.util.List;

/**
 * Created by ljg on 2018/5/4.
 */
public interface MenuMapper {
    List<Menu> selectByUserId(Long userId);
}
