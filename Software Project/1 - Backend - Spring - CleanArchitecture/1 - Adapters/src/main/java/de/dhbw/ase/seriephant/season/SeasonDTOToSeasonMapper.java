package de.dhbw.ase.seriephant.season;

import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.serie.SerieDTOToSerieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SeasonDTOToSeasonMapper implements Function<SeasonDTO, Season> {
    private final SerieDTOToSerieMapper serieDTOToSerieMapper;

    @Autowired
    public SeasonDTOToSeasonMapper(SerieDTOToSerieMapper serieDTOToSerieMapper) {
        this.serieDTOToSerieMapper = serieDTOToSerieMapper;
    }

    @Override
    public Season apply(SeasonDTO seasonDTO) {
        return this.map(seasonDTO);
    }

    private Season map(SeasonDTO seasonDTO) {
        if (seasonDTO == null) {
            return null;
        }
        return new Season(
                seasonDTO.getId(),
                seasonDTO.getSeasonNumber(),
                this.serieDTOToSerieMapper.apply(seasonDTO.getSerieDTO())
        );
    }
}
