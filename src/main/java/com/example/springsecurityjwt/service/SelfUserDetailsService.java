package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dao.UserMapper;
import com.example.springsecurityjwt.entity.SelfUserDetails;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SelfUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SelfUserDetails selfUserDetails = userMapper.selectByUsername(s);
        if (selfUserDetails == null) {
            throw new UsernameNotFoundException("数据库中无此用户！");
        }
        return selfUserDetails;
    }
}
