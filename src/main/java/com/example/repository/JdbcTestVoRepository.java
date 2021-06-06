package com.example.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.example.vo.Account;

public class JdbcTestVoRepository implements JdbcRepository {

	private final JdbcTemplate jdbcTemplate;
	public JdbcTestVoRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> long save(T TestVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> int update(T TestVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
