package com.woojin.sookje.Kakaopay.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    public Optional<CouponEntity> findTopByUserIdIsNull();

    @Modifying
    @Query(value = "UPDATE coupon c SET c.user_Id = :userId WHERE c.id = :couponId", nativeQuery = true)
    public void setUserId(@Param("couponId")Long couponId, @Param("userId") Long userId);

    public List<Optional<CouponEntity>> findByUserId(Long user_id);

    public Optional<CouponEntity> findByCouponId(String couponId);

    @Modifying
    @Query(value = "DELETE FROM coupon WHERE coupon_id = :couponId", nativeQuery = true)
    public void deleteCoupon(@Param("couponId") String couponId);
}
