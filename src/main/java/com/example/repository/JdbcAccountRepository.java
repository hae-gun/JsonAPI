package com.example.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.example.vo.Account;
public class JdbcAccountRepository implements JdbcRepository{
	
	private final JdbcTemplate jdbcTemplate;
	public JdbcAccountRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int count() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(*) from accounts", Integer.class);
	}

	@Override
	public <T> long save(T account) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement("insert into accounts (email) values (?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, ((Account)account).getEmail());
			return ps;
        }, keyHolder);
		return keyHolder.getKey().longValue();
	}


	@Override
	public <T> int update(T account) {
		return jdbcTemplate.update("update accounts set email = ? where id = ?", 
				((Account)account).getEmail(),
				((Account)account).getId());
	}

	@Override
	public int deleteById(Long id) {
		return jdbcTemplate.update("delete accounts where id = ?",
				id);
	}

	@Override
	public List<Account> findAll() {
		return jdbcTemplate.query("select * from accounts", 
				(rs, rowNum) -> new Account(
							rs.getLong("id"),
							rs.getString("email")
						)
				);
				
	}

	@Override
	public Account findById(Long id) {
		try {
			return jdbcTemplate.queryForObject("select * from accounts where id = ?",
					new Object[] {id}, 
					(rs, rowNum) -> Optional.of(new Account(
							rs.getLong("id"),
							rs.getString("email")
						))
					).orElse(null);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Account findByEmail(String email) {
		try {
			return jdbcTemplate.queryForObject("select * from accounts where email = ?", 
					new Object[] {email}, 
					(rs, rowNum) -> Optional.of(new Account(
							rs.getLong("id"),
							rs.getString("email")
						))
					).orElse(null);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public void deleteAll() {
		jdbcTemplate.update("delete from accounts");
		
	}
}
