package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.dao.ResourceMapper;
import com.example.springsecurityjwt.entity.ResourceBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ResourceService {

    @Resource
    private ResourceMapper resourceMapper;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Cacheable(value = "allowRolesForRequestURL")
    public Set<String> allowRolesForRequestURL(String requestURL) {
        Set<String> roles = new HashSet();

        // 从数据库中得到所有资源，以及对应的角色
        List<ResourceBean> resourceBeans = resourceMapper.selectAllResource();
        for (ResourceBean resource : resourceBeans) {
            //首先进行地址匹配
            if (antPathMatcher.match(resource.getUrl(), requestURL)) {
                String[] resourceRoles = resource.getRolesArray();
                setRoles(roles, resourceRoles);
            }
        }

        return roles;
    }

    private void setRoles(Set<String> roles, String[] resourceRoles) {
        for (String role : resourceRoles) {
            roles.add(role);
        }
    }
}
