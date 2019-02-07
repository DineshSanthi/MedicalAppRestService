package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.repo.depo.model.Column;
import com.repo.depo.model.GlobalProperties;

public interface GlobalPropertiesRepository extends MongoRepository<GlobalProperties, String>{

	Optional<GlobalProperties> findById(String id);


	
	void deleteById(String id);
}
