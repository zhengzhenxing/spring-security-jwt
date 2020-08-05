package com.example.springsecurityjwt.dao;

import com.example.springsecurityjwt.entity.SelfUserDetails;
import io.lettuce.core.dynamic.annotation.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SelfUserDetails record);

    int insertSelective(SelfUserDetails record);

    SelfUserDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SelfUserDetails record);

    int updateByPrimaryKey(SelfUserDetails record);

    SelfUserDetails selectByUsername(@Param("username") String username);
}