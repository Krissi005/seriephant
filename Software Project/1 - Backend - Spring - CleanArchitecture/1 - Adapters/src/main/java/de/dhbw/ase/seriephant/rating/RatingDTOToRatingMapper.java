package de.dhbw.ase.seriephant.rating;

import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.rating.Rating;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RatingDTOToRatingMapper implements Function<RatingDTO, Rating> {
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;
    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;

    @Autowired
    public RatingDTOToRatingMapper(RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper, UserRepository userRepository, EpisodeRepository episodeRepository) {
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
    }

    @Override
    public Rating apply(RatingDTO ratingDTO) {
        return this.map(ratingDTO);
    }

    private Rating map(RatingDTO ratingDTO) {
        return new Rating(
                this.ratingKeyDTOToRatingKeyMapper.apply(ratingDTO.getRatingKeyDTO()),
                this.userRepository.getById(ratingDTO.getUserDTO().getId()),
                this.episodeRepository.getById(ratingDTO.getEpisodeDTO().getId()),
                ratingDTO.getRating()
        );
    }
}
