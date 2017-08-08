package com.ds.webapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.lyrics.Lyrics;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.opencsv.CSVWriter;

public class lyricsMood {

	public static void main(String[] args) throws FileNotFoundException, IOException, NumberFormatException, MusixMatchException{
		BufferedReader br = new BufferedReader(new FileReader("D:\\NEU_Term4\\BigData\\Final_Project\\MSDTrackID_MMID.csv"));
		String line = br.readLine();


		String csv1 = "lyricsMood.csv";
		CSVWriter writer = new CSVWriter(new FileWriter(csv1));

		while (line != null) {
			System.out.println(" New Line"+line);
			String[] linecontents = line.split(",");
			String track_id=linecontents[0];
			String mmid=linecontents[1];
			//int mmid=9239868;
			//mm
			String mmApiKey = "69acc0e1db9665b7a345a4d23a0e1da5";
			MusixMatch musixMatch = new MusixMatch(mmApiKey);

			// tone analyzer
			ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
			service.setUsernameAndPassword("1a544dc1-f215-4e5d-be91-c2472da6a454", "lkzxQh6wKbXn");

			System.out.println("MM"+mmid);
			Lyrics lyrics=null;
			try{
				lyrics = musixMatch.getLyrics(Integer.parseInt(mmid));

				//System.out.println("try");
				System.out.println("track id" +mmid);
				System.out.println("Lyrics ID       : "     + lyrics.getLyricsId());
				System.out.println("Lyrics Language : "     + lyrics.getLyricsLang());
				System.out.println("Lyrics Body     : "     + lyrics.getLyricsBody());

				//get lyrics tone


				System.out.println("tone of lyrics");
				String lyricsText=lyrics.getLyricsBody();    		
				String replacedLyrics = lyricsText.replace('\n', ' ');
				ToneAnalysis tone = service.getTone(replacedLyrics.trim(), null).execute();    		   		
				String toneString=tone.toString();

				//get final score of lyrics
				JsonParser jsonParser = new JsonParser();

				JsonObject tonej = (JsonObject) jsonParser.parse(toneString);
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
				String finalMood=null;
				while(it.hasNext()){
					Map.Entry pair = (Map.Entry)it.next();
					if(finalScore==(Double)pair.getValue()){
						System.out.println("Tone"+pair.getKey()+"score:"+finalScore);
						finalMood=String.valueOf(pair.getKey());
						break;
					}
				}

				String[] currentRecord = new String[3];
				currentRecord[0] = track_id;
				currentRecord[1]=finalMood;
				currentRecord[2] = String.valueOf(finalScore);


				writer.writeNext(currentRecord);
				
			}catch(Exception e){
				System.out.println("Error:"+mmid);
			}
			line = br.readLine();
			

		}
		writer.close();
	}

}
