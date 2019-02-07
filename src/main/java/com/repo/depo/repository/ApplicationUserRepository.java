package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.ApplicationUser;
import com.repo.depo.model.Column;
import com.repo.depo.model.Table;

@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String>{

	Optional<ApplicationUser> findById(String id);

	
	List<ApplicationUser> findByAppName(String appName);

	
	void deleteById(String id);
}
