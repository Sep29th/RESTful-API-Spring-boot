package com.sep29th.rest.webservices.restfultwebservices.jpa;

import com.sep29th.rest.webservices.restfultwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
