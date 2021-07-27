package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfiguration {
	
	@Value("${spring.datasource.driver-class-name}")
	String driveName;
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driveName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
//	@Bean
//	public JdbcAccountRepository jdbcAccountRepository() {
//		return new JdbcAccountRepository(jdbcTemplate());
//	}
//	@Bean
//	public JdbcTestVoRepository testRepository() {
//		return new JdbcTestVoRepository(jdbcTemplate());
//	}
//	
//	@Bean
//	public JdbcService jdbcService() {
//		return new JdbcService(jdbcAccountRepository());
//	}
}
