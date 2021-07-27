package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BojVo;
@Repository
public interface BojRepository extends JpaRepository<BojVo, String>{//,BojRepositorySupport,QuerydslPredicateExecutor<BojVo>{
	List<BojVo> findByLevelLike(String level);
	List<BojVo> findByNameLike(String name);
}
// JpaRepository like 검색을 원한다면 메서드 명을 findByXXXLike로 설정한후 parameter 값으로 '%검색어%' 로 보내면 Like 검색이 가능해진다.