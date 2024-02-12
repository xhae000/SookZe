package com.woojin.sookje.Kakaopay.Jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
      //  response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "wrong username or password");

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
                                    
        response.getWriter().write("401 error : ID 혹은 PW 잘못됨.");
    }



}