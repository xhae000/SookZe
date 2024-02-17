package com.woojin.sookje.Kakaopay.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.woojin.sookje.Kakaopay.Dto.CouponDto;
import com.woojin.sookje.Kakaopay.Entity.CouponEntity;
import com.woojin.sookje.Kakaopay.Entity.UsedCouponEntity;
import com.woojin.sookje.Kakaopay.Repository.CouponRepository;
import com.woojin.sookje.Kakaopay.Repository.UsedCouponRepository;
import com.woojin.sookje.Kakaopay.Repository.UserRepository;
import com.woojin.sookje.Kakaopay.Service.CouponService;
import com.woojin.sookje.Kakaopay.Util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final UsedCouponRepository usedCouponRepository;

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
        return couponRepository.findByUserId(userId).stream()
            .filter(entity -> !isExpiredCoupon(entity.get()))
            .map(entity -> entity.get().getCouponId())
            .toList();
    }

    @Override
    @Transactional(readOnly=true)
    public boolean isExpiredCoupon(String couponId){ // input : couponId
        // 쿠폰 유효기간 : 7일 
        Optional<CouponEntity> optionalCouponEntity = couponRepository.findByCouponId(couponId);
        if(optionalCouponEntity.isEmpty()) 
            return true;
        Long dateDifference = getCouponExpirationDateDifference(optionalCouponEntity.get().getCreationDate());

        return dateDifference  >= 8; 
    }

    @Override
    @Transactional(readOnly=true)
    public boolean isExpiredCoupon(CouponEntity couponEntity){ // input : couponEntity
        // 쿠폰 유효기간 : 7일 
        Long dateDifference = getCouponExpirationDateDifference(couponEntity.getCreationDate());

        // sql date : date까지만 저장, timestamp : seconds까지 저장 
        // 쿠폰 유효기간은 7일이 지난 날의 자정까지임
        return dateDifference  >= 8; 
    }

    private static Long getCouponExpirationDateDifference(Date creationDate){
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        Date sqlDate = creationDate;

        return (currentTime.getTime() - sqlDate.getTime()) / (1000 * 60 * 60 * 24); 
    }

    @Override
    @Transactional(readOnly=true)
    public List<CouponDto> getCouponsExpiredToday(){
        return couponRepository.findAll().stream()
            .filter(c -> Math.floor(getCouponExpirationDateDifference(c.getCreationDate())) == 8 
                && c.getUserId() == userRepository.findUserIdByUsername(SecurityUtil.getCurrentUsername().get()).get()) // todo : repository에서 일단 자기꺼만 가져오기 ..
            .map(CouponDto::new)
            .toList();
    }

    @Override
    @Transactional(readOnly=true)
    public List<CouponDto> getCouponsLeft3days(){
        return couponRepository.findAll().stream()
            .filter(c -> Math.floor(getCouponExpirationDateDifference(c.getCreationDate())) == 5)
            .map(CouponDto::new)
            .toList();
    }

    @Override
    @Transactional
    public boolean useCoupon(String couponId){
        Optional<CouponEntity> optionalCoupon = couponRepository.findByCouponId(couponId);
        if(optionalCoupon.isEmpty()){
            return false;
        }
        CouponEntity coupon = optionalCoupon.get();

        if(coupon.getUserId() != userRepository.findUserIdByUsername(SecurityUtil.getCurrentUsername().get()).get()){ // 함수로 만들자 user id 얻는거
            return false;
        }
        if(isExpiredCoupon(coupon.getCouponId())){
            return false;
        }
        
        UsedCouponEntity usedCouponEntity = new UsedCouponEntity(coupon);

        couponRepository.deleteCoupon(coupon.getCouponId());
        usedCouponRepository.save(usedCouponEntity);

        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getUsedCouponIds(){
        List<UsedCouponEntity> usedCoupons = usedCouponRepository.getMyUsedCoupons(userRepository.findUserIdByUsername(SecurityUtil.getCurrentUsername().get()).get());

        return usedCoupons.stream()
            .map(uC -> uC.getCouponId())
            .toList();
    }

    @Transactional
    @Override
    public Boolean cancelCoupon(String couponId){
        Optional<UsedCouponEntity> optionalUsedCoupon = usedCouponRepository.getMyUsedCouponByCouponId(couponId);
        if(optionalUsedCoupon.isEmpty()){ // 쓰지 않은 쿠폰 (없는 쿠폰)
            return false;
        }

        CouponEntity coupon = new CouponEntity(optionalUsedCoupon.get());

        if(isExpiredCoupon(coupon)){ // 쿠폰 만료
            return false;
        }

        couponRepository.save(coupon);
        usedCouponRepository.deleteUsedCoupon(couponId);

        return true;
    }
}
