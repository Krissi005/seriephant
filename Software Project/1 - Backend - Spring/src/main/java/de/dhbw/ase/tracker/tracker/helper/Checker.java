package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.*;
import de.dhbw.ase.tracker.tracker.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;

@Component
public class Checker {
    static EpisodeRepository episodeRepository;
    static GenreRepository genreRepository;
    static SeasonRepository seasonRepository;
    static SerieRepository serieRepository;
    static UserRepository userRepository;

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
        Checker.episodeRepository = episodeRepositoryInitalize;
        Checker.genreRepository = genreRepositoryInitalize;
        Checker.seasonRepository = seasonRepositoryInitalize;
        Checker.serieRepository = serieRepositoryInitalize;
        Checker.userRepository = userRepositoryInitalize;
    }

    public static void deleteSeenEpisodesOfUser(Long episodeId) {
        Episode episode = episodeRepository.getById(episodeId);
        for (User user : episode.getUsers()) {
            user.notSeeEpsiode(episode);
        }
    }

    public static Episode getEpisodeById(Long id) throws ValidationException {
        if (id != null && episodeRepository.existsById(id)) {
            return episodeRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Genre getGenreById(Long id) throws ValidationException {
        if (id != null && genreRepository.existsById(id)) {
            return genreRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Season getSeasonById(Long id) throws ValidationException {
        if (id != null && seasonRepository.existsById(id)) {
            return seasonRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Serie getSerieById(Long id) throws ValidationException {
        if (id != null && serieRepository.existsById(id)) {
            return serieRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }
}
