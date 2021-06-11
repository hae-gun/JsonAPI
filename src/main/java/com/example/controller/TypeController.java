package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.BojnProbTypeService;
import com.example.vo.BojDto;

@RestController
@RequestMapping("/type")
public class TypeController {

	
	private final BojnProbTypeService service;
	
	public TypeController(BojnProbTypeService service) {
		this.service = service;
	}
	
	public List<BojDto> getAllBojDto(){
		return service.getAll();
	}
	
	
	
}
