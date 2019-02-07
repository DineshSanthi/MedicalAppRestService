package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.repo.depo.model.AbstractEntityObject;

public interface DataRepository extends MongoRepository<AbstractEntityObject, String> {
	
	List<AbstractEntityObject> findById(String id);
	
	

	void deleteById(String id);

	
}
