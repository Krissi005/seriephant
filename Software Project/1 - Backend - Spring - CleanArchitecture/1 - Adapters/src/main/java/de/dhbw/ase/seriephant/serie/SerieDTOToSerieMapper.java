package de.dhbw.ase.seriephant.serie;

import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import de.dhbw.ase.seriephant.domain.serie.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SerieDTOToSerieMapper implements Function<SerieDTO, Serie> {
    private final GenreRepository genreRepository;

    @Autowired
    public SerieDTOToSerieMapper(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Serie apply(SerieDTO serieDTO) {
        return this.map(serieDTO);
    }

    private Serie map(SerieDTO serieDTO) {
        return new Serie(
                serieDTO.getId(),
                serieDTO.getTitle(),
                serieDTO.getDescription(),
                serieDTO.getReleaseYear(),
                this.genreRepository.getById(serieDTO.getGenreDTO().getId())
        );
    }
}
