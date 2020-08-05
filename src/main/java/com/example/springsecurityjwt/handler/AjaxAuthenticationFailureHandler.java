package com.example.springsecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurityjwt.api.AjaxResponseBody;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败的处理类
 */
@Component
public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("400");
        responseBody.setMsg("登录失败");

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
