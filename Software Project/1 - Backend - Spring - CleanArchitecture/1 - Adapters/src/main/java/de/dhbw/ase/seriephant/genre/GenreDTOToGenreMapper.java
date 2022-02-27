package de.dhbw.ase.seriephant.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GenreDTOToGenreMapper implements Function<GenreDTO, Genre> {
    @Override
    public Genre apply(GenreDTO genreDTO) {
        return this.map(genreDTO);
    }

    private Genre map(GenreDTO genreDTO) {
        return new Genre(
                genreDTO.getId(),
                genreDTO.getTitle(),
                genreDTO.getDescription());
    }
}
