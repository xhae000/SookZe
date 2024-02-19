package com.woojin.sookje.Kakaopay.Util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.woojin.sookje.Kakaopay.Service.CouponService;
import com.woojin.sookje.Kakaopay.Service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CouponScheduler {

    private final CouponService couponService;
    private final UserService userService;

    private String getExpirationMessage(Long userId, String couponId){
        String username = userService.getUsernameByUserId(userId);
        return "["+username+"] 님의 보유하신 다음 쿠폰이 3일 뒤 삭제됩니다. 쿠폰 번호: "+couponId;
    }

    /**
     * 매일 아침 9시마다, 3일 뒤 소멸되는 쿠폰 출력
     */
    @Scheduled(cron = "0 0 9 * * *") // 매일 아침 9시 실행
    public void sendCouponExpiredMsg(){
        couponService.getCouponsLeft3days().stream()
            .forEach(c -> 
                System.out.println(getExpirationMessage(c.getUserId(), c.getCouponId()))
                );
    }


}
