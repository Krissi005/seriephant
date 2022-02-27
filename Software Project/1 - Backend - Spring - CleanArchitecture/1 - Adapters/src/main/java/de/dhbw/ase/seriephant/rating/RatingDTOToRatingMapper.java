package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.episode.EpisodeDTOToEpisodeMapper;
import de.dhbw.ase.seriephant.user.UserDTOToUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingDTOToRatingMapper implements Function<RatingDTO, Rating> {
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;
    private final UserDTOToUserMapper userDTOToUserMapper;
    private final EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper;

    @Autowired
    public RatingDTOToRatingMapper(RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper, UserDTOToUserMapper userDTOToUserMapper, EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper) {
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
        this.userDTOToUserMapper = userDTOToUserMapper;
        this.episodeDTOToEpisodeMapper = episodeDTOToEpisodeMapper;
    }

    @Override
    public Rating apply(RatingDTO ratingDTO) {
        return this.map(ratingDTO);
    }

    private Rating map(RatingDTO ratingDTO) {
        if (ratingDTO == null) {
            return null;
        }

        return new Rating(
                this.ratingKeyDTOToRatingKeyMapper.apply(ratingDTO.getRatingKeyDTO()),
                this.userDTOToUserMapper.apply(ratingDTO.getUserDTO()),
                this.episodeDTOToEpisodeMapper.apply(ratingDTO.getEpisodeDTO()),
                ratingDTO.getRating()
        );
    }
}
