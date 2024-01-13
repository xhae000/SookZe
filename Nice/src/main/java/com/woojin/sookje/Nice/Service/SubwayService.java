package com.woojin.sookje.Nice.Service;

import java.io.IOException;
import java.util.List;

import com.woojin.sookje.Nice.DTO.AveragePeopleDTO;
import com.woojin.sookje.Nice.DTO.PeopleGapDTO;
import com.woojin.sookje.Nice.DTO.SubwayDTO;

public interface SubwayService {
    public List<SubwayDTO> createSubwayDatabase() throws IOException;
    public List<AveragePeopleDTO> findMost10AvgSubway();
    public AveragePeopleDTO findLowestAverageSubway();
    public PeopleGapDTO findHighestGapSubway();
}
