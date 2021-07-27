package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class TestVo {
	@Id
	private int id;
	private String name;
	private String url;
	
	
	
	public TestVo() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	@Override
	public String toString() {
		return "TestVo [id=" + id + ", name=" + name + ", url=" + url + "]";
	}
	
	
}
