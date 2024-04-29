package com.postify.postify;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.postify.postify.user.User;
import com.postify.postify.user.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void findByUsername_whenUserExists_returnsUser() {
		User user = new User();
		
		user.setUsername("test-user");
		user.setDisplayName("test-display");
		user.setPassword("P4ssword");
		
		testEntityManager.persist(user);
		userRepository.findByUsername("test-user");
		
		User inDB = userRepository.findByUsername("test-user");
		assertThat(inDB).isNotNull();
	}
	
	@Test
	public void findByUsername_whenUserDoesNotExist_returnNull() {
		User inDB = userRepository.findByUsername("noneexistinguser");
		assertThat(inDB).isNull();
	}
}
