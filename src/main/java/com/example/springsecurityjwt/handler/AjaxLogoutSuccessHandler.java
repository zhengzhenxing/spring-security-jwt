package com.example.springsecurityjwt.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecurityjwt.api.AjaxResponseBody;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功的处理类
 */
@Component
public class AjaxLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("100");
        responseBody.setMsg("登出成功");

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }

}
