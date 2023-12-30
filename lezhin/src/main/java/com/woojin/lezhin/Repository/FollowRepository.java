package com.woojin.lezhin.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woojin.lezhin.Entity.FollowEntity;

@Repository
public interface FollowRepository extends JpaRepository<FollowEntity, Long>{
    FollowEntity save(FollowEntity follow);
    Optional<FollowEntity> findById(int id);
    Optional<FollowEntity> findByFollowerId(int id);

}
