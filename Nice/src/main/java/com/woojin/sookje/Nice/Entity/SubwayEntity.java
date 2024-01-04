package com.woojin.sookje.Nice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class SubwayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id", nullable = false, updatable = false)
    Long id;

    @Column(name = "line", nullable = false, updatable = true)
    int line;   

    @Column(name = "subwayNum", nullable = false, updatable = true)
    int subwayNum;

    @Column(name = "subwayName", nullable = false, updatable = true)
    String subwayName;

    @Column(name = "peopleCnt", nullable = false, updatable = false)
    int peopleCnt;

    @Column(name = "date", nullable = false, updatable = false)
    String date;

    
    @Builder(builderClassName = "builder")
    public SubwayEntity(int line, int subwayNum, String subwayName, int peopleCnt, String date){
        this.line = line;
        this.subwayName = subwayName;
        this.subwayNum = subwayNum;
        this.peopleCnt = peopleCnt;
        this.date = date;
    }
}
