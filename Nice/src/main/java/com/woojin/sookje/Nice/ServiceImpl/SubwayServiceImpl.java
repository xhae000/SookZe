package com.woojin.sookje.Nice.ServiceImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;


import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.woojin.sookje.Nice.DTO.SubwayDTO;
import com.woojin.sookje.Nice.Entity.SubwayEntity;
import com.woojin.sookje.Nice.Repository.SubwayRepository;
import com.woojin.sookje.Nice.Service.SubwayService;

@Service
public class SubwayServiceImpl implements SubwayService{
    public SubwayRepository subwayRepository;

    public SubwayServiceImpl(SubwayRepository subwayRepository){
        this.subwayRepository = subwayRepository;
    }

    @Override
    public List<SubwayDTO> createSubwayDatabase() throws IOException {
        if(subwayRepository.countBy() > 0) 
            return subwayRepository.findAll().stream()
                    .map(SubwayDTO::new)
                    .collect(Collectors.toList()); // 모듈화 
        
        List<SubwayDTO> subways = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("static/subway_data.csv");
        java.nio.file.Path path = Paths.get(resource.getURI());

        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            lines.forEach(line ->{
                String[] dataArr = line.split(",");
                SubwayEntity subwayEntity = SubwayEntity.builder()
                    .line(Integer.parseInt(dataArr[1]))
                    .subwayNum(Integer.parseInt(dataArr[2]))
                    .subwayName(dataArr[3])
                    .date(dataArr[4])
                    .peopleCnt(Integer.parseInt(dataArr[5]))
                    .build();
                subways.add(new SubwayDTO(subwayRepository.save(subwayEntity)));
            });
        }

        return subways;
        
    }


}
