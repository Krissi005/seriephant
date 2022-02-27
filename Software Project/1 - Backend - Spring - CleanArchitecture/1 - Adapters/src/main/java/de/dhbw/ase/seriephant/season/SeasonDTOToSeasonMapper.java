package de.dhbw.ase.seriephant.season;

import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.serie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SeasonDTOToSeasonMapper implements Function<SeasonDTO, Season> {
    private final SerieRepository serieRepository;

    @Autowired
    public SeasonDTOToSeasonMapper(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public Season apply(SeasonDTO seasonDTO) {
        return this.map(seasonDTO);
    }

    private Season map(SeasonDTO seasonDTO) {
        return new Season(
                seasonDTO.getId(),
                seasonDTO.getSeasonNumber(),
                this.serieRepository.getById(seasonDTO.getSerieDTO().getId())
        );
    }
}
