/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfile;

/**
 *
 * @author teckc
 */
public class TrackCount {
    private String trackId;
    private int playCount;
    
    public TrackCount(String trackId,int playCount){
        this.trackId=trackId;
        this.playCount=playCount;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
    
    
   
    
}
