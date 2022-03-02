package de.dhbw.ase.seriephant.episode;

import de.dhbw.ase.seriephant.actor.ActorDTOToActorMapper;
import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.season.SeasonDTOToSeasonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EpisodeDTOToEpisodeMapper implements Function<EpisodeDTO, Episode> {
    private final SeasonDTOToSeasonMapper seasonDTOToSeasonMapper;
    private final ActorDTOToActorMapper actorDTOToActorMapper;

    @Autowired
    public EpisodeDTOToEpisodeMapper(SeasonDTOToSeasonMapper seasonDTOToSeasonMapper, ActorDTOToActorMapper actorDTOToActorMapper) {
        this.seasonDTOToSeasonMapper = seasonDTOToSeasonMapper;
        this.actorDTOToActorMapper = actorDTOToActorMapper;
    }

    @Override
    public Episode apply(EpisodeDTO episodeDTO) {
        return this.map(episodeDTO);
    }

    private Episode map(EpisodeDTO episodeDTO) {
        if (episodeDTO == null) {
            return null;
        }
        List<Actor> actors = episodeDTO.getActorDTOs() == null ? new ArrayList<>() : episodeDTO.getActorDTOs()
                .stream()
                .filter(Objects::nonNull)
                .map(this.actorDTOToActorMapper::apply)
                .collect(Collectors.toList());

        return new Episode(
                episodeDTO.getId(),
                episodeDTO.getTitle(),
                episodeDTO.getReleaseDate() == null ? null : LocalDate.parse(episodeDTO.getReleaseDate()),
                episodeDTO.getEpisodeNumber(),
                this.seasonDTOToSeasonMapper.apply(episodeDTO.getSeasonDTO()),
                actors
        );
    }
}
