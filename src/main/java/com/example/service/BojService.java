package com.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.repository.BojRepository;
import com.example.vo.BojVo;

@Service
public class BojService {

	JSONParser parser = new JSONParser();

	private final JpaRepository repository;

	public BojService(BojRepository repository) {
		this.repository = repository;
	}

	public JSONObject readJsonFile(String tier) throws FileNotFoundException, IOException, ParseException {
		System.out.println("in ReadJsonFile");
		String selectTier = tier.toLowerCase();
		ClassPathResource resource = new ClassPathResource("/python/tier_" + selectTier + ".json");
		System.out.println(resource.getURI());
		// Path path = Paths.get(resource.getURI());

		String source = "/home/chlgprms/crawling/JsonAPI/build/resources/main/python/tier_" + selectTier + ".json";
		File file = new File(source);
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
	public List<BojVo> searchByTier(String tier) {
		String search = "%" + tier.toLowerCase() + "%";
		return ((BojRepository) repository).findByLevelLike(search);
	}

	public List<BojVo> searchAll() {
		return repository.findAll();
	}

	private void makeResultMsg(JSONObject result) {
		result.put("code", "0000");
		result.put("action", "saveTierData");
		result.put("msg", "complete");
	}

	public List<BojVo> searchByName(String name) {
		String search = "%" + name.toLowerCase() + "%";
		return ((BojRepository) repository).findByNameLike(search);
	}

	public List<BojVo> randomProb(String tier, int i) {
		String level = i != 0 ? tier + i : tier;
		List allProb = searchByTier(level);

		Set<Integer> randomGet2 = new HashSet<>();

		while (randomGet2.size() < 2) {
			int index = (int) (Math.random() * allProb.size());
			randomGet2.add(index);
		}
		List result2 = new ArrayList();
		for (Integer index : randomGet2) {
			result2.add(allProb.get(index));
		}
		return result2;
	}

	public BojVo makeVo(JSONObject obj) {

		return new BojVo(obj.get("id").toString(), obj.get("level").toString(), obj.get("name").toString(),
				obj.get("url").toString());
	}
}
