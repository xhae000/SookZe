package com.woojin.sookje.Kakaopay.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Jwt.JwtFilter;
import com.woojin.sookje.Kakaopay.Jwt.TokenProvider;
import com.woojin.sookje.Kakaopay.Service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RequiredArgsConstructor
@RestController
public class AuthentiactionController {
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/login") // do.
    public String login(@RequestBody UserDto user) {
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());    

        // login 메소드가 실행이 될 때 CustomUserDetailsService class의 loadUserByUsername 메소드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authToken);
        // 해당 객체를 SecurityContextHolder에 저장하고
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // authentication 객체를 createToken 메소드를 통해서 JWT Token을 생성
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        // response header에 jwt token에 넣어줌
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return "";
    }


    // 테스트의 용이함을 위해 회원가입 form 생략 및 간소화
    @GetMapping("/signUp")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        UserDto userDto = UserDto.builder()
            .username(username)
            .password(password)
            .build();
        return userService.signUp(userDto) + " : 회원가입 완료!";
    }
    
    
}
