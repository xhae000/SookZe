package com.woojin.sookje.Kakaopay.ServiceImpl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Entity.AuthorityEntity;
import com.woojin.sookje.Kakaopay.Entity.UserEntity;
import com.woojin.sookje.Kakaopay.Jwt.TokenProvider;
import com.woojin.sookje.Kakaopay.Repository.UserRepository;
import com.woojin.sookje.Kakaopay.Service.UserService;
import com.woojin.sookje.Kakaopay.Util.SecurityUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final SecurityUtil securityUtil;


    @Override
    @Transactional(readOnly = false)
    public String signUp(UserDto userDto) {
        String username = userDto.getUsername();
            if (userRepository.findOneWithAuthorityByUsername(username).orElse(null) != null) {
                // 중복 username
                throw new RuntimeException(username + " : already existed username.");
            }

        AuthorityEntity authorityEntity = AuthorityEntity.builder()
            .authorityName("ROLE_USER")
            .build();

        UserEntity userEntity = UserEntity.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .authority(Collections.singleton(authorityEntity))
            .isActivated(true)
            .build();
        userRepository.save(userEntity);

        return username;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthorityByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> getThisUserWithAuthorities() {
       return userRepository.findOneWithAuthorityByUsername(securityUtil.getCurrentUsername().get());
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean login(String username, String password, HttpServletResponse res) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(username, password);    

        /*  AuthenticationMangager : user 정보 불러오고, pw 대조하는 인증 작업 처리
            user 정보가 없거나(username not found), pw가 일치하지 않으면 throw exeption,  AuthenticationEntryPoint에서 예외 핸들링 
        */
        Authentication authentication = null;
            authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
  
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);

        res.addCookie(createAccessTokenCookie(jwt));
        return true;
    }
    
    @Transactional(readOnly = true)
    @Override
    public String getUsernameByUserId(Long userId){
        return userRepository.findUsernameById(userId);
    }

    private Cookie createAccessTokenCookie(String jwt){
        // access token 생성 및 클라이언트 저장 
        Cookie cookie;
        cookie = new Cookie("ACCESS_TOKEN", jwt);

        cookie.setHttpOnly(true);  //httponly 옵션 설정
        cookie.setPath("/"); // 모든 곳에서 쿠키열람이 가능하도록 설정
        cookie.setMaxAge(60 * 60 * 24); //쿠키 만료시간 설정 (24 h)

        return cookie;
    }
}
