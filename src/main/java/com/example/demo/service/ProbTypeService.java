package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ProbTypeRepository;
import com.example.demo.vo.BojDto;
import com.example.demo.vo.BojProbType;
import com.example.demo.vo.BojVo;
import com.example.demo.vo.ProbTypeDto;
import com.example.demo.vo.ProbTypeDto2;
import com.example.demo.vo.ProbTypeVo;

@Service
public class ProbTypeService {

	private final ProbTypeRepository repository;
	Map<String, Integer> index = new HashMap<String, Integer>();
	String[] levels = {"bronze","silver","gold","platinum","diamond","ruby"};
	public ProbTypeService(ProbTypeRepository repository) {
		this.repository = repository;
		for(int idx=0; idx<levels.length; idx++) {
			index.put(levels[idx], idx);
		}
	}

	public List<ProbTypeDto2> getDtos() {
		List<ProbTypeVo> list = repository.findAll();
		return getList(list);
	}

	public List<ProbTypeDto2> getDtos(Long id) {
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		return getList(list);
	}

	public List<ProbTypeDto> getDtos(Long id, String level) {
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		return getList(list,level);
	}
	
	public List<ProbTypeDto2> getDtosPerLevel(Long id){
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		return getList2(list);
	}

	
	public List<ProbTypeDto2> getList(List<ProbTypeVo> list){
		List<ProbTypeDto2> dtoList = new ArrayList<ProbTypeDto2>();
		list.stream().forEach(vo -> dtoList.add(vo.getPTD2()));
		return dtoList;
	}
	public List<ProbTypeDto2> getList2(List<ProbTypeVo> list){
		List<ProbTypeDto2> dtoList = new ArrayList<ProbTypeDto2>();
		list.stream().forEach(vo-> dtoList.add(vo.getPTD2()));
		return dtoList;
	}

	public Map<String,List<ProbTypeDto2>> testMethod(List<ProbTypeVo> list){


		return null;
	}
	
	public List<ProbTypeDto> getList(List<ProbTypeVo> list,String level){
		List<ProbTypeDto> dtoList = new ArrayList<ProbTypeDto>();
		for (ProbTypeVo vo : list) {
			ProbTypeDto dto = new ProbTypeDto(vo);
			List<BojVo> bojList = new ArrayList<BojVo>();
			for (BojProbType bptVo : vo.getBojProbTypes()) {
				BojDto bojDto = new BojDto(bptVo.getBojVo());
				if(bojDto.getLevel() != null && bojDto.getLevel().contains(level)) {
					bojList.add(bojDto.convertToBojVo());
				}
			}
			dto.setProbs(bojList);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	
	
	

}
