package pl.lker.tv.scheduler.model;

import lombok.Value;

import java.util.List;

@Value
public class Channel {

    List<Seance> seances;
    List<String> genres;

}

