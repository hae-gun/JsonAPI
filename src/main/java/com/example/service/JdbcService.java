package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.repository.JdbcAccountRepository;
import com.example.vo.Account;

public class JdbcService {
	
	private final JdbcAccountRepository jdbcAccountRepository; 
	public JdbcService(JdbcAccountRepository jdbcAccountRepository) {
		this.jdbcAccountRepository = jdbcAccountRepository;
	}
	public List<Account> getAllEmail() {
		return jdbcAccountRepository.findAll();
	}
	public Account findById(Long id) {
		return jdbcAccountRepository.findById(id);
	}
	public Long saveMember(Account account) {
		return jdbcAccountRepository.save(account);
	}
	public int deleteById(Long id) {
		return jdbcAccountRepository.deleteById(id);
	}
	
	public List<String> getEmailsOnlyId(){
		List<Account> list = jdbcAccountRepository.findAll();
		List<String> result = new ArrayList<>();
		for(Account act:list) {
			result.add(act.getEmail());
		} 
		return result;
	}
	
}
