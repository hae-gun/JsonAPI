package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.TestRepository;
import com.example.demo.vo.TestVo;

@Service
public class JpaServices {
	
	private final TestRepository testRepository;
	public JpaServices(TestRepository testRepository) {
		this.testRepository = testRepository;
	}
	public<T> List<T> findAll() {
		return (List<T>) testRepository.findAll();
	}
	public TestVo insertOne(TestVo vo) {
		return testRepository.save(vo);
	}
	
	
}
