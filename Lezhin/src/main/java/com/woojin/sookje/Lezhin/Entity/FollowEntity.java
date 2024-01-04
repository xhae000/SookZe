package com.woojin.sookje.Lezhin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@ToString
@AllArgsConstructor
@Builder
public class FollowEntity{
    public FollowEntity(Long followerId, Long targetId){
        this.followerId = followerId;
        this.targetId = targetId;    
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "follower_id", updatable = false)
    private Long followerId;
    @Column(name = "target_id", updatable = false)
    private Long targetId;
}