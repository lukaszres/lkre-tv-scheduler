package pl.lker.tv.scheduler.model;

import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Value
public class Seance {

    String title;
    Date time;
    String genre;
    String episode;
    String channel;

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(!(o instanceof Seance)){
            return false;
        }
        Seance s = (Seance) o;
        return (toString().equals(o.toString()));
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        String episodeString = episode == null ? "" : " :: " + episode;
        return df.format(time) + " :: " + channel + " :: " + genre + " :: " + title + episodeString;
    }
}

