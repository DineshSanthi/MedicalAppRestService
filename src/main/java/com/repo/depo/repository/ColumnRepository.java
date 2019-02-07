package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Column;
import com.repo.depo.model.Table;

@Repository
public interface ColumnRepository extends MongoRepository<Column, String>{

	Optional<Column> findById(String id);

	List<Column> findByTableName(String tableName);
	
	List<Column> findByAppName(String appName);

	
	void deleteById(String id);
}
