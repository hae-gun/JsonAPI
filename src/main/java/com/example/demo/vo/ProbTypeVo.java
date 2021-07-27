package com.example.demo.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.simple.JSONObject;

@Entity
@Table(name="PTYPE")
public class ProbTypeVo {
	@Id
	@GeneratedValue
	@Column(name="probType_id")
	private Long id;
	
	private Long typeNo;
	private String type;
	private String typeEng;
	@OneToMany(mappedBy = "probTypeVo")
	private List<BojProbType> bojProbTypes = new ArrayList<BojProbType>();

	
//	{
//    "num": 124,
//    "name": "수학",
//    "count": 29,
//    "eng": "MATHEMATICS"
//}
	public ProbTypeVo() {
	}
	public ProbTypeVo(JSONObject obj) {
		this.typeNo = Long.valueOf(obj.get("num").toString());
		this.type = obj.get("name").toString();
		this.typeEng = obj.get("eng").toString();
	}
	
	public ProbTypeVo(Long typeNo, String type, String typeEng) {
		this.typeNo = typeNo;
		this.type = type;
		this.typeEng = typeEng;
	}

	public String getTypeEng() {
		return typeEng;
	}
	public void setTypeEng(String typeEng) {
		this.typeEng = typeEng;
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
	@Override
	public String toString() {
		return "ProbTypeVo [typeNo=" + typeNo + ", type=" + type + ", typeEng=" + typeEng + ", size:"+this.bojProbTypes.size()+"]";
	}
	
	public ProbTypeDto2 parseDto2() {
		return new ProbTypeDto2(this);
	}
	public ProbTypeDto2 parseDto2(List list) {
		return new ProbTypeDto2(this,list);
	}
	
	public ProbTypeDto parseDto() {
		return new ProbTypeDto(this);
	}
	
	public List<BojDto> getBojDto(){
		return this.bojProbTypes.stream().map(vo -> vo.bojVo.parseBojDto()).collect(Collectors.toList());
	}

	public ProbTypeDto2 getPTD2(){
		return parseDto2(getBojDto());
	}
}
