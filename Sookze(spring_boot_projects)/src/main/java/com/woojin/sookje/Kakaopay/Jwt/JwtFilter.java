package com.woojin.sookje.Kakaopay.Jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    @Value("${jwt.authorization-header}")
    public static String AUTHORIZATION_HEADER;
    @Value("${jwt.access-token-cookie-name}")
    private static String ACCESS_TOKEN_COOKIE_NAME;
    private final TokenProvider tokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException{
        String jwt = resolveToken(request);

        if(StringUtils.hasText(jwt) && tokenProvider.valdiateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Cookie cookies[] = httpServletRequest.getCookies();
        String bearerToken = null;

        for(int i=0; i < (cookies == null ? 0 : cookies.length); i++){
            if(cookies[i].getName() == ACCESS_TOKEN_COOKIE_NAME)
                bearerToken = cookies[i].getValue();
        }
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) 
            return bearerToken.substring(7);
        return null;
    }
    
}
