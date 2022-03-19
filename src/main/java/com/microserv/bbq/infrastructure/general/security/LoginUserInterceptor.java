package com.microserv.bbq.infrastructure.general.security;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 用户鉴权信息-拦截器
 *
 * @author mingjie
 * @date 2022/3/19
 */
public class LoginUserInterceptor implements HandlerInterceptor {
    private static final String HEADER_LOGIN_USER = "X-LOGIN-USER";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginUserBase64String = request.getHeader(HEADER_LOGIN_USER);
        String loginUserString = new String(Base64.getDecoder().decode(loginUserBase64String), StandardCharsets.UTF_8);
        LoginUser loginUser = JSON.parseObject(loginUserString, LoginUser.class);
        SecurityContext.set(loginUser);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        SecurityContext.clear();
    }
}
