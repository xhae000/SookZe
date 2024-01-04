package com.woojin.sookje.Lezhin.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.woojin.sookje.Lezhin.Repository.FollowRepository;


@RestController
public class LezhinController {
    FollowRepository followRepository;

    public LezhinController (FollowRepository followRepository){
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
