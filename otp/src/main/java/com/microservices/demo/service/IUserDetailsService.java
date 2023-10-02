package com.microservices.demo.service;

import com.microservices.demo.entity.UserDetails;
public interface IUserDetailsService {
	
	public String saveUserDetails(UserDetails user);
	public UserDetails getUserDetails(Integer id);

}
