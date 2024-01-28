package com.woojin.sookje.Nice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
public class SubwayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "line", nullable = false, updatable = true)
    private int line;   

    @Column(name = "subway_num", nullable = false, updatable = true)
    private int subwayNum;

    @Column(name = "subway_name", nullable = false, updatable = true)
    private String subwayName;

    @Column(name = "people_cnt", nullable = false, updatable = false)
    private int peopleCnt;

    @Column(name = "date", nullable = false, updatable = false)
    private String date;
    
    @Builder(builderClassName = "builder")
    public SubwayEntity(int line, int subwayNum, String subwayName, int peopleCnt, String date){
        this.line = line;
        this.subwayName = subwayName;
        this.subwayNum = subwayNum;
        this.peopleCnt = peopleCnt;
        this.date = date;
    }
}
