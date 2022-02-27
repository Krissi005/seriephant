package de.dhbw.ase.seriephant.episode;

import de.dhbw.ase.seriephant.actor.ActorDTO;
import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.actor.ActorRepository;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.rating.RatingRepository;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import de.dhbw.ase.seriephant.rating.RatingKeyDTOToRatingKeyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class EpisodeDTOToEpisodeMapper implements Function<EpisodeDTO, Episode> {
    private final SeasonRepository seasonRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final ActorRepository actorRepository;
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;

    @Autowired
    public EpisodeDTOToEpisodeMapper(SeasonRepository seasonRepository, UserRepository userRepository, RatingRepository ratingRepository, ActorRepository actorRepository, RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper) {
        this.seasonRepository = seasonRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.actorRepository = actorRepository;
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
    }

    @Override
    public Episode apply(EpisodeDTO episodeDTO) {
        return this.map(episodeDTO);
    }

    private Episode map(EpisodeDTO episodeDTO) {

        List<Actor> actors = episodeDTO.getActorDTOs() == null ? null : episodeDTO.getActorDTOs()
                .stream()
                .map(ActorDTO::getId)
                .map(this.actorRepository::getById)
                .collect(Collectors.toList());

        return new Episode(
                episodeDTO.getId(),
                episodeDTO.getTitle(),
                LocalDate.parse(episodeDTO.getReleaseDate()),
                episodeDTO.getEpisodeNumber(),
                this.seasonRepository.getById(episodeDTO.getSeasonDTO().getId()),
                actors
        );
    }
}
