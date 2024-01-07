package com.woojin.sookje.Nice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.woojin.sookje.Nice.Entity.AveragePeopleInterface;
import com.woojin.sookje.Nice.Entity.SubwayEntity;

@Repository
public interface SubwayRepository extends JpaRepository<SubwayEntity, Long>{
    public SubwayEntity save(SubwayEntity subwayEntity);
    public Long countBy();

    @Query(nativeQuery = true, 
        value = "SELECT subway_name , SUM(people_cnt) AS avg_people_cnt"
             + " FROM subway_entity"
             + " GROUP BY subway_name"
             + " ORDER BY SUM(people_cnt)"
             + " DESC LIMIT 0, 10" )
    public Optional<List<AveragePeopleInterface>> findMost10Subways();
}
