package de.dhbw.ase.seriephant.user;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.rating.RatingRepository;
import de.dhbw.ase.seriephant.domain.user.User;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
import de.dhbw.ase.seriephant.rating.RatingKeyDTOToRatingKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserDTOToUserMapper implements Function<UserDTO, User> {
    private final RatingRepository ratingRepository;
    private final EpisodeRepository episodeRepository;
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;

    @Autowired
    public UserDTOToUserMapper(RatingRepository ratingRepository, EpisodeRepository episodeRepository, RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper) {
        this.ratingRepository = ratingRepository;
        this.episodeRepository = episodeRepository;
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
    }

    @Override
    public User apply(UserDTO userDTO) {
        return this.map(userDTO);
    }

    private User map(UserDTO userDTO) {
        List<Episode> watchedEpisodes = userDTO.getWatchedEpisodeDTOs()
                .stream()
                .map(EpisodeDTO::getId)
                .map(this.episodeRepository::getById)
                .collect(Collectors.toList());

        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                watchedEpisodes
        );
    }
}
