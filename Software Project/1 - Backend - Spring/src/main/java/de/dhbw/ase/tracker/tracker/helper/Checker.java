package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.*;
import de.dhbw.ase.tracker.tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;

@Component
public class Checker {
    static ActorRepository actorRepository;
    static EpisodeRepository episodeRepository;
    static GenreRepository genreRepository;
    static SeasonRepository seasonRepository;
    static SerieRepository serieRepository;
    static UserRepository userRepository;

    @Autowired
    private ActorRepository actorRepositoryInitialize;

    @Autowired
    private EpisodeRepository episodeRepositoryInitalize;

    @Autowired
    private GenreRepository genreRepositoryInitalize;

    @Autowired
    private SeasonRepository seasonRepositoryInitalize;

    @Autowired
    private SerieRepository serieRepositoryInitalize;

    @Autowired
    private UserRepository userRepositoryInitalize;

    @PostConstruct
    public void init() {
        Checker.actorRepository = actorRepositoryInitialize;
        Checker.episodeRepository = episodeRepositoryInitalize;
        Checker.genreRepository = genreRepositoryInitalize;
        Checker.seasonRepository = seasonRepositoryInitalize;
        Checker.serieRepository = serieRepositoryInitalize;
        Checker.userRepository = userRepositoryInitalize;
    }

    public static Episode getEpisodeById(Long epsiodeId) throws ValidationException {
        if (epsiodeId != null && episodeRepository.existsById(epsiodeId)) {
            return episodeRepository.getById(epsiodeId);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Genre getGenreById(Long genreId) throws ValidationException {
        if (genreId != null && genreRepository.existsById(genreId)) {
            return genreRepository.getById(genreId);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Season getSeasonById(Long seasonId) throws ValidationException {
        if (seasonId != null && seasonRepository.existsById(seasonId)) {
            return seasonRepository.getById(seasonId);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Serie getSerieById(Long serieId) throws ValidationException {
        if (serieId != null && serieRepository.existsById(serieId)) {
            return serieRepository.getById(serieId);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static User getUserById(Long userId) throws ValidationException {
        if (userId != null && userRepository.existsById(userId)) {
            return userRepository.getById(userId);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Actor getActorById(Long actorId) throws ValidationException {
        if (actorId != null && actorRepository.existsById(actorId)) {
            return actorRepository.getById(actorId);
        }
        throw new ValidationException("SeasonId is not known.");
    }
}
