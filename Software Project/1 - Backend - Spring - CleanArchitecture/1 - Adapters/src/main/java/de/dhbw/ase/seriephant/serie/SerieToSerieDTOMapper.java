package de.dhbw.ase.seriephant.serie;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import de.dhbw.ase.seriephant.genre.GenreToGenreDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SerieToSerieDTOMapper implements Function<Serie, SerieDTO> {
    private final GenreToGenreDTOMapper genreToGenreDTOMapper;

    @Autowired
    public SerieToSerieDTOMapper(GenreToGenreDTOMapper genreToGenreDTOMapper) {
        this.genreToGenreDTOMapper = genreToGenreDTOMapper;
    }

    @Override
    public SerieDTO apply(Serie serie) {
        return this.map(serie);
    }

    private SerieDTO map(Serie serie) {
        return new SerieDTO(
                serie.getId(),
                serie.getTitle(),
                serie.getDescription(),
                serie.getReleaseYear(),
                serie.getGenre() == null ? null : this.genreToGenreDTOMapper.apply(serie.getGenre())
        );
    }
}
