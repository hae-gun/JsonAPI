package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.repository.BojProbTypeRepository;
import com.example.repository.BojRepository;
import com.example.repository.ProbTypeRepository;
import com.example.vo.BojDto;
import com.example.vo.BojVo;

@Service
public class BojnProbTypeService {

	private final BojRepository bojRepo;
	private final ProbTypeRepository typeRepo;
	private final BojProbTypeRepository middleRepo;
	
	public BojnProbTypeService(BojRepository bojRepo, ProbTypeRepository typeRepo, BojProbTypeRepository middleRepo) {
		this.bojRepo = bojRepo;
		this.typeRepo = typeRepo;
		this.middleRepo = middleRepo;
	}



	public List<BojDto> getAll() {
		return null;
	}
	

}
