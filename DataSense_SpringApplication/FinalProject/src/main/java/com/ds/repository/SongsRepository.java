package com.ds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ds.controller.Songs;

@Repository
public interface SongsRepository extends MongoRepository<Songs, String>{
	
	
	
}
