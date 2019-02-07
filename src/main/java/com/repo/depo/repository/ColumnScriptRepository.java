package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.repo.depo.model.ColumnScript;


public interface ColumnScriptRepository extends MongoRepository<ColumnScript, String>{

	void deleteById(String id);

	Optional<ColumnScript> findById(String id);
}
