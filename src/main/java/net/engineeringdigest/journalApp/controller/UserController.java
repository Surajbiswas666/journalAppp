package net.engineeringdigest.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.engineeringdigest.journalApp.apiresponse.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	WeatherService weatherService;
	@GetMapping
	public List<User> getAllUsers()
	{
		return userService.getAll();
	}
	
	
	
	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user)
	{
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		User userInDb = userService.findByUserName(userName);
	
		userInDb.setUserName(user.getUserName());
		userInDb.setPassword(user.getPassword());
		userService.saveNewUser(userInDb);
	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteUserById()
	{
		Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
		userService.deleteByUserName(authentication.getName());
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/greeting")
	public ResponseEntity<?> greeting()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WeatherResponse weatherResponse = weatherService.getWeather("Chennai");
		String greeting ="";
		if(weatherResponse!=null)
		{
			greeting = ", Weather feels like "+ weatherResponse.getCurrent().getFeelslike();
		}
		return new ResponseEntity<>("Hi  " + authentication.getName() + greeting,HttpStatus.OK);
	}
	
}
