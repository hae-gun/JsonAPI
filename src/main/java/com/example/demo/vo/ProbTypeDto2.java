package com.example.demo.vo;

import java.util.ArrayList;
import java.util.List;

public class ProbTypeDto2 {
	
	private Long id;
	private Long typeNo;
	private String type;
	private String typeEng;
	private List<BojDto> probs = new ArrayList<BojDto>();
	
	public ProbTypeDto2(ProbTypeVo dto) {
		this.id = dto.getId();
		this.typeNo = dto.getTypeNo();
		this.type = dto.getType();
		this.typeEng = dto.getTypeEng();
	}
	public ProbTypeDto2(ProbTypeVo dto, List<BojDto> probs) {
		this.id = dto.getId();
		this.typeNo = dto.getTypeNo();
		this.type = dto.getType();
		this.typeEng = dto.getTypeEng();
		this.probs = probs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(Long typeNo) {
		this.typeNo = typeNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeEng() {
		return typeEng;
	}
	public void setTypeEng(String typeEng) {
		this.typeEng = typeEng;
	}
	public List<BojDto> getProbs() {
		return probs;
	}
	public void setProbs(List<BojDto> probs) {
		this.probs = probs;
	}
	
	
}
