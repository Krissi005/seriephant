package de.dhbw.ase.seriephant.user;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.user.User;
import de.dhbw.ase.seriephant.episode.EpisodeDTOToEpisodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserDTOToUserMapper implements Function<UserDTO, User> {
    private final EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper;

    @Autowired
    public UserDTOToUserMapper(EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper) {
        this.episodeDTOToEpisodeMapper = episodeDTOToEpisodeMapper;
    }


    @Override
    public User apply(UserDTO userDTO) {
        return this.map(userDTO);
    }

    private User map(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        List<Episode> watchedEpisodes = new ArrayList<>();
        if (userDTO.getWatchedEpisodeDTOs() != null) {
            watchedEpisodes = userDTO.getWatchedEpisodeDTOs()
                    .stream()
                    .filter(Objects::nonNull)
                    .map(this.episodeDTOToEpisodeMapper::apply)
                    .collect(Collectors.toList());
        }

        return new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                watchedEpisodes
        );
    }
}
