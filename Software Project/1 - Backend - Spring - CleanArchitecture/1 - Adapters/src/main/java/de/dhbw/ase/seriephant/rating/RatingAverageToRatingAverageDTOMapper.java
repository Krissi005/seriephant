package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.RatingAverage;
import de.dhbw.ase.seriephant.episode.EpisodeApplicationService;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.function.Function;

@Component
public class RatingAverageToRatingAverageDTOMapper implements Function<RatingAverage, RatingAverageDTO> {
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;
    private final EpisodeApplicationService episodeApplicationService;

    @Autowired
    public RatingAverageToRatingAverageDTOMapper(EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper, EpisodeApplicationService episodeApplicationService) {
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
        this.episodeApplicationService = episodeApplicationService;
    }

    @Override
    public RatingAverageDTO apply(RatingAverage ratingAverage) {
        return this.map(ratingAverage);
    }

    private RatingAverageDTO map(RatingAverage ratingAverage) {
        EpisodeDTO episodeDTO;
        try {
            episodeDTO = this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.getEpisodeById(ratingAverage.getEpisodeId()));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return new RatingAverageDTO(
                episodeDTO,
                ratingAverage.getAverageRatingValue(),
                ratingAverage.getNumberOfRatings()
        );
    }
}
