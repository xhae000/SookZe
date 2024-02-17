package com.woojin.sookje.Kakaopay.Service;

import java.util.Optional;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;

import jakarta.servlet.http.HttpServletResponse;


public interface UserService {
    public String signUp(UserDto user);
    public Optional<UserEntity> getUserWithAuthorities(String username);
    public Optional<UserEntity> getThisUserWithAuthorities();
    public Boolean login(String username, String password, HttpServletResponse res);
    public String getUsernameByUserId(Long userId);
}
