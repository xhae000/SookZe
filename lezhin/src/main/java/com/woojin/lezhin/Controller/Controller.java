package com.woojin.lezhin.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woojin.lezhin.Entity.FollowEntity;
import com.woojin.lezhin.Repository.FollowRepository;

import lombok.RequiredArgsConstructor;

@RestController
public class Controller {
    FollowRepository followRepository;

    public Controller (FollowRepository followRepository){
        this.followRepository = followRepository;
    }

    /**
     * 1. 팔로우
     */
    @PostMapping("/follow")
    public String follow(@RequestParam Long targetUserId){

        return "test";
    }   

    /**
     * 2. 포스팅
     */
    @GetMapping("/post")
    public String posting(){
        return "test";
    }   
}
