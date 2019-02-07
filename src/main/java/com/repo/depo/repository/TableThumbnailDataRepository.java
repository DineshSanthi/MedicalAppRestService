package com.repo.depo.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.repo.depo.model.TableThumbnailData;


@Repository
public interface TableThumbnailDataRepository extends MongoRepository<TableThumbnailData, ObjectId> {
	
	Optional<TableThumbnailData> findById(ObjectId objectId);

}
