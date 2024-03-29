package com.woojin.sookje.Kakaopay.Util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.woojin.sookje.Kakaopay.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityUtil {
    private final UserRepository userRepository;

    public Optional<String> getCurrentUsername() {
        // authentication객체가 저장되는 시점은 JwtFilter의 doFilter 메소드에서 
        // Request가 들어올 때 SecurityContext에 Authentication 객체를 저장해서 사용
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            System.out.println("로그인이 안되어있음.");
            return Optional.empty();
        }
        return Optional.ofNullable(authentication.getName());
    }

    public Long getCurrentUserId() {
            return userRepository.findUserIdByUsername(getCurrentUsername().get()).get();
    }
}
