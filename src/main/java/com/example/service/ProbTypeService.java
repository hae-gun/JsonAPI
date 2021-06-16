package com.example.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.repository.ProbTypeRepository;
import com.example.vo.BojDto;
import com.example.vo.BojProbType;
import com.example.vo.BojVo;
import com.example.vo.ProbTypeDto;
import com.example.vo.ProbTypeVo;

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

	public List<ProbTypeDto> getDtos() {
		List<ProbTypeVo> list = repository.findAll();
		return getList(list);
	}

	public List<ProbTypeDto> getDtos(Long id) {
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		return getList(list);
	}

	public List<ProbTypeDto> getDtos(Long id, String level) {
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		return getList(list,level);
	}
	
	public List<ProbTypeDto> getDtosPerLevel(Long id){
		List<ProbTypeVo> list = repository.findByTypeNo(id);
		List<ProbTypeDto> dtos = getList(list);
		
		for(ProbTypeDto dto : dtos) {
			Map<String,List<BojVo>> probByLevel = new HashMap<String, List<BojVo>>();
			for(BojVo bojVo : dto.getProbs()) {
				String level = bojVo.getLevel().substring(0,bojVo.getLevel().length()-1);
				if(probByLevel.get(level) == null) {
					List<BojVo> bojList = new ArrayList<>();
					bojList.add(bojVo);
					probByLevel.put(level, bojList);
				}else {
					probByLevel.get(level).add(bojVo) ;
				}
			}
			
			List<BojVo> tempList = new ArrayList<>();
			BojVo[] voArr = new BojVo[6];
			for(String key : probByLevel.keySet()) {
				int size = probByLevel.get(key).size();
				int idx =(int) (Math.random()*size);
				BojVo saveVo = probByLevel.get(key).get(idx);
				String level = saveVo.getLevel().substring(0,saveVo.getLevel().length()-1);
				voArr[index.get(level)] = saveVo;
			}
			
			for(BojVo vo : voArr) {
				if(vo != null) tempList.add(vo);
			}
			
			System.out.println(tempList);
			dto.setProbs(tempList);
//			dto.setProbs(Arrays.asList(voArr));
		}
		return dtos;
	}

	
	public List<ProbTypeDto> getList(List<ProbTypeVo> list){
		List<ProbTypeDto> dtoList = new ArrayList<ProbTypeDto>();
		for (ProbTypeVo vo : list) {
			System.out.println(vo);
			ProbTypeDto dto = new ProbTypeDto(vo);
			List<BojVo> bojList = new ArrayList<BojVo>();
			for (BojProbType bptVo : vo.getBojProbTypes()) {
				BojDto bojDto = new BojDto(bptVo.getBojVo());
				bojList.add(bojDto.convertToBojVo());
			}
			dto.setProbs(bojList);
			dtoList.add(dto);
		}
		return dtoList;
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
