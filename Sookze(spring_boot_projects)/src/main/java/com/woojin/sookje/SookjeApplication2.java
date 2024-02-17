package com.woojin.sookje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication  
@EnableScheduling
public class SookjeApplication2 {
	public static void main(String[] args) {
		SpringApplication.run(SookjeApplication2.class, args);
	}
}	
