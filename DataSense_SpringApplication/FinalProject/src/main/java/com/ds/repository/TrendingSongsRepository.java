package com.ds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.controller.TrendingSongs;

public interface TrendingSongsRepository  extends MongoRepository<TrendingSongs, String>{
	

}
