package com.woojin.sookje.Kakaopay.Service;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;


public interface UserService {
    
    @Transactional
    public String signUp(UserDto user);

    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserWithAuthorities(String username);

    @Transactional(readOnly = true)
    public Optional<UserEntity> getThisUserWithAuthorities();
}
