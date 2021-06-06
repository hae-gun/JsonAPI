package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.JsonApiApplication;
import com.example.vo.Account;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = JsonApiApplication.class)
class JdbcAccountRepositoryTest { //JUnit5 로 동작하고 있음.
	@Autowired
	private JdbcAccountRepository jdbcAccountRepository;
	
	String defaultEmail = "add@email.com";
	
	@BeforeEach //JUnit4 에서 @Before 과 같은 기능
	public void setUp() {
		System.out.println("Do setUp");
		jdbcAccountRepository.deleteAll();		
	}
	
	@Test
	public void count() {
		assertEquals(0, jdbcAccountRepository.count());
		jdbcAccountRepository.save(new Account("add1@email.com"));
		jdbcAccountRepository.save(new Account("add2@email.com"));
		jdbcAccountRepository.save(new Account("add3@email.com"));
		assertEquals(3, jdbcAccountRepository.count());
	}
	
	@Test
	public void saveAndFindAll() {
		jdbcAccountRepository.save(new Account("add1@email.com"));
		jdbcAccountRepository.save(new Account("add2@email.com"));
		jdbcAccountRepository.save(new Account("add3@email.com"));
		
		List<Account> getAll = jdbcAccountRepository.findAll();
		assertEquals(3, getAll.size());
	}
	
	@Test
	public void saveAndFindById() {
		long resultId = jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findById(resultId);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void saveAndFindByEmail() {
		jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findByEmail(defaultEmail);
		
		assertNotNull(getAccount);
		assertEquals(defaultEmail, getAccount.getEmail());
	}
	
	@Test
	public void update() {
		long resultId = jdbcAccountRepository.save(new Account(defaultEmail));
		Account getAccount = jdbcAccountRepository.findById(resultId);
		
		getAccount.setEmail("update@email.com");
		jdbcAccountRepository.update(getAccount);
		Account updatedAccount = jdbcAccountRepository.findById(resultId);
		assertEquals("update@email.com", updatedAccount.getEmail());
	}
	
	@Test
	public void deleteById() {
		long resultId1 = jdbcAccountRepository.save(new Account("add1@email.com"));
		long resultId2 = jdbcAccountRepository.save(new Account("add2@email.com"));
		long resultId3 = jdbcAccountRepository.save(new Account("add3@email.com"));
		
		jdbcAccountRepository.deleteById(resultId2);
		assertEquals(2, jdbcAccountRepository.count()); 
		
		assertNotNull(jdbcAccountRepository.findById(resultId1));
		assertNull(jdbcAccountRepository.findById(resultId2));
		assertNotNull(jdbcAccountRepository.findById(resultId3));
	}

}
