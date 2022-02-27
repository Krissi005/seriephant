package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import de.dhbw.ase.seriephant.user.UserToUserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingToRatingDTOMapper implements Function<Rating, RatingDTO> {
    private final RatingKeyToRatingKeyDTOMapper ratingKeyToRatingKeyDTOMapper;
    private final UserToUserDTOMapper userToUserDTOMapper;
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;

    @Autowired
    public RatingToRatingDTOMapper(RatingKeyToRatingKeyDTOMapper ratingKeyToRatingKeyDTOMapper, UserToUserDTOMapper userToUserDTOMapper, EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper) {
        this.ratingKeyToRatingKeyDTOMapper = ratingKeyToRatingKeyDTOMapper;
        this.userToUserDTOMapper = userToUserDTOMapper;
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
    }

    @Override
    public RatingDTO apply(Rating rating) {
        return this.map(rating);
    }

    private RatingDTO map(Rating rating) {
        return new RatingDTO(
                this.ratingKeyToRatingKeyDTOMapper.apply(rating.getId()),
                this.userToUserDTOMapper.apply(rating.getUser()),
                this.episodeToEpisodeDTOMapper.apply(rating.getEpisode()),
                rating.getRating()
        );
    }
}
