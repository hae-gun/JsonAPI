package com.example.vo;

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
}
