package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vo.ProbTypeVo;
@Repository
public interface ProbTypeRepository extends JpaRepository<ProbTypeVo, Long>{

}
