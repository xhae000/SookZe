package com.woojin.sookje.Kakaopay.Service;

import java.util.List;
import java.util.Optional;


import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

public interface CouponService {
    public void createCoupons(int N);
    public String issueCoupon();
    public List<String> getMyCouponIds();
}
