package com.woojin.sookje.Kakaopay.Service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;

import jakarta.servlet.http.HttpServletResponse;


public interface UserService {
    
    @Transactional
    public String signUp(UserDto user);

    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserWithAuthorities(String username);

    @Transactional(readOnly = true)
    public Optional<UserEntity> getThisUserWithAuthorities();

    @Transactional(readOnly = true)
    public Boolean login(String username, String password, HttpServletResponse res);
}
