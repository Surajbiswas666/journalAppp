package net.engineeringdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserDetailsServiceImpl;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.utils.JwtUtil;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

	
	@Autowired
	AuthenticationManager authentication;

	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/signup")
	public void createUser(@RequestBody User user)
	{
		userService.saveNewUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user)
	{
		try {
			Authentication authenticate = authentication
					.authenticate(new UsernamePasswordAuthenticationToken
							(user.getUserName(), user.getPassword()));
			UserDetails userDetails=  userDetailsService.loadUserByUsername(user.getUserName());
			String jwt = jwtUtil.generateToken(userDetails.getUsername());
			return new ResponseEntity<>(jwt,HttpStatus.OK);
		}catch (Exception e) {
			log.error("Exception poccured while CreateAuthenticationToken ",e);
			return new ResponseEntity<>("Incorrect username or password",HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
}
