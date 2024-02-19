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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/kakaopay/coupon")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponService;

    /**
     * 랜덤한 N개 쿠폰 생성
     */
    @GetMapping("/save")
    public String follow(@RequestParam int N){
        couponService.createCoupons(N);
        couponService.getCouponsExpiredToday();
        return N + "개의 쿠폰이 생성됨";
    }       

    /**
     * 쿠폰 발급
     */
    @GetMapping("/issueCoupon")
    public String issueCoupon(){
       String issuedCouponId = couponService.issueCoupon();
       return "다음 쿠폰이 발급됨 : " + issuedCouponId;
    }

    /**
     * 유저가 사용할 수 있는 쿠폰
     */
    @GetMapping("/getMyCouponIds")
    public String getMyCouponIds(){
        List<String> couponIds = couponService.getMyCouponIds();

        if(couponIds.size() == 0)
            return "가진 쿠폰이 없음";

        return "당신은 다음 쿠폰을 갖고 있어요.<br>" + 
            couponIds.stream()
            .collect(Collectors.joining("<br>"));
    }

    /**
     * 유저가 사용한 쿠폰
     */
    @GetMapping("/getMyUsedCouponIds")
    public String getMyUsedCouponIds(){
        List<String> usedCouponIds = couponService.getUsedCouponIds();

        if(usedCouponIds.size() == 0)
            return "사용한 쿠폰 없음";

        return "당신은 다음 쿠폰들을 사용했어요.<br>" + 
            usedCouponIds.stream()
            .collect(Collectors.joining("<br>"));
    }


    /**
     * 쿠폰 사용
     */
    @GetMapping("/useCoupon/{couponId}")
    public String useCoupon(@PathVariable(required = true) String couponId){
        boolean isSuccessfulUsing = couponService.useCoupon(couponId);

        return isSuccessfulUsing ? "사용 완료됨" : "이거 못씀";
    }

    /**
     * 쿠폰 사용 취소 (기간 만료되지 않았다면, 재사용 가능)
     */
    @GetMapping("/cancelCoupon/{couponId}")
    public String cancelCoupon(@PathVariable(required = true) String couponId){
        boolean isSuccessfulCancel = couponService.cancelCoupon(couponId);

        return isSuccessfulCancel ? "사용취소 됨" : "없거나 기간만료된 쿠폰임";
    }

    /**
     * 유저가 갖고있는 쿠폰 중 오늘 만료되는 쿠폰들
     */
    @GetMapping("/getCouponsExpiredToday")
    public String getCouponsExpiredToday(){
        List<CouponDto> couponsExpiredToday = couponService.getCouponsExpiredToday();

        if(couponsExpiredToday == null) 
            return "가진 쿠폰 없음";
        else if(couponsExpiredToday.size() == 0)
            return "오늘 만료되는 쿠폰 없음";
    
        return "당신이 가진 쿠폰 중 다음 쿠폰들은 오늘 만료될거에요.<br>" +
            couponsExpiredToday.stream()
            .map(CouponDto::getCouponId)
            .collect(Collectors.joining("<br>"));
    }

}
