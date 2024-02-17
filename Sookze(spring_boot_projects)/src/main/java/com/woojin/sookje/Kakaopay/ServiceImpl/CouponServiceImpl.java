package com.woojin.sookje.Kakaopay.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;
import com.woojin.sookje.Kakaopay.Repository.CouponRepository;
import com.woojin.sookje.Kakaopay.Repository.UserRepository;
import com.woojin.sookje.Kakaopay.Service.CouponService;
import com.woojin.sookje.Kakaopay.Util.SecurityUtil;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;

    @Override
    public void createCoupons(int N) {
        for (int i=0 ; i<N ; i++)
            couponRepository.save(new CouponEntity());  // default value로 저장
    }

    @Override
    @Transactional
    public String issueCoupon(){
        Optional<CouponEntity> OptionalNotIssuedCoupon = couponRepository.findTopByUserIdIsNull();
        if(OptionalNotIssuedCoupon.isEmpty()) return "쿠폰을 먼저 생성해주세요.";

        CouponEntity notIssuedCoupon = OptionalNotIssuedCoupon.get();

        Long currentUserId = userRepository.findUserIdByUsername(SecurityUtil.getCurrentUsername().get()).get();
        couponRepository.setUserId(notIssuedCoupon.getId(), currentUserId);

        return notIssuedCoupon.getCouponId();
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<String> getMyCouponIds(){
        Long userId = userRepository.findUserIdByUsername(SecurityUtil.getCurrentUsername().get()).get();
        return couponRepository.findByUserId(userId).stream().map(entity->entity.get().getCouponId().toString()).toList();
    }
}
