package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.BojProbType;
import org.springframework.stereotype.Repository;

@Repository
public interface BojProbTypeRepository extends JpaRepository<BojProbType, Long>{

}
