package com.ds.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.ds.pojo.SongRecommendation;
import com.ds.repository.RecommendationRepository;
import com.ds.repository.SongsRepository;
import com.ds.repository.TrendingSongsRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;

@Controller
public class HomeController {

	@Autowired
	private SongsRepository songsRepository;

	@Autowired
	private RecommendationRepository recommendationRepository;

	@Autowired
	private TrendingSongsRepository trendRespository;
	
	@Autowired
	private SongsRepository LyricsMoodRepository;

	@Autowired
	private MongoTemplate mongoTemplate;


	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String getDashboard() {

		return "dashboard";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHomePage() {

		return "index";
	}
	
	//Get recommendation based on user mood input, mahout recommendations and latest trend
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public ModelAndView getSongRecommendations(HttpServletRequest request) {

		String mood=request.getParameter("mood");
		String userId=request.getParameter("userId");
		//calculate mood of a user from his/her text
		String estimatedMood=analyzeTone(mood);
		
		//Calculate and find the best recommendation for a user based on user Input,latest trends and mahout output
		ModelAndView mv=new ModelAndView();
		mv=getCummulativeRecommendedSongs(userId, estimatedMood,mv);
		mv.addObject("mood",estimatedMood);
		//System.out.println(songRecommendationList.get(1).getSongId());
		
		mv.setViewName("home");
		return mv;
	}

	public ModelAndView getCummulativeRecommendedSongs(String userId, String estimatedMood,ModelAndView mv){
		
		List<Songs> trendingRecommendedSongs = new ArrayList<Songs>();
		List<Songs> recommendedSongsList=new ArrayList<Songs>();
		List<Songs> bestRecommendedSongs = new ArrayList<Songs>();
		
		
		
		try{
			
			MongoOperations mongoOperation = (MongoOperations) mongoTemplate;
			System.out.println("operation " +mongoOperation);

			Query query2 = new Query();

			query2.addCriteria(Criteria.where("userId").is(Integer.parseInt(userId)));
			Recommendation recommendation = mongoOperation.findOne(query2, Recommendation.class, "mahout_reco_output");
			
			//mahout recommended songs
			List<SongRecommendation> songRecommendationList = recommendation.getSongRecommendation();
			
			System.out.println("RECOMMENDATION SIZE"+songRecommendationList.size());
			recommendedSongsList=new ArrayList<Songs>();
			

			for(SongRecommendation eachSong : songRecommendationList){

				System.out.println("Title "+eachSong.getDs_song_id());
				//msd_song_idList.add(eachSong.getMsd_song_id());

				Query songQuery = new Query();
				songQuery.addCriteria(Criteria.where("Song_id").is(eachSong.getMsd_song_id()));
				Songs song = mongoOperation.findOne(songQuery, Songs.class, "songs_dataset");
				song.setSong_id(eachSong.getMsd_song_id());
												
				if(song!=null) {

					recommendedSongsList.add(song);
					

				}
						
			}
			
			//list of trending songs
			List<TrendingSongs> trendingSongs = mongoOperation.findAll(TrendingSongs.class,"trending_songs_filtered_year");
				
			//Trending Mahout recommended songs
			List<String> trendingRecommendedSongIds = new ArrayList();
			
			//onle get top 20 trending songs
			
            for(SongRecommendation s :songRecommendationList){
                String id = s.getMsd_song_id();
                for(TrendingSongs ts : trendingSongs){
                    
                	if(ts.get_id().equalsIgnoreCase(id)){
                    	
                    	trendingRecommendedSongIds.add(ts.get_id());
                    	Query songQuery = new Query();
                    	songQuery.addCriteria(Criteria.where("Song_id").is(ts.get_id()));
        				Songs song = mongoOperation.findOne(songQuery, Songs.class, "songs_dataset");
                        System.out.println("Common of MAHOUT and TRENDING IS " +id);
                        trendingRecommendedSongs.add(song);
                        
                        
                    }
                    
                }
            }
            
            for(String a: trendingRecommendedSongIds){
            	
            	for(Songs s: recommendedSongsList){
            		
            		if(a.equalsIgnoreCase(s.getSong_id())){
            			            			
            			String currTrackId = s.getTrack_id();
            			System.out.println("I got the TRACK ID as " +currTrackId + " for the SONG ID " +s.getSong_id());
            			
            			Query queryForLyrics = new Query();

                        queryForLyrics.addCriteria(Criteria.where("track_id").is(currTrackId));
            			Lyrics_Mood lyricsMood = mongoOperation.findOne(queryForLyrics, Lyrics_Mood.class, "lyrics_info");
            			if(lyricsMood!= null && estimatedMood==lyricsMood.getMood()){
            				System.out.println("Best Recommended song id"+s.getSong_id());
            					bestRecommendedSongs.add(s);
            					
            				
            				System.out.println("FINAL COMMON SONG IS " +s.getSong_id() + " for MOOD " +lyricsMood.getMood());
            				
            			}
            		}
            	}
            }
            int bestRecoSize=0;
            if(bestRecommendedSongs!=null)
            	bestRecoSize=bestRecommendedSongs.size();
            
            int count=0;
                     if(bestRecoSize<4){
                    	 for(int i=bestRecoSize;i<5;i++){
                    		 bestRecommendedSongs.add(recommendedSongsList.get(count));
                    		 count++;
                    	 }
                     }
                     int trendRecoSize=0;
                     if(trendingRecommendedSongs!=null){
                    	 trendRecoSize=trendingRecommendedSongs.size();
                     }
                     
                     if(trendRecoSize<4){
                    	 for(int i=trendRecoSize;i<5;i++){
                    		 trendingRecommendedSongs.add(recommendedSongsList.get(count));
                    		 count++;
                    	 }
                     }
                     ArrayList<Songs> bestRecoSongs=new ArrayList<Songs>();
                     ArrayList<Songs> trendRecoSongs=new ArrayList<Songs>();
                     ArrayList<Songs> recoSongs=new ArrayList<Songs>();
                     for(int i=0;i<4;i++){
                    	 bestRecoSongs.add(bestRecommendedSongs.get(i));
                    	 trendRecoSongs.add(trendingRecommendedSongs.get(i));
                    	 recoSongs.add(recommendedSongsList.get(i));
                     }


                       mv.addObject("recommendedSongsList",recoSongs);
                       mv.addObject("trendingRecommendedSongs",trendRecoSongs);
                       mv.addObject("bestRecommendedSongs",bestRecoSongs); 
                       
           
           
           
            return mv;
            
            
		}catch(Exception ex){
			System.out.println("Error");
		}
		return null;
	}


	public static String callURL(String myURL) {
		System.out.println("Requested URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {

				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ myURL, e);
		}

		return sb.toString();
	}
	
