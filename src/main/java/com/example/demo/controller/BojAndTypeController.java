package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ProbTypeService;
import com.example.demo.vo.ProbTypeDto;
import com.example.demo.vo.ProbTypeDto2;

@RestController
@RequestMapping("/api")
public class BojAndTypeController {

	private final ProbTypeService service;

	public BojAndTypeController(ProbTypeService service) {
		this.service = service;
	}
	
	@GetMapping("/all")
	public List<ProbTypeDto2> getAll(){
		return service.getDtos();
	}
	
	// /{알고리즘번호}
	@GetMapping("/{type}")
	public List<ProbTypeDto2> getTypeProb(@PathVariable Long type){
		return service.getDtos(type);
	}
	
	// /{알고리즘번호}/레벨
	@GetMapping("/{type}/{level}")
	public List<ProbTypeDto> getTypeProbByLevel(@PathVariable Long type,@PathVariable String level){
		return service.getDtos(type,level);
	}
	@GetMapping("/{type}/one")
	public List<ProbTypeDto2> getTypeProbOneByOne(@PathVariable Long type){
		return service.getDtosPerLevel(type);
	}
	
	
}
