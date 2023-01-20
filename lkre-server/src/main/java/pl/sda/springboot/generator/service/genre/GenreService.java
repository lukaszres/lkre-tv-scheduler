package pl.sda.springboot.generator.service.genre;

import pl.sda.springboot.model.Seance;

import java.util.List;

public interface GenreService {
    List<String> createGenres(List<Seance> seances);
}
