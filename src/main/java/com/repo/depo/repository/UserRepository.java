package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Application;
import com.repo.depo.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	Optional<User> findById(String id);

	void deleteById(String id);

}
