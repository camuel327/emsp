package com.example.emsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.emsp.mapper"})
public class EmspApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmspApplication.class, args);
	}

}
