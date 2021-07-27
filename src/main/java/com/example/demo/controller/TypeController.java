package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.BojnProbTypeService;
import com.example.demo.vo.BojDto;
import com.example.demo.vo.InputDto;
import com.example.demo.vo.ProbTypeDto2;

@RestController
@RequestMapping("/type")
public class TypeController {

	
	private final BojnProbTypeService service;
	
	public TypeController(BojnProbTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<ProbTypeDto2> getAllBojDto(){
		return service.getAll();
	}
	
	@PostMapping("/save")
	public BojDto getOne(@RequestBody InputDto inputDto) {
		System.out.println("BOJID: "+inputDto.getBojId());
		System.out.println("PROBTYPEID: "+inputDto.getProbTypeId());
		return service.save(inputDto.getBojId(), inputDto.getProbTypeId());
	}
	
	@GetMapping("/{type}")
	public List<ProbTypeDto2> getListByType(@PathVariable String type) {
		return service.getProbsByType(type);
	}
	
	@GetMapping("/type-no/{type}")
	public List<ProbTypeDto2> getByTypeNo(@PathVariable String type) {
		return service.getProbsByTypeNo(Long.valueOf(type));
	}
	
	@GetMapping("/bpt/save")
	public JSONArray save() throws FileNotFoundException, IOException, ParseException {
		return service.saveProbType();
	}
	
	@GetMapping("/matchs")
	public JSONArray match() throws FileNotFoundException, IOException, ParseException {
		return service.matchBojAndProb();
	}
	
	@GetMapping("/test")
	public List<ProbTypeDto2> test() throws FileNotFoundException, IOException, ParseException {
		return service.test();
	}
	@PostMapping("/test")
	public List<ProbTypeDto2> test(@RequestBody Long... data) throws FileNotFoundException, IOException, ParseException {
		return service.test(data);
	}
	
}
