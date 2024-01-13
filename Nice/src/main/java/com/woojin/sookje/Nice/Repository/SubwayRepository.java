package com.woojin.sookje.Nice.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.woojin.sookje.Nice.Entity.AveragePeopleInterface;
import com.woojin.sookje.Nice.Entity.PeopleGapInterface;
import com.woojin.sookje.Nice.Entity.SubwayEntity;

// @Repository
public interface SubwayRepository extends JpaRepository<SubwayEntity, Long>{
    public Long countBy();

    @Query(nativeQuery = true, 
        value = "SELECT subway_name , SUM(people_cnt) AS avg_people_cnt"
             + " FROM subway_entity"
             + " GROUP BY subway_name"
             + " ORDER BY avg_people_cnt"
             + " DESC LIMIT 0, 10" )
    public Optional<List<AveragePeopleInterface>> findMost10Subways();

    @Query(nativeQuery = true, 
        value = "SELECT subway_name , AVG(people_cnt) AS avg_people_cnt"
             + " FROM subway_entity"
             + " GROUP BY subway_name"
             + " ORDER BY avg_people_cnt"
             + " LIMIT 1" )
    public Optional<AveragePeopleInterface> getLowestAvgSubway();

    @Query(nativeQuery = true, 
        value = "SELECT subway_name , (MAX(people_cnt)-MIN(people_cnt)) AS people_gap"
             + " FROM subway_entity"
             + " GROUP BY subway_name"
             + " ORDER BY people_gap"
             + " DESC LIMIT 1" )
    public Optional<PeopleGapInterface> getHighestGapSubway();

    
}
