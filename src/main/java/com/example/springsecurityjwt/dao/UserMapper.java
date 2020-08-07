package com.example.springsecurityjwt.dao;

import com.example.springsecurityjwt.entity.SelfUserDetails;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.cache.annotation.Cacheable;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SelfUserDetails record);

    int insertSelective(SelfUserDetails record);

    SelfUserDetails selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SelfUserDetails record);

    int updateByPrimaryKey(SelfUserDetails record);

    @Cacheable(value = "selectByUsername")
    SelfUserDetails selectByUsername(@Param("username") String username);
}