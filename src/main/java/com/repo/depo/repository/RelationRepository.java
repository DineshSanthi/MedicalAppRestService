package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Relation;


@Repository
public interface RelationRepository extends MongoRepository<Relation, String> {
	
	Optional<Relation> findById(String id);
	
	List<Relation> findByPrimaryTable(String primaryTable);
	
	List<Relation> findByAppName(String appName);

	void deleteById(String id);

}
