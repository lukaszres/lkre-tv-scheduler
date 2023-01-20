package pl.lker.tv.scheduler.generator;


import pl.lker.tv.scheduler.model.Seance;

import java.util.List;

public interface HtmlService {
    List<Seance> downloadSeances();
}
