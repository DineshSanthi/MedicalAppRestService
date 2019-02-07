package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.repo.depo.model.TableScript;

public interface TableScriptRepository extends MongoRepository<TableScript, String>{

	void deleteById(String id);

	Optional<TableScript> findById(String id);
}
