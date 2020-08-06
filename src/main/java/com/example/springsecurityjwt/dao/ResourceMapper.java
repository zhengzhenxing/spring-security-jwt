package com.example.springsecurityjwt.dao;

import com.example.springsecurityjwt.entity.ResourceBean;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResourceBean record);

    int insertSelective(ResourceBean record);

    ResourceBean selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourceBean record);

    int updateByPrimaryKey(ResourceBean record);

    @Cacheable(value = "selectAllResource")
    List<ResourceBean> selectAllResource();
}