package com.sep29th.rest.webservices.restfultwebservices.jpa;

import com.sep29th.rest.webservices.restfultwebservices.user.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
