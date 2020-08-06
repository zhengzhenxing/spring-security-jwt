package com.example.springsecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurityjwt.api.CommonResult;
import com.example.springsecurityjwt.config.TokenProperties;
import com.example.springsecurityjwt.entity.SelfUserDetails;
import com.example.springsecurityjwt.utils.JwtTokenUtil;
import io.netty.util.CharsetUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录成功的处理类
 */
@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private TokenProperties tokenProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        SelfUserDetails selfUserDetails = (SelfUserDetails)authentication.getPrincipal();

        String accessToken = JwtTokenUtil.generateToken(selfUserDetails.getUsername(), tokenProperties.getTokenExpireSecond());
        String refreshToken = JwtTokenUtil.generateToken(selfUserDetails.getUsername(), tokenProperties.getRefreshTokenExpiredSecond(), tokenProperties.getSecretKey());

        Map<String, String> map = new HashMap<>();
        map.put("accessToken", accessToken);
        map.put("refreshToken", refreshToken);

        CommonResult result = CommonResult.success(map, "登录成功");

        httpServletResponse.setCharacterEncoding(CharsetUtil.UTF_8.name());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }

}
