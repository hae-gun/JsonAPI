package com.example.controller;

import com.example.vo.BojVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.JdbcAccountRepository;
import com.example.service.JdbcService;
import com.example.vo.Account;
import com.example.vo.TestVo;

@RestController
@RequestMapping("/test")
public class Test {
	
	@GetMapping("/vo")
	public BojVo getMappingTest(){
		BojVo vo = new BojVo();
		vo.setId("123");
		vo.setName("name");
		vo.setLevel("level");
		return vo;
	}
}
