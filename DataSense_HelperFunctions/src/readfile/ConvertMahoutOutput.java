/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author teckc
 */
public class ConvertMahoutOutput {


    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        JSONArray rootList = new JSONArray();

        BufferedReader readMahdout_output = new BufferedReader(new FileReader("mahout_output.csv"));
        BufferedReader readInputToMahout = new BufferedReader(new FileReader("input_to_Mahout.csv"));
       // Map<String, Integer> songsmap = ReadFile.songsmap;
       BufferedReader readSongIdMapping = new BufferedReader(new FileReader("songsIdMapping.csv"));

        Map<Integer, String> songsmap = new HashMap<Integer, String>();
        String readSongsMapLine = readSongIdMapping.readLine();

        while (readSongsMapLine != null) {
            String[] fullLine = readSongsMapLine.split(",");
            songsmap.put(Integer.parseInt(fullLine[1]), (fullLine[0]));
            readSongsMapLine = readSongIdMapping.readLine();

        }

        System.out.println("songsmap loaded");
        HashMap<Integer, Integer> totCountMap = new HashMap<>();

        String line2 = readInputToMahout.readLine();
        while (line2 != null) {

            String[] fullLine = line2.split(",");
            int songKey = Integer.parseInt(fullLine[1]);
            int songCount = Integer.parseInt(fullLine[2]);

            if (totCountMap.containsKey(songKey)) {
                totCountMap.put(songKey, totCountMap.get(songKey) + songCount);
            } else {
                totCountMap.put(songKey, songCount);

            }

            line2 = readInputToMahout.readLine();
        }

        String line = readMahdout_output.readLine();

        while (line != null) {
            JSONObject objLine = new JSONObject();
            String[] linecontents = line.split(",");
            int userId = Integer.parseInt(linecontents[0]);
            objLine.put("userId", userId);
            //for(int j=1;j<linecontents.length;j++){
            //String[] recos = linecontents[j].split(",");
            JSONArray recoList = new JSONArray();
            for (int i = 1; i < linecontents.length; i++) {
                String songId;
                double scoreOfreco;
                int tempCount = 0;
                JSONObject eachReco = new JSONObject();
                String[] scoreSong = linecontents[i].split(":");
                songId = scoreSong[0].replaceAll("[^0-9]", "").trim();                
                System.out.println("songid " +songId);
                tempCount = totCountMap.get(Integer.parseInt(songId));               
                scoreOfreco = Double.parseDouble(scoreSong[1].replaceAll("[^0-9]", "").trim());
                
                eachReco.put("msd_song_id", songsmap.get(Integer.parseInt(songId)));
                eachReco.put("ds_song_id", (songId));
                eachReco.put("score", scoreOfreco);
                eachReco.put("totalCountOfSong", tempCount);
                recoList.add(eachReco);
            }

            objLine.put("recos", recoList);
            rootList.add(objLine);

            line = readMahdout_output.readLine();
        }

        //System.out.println("fileconversion.FileConversion.main()" +rootList);
        try (FileWriter filetowrite = new FileWriter("mahout_reco_output.json")) {

            filetowrite.write(rootList.toJSONString());
            filetowrite.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
