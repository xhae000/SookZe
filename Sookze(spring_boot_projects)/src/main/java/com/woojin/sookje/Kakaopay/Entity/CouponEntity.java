package com.woojin.sookje.Kakaopay.Entity;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access  = AccessLevel.PUBLIC) 
@Builder
@Getter
@Table(name = "coupon")
public class CouponEntity{

    /**
     *  쿠폰 취소 시 [사용한 쿠폰] -> [쿠폰]으로 변환하기 위한 생성자
     */
    public CouponEntity(UsedCouponEntity usedCouponEntity){
        this.id = usedCouponEntity.getId();
        this.userId = usedCouponEntity.getUserId();
        this.couponId = usedCouponEntity.getCouponId();
        this.creationDate = usedCouponEntity.getCreationDate();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_id", updatable = true, nullable = true)
    @Builder.Default
    private Long userId = null;

    /**
     *  쿠폰 번호
     */
    @Column(name = "coupon_id", updatable = false, nullable = false)
    @Builder.Default
    private String couponId = UUID.randomUUID().toString();

    @Column(name = "creation_date",updatable = false, nullable = false)
    @CreationTimestamp
    //@UpdateTimestamp
    private Date creationDate;
    
   

}