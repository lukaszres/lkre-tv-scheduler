package pl.sda.springboot.model;

import lombok.Value;

import java.util.List;

@Value
public class Channel {

    List<Seance> seances;
    List<String> genres;

}

