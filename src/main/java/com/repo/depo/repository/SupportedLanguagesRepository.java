package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Column;
import com.repo.depo.model.SupportedLanguages;
import com.repo.depo.model.Table;

@Repository
public interface SupportedLanguagesRepository extends MongoRepository<SupportedLanguages, String>{

	Optional<SupportedLanguages> findById(String id);

	
	List<SupportedLanguages> findByAppName(String appName);

	
	void deleteById(String id);
}
