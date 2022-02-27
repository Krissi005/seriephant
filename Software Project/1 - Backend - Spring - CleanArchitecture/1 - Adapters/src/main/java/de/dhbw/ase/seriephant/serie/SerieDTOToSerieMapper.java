package de.dhbw.ase.seriephant.serie;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import de.dhbw.ase.seriephant.genre.GenreDTOToGenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SerieDTOToSerieMapper implements Function<SerieDTO, Serie> {
    private final GenreDTOToGenreMapper genreDTOToGenreMapper;

    @Autowired
    public SerieDTOToSerieMapper(GenreDTOToGenreMapper genreDTOToGenreMapper) {
        this.genreDTOToGenreMapper = genreDTOToGenreMapper;
    }

    @Override
    public Serie apply(SerieDTO serieDTO) {
        return this.map(serieDTO);
    }

    private Serie map(SerieDTO serieDTO) {
        if (serieDTO == null) {
            return null;
        }
        return new Serie(
                serieDTO.getId(),
                serieDTO.getTitle(),
                serieDTO.getDescription(),
                serieDTO.getReleaseYear(),
                this.genreDTOToGenreMapper.apply(serieDTO.getGenreDTO())
        );
    }
}
