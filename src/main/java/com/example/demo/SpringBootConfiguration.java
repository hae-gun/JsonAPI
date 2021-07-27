package com.example.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.querydsl.jpa.impl.JPAQueryFactory;


@Configuration
@ComponentScan(basePackages = "com.example") 
@PropertySource("classpath:/application.yaml")
@EnableJpaRepositories(basePackages = "com.example.demo.repository")
//@EntityScan(basePackages = "com.example.demo.vo") // Entity 가 not a managed type 인 이유 -> Entity scan을 하지 않았기 때문이다.
public class SpringBootConfiguration {
	 
	@PersistenceContext
	private EntityManager entityManager;
	
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
	
	
//	@Bean 
//	public TestVo testVo() {
//		return new TestVo();
//	}
	
//	@Bean
//	public JpaRepository<TestVo, Long> jpaRepositoryConfigExtension() {
//		return new TestRepo
//	}
	
}
