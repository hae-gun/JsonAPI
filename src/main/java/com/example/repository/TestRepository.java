package com.example.repository;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vo.TestVo;
@Repository
public interface TestRepository extends JpaRepository<TestVo, Long> {

}
