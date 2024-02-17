package com.woojin.sookje.Kakaopay.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woojin.sookje.Kakaopay.Entity.UsedCouponEntity;

public interface UsedCouponRepository extends JpaRepository<UsedCouponEntity, Long> {
    @Query(value = "SELECT * FROM used_coupon WHERE user_id = :userId", nativeQuery = true)
    public List<UsedCouponEntity> getMyUsedCoupons(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM used_coupon WHERE coupon_id = :couponId", nativeQuery = true)
    public Optional<UsedCouponEntity> getMyUsedCouponByCouponId(@Param("couponId") String couponId);

    @Modifying
    @Query(value = "DELETE FROM used_coupon WHERE coupon_id = :couponId", nativeQuery = true)
    public void deleteUsedCoupon(@Param("couponId") String couponId);
}
