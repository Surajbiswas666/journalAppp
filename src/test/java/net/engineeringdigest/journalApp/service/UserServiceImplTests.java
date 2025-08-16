package net.engineeringdigest.journalApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import com.mongodb.assertions.Assertions;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

public class UserServiceImplTests {

	@InjectMocks
	private UserDetailsServiceImpl userDetailsService;
	
	@Mock
	private UserRepository userRepository;
	@BeforeEach
	@Disabled
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	@Disabled
	@Test
	void loadUserByUsernametest()
	{
		when(userRepository.findByUserName(ArgumentMatchers.anyString()))
		.thenReturn(User.builder()
				.userName("Patlu")
				.password("inininij")
				.roles(new ArrayList<>())
				.build());
		UserDetails user = userDetailsService.loadUserByUsername("Patlu");
		Assertions.assertNotNull(user);
	}
}
