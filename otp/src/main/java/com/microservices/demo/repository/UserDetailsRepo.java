package com.microservices.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservices.demo.entity.UserDetails;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

}
