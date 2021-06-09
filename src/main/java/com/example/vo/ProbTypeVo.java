package com.example.vo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PTYPE")
public class ProbTypeVo {
	@Id
	@GeneratedValue
	@Column(name="probType_id")
	private Long id;
	
	private Long typeNo;
	private String type;
	
	@OneToMany(mappedBy = "probTypeVo")
	private List<BojProbType> bojProbTypes;

	
	
	public ProbTypeVo() {
	}
	public ProbTypeVo(Long id, String type,Long typeNo) {
		this.id = id;
		this.type = type;
		this.typeNo = typeNo;
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
	public List<BojProbType> getBojProbTypes() {
		return bojProbTypes;
	}
	public void setBojProbTypes(List<BojProbType> bojProbTypes) {
		this.bojProbTypes = bojProbTypes;
	}
	public Long getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(Long typeNo) {
		this.typeNo = typeNo;
	}
	
	
}
