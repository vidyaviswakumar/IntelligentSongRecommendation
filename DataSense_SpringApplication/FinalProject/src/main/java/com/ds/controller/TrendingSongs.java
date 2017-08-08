package com.ds.controller;

	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

	@Document(collection="trending_songs_filtered_year")
	public class TrendingSongs {
	        
	    @Id
	    private String _id;
	    
	    @Field("totalPlayCount")
	    private int totalPlayCount;
 
	    public String get_id() {
	        return _id;
	    }

	    public void set_id(String _id) {
	        this._id = _id;
	    }

	    public int getTotalPlayCount() {
	        return totalPlayCount;
	    }

	    public void setTotalPlayCount(int totalPlayCount) {
	        this.totalPlayCount = totalPlayCount;
	    }

	    @Override
	   public String toString() {
	       return String.valueOf(_id);
	   }
	               
	}