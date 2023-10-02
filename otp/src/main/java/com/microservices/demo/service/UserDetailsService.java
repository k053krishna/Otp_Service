package com.microservices.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.microservices.demo.entity.UserDetails;
import com.microservices.demo.repository.UserDetailsRepo;

@Service
public class UserDetailsService implements IUserDetailsService{

	@Autowired
	UserDetailsRepo userRepo;
	
	@Override
	public String saveUserDetails(UserDetails user) {
		userRepo.save(user);
		return "user is saved success fully";
	}

	@Override
	public UserDetails getUserDetails(Integer id) {
		
		Optional<UserDetails> optUser=userRepo.findById(id);
		
		if(optUser.isPresent()) {
			return optUser.get();
		}
		else {
			
			return null;
		}
		
	}

}
