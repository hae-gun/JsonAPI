package com.example.vo;

import java.util.ArrayList;
import java.util.List;

public class ProbTypeDto {
	
	
	private Long id;
	private Long typeNo;
	private String type;
	
	private List<BojVo> probs = new ArrayList<BojVo>();

	
	
	public ProbTypeDto() {
		super();
	}



	public ProbTypeDto(Long id, Long typeNo, String type) {
		this.id = id;
		this.typeNo = typeNo;
		this.type = type;
	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public List<BojVo> getProbs() {
		return probs;
	}



	public void setProbs(List<BojVo> probs) {
		this.probs = probs;
	}



	public Long getTypeNo() {
		return typeNo;
	}
	
	
	
}
