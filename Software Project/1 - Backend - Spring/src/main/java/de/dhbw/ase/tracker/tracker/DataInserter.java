package de.dhbw.ase.tracker.tracker;

import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Genre;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Genre genre = new Genre("Horror", "Löst Gefühle der Angst, des Schreckens und Verstörung aus.");
        genreRepository.save(genre);
        genre = new Genre("SciFi", "Meist geht es um Zeitreisen, Reisen zu anderen Planeten oder um neuartige Erfindungen");
        genreRepository.save(genre);
        genre = new Genre("Thriller", "Es wird eine Spannung erzeugt, die während des gesamten Handlungsverlaufs präsent ist.");
        genreRepository.save(genre);

        Season season = new Season(5);
        seasonRepository.save(season);

        Episode episode1 = new Episode("Rückkehr nach Smallville", 1, season);
        episodeRepository.save(episode1);
        Episode episode2 = new Episode("Seltsames Verschwinden", 2, season);
        episodeRepository.save(episode2);
        List<Episode> episodes = new ArrayList<>();
        episodes.add(episode1);
        episodes.add(episode2);
    }
}
