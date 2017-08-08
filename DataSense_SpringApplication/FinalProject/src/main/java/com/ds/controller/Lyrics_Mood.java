package com.ds.controller;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="lyrics_info")
public class Lyrics_Mood {

	
	@Id
	private String _id;
	
	@Field("track_id")
	private String track_id;
	
	@Field("mood")
	private String mood;
	
	@Field("title")
	private String title;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getTrack_id() {
		return track_id;
	}

	public void setTrack_id(String track_id) {
		this.track_id = track_id;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
    public String toString() {
        return String.valueOf(track_id);
    }
}