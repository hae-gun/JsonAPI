package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
@EntityScan(basePackages = "com.example.demo.vo")
public class JsonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonApiApplication.class, args);
	}

}
