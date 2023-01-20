package pl.lker.tv.scheduler.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.lker.tv.scheduler.generator.HtmlService;
import pl.lker.tv.scheduler.generator.service.HtmlServiceImpl;
import pl.lker.tv.scheduler.model.Seance;
import pl.lker.tv.scheduler.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
public class ServiceController {

    @GetMapping("/service")
    public User serv() {
        return new User("firstname", "lastname");
    }

    @GetMapping("/seances")
    public ResponseEntity<List<Seance>> seances() {
        HtmlService htmlService = new HtmlServiceImpl();
        List<Seance> seances = null;
//        seances = htmlService.downloadSeances();
        seances = getSeances();
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }

    private List<Seance> getSeances() {
        List<Seance> seances = new LinkedList<>();
        seances.add(new Seance("title1", new Date(), "genre1", "episode", "channel1"));
        seances.add(new Seance("title2", new Date(), "genre2", "episode", "channel1"));
        seances.add(new Seance("title3", new Date(), "genre", "episode", "channel1"));
        seances.add(new Seance("title4", new Date(), "genre1", "episode", "channel1"));
        seances.add(new Seance("title5", new Date(), "genre", "episode", "channel1"));
        return seances;
    }

}
