package com.example.springsecurityjwt.config;

import com.example.springsecurityjwt.filter.CustomAuthenticationFilter;
import com.example.springsecurityjwt.filter.JwtAuthenticationTokenFilter;
import com.example.springsecurityjwt.handler.*;
import com.example.springsecurityjwt.service.SelfUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    AjaxAuthenticationEntryPoint authenticationEntryPoint;  // 未登陆时返回 JSON 格式的数据给前端（否则为 html）

    @Resource
    AjaxAuthenticationSuccessHandler authenticationSuccessHandler;  // 登录成功返回的 JSON 格式数据给前端（否则为 html）

    @Resource
    AjaxAuthenticationFailureHandler authenticationFailureHandler;  // 登录失败返回的 JSON 格式数据给前端（否则为 html）

    @Resource
    AjaxLogoutSuccessHandler logoutSuccessHandler;  // 注销成功返回的 JSON 格式数据给前端（否则为 登录时的 html）

    @Resource
    AjaxAccessDeniedHandler accessDeniedHandler;    // 无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）

    @Resource
    SelfUserDetailsService userDetailsService; // 自定义user

    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter; // JWT 拦截器

    @Resource
    UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 加入自定义的安全认证
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

        // 这里可启用我们自己的登陆验证逻辑
        // auth.authenticationProvider(userAuthenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/home/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 去掉 CSRF（跨域）
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用JWT，关闭session
                .and()

                // 未登陆时返回 JSON
                .httpBasic().authenticationEntryPoint(authenticationEntryPoint)
                .and()

                // 所有请求必须认证
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .anyRequest()

                // 认证的逻辑
                .access("@rbacauthorityservice.hasPermission(request, authentication)") // RBAC 动态 url 认证
                .and()

                //开启登录
                /*
                .formLogin()
                .loginPage("/")
                .successHandler(authenticationSuccessHandler) // 登录成功
                .failureHandler(authenticationFailureHandler) // 登录失败
                .permitAll()
                .and()
                */

                // 登出
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(logoutSuccessHandler)
                .permitAll();

        // 记住我
        http.rememberMe().rememberMeParameter("remember-me").userDetailsService(userDetailsService).tokenValiditySeconds(300);

        // 无权访问 JSON 格式的数据
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        // 用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter实现使用json 数据也可以登陆
        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        // 设置执行其他工作前的 filter （最重要的验证 JWT）
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // 注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setFilterProcessesUrl("/auth/login"); // 设置登陆接口名

        // 这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
