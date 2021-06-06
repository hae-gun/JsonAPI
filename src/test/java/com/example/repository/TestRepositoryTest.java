package com.example.repository;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.JsonApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration(classes = JsonApiApplication.class)
//@ExtendWith(MockitoExtension.class);
public class TestRepositoryTest {
	
	@Autowired
	BojRepository repository;
	
	
	
	@Test
	public void load() {
		List list = repository.findAll();
		assertThat(list.size(),not(0));
	}
	
	
}
