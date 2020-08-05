package com.example.springsecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurityjwt.api.AjaxResponseBody;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 未登陆的处理类
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("000");
        responseBody.setMsg("未登录！");

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}
