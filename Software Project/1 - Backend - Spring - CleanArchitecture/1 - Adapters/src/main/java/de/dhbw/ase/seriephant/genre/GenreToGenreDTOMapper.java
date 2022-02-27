package de.dhbw.ase.seriephant.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GenreToGenreDTOMapper implements Function<Genre, GenreDTO> {
    @Override
    public GenreDTO apply(Genre genre) {
        return this.map(genre);
    }

    private GenreDTO map(Genre genre) {
        return new GenreDTO(
                genre.getId(),
                genre.getTitle(),
                genre.getDescription()
        );
    }
}
