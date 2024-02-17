package com.woojin.sookje.Kakaopay.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Kakaopay.Dto.CouponDto;
import com.woojin.sookje.Kakaopay.Service.CouponService;

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
        couponService.getCouponsExpiredToday();
        return N + "개의 쿠폰이 생성됨";
    }       

    @GetMapping("/issueCoupon")
    public String issueCoupon(){
       String issuedCouponId = couponService.issueCoupon();

       return "다음 쿠폰이 발급됨 : " + issuedCouponId;
    }

    @GetMapping("/getMyCouponIds")
    public String getMyCouponIds(){
        List<String> couponIds = couponService.getMyCouponIds();

        return "당신은 다음 쿠폰을 갖고 있어요." + 
            couponIds.stream()
            .collect(Collectors.joining("\n"));
    }

    @GetMapping("/getMyUsedCouponIds")
    public String getMyUsedCouponIds(){
        List<String> usedCouponIds = couponService.getUsedCouponIds();

        return "당신은 다음 쿠폰들을 썼어요." + 
            usedCouponIds.stream()
            .collect(Collectors.joining("\n"));
    }

    @GetMapping("/useCoupon/{couponId}")
    public String useCoupon(@PathVariable(required = true) String couponId){
        boolean isSuccessfulUsing = couponService.useCoupon(couponId);

        return isSuccessfulUsing ? "사용 완료됨" : "이거 못씀";
    }

    @GetMapping("/cancelCoupon/{couponId}")
    public String cancelCoupon(@PathVariable(required = true) String couponId){
        boolean isSuccessfulCancel = couponService.cancelCoupon(couponId);

        return isSuccessfulCancel ? "사용취소 됨" : "없거나 기간만료된 쿠폰임";
    }

    @GetMapping("/getCouponsExpiredToday")
    public String getCouponsExpiredToday(){
        List<CouponDto> couponsExpiredToday = couponService.getCouponsExpiredToday();

        return "당신이 가진 쿠폰 중 다음 쿠폰들은 오늘 만료될거에요." +
            couponsExpiredToday.stream()
            .map(CouponDto::getCouponId)
            .collect(Collectors.joining("\n"));
    }

}
