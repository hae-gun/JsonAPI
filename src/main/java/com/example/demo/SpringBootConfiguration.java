package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan(basePackages = "com.example") 
@PropertySource("classpath:/application.properties")
@EnableJpaRepositories(basePackages = "com.example.repository")
@EntityScan(basePackages = "com.example.vo") // Entity 가 not a managed type 인 이유 -> Entity scan을 하지 않았기 때문이다.
public class SpringBootConfiguration {
	 
//	@Bean 
//	public TestVo testVo() {
//		return new TestVo();
//	}
	
//	@Bean
//	public JpaRepository<TestVo, Long> jpaRepositoryConfigExtension() {
//		return new TestRepo
//	}
	
}
