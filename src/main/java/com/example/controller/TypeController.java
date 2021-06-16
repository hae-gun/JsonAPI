package com.example.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.BojnProbTypeService;
import com.example.vo.BojDto;
import com.example.vo.InputDto;
import com.example.vo.ProbTypeDto;

@RestController
@RequestMapping("/type")
public class TypeController {

	
	private final BojnProbTypeService service;
	
	public TypeController(BojnProbTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<ProbTypeDto> getAllBojDto(){
		return service.getAll();
	}
	
	@PostMapping("/save")
	public BojDto getOne(@RequestBody InputDto inputDto) {
		System.out.println("BOJID: "+inputDto.getBojId());
		System.out.println("PROBTYPEID: "+inputDto.getProbTypeId());
		return service.save(inputDto.getBojId(), inputDto.getProbTypeId());
	}
	
	@GetMapping("/{type}")
	public List<ProbTypeDto> getListByType(@PathVariable String type) {
		return service.getProbsByType(type);
	}
	
	@GetMapping("/typeNo/{type}")
	public List<ProbTypeDto> getByTypeNo(@PathVariable String type) {
		return service.getProbsByTypeNo(Long.valueOf(type));
	}
	
	@GetMapping("/bpt/save")
	public JSONArray save() throws FileNotFoundException, IOException, ParseException {
		return service.saveProbType();
	}
	
	@GetMapping("/test")
	public JSONArray test() throws FileNotFoundException, IOException, ParseException {
		return service.matchBojAndProb();
	}
	
	
}
