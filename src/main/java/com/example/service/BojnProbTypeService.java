package com.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.repository.BojProbTypeRepository;
import com.example.repository.BojRepository;
import com.example.repository.ProbTypeRepository;
import com.example.vo.BojDto;
import com.example.vo.BojProbType;
import com.example.vo.BojVo;
import com.example.vo.ProbTypeDto;
import com.example.vo.ProbTypeVo;

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
	@Transactional
	public BojDto save(String bojVo, Long probTypeVo) {
		
		BojVo boj = bojRepo.findById(bojVo).get();
		ProbTypeVo prob = typeRepo.findById(probTypeVo).get();
		
		BojProbType middle = new BojProbType(boj, prob);
		
		boj.getBojProbType().add(middle);
		prob.getBojProbTypes().add(middle);
		
		middleRepo.save(middle);
		
		return new BojDto(boj,middle.getProbTypeVo().getType());
	}
	
	public List<ProbTypeDto> getProbsByType(String type){
		List<ProbTypeVo> result = typeRepo.findByTypeLike(type);
		List<ProbTypeDto> output = new ArrayList<ProbTypeDto>();
		for(ProbTypeVo vo : result) {
			Set<BojVo> allProb = new HashSet<>();
			for(BojProbType vo2 :vo.getBojProbTypes()) {
				BojVo tmp = vo2.getBojVo();
				BojVo saveData = new BojVo(tmp.getId(), tmp.getLevel(), tmp.getName(), tmp.getUrl());
				allProb.add(saveData);
			}
			
			ProbTypeDto dto = new ProbTypeDto(vo.getId(), vo.getTypeNo(), vo.getType());
			dto.setProbs(new ArrayList<BojVo>(allProb));
			output.add(dto);
		}
		return output;
	}


	public List<BojDto> getAll() {
		return null;
	}
	

}
