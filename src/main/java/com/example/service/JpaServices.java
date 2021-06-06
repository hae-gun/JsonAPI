package com.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.repository.TestRepository;
import com.example.vo.BojVo;
import com.example.vo.TestVo;

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
