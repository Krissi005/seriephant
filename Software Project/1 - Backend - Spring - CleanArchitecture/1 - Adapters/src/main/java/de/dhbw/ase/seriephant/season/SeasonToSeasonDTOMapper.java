package de.dhbw.ase.seriephant.season;

import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.serie.SerieToSerieDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SeasonToSeasonDTOMapper implements Function<Season, SeasonDTO> {
    private final SerieToSerieDTOMapper serieToSerieDTOMapper;

    @Autowired
    public SeasonToSeasonDTOMapper(SerieToSerieDTOMapper serieToSerieDTOMapper) {
        this.serieToSerieDTOMapper = serieToSerieDTOMapper;
    }

    @Override
    public SeasonDTO apply(Season season) {
        return this.map(season);
    }

    private SeasonDTO map(Season season) {
        return new SeasonDTO(
                season.getId(),
                season.getSeasonNumber(),
                season.getSerie() == null ? null : this.serieToSerieDTOMapper.apply(season.getSerie())
        );
    }
}
