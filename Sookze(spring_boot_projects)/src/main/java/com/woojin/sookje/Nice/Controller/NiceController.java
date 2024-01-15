package com.woojin.sookje.Nice.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Nice.Service.SubwayService;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class NiceController {  
    private final SubwayService subwayService;

    public NiceController(SubwayService subwayService){
        this.subwayService = subwayService;
    }

    /**
     * 데이터파일의 각 레코드를 DB에 저장하는 API
     */
    @GetMapping(value = {"/", "/nice/createDB"})
    public String createDatabase() throws IOException{
        return subwayService.createSubwayDatabase().toString();
    }

    /**
     * 평균 승객이 가장 많은 상위 10개역과 그 값을 반환하는 API
     */
    @GetMapping("/nice/getMost10Average")
    public String getMost10Average() {
        return subwayService.findMost10AvgSubway().toString();
    }

    /**
     * 평균 승객이 가장 적은 역과 그 값을 반환하는 API
     */
    @GetMapping("/nice/getLowestAverage")
    public String getLowestAverage(){
        return subwayService.findLowestAverageSubway().toString();
    }

    /**
     * 승객의 가장 많을 때와 가장 적을 때의  차가 가장 큰 역과 그 값을 반환하는 API
     */
    @GetMapping("/nice/getHighestGap")
    public String getHighestGap() {
        return subwayService.findHighestGapSubway().toString();
    }
    
    


}
