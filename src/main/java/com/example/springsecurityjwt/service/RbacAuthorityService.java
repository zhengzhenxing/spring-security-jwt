package com.example.springsecurityjwt.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Set;

@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Resource
    private ResourceService resourceService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {

            Collection<? extends GrantedAuthority> authorities = ((UserDetails) userInfo).getAuthorities();
            Set<String> allowRoles = resourceService.allowRolesForRequestURL(request.getRequestURI());

            for (GrantedAuthority authority : authorities) {
                for (String allowRole : allowRoles) {
                    if (authority.getAuthority().equals(allowRole)) {
                        hasPermission = true;
                        break;
                    }
                }
            }

            return hasPermission;
        } else {
            return false;
        }

    }
}
