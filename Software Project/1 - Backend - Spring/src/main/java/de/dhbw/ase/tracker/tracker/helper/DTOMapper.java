package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.Genre;
import de.dhbw.ase.tracker.tracker.model.GenreDTO;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOMapper {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    SeasonRepository seasonRepository;

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
}
