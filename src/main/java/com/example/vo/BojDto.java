package com.example.vo;

import java.util.List;

public class BojDto {
	
	private String id;
	private String level;
	private String name;
	private String url;
	private List<ProbTypeVo> types;
	public BojDto() {
	}
	public BojDto(String id, String level, String name, String url, List<ProbTypeVo> types) {
		this.id = id;
		this.level = level;
		this.name = name;
		this.url = url;
		this.types = types;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ProbTypeVo> getTypes() {
		return types;
	}
	public void setTypes(List<ProbTypeVo> types) {
		this.types = types;
	}
	
	
}
