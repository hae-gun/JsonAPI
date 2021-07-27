package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class BojProbType {
	
	@Id
	@GeneratedValue
	Long no;
	
	@ManyToOne
	@JoinColumn(name="boj_id")
	BojVo bojVo;
	
	@ManyToOne
	@JoinColumn(name="probType_id")
	ProbTypeVo probTypeVo;
	
	public BojProbType() {
	}

	public BojProbType(BojVo bojVo, ProbTypeVo probTypeVo) {
		this.bojVo = bojVo;
		this.probTypeVo = probTypeVo;
	}

	public BojProbType(Long no, BojVo bojVo, ProbTypeVo probTypeVo) {
		this.no = no;
		this.bojVo = bojVo;
		this.probTypeVo = probTypeVo;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public BojVo getBojVo() {
		return bojVo;
	}

	public void setBojVo(BojVo bojVo) {
		this.bojVo = bojVo;
	}

	public ProbTypeVo getProbTypeVo() {
		return probTypeVo;
	}

	public void setProbTypeVo(ProbTypeVo probTypeVo) {
		this.probTypeVo = probTypeVo;
	}
}
