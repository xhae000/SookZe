package com.woojin.sookje.Kakaopay.Service;

import java.util.List;

import com.woojin.sookje.Kakaopay.Dto.CouponDto;
import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

public interface CouponService {
    public void createCoupons(int N);
    public String issueCoupon();
    public List<String> getMyCouponIds();
    public boolean isExpiredCoupon(String couponId);
    public boolean isExpiredCoupon(CouponEntity couponEntity);
    public List<CouponDto> getCouponsExpiredToday();
    public List<CouponDto> getCouponsLeft3days();
    public boolean useCoupon(String couponId);
    public List<String> getUsedCouponIds();
    public Boolean cancelCoupon(String couponId);

}
