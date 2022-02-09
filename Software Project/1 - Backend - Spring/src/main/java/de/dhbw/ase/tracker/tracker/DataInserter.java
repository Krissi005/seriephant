package de.dhbw.ase.tracker.tracker;

import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Genre;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.Serie;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.tracker.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DataInserter implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    SerieRepository serieRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Genre genre = new Genre("Horror", "Löst Gefühle der Angst, des Schreckens und Verstörung aus.");
        genreRepository.save(genre);
        genre = new Genre("SciFi", "Meist geht es um Zeitreisen, Reisen zu anderen Planeten oder um neuartige Erfindungen");
        genreRepository.save(genre);
        genre = new Genre("Thriller", "Es wird eine Spannung erzeugt, die während des gesamten Handlungsverlaufs präsent ist.");
        genreRepository.save(genre);

        Serie serie = new Serie("Superman & Lois", "Superman & Lois ist eine US-amerikanische Science-Fiction-Fernsehserie, die auf den gleichnamigen Figuren, Superman und Lois Lane, aus den DC Comics basiert.");
        serieRepository.save(serie);

        Season season2 = new Season(2, serie);
        seasonRepository.save(season2);

        Season season1 = new Season(1, serie);
        seasonRepository.save(season1);

        Episode episode1 = new Episode("Rückkehr nach Smallville", 1, season1);
        episodeRepository.save(episode1);
        Episode episode2 = new Episode("Seltsames Verschwinden", 3, season1);
        episodeRepository.save(episode2);
        Episode episode3 = new Episode("Erbe", 2, season2);
        episodeRepository.save(episode3);
    }
}
