package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Application;
import com.repo.depo.model.Roles;


@Repository
public interface RolesRepository extends MongoRepository<Roles, String> {
	
	Optional<Roles> findById(String id);

	void deleteById(String id);

}
