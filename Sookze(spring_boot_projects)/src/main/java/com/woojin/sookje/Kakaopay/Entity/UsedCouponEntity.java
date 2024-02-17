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
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access  = AccessLevel.PUBLIC)
@Builder
@Getter
@Table(name = "used_coupon")
public class UsedCouponEntity{
    public UsedCouponEntity(CouponEntity unusedCoupon){
        this.id = unusedCoupon.getId();
        this.creationDate = unusedCoupon.getCreationDate();
        this.userId = unusedCoupon.getUserId();
        this.couponId = unusedCoupon.getCouponId();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "user_id", updatable = true, nullable = false)
    private Long userId;

    /**
     *  쿠폰 번호
     */
    @Column(name = "coupon_id", updatable = false, nullable = false)
    private String couponId;

    @Column(name = "creation_date",updatable = false, nullable = false)
    @CreationTimestamp
    //@UpdateTimestamp
    private Date creationDate;
    
   

}