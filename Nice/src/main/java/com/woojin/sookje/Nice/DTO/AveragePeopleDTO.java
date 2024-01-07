package com.woojin.sookje.Nice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AveragePeopleDTO {
    String subwayName;
    int avgPeopleCnt;
}
