package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BojService;
import com.example.demo.vo.BojDto;
import com.example.demo.vo.BojVo;

@RestController
@RequestMapping("/boj")
public class JsonController {
	

	private final BojService bojService;
	public JsonController(BojService bojService) {
		System.out.println("make JsonController");
		this.bojService = bojService;
	}

	@GetMapping("/test")
	public List<BojVo> test(){
		return bojService.test();
	}

	@PostMapping("/update")
	public JSONObject updateProbs() throws IOException, ParseException {
		return bojService.resetData();
	}

	@GetMapping("/probs/{tier}")
	public JSONObject testBoj(@PathVariable String tier) throws FileNotFoundException, IOException, ParseException {
		return bojService.getJsonData(tier);
	}
	@PostMapping("/probArrs/{tier}")
	public JSONObject saveJsonData(@PathVariable String tier) throws FileNotFoundException, IOException, ParseException {
		return bojService.saveTierData(tier);
	}
	@GetMapping("/probArrs/{tier}")
	public List<BojDto> searchTierData(@PathVariable String tier) throws FileNotFoundException, IOException, ParseException {
		return bojService.searchByTier(tier);
	}
	@GetMapping("/probArrs")
	public List<BojVo> searchAllData() throws FileNotFoundException, IOException, ParseException {
		return bojService.searchAll();
	}
	@GetMapping("/prob/{name}")
	public List<BojDto> searchByName(@PathVariable String name){
		return bojService.searchByName(name);
	}

	@GetMapping("/random/{tier}")
	public List<BojDto> getRandomProb(@PathVariable String tier){
		return bojService.randomProb(tier,0);
	}

	@GetMapping("/random/{tier}/{level}")
	public List<BojDto> getRandomProb(@PathVariable String tier,@PathVariable int level){
		return bojService.randomProb(tier,level);
	}
	
	
	
	
}
