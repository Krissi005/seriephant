package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.RatingAverage;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingAverageToRatingAverageDTOMapper implements Function<RatingAverage, RatingAverageDTO> {
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;

    @Autowired
    public RatingAverageToRatingAverageDTOMapper(EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper) {
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
    }

    @Override
    public RatingAverageDTO apply(RatingAverage ratingAverage) {
        return this.map(ratingAverage);
    }

    private RatingAverageDTO map(RatingAverage ratingAverage) {
        return new RatingAverageDTO(
                ratingAverage.getEpisodeId(),
                ratingAverage.getEpisodeTitle(),
                ratingAverage.getAverageRatingValue(),
                ratingAverage.getNumberOfRatings()
        );
    }
}
