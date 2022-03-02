package de.dhbw.ase.seriephant.episode;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.actor.ActorRepository;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeApplicationService {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final ActorRepository actorRepository;
    private final UserRepository userRepository;

    @Autowired
    private EpisodeApplicationService(EpisodeRepository episodeRepository, SeasonRepository seasonRepository, ActorRepository actorRepository, UserRepository userRepository) {
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
        this.actorRepository = actorRepository;
        this.userRepository = userRepository;
    }

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Episode saveEpisode(Episode episode) throws ValidationException {
        if (episode != null && (episode.getId() == null || !this.episodeRepository.existsById(episode.getId()))) {
            List<Actor> actors = new ArrayList<>();
            if (episode.getActors() != null) {
                episode.getActors().forEach(actor -> {
                    if (this.actorRepository.existsById(actor.getId())) {
                        actors.add(actor);
                    }
                });
            }
            episode.setActors(actors);
            return this.updateSeason(episode);
        }
        throw new ValidationException("Episode is not valid.");
    }

    private Episode updateSeason(Episode episode) throws ValidationException {
        if (episode.getSeason() != null && episode.getSeason().getId() != null && this.seasonRepository.existsById(episode.getSeason().getId())) {
            episode.setSeason(this.seasonRepository.getById(episode.getSeason().getId()));
            return this.episodeRepository.save(episode);
        }
        throw new ValidationException("Season is not valid.");
    }

    public Episode saveEpisode(String title, LocalDate releaseDate, Integer episodeNumber, Long seasonId) throws ValidationException {
        if (this.seasonRepository.existsById(seasonId)) {
            Episode episodeToCreate = new Episode(title, releaseDate, episodeNumber, this.seasonRepository.getById(seasonId));
            return this.episodeRepository.save(episodeToCreate);
        }
        throw new ValidationException("Id of Season is not known.");
    }

    /************************************************************************************************************************************/
    /*
         _____                _
        |  __ \              | |
        | |__) |___  __ _  __| |
        |  _  // _ \/ _` |/ _` |
        | | \ \  __/ (_| | (_| |
        |_|  \_\___|\__,_|\__,_|
    */
    public Episode getEpisodeById(Long episodeId) throws ValidationException {
        if (this.episodeRepository.existsById(episodeId)) {
            return this.episodeRepository.getById(episodeId);
        }
        throw new ValidationException("Id of Episode is not known.");
    }

    public List<Episode> getEpisodeByUserId(Long userId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            return this.episodeRepository.getEpisodesByUsersEquals(this.userRepository.getById(userId));
        }
        throw new ValidationException("Id of User is not known.");
    }

    public List<Episode> getEpisodeNotByUserId(Long userId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            return this.episodeRepository.getEpisodesByUserNotEquals(userId);
        }
        throw new ValidationException("Id of User is not known.");
    }

    public List<Episode> getAllEpisodes() {
        return this.episodeRepository.findAll();
    }

    /************************************************************************************************************************************/
    /*
         _    _           _       _
        | |  | |         | |     | |
        | |  | |_ __   __| | __ _| |_ ___
        | |  | | '_ \ / _` |/ _` | __/ _ \
        | |__| | |_) | (_| | (_| | ||  __/
        \____/| .__/ \__,_|\__,_|\__\___|
              | |
              |_|
    */
    public Episode updateEpisode(Episode episode) throws ValidationException {
        if (episode != null && episode.getId() == null && this.episodeRepository.existsById(episode.getId())) {
            Episode foundEpisode = this.episodeRepository.getById(episode.getId());
            foundEpisode.setTitle(episode.getTitle());
            foundEpisode.setReleaseDate(episode.getReleaseDate());
            foundEpisode.setEpisodeNumber(episode.getEpisodeNumber());
            if (episode.getActors() != null && !episode.getActors().isEmpty()) {
                for (Actor actor : foundEpisode.getActors()) {
                    episode.removeActor(actor);
                }
                for (Actor actor : episode.getActors()) {
                    episode.addActor(actor);
                }
            }
            return this.updateSeason(episode, foundEpisode);
        }
        throw new ValidationException("Episode is not valid.");
    }

    private Episode updateSeason(Episode episode, Episode foundEpisode) throws ValidationException {
        if (episode.getSeason() != null && episode.getSeason().getId() != null && this.seasonRepository.existsById(episode.getSeason().getId())) {
            foundEpisode.setSeason(this.seasonRepository.getById(episode.getSeason().getId()));
            return this.episodeRepository.save(foundEpisode);
        }
        throw new ValidationException("Season is not valid.");
    }

    public Episode addActor(Long episodeId, Long actorId) throws ValidationException {
        if (this.episodeRepository.existsById(episodeId)) {
            if (this.actorRepository.existsById(actorId)) {
                Episode foundEpisode = this.episodeRepository.getById(episodeId);
                foundEpisode.addActor(this.actorRepository.getById(actorId));
                return this.episodeRepository.save(foundEpisode);
            }
            throw new ValidationException("Id of Actor is not known.");
        }
        throw new ValidationException("Id of Episode is not known.");
    }

    public Episode removeActor(Long episodeId, Long actorId) throws ValidationException {
        if (this.episodeRepository.existsById(episodeId)) {
            if (this.actorRepository.existsById(actorId)) {
                Episode foundEpisode = this.episodeRepository.getById(episodeId);
                foundEpisode.removeActor(this.actorRepository.getById(actorId));
                return this.episodeRepository.save(foundEpisode);
            }
            throw new ValidationException("Id of Actor is not known.");
        }
        throw new ValidationException("Id of Episode is not known.");
    }

    /************************************************************************************************************************************/
    /*
         _____       _      _
        |  __ \     | |    | |
        | |  | | ___| | ___| |_ ___
        | |  | |/ _ \ |/ _ \ __/ _ \
        | |__| |  __/ |  __/ ||  __/
        |_____/ \___|_|\___|\__\___|
    */
    public void deleteEpisode(Long episodeId) throws ValidationException {
        if (this.episodeRepository.existsById(episodeId)) {
            this.episodeRepository.deleteById(episodeId);
        } else {
            throw new ValidationException("Id of Episode is not known.");
        }
    }
    /************************************************************************************************************************************/
}
