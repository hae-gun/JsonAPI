package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.vo.BojVo;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class BojRepositorySupport extends QuerydslRepositorySupport{

	private final JPAQueryFactory jpaQueryFactory;
	
	public BojRepositorySupport(JPAQueryFactory jpaQueryFactory) {
		super(BojVo.class);
		this.jpaQueryFactory = jpaQueryFactory;
	}
	
//	public List<BojVo> findByName(String name){
//		return jpaQueryFactory.selectFrom(name);
//	}

}
