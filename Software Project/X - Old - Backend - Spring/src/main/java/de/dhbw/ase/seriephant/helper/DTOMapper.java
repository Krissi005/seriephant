package de.dhbw.ase.seriephant.helper;

import de.dhbw.ase.seriephant.model.*;
import de.dhbw.ase.seriephant.repository.EpisodeRepository;
import de.dhbw.ase.seriephant.repository.GenreRepository;
import de.dhbw.ase.seriephant.repository.SerieRepository;
import de.dhbw.ase.seriephant.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;

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
        return new EpisodeDTO(episode.getTitle(), episode.getReleaseDate(), episode.getEpisodeNumber(), episode.getSeason().getId());
    }

    public static Episode convertDTOToEpisode(EpisodeDTO episodeDTO) throws ParseException {
        return new Episode(episodeDTO.getTitle(), episodeDTO.getReleaseDate(), episodeDTO.getEpisodeNumber(), seasonRepository.getById(episodeDTO.getSeasonId()));
    }

    public static void updateEpisodeFromDTO(Episode episode, EpisodeDTO episodeDTO) {
        if (episodeDTO.getTitle() != null) {
            episode.setTitle(episodeDTO.getTitle());
        }
        if (episodeDTO.getReleaseDate() != null) {
            episode.setReleaseDate(episodeDTO.getReleaseDate());
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
        return new SerieDTO(serie.getTitle(), serie.getDescription(), serie.getReleaseYear(), serie.getGenre().getId());
    }

    public static Serie convertDTOToSerie(SerieDTO serieDTO){
        return new Serie(serieDTO.getTitle(), serieDTO.getDescription(), serieDTO.getReleaseYear(), genreRepository.getById(serieDTO.getGenreId()));
    }

    public static void updateSerieFromDTO(Serie serie, SerieDTO serieDTO) {
        if (serieDTO.getTitle() != null) {
            serie.setTitle(serieDTO.getTitle());
        }
        if (serieDTO.getDescription() != null) {
            serie.setDescription(serieDTO.getDescription());
        }
        if (serieDTO.getReleaseYear() != null) {
            serie.setReleaseYear(serieDTO.getReleaseYear());
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

    public static ActorDTO convertActorToDTO(Actor actor){
        return new ActorDTO(actor.getFirstName(), actor.getLastName());
    }

    public static Actor convertDTOToActor(ActorDTO actorDTO){
        return new Actor(actorDTO.getFirstName(), actorDTO.getLastName());
    }

    public static void updateActorFromDTO(Actor actor, ActorDTO actorDTO) {
        if (actorDTO.getFirstName() != null) {
            actor.setFirstName(actorDTO.getFirstName());
        }
        if (actorDTO.getLastName() != null) {
            actor.setLastName(actorDTO.getLastName());
        }
    }
}
