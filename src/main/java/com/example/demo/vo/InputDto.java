package com.example.demo.vo;

public class InputDto {
	
	private String bojId;
	private Long probTypeId;
	
	
	
	public InputDto() {
	}

	public InputDto(String bojId, Long probTypeId) {
		this.bojId = bojId;
		this.probTypeId = probTypeId;
	}

	public String getBojId() {
		return bojId;
	}

	public void setBojId(String bojId) {
		this.bojId = bojId;
	}

	public Long getProbTypeId() {
		return probTypeId;
	}

	public void setProbTypeId(Long probTypeId) {
		this.probTypeId = probTypeId;
	}
	
	
	
}
