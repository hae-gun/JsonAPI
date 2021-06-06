package com.example.repository;

import java.util.List;

import com.example.vo.Account;

public interface JdbcRepository {
	int count();

	<T>long save(T account);

	<T>int update(T account);

	int deleteById(Long id);

	void deleteAll();

	<T> List<T> findAll();

	<T> T findById(Long id);

	<T> T findByEmail(String email);
}
