package com.woojin.sookje.Nice.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.woojin.sookje.Nice.Service.SubwayService;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class NiceController {  
    private SubwayService subwayService;

    public NiceController(SubwayService subwayService){
        this.subwayService = subwayService;
    }

    @GetMapping("/nice/createDB")
    public String createDatabase(){
        try {
            return subwayService.createSubwayDatabase().toString();
        } catch (IOException e) {
            return "error ...";
        }
    }
}
