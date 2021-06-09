package com.example.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="probTable")
public class BojVo {
	@Id
	@NonNull
	@Column(name="boj_id")
	private String id;
	private String level;
	private String name;
	private String url;
	
	@OneToMany(mappedBy = "bojVo")
	private List<BojProbType> bojProbType;
	
	public BojVo() {
		
	}
	public BojVo(@NonNull String id, String level, String name, String url) {
		this.id = id;
		this.level = level;
		this.name = name;
		this.url = url;
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
	@Override
	public String toString() {
		return "BojVo [id=" + id + ", level=" + level + ", name=" + name + ", url=" + url + "]";
	}
	public List<BojProbType> getBojProbType() {
		return bojProbType;
	}
	public void setBojProbType(List<BojProbType> bojProbType) {
		this.bojProbType = bojProbType;
	}
	
	
}
