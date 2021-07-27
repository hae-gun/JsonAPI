package com.example.demo.vo;

import java.util.ArrayList;
import java.util.List;

public class BojDto {
	
	private String id;
	private String level;
	private String name;
	private String url;
	private List<String> types = new ArrayList<String>();
	public BojDto() {
	}
	public BojDto(String id, String level, String name, String url) {
		this.id = id;
		this.level = level;
		this.name = name;
		this.url = url;
//		this.types = types;
	}
//	public BojDto(BojVo vo, String type) {
//		this.id = vo.getId();
//		this.level = vo.getLevel();
//		this.name = vo.getName();
//		this.url = vo.getUrl();
//		if(type != null) {
//			this.types.add(type);
//		}
//	}
	public BojDto(BojVo vo) {
		this.id = vo.getId();
		this.level = vo.getLevel();
		this.name = vo.getName();
		this.url = vo.getUrl();
		vo.getBojProbType().stream().forEach(v->types.add(v.probTypeVo.getType()));
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
	public List<String> getTypes() {
		return types;
	}
	
	public void addType(String type) {
		this.types.add(type);
	}
	public BojVo convertToBojVo() {
		return new BojVo(id,level,name,url);
	}
	@Override
	public String toString() {
		return "BojDto [id=" + id + ", level=" + level + ", name=" + name + ", url=" + url + ", types=" + types + "]";
	}
	
}
