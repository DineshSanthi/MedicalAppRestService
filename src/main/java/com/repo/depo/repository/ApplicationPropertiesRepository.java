package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.ApplicationProperties;
import com.repo.depo.model.Column;
import com.repo.depo.model.Table;

@Repository
public interface ApplicationPropertiesRepository extends MongoRepository<ApplicationProperties, String>{

	Optional<ApplicationProperties> findById(String id);

	
	List<ApplicationProperties> findByAppName(String appName);

	
	void deleteById(String id);
}
