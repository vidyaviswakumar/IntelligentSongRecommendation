/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;

import com.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ashishdass
 */
public class ReadFile {

    /**
     * @param args the command line arguments
     * input dataset consists: user_id,song_id,playcount
     * but this dataset contains user_id and song_id in the form of String,
     *so to prepare input to mahout in the same format but with columns as integers , and also store the mapping of original ids
     */
    
    static Map<String, Integer> songsmap;
    static{
        songsmap = new HashMap<String, Integer>();
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        //pass input_to_mahout : user_id,song_id,playcount
        BufferedReader br1 = new BufferedReader(new FileReader("input_to_mahout_derived.csv"));
        Map<String, List<TrackCount>> mymap = new HashMap<String, List<TrackCount>>();
        Map<String, Integer> usersmap = new HashMap<String, Integer>();
        Map<Integer, List<TrackCount>> finalmap = new HashMap<Integer, List<TrackCount>>();
        int counterOfUsers = 1;
        int counterOfSongs = 1;
        String line = br1.readLine();
        
        //prepare hashmaps to store original song-ids and user_ids and compute new
        while (line != null) {
            //System.out.println(" "+line);
            String[] linecontents = line.split(",");

            if (songsmap.containsKey(linecontents[1])) {
            } else {
                songsmap.put(linecontents[1], counterOfSongs);
                counterOfSongs++;
            }

            if (usersmap.containsKey(linecontents[0])) {
            } else {
                usersmap.put(linecontents[0], counterOfUsers);
                counterOfUsers++;
            }

            if (mymap.get(linecontents[0]) != null) {
                List<TrackCount> local = mymap.get(linecontents[0]);
                TrackCount tc = new TrackCount(linecontents[1], Integer.parseInt(linecontents[2]));
                local.add(tc);
                mymap.put(linecontents[0], local);

            } else {
                List<TrackCount> local = new ArrayList<>();
                TrackCount tc = new TrackCount(linecontents[1], Integer.parseInt(linecontents[2]));
                local.add(tc);
                //local.add(linecontents[1]);
                mymap.put(linecontents[0], local);
            }

            line = br1.readLine();

        }

        for (String name : mymap.keySet()) {

            //String key =name.toString();
            if (usersmap.get(name) != null) {

                int usernum = usersmap.get(name);
                List<TrackCount> tempsonglist = new ArrayList();
                List<TrackCount> valuelist = mymap.get(name);
                for (int i = 0; i < valuelist.size(); i++) {
                    TrackCount s = valuelist.get(i);
                    String currvalue = songsmap.get(s.getTrackId()).toString();
                    int countnow = s.getPlayCount();
                    TrackCount newTC = new TrackCount(currvalue, countnow);
                    tempsonglist.add(newTC);
                }

                finalmap.put(usernum, tempsonglist);
                //System.out.println(name + " " + valuelist.toString());
            }
        }
        
        Map<Integer,String> reverseSongsMap=new HashMap<>();
        for (String song : songsmap.keySet()) {
            reverseSongsMap.put(songsmap.get(song), song);
        }
        
        //write output to .csv file to provide input to mahout
        String csv = "input_to_Mahout.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        for (Integer name : finalmap.keySet()) {

            List<TrackCount> value = finalmap.get(name);

            String[] currentRecord = new String[value.size() + 2];

            for (TrackCount retval : value) {
                currentRecord[0] = String.valueOf(name);
                currentRecord[1] = retval.getTrackId();
                currentRecord[2] = String.valueOf(retval.getPlayCount());
                //currentRecord[3] = String.valueOf(retval.getPlayCount());
                // System.out.println(String.valueOf(name)+","+retval);

                writer.writeNext(currentRecord);
            }
        }
        writer.close();

        //triplets with original msg_song_id
        String csv_triplet_msd = "csv_triplet_msd.csv";
        CSVWriter writer_csv_triplet_msd= new CSVWriter(new FileWriter(csv_triplet_msd));

        for (Integer name : finalmap.keySet()) {

            List<TrackCount> value = finalmap.get(name);

            String[] currentRecord = new String[4];

            for (TrackCount retval : value) {
                currentRecord[0] = String.valueOf(name);
                currentRecord[1] = retval.getTrackId();
                currentRecord[2] = String.valueOf(retval.getPlayCount());
                currentRecord[3] = String.valueOf(reverseSongsMap.get(Integer.parseInt(retval.getTrackId())));
                // System.out.println(String.valueOf(name)+","+retval);

                writer_csv_triplet_msd.writeNext(currentRecord);
            }
        }
        writer_csv_triplet_msd.close();

        
        
        
        //store reference of original song ids - Million Song Dataset mapped with our (DataSense) generated song -id
        String csv1 = "songsIdMapping.csv";
        CSVWriter writer1 = new CSVWriter(new FileWriter(csv1));

        for (String song : songsmap.keySet()) {
            //Integer value = songsmap.get(song);
            String[] currentRecord1 = new String[2];
            currentRecord1[0] = song;
            currentRecord1[1] = String.valueOf(songsmap.get(song));

            writer1.writeNext(currentRecord1);
        }

        writer1.close();

    }

    
}
