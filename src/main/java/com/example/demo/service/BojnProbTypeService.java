package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.BojProbTypeRepository;
import com.example.demo.repository.BojRepository;
import com.example.demo.repository.ProbTypeRepository;
import com.example.demo.util.DtoUtil;
import com.example.demo.vo.BojDto;
import com.example.demo.vo.BojProbType;
import com.example.demo.vo.BojVo;
import com.example.demo.vo.ProbTypeDto2;
import com.example.demo.vo.ProbTypeVo;

@Service
public class BojnProbTypeService {

	private final BojRepository bojRepo;
	private final ProbTypeRepository typeRepo;
	private final BojProbTypeRepository middleRepo;
	
	private JSONParser parser = new JSONParser();
	
	public BojnProbTypeService(BojRepository bojRepo, ProbTypeRepository typeRepo, BojProbTypeRepository middleRepo) {
		this.bojRepo = bojRepo;
		this.typeRepo = typeRepo;
		this.middleRepo = middleRepo;
	}
	@Transactional
	public BojDto save(String bojVo, Long probTypeVoNo) {
		
		System.out.println("MATCHING BOJVO:"+bojVo+" PROBTYPEVO:"+probTypeVoNo);
		BojVo boj = bojRepo.findById(bojVo).isPresent() ? bojRepo.findById(bojVo).get() : new BojVo();
		System.out.println(boj);
		ProbTypeVo prob = typeRepo.findByTypeNo(probTypeVoNo) != null ? typeRepo.findByTypeNo(probTypeVoNo).get(0) : null;
		System.out.println(boj);
		if( boj.getId() != null  && prob != null) {
			BojProbType middle = new BojProbType(boj, prob);
			boj.getBojProbType().add(middle);
			prob.getBojProbTypes().add(middle);
			System.out.println("MIDDLE::"+middle);
			System.out.println("BOJ::"+boj);
			System.out.println("PROB::"+prob);
			middleRepo.save(middle);
			return new BojDto(boj);
		}else {
			return new BojDto();
		}
		
	}
	
	public List<ProbTypeDto2> getProbsByType(String type){
		return typeRepo.findByTypeLike(type)
				.stream()
				.map(vo->vo.parseDto2(vo.getBojDto()))
				.collect(Collectors.toList());
	}

	public JSONArray saveProbType() throws FileNotFoundException, IOException, ParseException {

		JSONArray arr = DtoUtil.getJsonArray("/python/rearrange2.json");
		for(int idx=0; idx < arr.size(); idx++) {
			JSONObject obj = (JSONObject)arr.get(idx);
			ProbTypeVo vo = new ProbTypeVo(obj);
			typeRepo.save(vo);
		}
		
		return arr;
	}
	
	public JSONArray matchBojAndProb() throws FileNotFoundException, IOException, ParseException {
		middleRepo.deleteAll();
		JSONArray result = new JSONArray();
		for(int no=0; no<34; no++) {
			JSONObject json = DtoUtil.readJsonFile("/python/type_prob_"+no+".json");
//			JSONObject json = DtoUtil.readJsonFile("/home/chlgprms/JsonAPI/src/main/resources/python/type_prob_"+no+".json");
			String[] idxStr = {"0","1","2","3","4"};
			for(int idx=0; idx<5; idx++) {
				JSONObject data = (JSONObject)json.get(idxStr[idx]);
				data = (JSONObject)data.get("0");
				System.out.println(data);
				JSONArray arr = (JSONArray) parser.parse(data.get("list").toString());
				System.out.println(arr);

				for(int jIdx=0; jIdx < arr.size(); jIdx++) {
					save(String.valueOf(arr.get(jIdx)), Long.valueOf(String.valueOf(data.get("typeNum").toString())));
				}
				data.put("count", arr.size());
				result.add(data);
			}
		}
		return new JSONArray();
	}
	
	public List<ProbTypeDto2> getAll() {
		return typeRepo.findAll()
				.stream()
				.map(ProbTypeVo::parseDto2)
				.collect(Collectors.toList());
	}
	public List<ProbTypeDto2> getProbsByTypeNo(Long type) {
		return typeRepo.findByTypeNo(type)
				.stream()
				.map(vo -> vo.parseDto2(vo.getBojDto()))
				.collect(Collectors.toList());
	}
	public List<ProbTypeDto2> test(Long... set) {
		return typeRepo.findByTypeNoIn(set).stream()
				.map(ProbTypeVo::parseDto2)
				.collect(Collectors.toList());
	}
	
	
}
