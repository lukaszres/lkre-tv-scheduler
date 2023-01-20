package pl.sda.springboot.generator;


import pl.sda.springboot.model.Seance;

import java.util.List;

public interface HtmlService {
    List<Seance> downloadSeances();
}
