package com.woojin.sookje.Kakaopay.Dto;

import java.sql.Date;

import com.woojin.sookje.Kakaopay.Entity.CouponEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CouponDto {
    public CouponDto(CouponEntity entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.couponId = entity.getCouponId();
        this.creationDate = entity.getCreationDate();
    }
    private Long id;

    private Long userId;

    private String couponId;

    private Date creationDate;
}
