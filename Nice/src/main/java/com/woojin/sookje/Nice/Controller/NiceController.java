package com.woojin.sookje.Nice.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Nice.Service.SubwayService;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class NiceController {  
    private SubwayService subwayService;

    public NiceController(SubwayService subwayService){
        this.subwayService = subwayService;
    }

    /**
     * 데이터파일의 각 레코드를 DB에 저장하는 API
     * @throws IOException
     */
    @GetMapping("/nice/createDB")
    public String createDatabase() throws IOException{
        return subwayService.createSubwayDatabase().toString();
    }

    @GetMapping("/nice/getMost10Average")
    public String getMethodName() {
        return subwayService.findMost10AvgSubway().toString();
    }
    


}
