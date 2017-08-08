package com.ds.controller;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="songs_dataset")
public class Songs {

	    @Id
	    private String id;

	    @Field("Song_id")
	    private String Song_id;
	    
	    @Field("Track_id")
	    private String Track_id;
	    
	    @Field("Title")
	    private String Title;
	    
	    @Field("Artist_name")
	    private String Artist_name;
	    
	    @Field("Artist_Location")
	    private String Artist_Location;
	    
//	    @Field("Danceability")
//	    private int Danceability;
//	    
//	    @Field("Duration")
//	    private double Duration;
//	    
//	    @Field("Energy")
//	    private int Energy;
//	    
//	    @Field("Loudness")
//	    private double Loudness;
//	    
//	    @Field("Mode")
//	    private int Mode;
//	    
//	    @Field("Mode_Confidence")
//	    private double Mode_Confidence;
//	    
//	    @Field("Tempo")
//	    private double Tempo;
//	    
//	    @Field("Artist_7digitalid")
//	    private int Artist_7digitalid;
//	    
	    @Field("Artist_id")
	    private String Artist_id;
	    
//	    @Field("Track_7digitalid")
//	    private int Track_7digitalid;
//	    
//	    @Field("Year")
//	    private int Year;
//	    
//	    @Field("hot")
//	    private double hot;
//	    
//	    @Field("Release_7digitalid")
//	    private int Release_7digitalid;
//	    
//	    @Field("Release")
//	    private String Release;
//	    
//	    @Field("Similar_artists")
//	    private String[] Similar_artists;
//	    
//	    @Field("Artist_terms")
//	    private String[] Artist_terms;
//	    
//	    @Field("Artist_freq")
//	    private String[] Artist_freq;
//	    
//	    @Field("Artist_weight")
//	    private String[] Artist_weight;
	    
	    
	    

	    public Songs() {}

	    public Songs(String Song_id, String Track_id) {
	        this.Song_id = Song_id;
	        this.Track_id = Track_id;
	    }

	    
	    
	    public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSong_id() {
			return Song_id;
		}

		public void setSong_id(String song_id) {
			Song_id = song_id;
		}

		public String getTrack_id() {
			return Track_id;
		}

		public void setTrack_id(String track_id) {
			Track_id = track_id;
		}
		
		

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}
		
		

		public String getArtist_name() {
			return Artist_name;
		}

		public void setArtist_name(String artist_name) {
			Artist_name = artist_name;
		}

		public String getArtist_Location() {
			return Artist_Location;
		}

		public void setArtist_Location(String artist_Location) {
			Artist_Location = artist_Location;
		}

//		public int getDanceability() {
//			return Danceability;
//		}
//
//		public void setDanceability(int danceability) {
//			Danceability = danceability;
//		}
//
//		public double getDuration() {
//			return Duration;
//		}
//
//		public void setDuration(double duration) {
//			Duration = duration;
//		}
//
//		public int getEnergy() {
//			return Energy;
//		}
//
//		public void setEnergy(int energy) {
//			Energy = energy;
//		}
//
//		public double getLoudness() {
//			return Loudness;
//		}
//
//		public void setLoudness(double loudness) {
//			Loudness = loudness;
//		}
//
//		public int getMode() {
//			return Mode;
//		}
//
//		public void setMode(int mode) {
//			Mode = mode;
//		}
//
//		public double getMode_Confidence() {
//			return Mode_Confidence;
//		}
//
//		public void setMode_Confidence(double mode_Confidence) {
//			Mode_Confidence = mode_Confidence;
//		}
//
//		public double getTempo() {
//			return Tempo;
//		}
//
//		public void setTempo(double tempo) {
//			Tempo = tempo;
//		}
//
//		public int getArtist_7digitalid() {
//			return Artist_7digitalid;
//		}
//
//		public void setArtist_7digitalid(int artist_7digitalid) {
//			Artist_7digitalid = artist_7digitalid;
//		}

		public String getArtist_id() {
			return Artist_id;
		}

		public void setArtist_id(String artist_id) {
			Artist_id = artist_id;
		}

//		public int getTrack_7digitalid() {
//			return Track_7digitalid;
//		}
//
//		public void setTrack_7digitalid(int track_7digitalid) {
//			Track_7digitalid = track_7digitalid;
//		}
//
//
//		public int getRelease_7digitalid() {
//			return Release_7digitalid;
//		}
//
//		public void setRelease_7digitalid(int release_7digitalid) {
//			Release_7digitalid = release_7digitalid;
//		}
//
//		public String getRelease() {
//			return Release;
//		}
//
//		public int getYear() {
//			return Year;
//		}
//
//		public void setYear(int year) {
//			Year = year;
//		}
//
//		public double getHot() {
//			return hot;
//		}
//
//		public void setHot(double hot) {
//			this.hot = hot;
//		}
//
//		public void setRelease(String release) {
//			Release = release;
//		}
//
//		public String[] getSimilar_artists() {
//			return Similar_artists;
//		}
//
//		public void setSimilar_artists(String[] similar_artists) {
//			Similar_artists = similar_artists;
//		}
//
//		public String[] getArtist_terms() {
//			return Artist_terms;
//		}
//
//		public void setArtist_terms(String[] artist_terms) {
//			Artist_terms = artist_terms;
//		}
//
//		public String[] getArtist_freq() {
//			return Artist_freq;
//		}
//
//		public void setArtist_freq(String[] artist_freq) {
//			Artist_freq = artist_freq;
//		}
//
//		public String[] getArtist_weight() {
//			return Artist_weight;
//		}
//
//		public void setArtist_weight(String[] artist_weight) {
//			Artist_weight = artist_weight;
//		}

		@Override
	    public String toString() {
	        return Song_id;
	    }
}
	