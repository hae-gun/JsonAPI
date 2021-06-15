package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vo.BojVo;
import com.example.vo.ProbTypeVo;
@Repository
public interface ProbTypeRepository extends JpaRepository<ProbTypeVo, Long>{
	List<ProbTypeVo> findByTypeLike(String type);
	List<ProbTypeVo> findByTypeNo(Long type);
}
