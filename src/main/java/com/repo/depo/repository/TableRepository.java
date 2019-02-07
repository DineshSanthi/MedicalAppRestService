package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Table;


@Repository
public interface TableRepository extends MongoRepository<Table, String> {
	
	Optional<Table> findById(String id);

	List<Table> findByAppName(String appName);

	void deleteById(String id);

}
