package com.woojin.sookje.Kakaopay.ServiceImpl;

import org.springframework.stereotype.Service;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;
import com.woojin.sookje.Kakaopay.Repository.CouponRepository;
import com.woojin.sookje.Kakaopay.Service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    public CouponServiceImpl(CouponRepository couponRepository){
        this.couponRepository = couponRepository;
    }

    @Override
    public void createCoupons(int N) {
        for (int i=0 ; i<N ; i++)
            couponRepository.save(new CouponEntity());  // default value로 저장
    }
    
}
