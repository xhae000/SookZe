package com.woojin.sookje.Kakaopay.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    

}
