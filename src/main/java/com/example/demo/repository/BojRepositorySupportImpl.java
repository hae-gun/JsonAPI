package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BojVo;
//import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
@Repository
public class BojRepositorySupportImpl extends QuerydslRepositorySupport implements BojRepositorySupport{

	private final JPAQueryFactory queryFactory;
	
	public BojRepositorySupportImpl(JPAQueryFactory jpaQueryFactory) {
		super(BojVo.class);
		this.queryFactory = jpaQueryFactory;
	}
//	private QBojVo vo = QBojVo.bojVo;
//	
	@Override
	public List findName() {
		return null;
//		
//		Predicate predicate = vo.level.like("%silver%");
//				
//		return queryFactory.query().from(vo.bojVo)
//				.where(predicate)
//				.orderBy(vo.bojVo.id.desc()).limit(10L).fetch();
////		return queryFactory.select(vo.bojVo)
////							.from(vo.bojVo)
////							.where(vo.id.eq("1000"))
////							.orderBy(vo.bojVo.id.desc()).fetch();
	}

}
