package pl.lker.tv.scheduler.database;

import pl.lker.tv.scheduler.model.Seance;

import java.util.List;

public interface Repository {

    boolean insertSeance(Seance seance);

    boolean insertSeances(List<Seance> seance);

    List<Seance> selectSeances();
}
