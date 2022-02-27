package de.dhbw.ase.seriephant.user;

import de.dhbw.ase.seriephant.domain.user.User;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserToUserDTOMapper implements Function<User, UserDTO> {
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;

    public UserToUserDTOMapper(EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper) {
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
    }

    @Override
    public UserDTO apply(User user) {
        return this.map(user);
    }

    private UserDTO map(User user) {

        return new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getWatchedEpisodes().stream().map(this.episodeToEpisodeDTOMapper::apply).collect(Collectors.toList())
        );
    }
}
