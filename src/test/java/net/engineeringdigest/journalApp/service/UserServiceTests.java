package net.engineeringdigest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
@SpringBootTest
public class UserServiceTests {

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"Ram",
		"Suraj",
		"Motu",
		"Patlu"
	})
	public void tesAdd(String name)
	{
		//User user = userRepository.findByUserName(name);
		assertNotNull(userRepository.findByUserName(name),"Failed for " +name);
		//assertTrue(!user.getJournalEntries().isEmpty());
		//assertTrue(5>3);
	}
	@Disabled
	@ParameterizedTest
	@CsvSource({
		"1,1,2",
		"2,10,12",
		"2,2,6"
	})
	public void test(int a , int b, int expected) {
		assertEquals(expected, a+b);
	}
	


//	@ValueSource(strings = {
//		"Ram",
//		"Suraj",
//		"Motu",
//		"Patlu"
//	})
	@Disabled
	@ParameterizedTest
	@ArgumentsSource(UserArgumentsProvider.class)
	public void testSaveNewUser(User user)
	{
		assertTrue(userService.saveNewUser(user));
	}
	
	
	
	
}
