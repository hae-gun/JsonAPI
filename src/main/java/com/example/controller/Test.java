package com.example.controller;

import com.example.vo.BojVo;
import com.example.vo.lombok.LombokVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.vo.Account;
import com.example.vo.TestVo;

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
