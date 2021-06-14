package com.example.controller;

import java.util.List;

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
	public List<BojDto> getAllBojDto(){
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
	
}
