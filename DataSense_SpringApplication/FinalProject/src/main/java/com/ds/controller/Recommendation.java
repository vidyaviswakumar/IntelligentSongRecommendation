package com.ds.controller;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.ds.pojo.SongRecommendation;

@Document(collection="mahout_reco_output")
public class Recommendation {
	
	@Id
	private String _id;
	
	@Field("userId")	
	private int userId;
	
	@Field("recos")
	private List<SongRecommendation> songRecommendation;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	public List<SongRecommendation> getSongRecommendation() {
		return songRecommendation;
	}
	public void setSongRecommendation(List<SongRecommendation> songRecommendation) {
		this.songRecommendation = songRecommendation;
	}
	@Override
    public String toString() {
        return String.valueOf(userId);
    }
	
	

}
