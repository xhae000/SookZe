package com.woojin.sookje.Kakaopay.Entity;

import java.sql.Date;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor(access  = AccessLevel.PUBLIC)
@Builder
public class CouponEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "coupon_id", updatable = false, nullable = false)
    @Builder.Default
    private String couponId = UUID.randomUUID().toString();

    @Column(name = "is_expired", updatable = true, nullable = false)
    @Builder.Default
    private boolean isExpired = false;

    @Column(name = "creation_date",updatable = false, nullable = false)
    @CreationTimestamp
    //@UpdateTimestamp
    private Date creationDate;


   

}