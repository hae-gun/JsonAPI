package com.example.demo.vo;

import java.util.ArrayList;
import java.util.List;

public class ProbTypeDto {
	
	
	private Long id;
	private Long typeNo;
	private String type;
	private String typeEng;
	private List<BojVo> probs = new ArrayList<BojVo>();
	
	
	
	public ProbTypeDto() {
		super();
	}


	public ProbTypeDto(Long id, Long typeNo, String type) {
		this.id = id;
		this.typeNo = typeNo;
		this.type = type;
	}
	 
	public ProbTypeDto(ProbTypeVo vo) {
		this.id = vo.getId();
		this.typeNo = vo.getTypeNo();
		this.type = vo.getType();
		this.typeEng = vo.getTypeEng();
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

	public String getTypeEng() {
		return typeEng;
	}

	
	
	
}
