package net.engineeringdigest.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
	//private final Logger logger = LoggerFactory.getLogger(UserService.class);

	private final UserRepository userRepository;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public void saveEntry(User user)
	{
		userRepository.save(user);
	}
	
	public boolean saveNewUser(User user)
	{
		
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setRoles(Arrays.asList("USER"));
			userRepository.save(user);
			
		}catch(Exception e )
		{
			//logger.info("HAHA:");
			log.error("errror occured for {} ",user.getUserName(),e);
			log.warn("Warn loggerr");
			log.debug("Debug log");
			log.info("info log");
			log.trace("Tracce log");
			return false;
		}
		return true;
	}
	
	public List<User> getAll()
	{
		return userRepository.findAll();
	}
	
	public Optional<User> findByid(ObjectId id)
	{
		return userRepository.findById(id);
	}
	
	public void deleteById(ObjectId id)
	{
		userRepository.deleteById(id);
	}
	
	public User findByUserName(String username)
	{
		return userRepository.findByUserName(username);
	}
	
	public void deleteByUserName(String username)
	{
		userRepository.deleteByUserName(username);
	}

	public void saveAdmin(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList("ADMIN"));
		userRepository.save(user);
		
	}
	
}
