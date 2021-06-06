package com.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.repository.BojRepository;
import com.example.vo.BojVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BojServiceTest{
	
	@Autowired
	private BojRepository repository;
	
	@Test
	public void 가져오기() {
		System.out.println(repository);
	}
}
