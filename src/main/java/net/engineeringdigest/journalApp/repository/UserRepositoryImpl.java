package net.engineeringdigest.journalApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import net.engineeringdigest.journalApp.entity.User;

public class UserRepositoryImpl {

	@Autowired
	MongoTemplate mongoTempleTemplate;
	
	
	public List<User> getUserForSA()
	{
		Query query = new Query();
		try {
			query.addCriteria(Criteria.where("email").exists(true));
			query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
		List<User> users=  mongoTempleTemplate.find(query, User.class);
		return users;
	}
}
