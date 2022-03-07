package de.dhbw.ase.seriephant.ratingAggregate;

import de.dhbw.ase.seriephant.domain.ratingAggregate.RatingAggregate;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingAggregateToRatingAggregateDTOMapper implements Function<RatingAggregate, RatingAggregateDTO> {
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;

    @Autowired
    public RatingAggregateToRatingAggregateDTOMapper(EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper) {
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
    }

    @Override
    public RatingAggregateDTO apply(RatingAggregate ratingAggregate) {
        return this.map(ratingAggregate);
    }

    private RatingAggregateDTO map(RatingAggregate ratingAggregate) {
        return new RatingAggregateDTO(
                this.episodeToEpisodeDTOMapper.apply(ratingAggregate.getEpisode()),
                ratingAggregate.getAverageRating(),
                ratingAggregate.getNumberOfRatings()
        );
    }
}
