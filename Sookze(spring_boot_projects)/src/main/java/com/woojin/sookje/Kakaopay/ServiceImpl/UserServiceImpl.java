package com.woojin.sookje.Kakaopay.ServiceImpl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Entity.AuthorityEntity;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;
import com.woojin.sookje.Kakaopay.Enum.UserDtoType;
import com.woojin.sookje.Kakaopay.Enum.UserEntityType;
import com.woojin.sookje.Kakaopay.Repository.UserRepository;
import com.woojin.sookje.Kakaopay.Service.UserService;
import com.woojin.sookje.Kakaopay.Util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String signUp(UserDto userDto) {
        String username = userDto.getUsername();
            if (userRepository.findOneWithAuthorityByUsername(username).orElse(null) != null) {
                throw new RuntimeException(username + " : already existed username.");
            }

        AuthorityEntity authorityEntity = AuthorityEntity.builder()
            .authorityName("ROLE_USER")
            .build();

        UserEntity userEntity = UserEntity.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .authorities(Collections.singleton(authorityEntity))
            .isActivated(true)
            .build();
        userRepository.save(userEntity);

        return username;
    }

    @Override
    public Optional<UserEntity> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthorityByUsername(username);
    }

    @Override
    public Optional<UserEntity> getThisUserWithAuthorities() {
       return userRepository.findOneWithAuthorityByUsername(SecurityUtil.getCurrentUsername().get());
    }
    
}
