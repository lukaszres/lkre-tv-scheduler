package pl.lker.tv.scheduler.generator.service.genre;

import pl.lker.tv.scheduler.model.Seance;

import java.util.List;

public interface GenreService {
    List<String> createGenres(List<Seance> seances);
}
