package com.chatter.authservice.repository;

import com.chatter.authservice.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