	@RequestMapping(value = "/getUrl", method = RequestMethod.GET)
	public ModelAndView getSongUrl() {

		int releaseId=1625521;

		//weather api
		//String url = "http://api.apixu.com/v1/current.json?key=63fed499542f48d5877161640171904&q=Miami";
		String jsonUrl="http://api.7digital.com/1.2/release/details?releaseid=1625521&country=ww&oauth_consumer_key=7d6cj2x5qy75";

		String jsonUrlString=callURL(jsonUrl);



		String jsonString = callURL(jsonUrl);
		System.out.println("\n\njsonString: " + jsonString);

		// Replace this try catch block for all below subsequent examples
		//try {  
		//JSONArray jsonArray = new JSONArray(jsonString);
		//System.out.println("\n\nWeather: " + jsonArray);
		//} catch (JSONException e) {
		//  e.printStackTrace();
		//}



		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.setViewName("home");
		return modelAndView;
	}



	public String analyzeTone(String mood){
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);

		service.setUsernameAndPassword("1a544dc1-f215-4e5d-be91-c2472da6a454", "lkzxQh6wKbXn");

		ToneAnalysis tone=service.getTone(mood, null).execute();
		String finalMoodEstimate="";

		JsonParser jsonParser = new JsonParser();

		JsonObject tonej = (JsonObject) jsonParser.parse(tone.toString());
		JsonObject doc_tone = (JsonObject) tonej.get("document_tone");
		JsonArray tone_categories = (JsonArray) doc_tone.get("tone_categories");
		JsonObject emotionTone=(JsonObject)tone_categories.get(0);
		JsonArray tones = (JsonArray) emotionTone.get("tones");
		Map<String,Double> scoreTones=new HashMap<String,Double>();
		double sum=0.0;
		for (int i = 0; i < tones.size(); i++) {
			JsonObject toneObj = (JsonObject) tones.get(i);
			JsonElement tone_name = toneObj.get("tone_name");
			JsonElement score = toneObj.get("score");
			scoreTones.put(tone_name.toString(),score.getAsDouble());           
			sum+=score.getAsDouble();
		}
		double avg=sum/tones.size();
		System.out.println("avg"+avg);
		Iterator it = scoreTones.entrySet().iterator();

		double finalScore=0.0;
		it = scoreTones.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			double newValue=(Double)pair.getValue()/avg;
			scoreTones.put((String) pair.getKey(),newValue );
			if(newValue>finalScore)
				finalScore=newValue;
		}
		it = scoreTones.entrySet().iterator();

		while(it.hasNext()){
			Map.Entry pair = (Map.Entry)it.next();
			if(finalScore==(Double)pair.getValue()){
				System.out.println("Tone"+pair.getKey()+"score:"+finalScore);
				finalMoodEstimate=String.valueOf(pair.getKey());
				break;
			}
		}

		return finalMoodEstimate;

	}

}