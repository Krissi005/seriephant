package de.dhbw.ase.seriephant;

import de.dhbw.ase.seriephant.model.*;
import de.dhbw.ase.seriephant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.*;

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

    @Autowired
    UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Genre genre1 = new Genre("Horror", "Löst Gefühle der Angst, des Schreckens und Verstörung aus.");
        genreRepository.save(genre1);
        Genre genre2 = new Genre("SciFi", "Meist geht es um Zeitreisen, Reisen zu anderen Planeten oder um neuartige Erfindungen");
        genreRepository.save(genre2);
        Genre genre3 = new Genre("Thriller", "Es wird eine Spannung erzeugt, die während des gesamten Handlungsverlaufs präsent ist.");
        genreRepository.save(genre3);

        Serie serie1 = new Serie("Supergirl", "Supergirl ist eine US-amerikanische Science-Fiction-Fernsehserie, die auf der gleichnamigen Figur von DC Comics basiert.");
        serieRepository.save(serie1);

        Serie serie = new Serie("Superman & Lois", "Superman & Lois ist eine US-amerikanische Science-Fiction-Fernsehserie, die auf den gleichnamigen Figuren, Superman und Lois Lane, aus den DC Comics basiert.", 2021, genre2);
        serieRepository.save(serie);

        Season season2 = new Season(2, serie);
        seasonRepository.save(season2);

        Season season1 = new Season(1, serie);
        seasonRepository.save(season1);
        Episode episode1 = new Episode("Rückkehr nach Smallville", new GregorianCalendar(2021,Calendar.FEBRUARY, 23).getTime(), 1, season1);
        episodeRepository.save(episode1);
        Episode episode2 = new Episode("Seltsames Verschwinden", new GregorianCalendar(2021,Calendar.MARCH, 9).getTime(), 3, season1);
        episodeRepository.save(episode2);
        Episode episode3 = new Episode("Erbe", new GregorianCalendar(2021,Calendar.MARCH, 2).getTime(), 2, season2);
        episodeRepository.save(episode3);

        User user = new User("Pascal", "E");
        userRepository.save(user);
        User user1 = new User("Krissi", "A");
        userRepository.save(user1);

        user.watchEpisode(episode1);
        user.watchEpisode(episode2);
        userRepository.save(user);

    }
}
