package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Application;


@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
	
	Optional<Application> findById(String id);

	void deleteById(String id);

}
