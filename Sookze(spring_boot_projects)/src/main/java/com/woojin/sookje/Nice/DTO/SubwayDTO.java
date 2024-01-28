package com.woojin.sookje.Nice.DTO;

import com.woojin.sookje.Nice.Entity.SubwayEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubwayDTO {
    private Long id;
    private int line;   
    private int subwayNum;
    private String subwayName;
    private int peopleCnt;
    private String date;

    public SubwayDTO(SubwayEntity entity){
        this.id = entity.getId();
        this.line = entity.getLine();
        this.subwayNum = entity.getSubwayNum();
        this.subwayName = entity.getSubwayName();
        this.peopleCnt = entity.getPeopleCnt();
        this.date = entity.getDate();
    }
}
