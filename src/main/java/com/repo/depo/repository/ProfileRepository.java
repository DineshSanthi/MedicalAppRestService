package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Application;
import com.repo.depo.model.Column;
import com.repo.depo.model.Profile;
import com.repo.depo.model.Roles;


@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
	
	Optional<Profile> findById(String id);

	void deleteById(String id);

	List<Profile> findByAppName(String appName);
}
