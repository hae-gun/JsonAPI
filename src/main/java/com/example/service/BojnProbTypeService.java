package com.example.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.example.repository.BojProbTypeRepository;
import com.example.repository.BojRepository;
import com.example.repository.ProbTypeRepository;
import com.example.util.DtoUtil;
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
	
	private JSONParser parser = new JSONParser();
	
	public BojnProbTypeService(BojRepository bojRepo, ProbTypeRepository typeRepo, BojProbTypeRepository middleRepo) {
		this.bojRepo = bojRepo;
		this.typeRepo = typeRepo;
		this.middleRepo = middleRepo;
	}
	@Transactional
	public BojDto save(String bojVo, Long probTypeVo) {
		
		System.out.println("MATCHING BOJVO:"+bojVo+" PROBTYPEVO:"+probTypeVo);
		BojVo boj = bojRepo.findById(bojVo).isPresent() ? bojRepo.findById(bojVo).get() : new BojVo();
		System.out.println(boj);
		ProbTypeVo prob = typeRepo.findByTypeNo(probTypeVo) == null? null : typeRepo.findByTypeNo(probTypeVo).get(0);
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
	
	public List<ProbTypeDto> getProbsByType(String type){
		List<ProbTypeVo> result = typeRepo.findByTypeLike(type);
		List<ProbTypeDto> output = new ArrayList<ProbTypeDto>();
		for(ProbTypeVo vo : result) {
			Set<BojVo> allProb = new HashSet<>();
			for(BojProbType vo2 :vo.getBojProbTypes()) {
				BojVo tmp = vo2.getBojVo();
				BojVo saveData = new BojVo(tmp);
				allProb.add(saveData);
			}
			
			ProbTypeDto dto = new ProbTypeDto(vo.getId(), vo.getTypeNo(), vo.getType());
			dto.setProbs(new ArrayList<BojVo>(allProb));
			output.add(dto);
		}
		return output;
	}

	public JSONArray saveProbType() throws FileNotFoundException, IOException, ParseException {
//		String source = "/home/chlgprms/crawling/JsonAPI/build/resources/main/python/tier_" + selectTier + ".json";
		JSONObject object = DtoUtil.readJsonFile("/python/rearrange2.json");
//		JSONObject object = DtoUtil.readJsonFile("/home/chlgprms/JsonAPI/src/main/resources/python/rearrange2.json");
		JSONObject tmp = (JSONObject) parser.parse(object.get("data").toString());
		JSONArray arr = (JSONArray)parser.parse(tmp.get("0").toString());

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
//		int no=11;
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
//					System.out.println();
//					System.out.println("typeNum::"+Long.valueOf(data.get("typeNum").toString()));
					save(String.valueOf(arr.get(jIdx)), Long.valueOf(String.valueOf(data.get("typeNum").toString())));
				}
				data.put("count", arr.size());
				result.add(data);
				
			}
		}
		return new JSONArray();
	}
	
	
	public List<ProbTypeDto> getAll() {
		List<ProbTypeVo> list = typeRepo.findAll();
		List<ProbTypeDto> result = new ArrayList<ProbTypeDto>();
		for(ProbTypeVo vo: list) {
			result.add(new ProbTypeDto(vo));
		}
		return result;
	}
	public List<ProbTypeDto> getProbsByTypeNo(Long type) {
		List<ProbTypeVo> list = typeRepo.findByTypeNo(type);
		List<ProbTypeDto> result = new ArrayList<ProbTypeDto>();
		for(ProbTypeVo vo : list) {
			result.add(new ProbTypeDto(vo));
		}
		return result;
	}
	public List<ProbTypeDto> test() {
		Long[] set = {25L, 11L};
		List<ProbTypeVo> list= typeRepo.findByTypeNoIn(set);
		List<ProbTypeDto> result = new ArrayList<ProbTypeDto>();
		for(ProbTypeVo vo : list) {
			result.add(new ProbTypeDto(vo));
		}
		return result;
	}
	
	
}
