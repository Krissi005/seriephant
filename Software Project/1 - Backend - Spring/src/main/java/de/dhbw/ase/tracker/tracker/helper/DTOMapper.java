package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.*;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DTOMapper {
    static GenreRepository genreRepository;
    static EpisodeRepository episodeRepository;
    static SeasonRepository seasonRepository;

    @Autowired
    private GenreRepository genreRepositoryInitalize;

    @Autowired
    private EpisodeRepository episodeRepositoryInitalize;

    @Autowired
    private SeasonRepository seasonRepositoryInitalize;

    @PostConstruct
    public void init() {
        DTOMapper.genreRepository = genreRepositoryInitalize;
        DTOMapper.episodeRepository = episodeRepositoryInitalize;
        DTOMapper.seasonRepository = seasonRepositoryInitalize;
    }

    public static GenreDTO convertGenreToDTO(Genre genre){
        return new GenreDTO(genre.getTitle(), genre.getDescription());
    }

    public static Genre convertDTOToGenre(GenreDTO genreDTO){
        return new Genre(genreDTO.getTitle(), genreDTO.getDescription());
    }

    public static void updateGenreFromDTO(Genre genre, GenreDTO genreDTO) {
        if (genreDTO.getTitle() != null) {
            genre.setTitle(genreDTO.getTitle());
        }
        if (genreDTO.getDescription() != null) {
            genre.setDescription(genreDTO.getDescription());
        }
    }

    public static EpisodeDTO convertEpisodeToDTO(Episode episode){
        return new EpisodeDTO(episode.getTitle(), episode.getEpisodeNumber(), episode.getSeason().getId());
    }

    public static Episode convertDTOToEpisode(EpisodeDTO episodeDTO){
        return new Episode(episodeDTO.getTitle(), episodeDTO.getEpisodeNumber(), seasonRepository.getById(episodeDTO.getSeasonId()));
    }

    public static void updateEpisodeFromDTO(Episode episode, EpisodeDTO episodeDTO) {
        if (episodeDTO.getTitle() != null) {
            episode.setTitle(episodeDTO.getTitle());
        }
        if (episodeDTO.getEpisodeNumber() != null) {
            episode.setEpisodeNumber(episodeDTO.getEpisodeNumber());
        }
        if (episodeDTO.getSeasonId() != null && seasonRepository.existsById(episodeDTO.getSeasonId())) {
            episode.setSeason(seasonRepository.getById(episodeDTO.getSeasonId()));
        }
    }

    public static SeasonDTO convertEpisodeToDTO(Season season){
        return new SeasonDTO(season.getSeasonNumber(), season.getEpisodes());
    }

    public static Season convertDTOToSeason(SeasonDTO seasonDTO){
        return new Season(seasonDTO.getSeasonNumber());
    }

    public static void updateSeasonFromDTO(Season season, SeasonDTO seasonDTO) {
        if (seasonDTO.getSeasonNumber() != null) {
            season.setSeasonNumber(seasonDTO.getSeasonNumber());
        }
        if (seasonDTO.getEpisodes() != null) {
            seasonDTO.setEpisodes(seasonDTO.getEpisodes());
        }
    }
}
