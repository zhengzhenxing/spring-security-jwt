package com.example.springsecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurityjwt.api.AjaxResponseBody;
import com.example.springsecurityjwt.entity.SelfUserDetails;
import com.example.springsecurityjwt.utils.JwtTokenUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功的处理类
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("1");
        responseBody.setMsg("登录成功");

        SelfUserDetails selfUserDetails = (SelfUserDetails)authentication.getPrincipal();

        // 创建token，并返回，设置过期时间为300秒
        String jwtToken = JwtTokenUtil.generateToken(selfUserDetails.getUsername(), 300);
        responseBody.setJwtToken(jwtToken);

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
