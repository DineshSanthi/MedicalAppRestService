package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Reference;
@Repository
public interface ReferenceRepository extends MongoRepository<Reference, String> {

	Optional<Reference> findById(String id);

	void deleteById(String id);
}
