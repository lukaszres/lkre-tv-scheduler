package pl.lker.tv.scheduler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lker.tv.scheduler.database.Repository;
import pl.lker.tv.scheduler.database.RepositoryImpl;
import pl.lker.tv.scheduler.generator.HtmlService;
import pl.lker.tv.scheduler.generator.service.HtmlServiceImpl;
import pl.lker.tv.scheduler.model.Seance;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ServiceController {
    public static final long HOUR = 3600 * 1000; // in milli-seconds.
    private Repository repository = new RepositoryImpl();

    @GetMapping("/seances")
    public ResponseEntity<List<Seance>> seances() {
        HtmlService htmlService = new HtmlServiceImpl();
        List<Seance> seances = null;
//        seances = htmlService.downloadSeances();
        seances = getSeances();
        repository.insertSeance(seances.get(0));
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }

    @GetMapping("/database")
    public ResponseEntity<List<Seance>> databaseSeances() {
        final List<Seance> seances = repository.selectSeances();
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }

    private List<Seance> getSeances() {
        List<Seance> seances = new LinkedList<>();
        seances.add(new Seance("title2", new Date(new Date().getTime() + 2 * HOUR), "genre2", "episode", "channel1"));
        seances.add(new Seance("title1", new Date(new Date().getTime() + 1 * HOUR), "genre1", "episode", "channel1"));
        seances.add(new Seance("title4", new Date(new Date().getTime() + 4 * HOUR), "genre1", "episode", "channel1"));
        seances.add(new Seance("title3", new Date(new Date().getTime() + 3 * HOUR), "genre", "episode", "channel1"));
        seances.add(new Seance("title5", new Date(new Date().getTime() + 1 * HOUR), "genre", "episode", "channel1"));
        return seances;
    }

}
