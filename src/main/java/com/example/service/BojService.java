package com.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.repository.BojRepository;
import com.example.repository.ProbTypeRepository;
import com.example.vo.BojDto;
import com.example.vo.BojProbType;
import com.example.vo.BojVo;
import com.example.vo.ProbTypeVo;

@Service
public class BojService {

	JSONParser parser = new JSONParser();

	private final JpaRepository repository;
	private final JpaRepository otherRepo;
	public BojService(BojRepository repository, ProbTypeRepository otherRepo) {
		this.repository = repository;
		this.otherRepo = otherRepo;
	}

	public JSONObject readJsonFile(String tier) throws FileNotFoundException, IOException, ParseException {
		System.out.println("in ReadJsonFile");
		String selectTier = tier.toLowerCase();
		ClassPathResource resource = new ClassPathResource("/python/tier_" + selectTier + ".json");
		System.out.println(resource.getURI());
		 Path path = Paths.get(resource.getURI());

//		String source = "/home/chlgprms/JsonAPI/src/main/resources/python/tier_" + selectTier + ".json";
//		System.out.println(source);
		File file = new File(path.toUri());
		return (JSONObject) parser.parse(new FileReader(file));
	}

	public JSONObject getJsonData(String tier) throws FileNotFoundException, IOException, ParseException {

		JSONObject jobj = readJsonFile(tier);
		JSONObject prob = (JSONObject) jobj.get("list");
		return prob;
	}
	
	public JSONObject saveTierData(String tier) throws FileNotFoundException, IOException, ParseException {
		JSONObject data = getJsonData(tier);
		String[] levels = { "0", "1", "2", "3", "4" };
		JSONObject result = new JSONObject();
		for (String lv : levels) {
			JSONArray jarr = (JSONArray) data.get(lv);
			for (int i = 0; i < jarr.size(); i++) {
				JSONObject obj = (JSONObject) jarr.get(i);
				obj.put("level", tier + (5 - Integer.valueOf(lv)));
				BojVo vo = makeVo(obj);
				repository.save(vo);
			}
		}
		makeResultMsg(result);
		return result;
	}

//	@Caching
	public List<BojDto> searchByTier(String tier) {
		String search = "%" + tier.toLowerCase() + "%";
		List<BojVo> tmp = ((BojRepository) repository).findByLevelLike(search);
		
		List<BojDto> result = new ArrayList<BojDto>();
		
		for(BojVo vo:tmp) {
			if(vo.getBojProbType().size() > 0) {
				BojDto dto = new BojDto(vo);
				List<String> tmp2 = new ArrayList<String>();
				for(BojProbType bpt : vo.getBojProbType()) {
					String type = bpt.getProbTypeVo().getType();
					dto.getTypes().add(type);
				}
				result.add(dto);
			}else {
				result.add(new BojDto(vo));
			}
			
		}
		return result;
	}

	public List<BojVo> searchAll() {
		return repository.findAll();
	}

	private void makeResultMsg(JSONObject result) {
		result.put("code", "0000");
		result.put("action", "saveTierData");
		result.put("msg", "complete");
	}

	public List<BojDto> searchByName(String name) {
		String search = "%" + name.toLowerCase() + "%";
		
		List<BojVo> tmp = ((BojRepository) repository).findByNameLike(search);
		List<BojDto> result = new ArrayList<BojDto>();
		for(BojVo vo: tmp) {
			
			BojDto dto = new BojDto(vo);
			for(BojProbType bpt : vo.getBojProbType()) {
				dto.getTypes().add(bpt.getProbTypeVo().getType());
			}
			
			result.add(dto);
		}
		return result;
	}

	public List<BojDto> randomProb(String tier, int i) {
		String level = i != 0 ? tier + i : tier;
		List<BojDto> allProb = searchByTier(level);

		Set<Integer> randomGet2 = new HashSet<>();

		while (randomGet2.size() < 2) {
			int index = (int) (Math.random() * allProb.size());
			randomGet2.add(index);
		}
		List<BojDto> result2 = new ArrayList<BojDto>();
		for (Integer index : randomGet2) {
			result2.add(allProb.get(index));
		}
		return result2;
	}

	public BojVo makeVo(JSONObject obj) {

		return new BojVo(obj.get("id").toString(), obj.get("level").toString(), obj.get("name").toString(),
				obj.get("url").toString());
	}
	@Transactional
	public List<BojVo> test() {
		
		return  null;
	}
}
