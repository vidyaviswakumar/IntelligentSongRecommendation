package com.ds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ds.controller.Recommendation;

public interface RecommendationRepository extends MongoRepository<Recommendation, String>{
	

}
