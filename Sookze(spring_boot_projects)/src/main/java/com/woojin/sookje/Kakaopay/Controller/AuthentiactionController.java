package com.woojin.sookje.Kakaopay.Controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Kakaopay.Dto.UserDto;
import com.woojin.sookje.Kakaopay.Service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


/* get 방식을 통한 값 전송으로 로그인, 회원가입 과정 일부 간소화 */ 
@RequiredArgsConstructor
@RestController
public class AuthentiactionController {
    private final UserService userService;

    @GetMapping("/kakaopay/login") 
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse res)throws IOException {
        userService.login(username, password, res);
        return "로그인 되었음!";
    }

    @GetMapping("/kakaopay/signup")
    public String signUp(@RequestParam String username, @RequestParam String password) {
        UserDto userDto = UserDto.builder()
            .username(username)
            .password(password)
            .build();
        return userService.signUp(userDto) + " : 회원가입 완료!";  // return "username : 회원가입 완료!"
    }
    
    
}
