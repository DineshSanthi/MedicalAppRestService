package com.repo.depo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>{
	
	Optional<Customer> findById(String id);

	void deleteById(String id);

}
