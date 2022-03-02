package de.dhbw.ase.seriephant.episode;

import de.dhbw.ase.seriephant.actor.ActorDTO;
import de.dhbw.ase.seriephant.actor.ActorToActorDTOMapper;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.season.SeasonToSeasonDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EpisodeToEpisodeDTOMapper implements Function<Episode, EpisodeDTO> {
    private final SeasonToSeasonDTOMapper seasonToSeasonDTOMapper;
    private final ActorToActorDTOMapper actorToActorDTOMapper;

    @Autowired
    public EpisodeToEpisodeDTOMapper(SeasonToSeasonDTOMapper seasonToSeasonDTOMapper, ActorToActorDTOMapper actorToActorDTOMapper) {
        this.seasonToSeasonDTOMapper = seasonToSeasonDTOMapper;
        this.actorToActorDTOMapper = actorToActorDTOMapper;
    }

    @Override
    public EpisodeDTO apply(Episode episode) {
        return this.map(episode);
    }

    private EpisodeDTO map(Episode episode) {
        List<ActorDTO> actorDTOs = episode.getActors() == null ? null : episode.getActors()
                .stream()
                .filter(Objects::nonNull)
                .map(this.actorToActorDTOMapper::apply)
                .collect(Collectors.toList());

        return new EpisodeDTO(
                episode.getId(),
                episode.getTitle(),
                episode.getReleaseDate().toString(),
                episode.getEpisodeNumber(),
                this.seasonToSeasonDTOMapper.apply(episode.getSeason()),
                actorDTOs
        );
    }
}
