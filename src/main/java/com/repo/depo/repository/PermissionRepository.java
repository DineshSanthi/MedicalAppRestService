package com.repo.depo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.Application;
import com.repo.depo.model.Column;
import com.repo.depo.model.Permission;
import com.repo.depo.model.Roles;


@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
	
	Optional<Permission> findById(String id);

	void deleteById(String id);
	
	List<Permission> findByAppName(String appName);

}
