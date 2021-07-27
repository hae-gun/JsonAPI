package com.example.demo.controller;

import com.example.demo.vo.lombok.LombokVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {
	
	@GetMapping("/vo")
	public LombokVo getMappingTest(){
		LombokVo vo = LombokVo.builder()
				.id(1L).name("test").build();

		return vo;
	}
}
