package com.woojin.sookje.Kakaopay.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long>{
    

}
