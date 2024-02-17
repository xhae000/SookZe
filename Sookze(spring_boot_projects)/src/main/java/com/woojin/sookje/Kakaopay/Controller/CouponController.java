package com.woojin.sookje.Kakaopay.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;
import com.woojin.sookje.Kakaopay.Service.CouponService;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kakaopay/coupon")
public class CouponController {
    private final CouponService couponService;

    public CouponController (CouponService couponService){
        this.couponService = couponService;
    }

    /**
     * 1. 랜덤한 N개 쿠폰 생성
     */
    @GetMapping("/save")
    public String follow(@RequestParam int N){
        couponService.createCoupons(N);
        return N + "개의 쿠폰이 생성됨";
    }       

    @GetMapping("/issueCoupon")
    public String issueCoupon(){
       return "다음 쿠폰이 발급됨 : " + couponService.issueCoupon();
    }

    @GetMapping("/getMyCouponIds")
    public String getMyCouponIds(){
        List<String> couponIds = couponService.getMyCouponIds();
        return "당신은 다음 쿠폰을 갖고 있어요." + couponIds.stream().collect(Collectors.joining("\n"));
    }

}
