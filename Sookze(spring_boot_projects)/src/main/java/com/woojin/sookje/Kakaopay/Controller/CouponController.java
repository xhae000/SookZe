package com.woojin.sookje.Kakaopay.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Kakaopay.Repository.CouponRepository;
import com.woojin.sookje.Kakaopay.Service.CouponService;


@RestController
public class CouponController {
    private final CouponService couponService;

    public CouponController (CouponService couponService){
        this.couponService = couponService;
    }

    /**
     * 1. 랜덤한 N개 쿠폰 생성
     */
    @GetMapping("/kakaopay/coupon/save")
    public String follow(@RequestParam int N){
        couponService.createCoupons(N);
        return "created coupons!!";
    }       

}
