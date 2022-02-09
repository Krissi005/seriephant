package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.*;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.tracker.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DTOMapper {
    static GenreRepository genreRepository;
    static EpisodeRepository episodeRepository;
    static SeasonRepository seasonRepository;
    static SerieRepository serieRepository;

    @Autowired
    private GenreRepository genreRepositoryInitalize;

    @Autowired
    private EpisodeRepository episodeRepositoryInitalize;

    @Autowired
    private SeasonRepository seasonRepositoryInitalize;

    @Autowired
    private SerieRepository serieRepositoryInitalize;

    @PostConstruct
    public void init() {
        genreRepository = genreRepositoryInitalize;
        episodeRepository = episodeRepositoryInitalize;
        seasonRepository = seasonRepositoryInitalize;
        serieRepository = serieRepositoryInitalize;
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

    public static SeasonDTO convertSeasonToDTO(Season season){
        return new SeasonDTO(season.getSeasonNumber(), season.getSerie().getId());
    }

    public static Season convertDTOToSeason(SeasonDTO seasonDTO){
        return new Season(seasonDTO.getSeasonNumber(), serieRepository.getById(seasonDTO.getSerieId()));
    }

    public static void updateSeasonFromDTO(Season season, SeasonDTO seasonDTO) {
        if (seasonDTO.getSeasonNumber() != null) {
            season.setSeasonNumber(seasonDTO.getSeasonNumber());
        }
        if (seasonDTO.getSerieId() != null) {
            season.setSerie(serieRepository.getById(seasonDTO.getSerieId()));
        }
    }

    public static SerieDTO convertSerieToDTO(Serie serie){
        return new SerieDTO(serie.getTitle(), serie.getDescription(), serie.getGenre().getId());
    }

    public static Serie convertDTOToSerie(SerieDTO serieDTO){
        return new Serie(serieDTO.getTitle(), serieDTO.getDescription(), genreRepository.getById(serieDTO.getGenreId()));
    }

    public static void updateSerieFromDTO(Serie serie, SerieDTO serieDTO) {
        if (serieDTO.getTitle() != null) {
            serie.setTitle(serieDTO.getTitle());
        }
        if (serieDTO.getDescription() != null) {
            serie.setDescription(serieDTO.getDescription());
        }
        if (serieDTO.getGenreId() != null) {
            serie.setGenre(genreRepository.getById(serieDTO.getGenreId()));
        }
    }

    public static UserDTO convertUserToDTO(User user){
        return new UserDTO(user.getFirstName(), user.getLastName());
    }

    public static User convertDTOToUser(UserDTO userDTO){
        return new User(userDTO.getFirstName(), userDTO.getLastName());
    }

    public static void updateUserFromDTO(User user, UserDTO userDTO) {
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
    }
}
