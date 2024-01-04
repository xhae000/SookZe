package com.woojin.sookje.Nice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.woojin.sookje.Nice.Entity.SubwayEntity;

@Repository
public interface SubwayRepository extends JpaRepository<SubwayEntity, Long>{
    public SubwayEntity save(SubwayEntity subwayEntity);
    public Long countBy();
}
