package de.dhbw.ase.seriephant.episode;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EpisodeApplicationService {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    private EpisodeApplicationService(EpisodeRepository episodeRepository, SeasonRepository seasonRepository) {
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
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
        return this.episodeRepository.save(episode);
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
        throw new ValidationException("Id of Eisode is not known.");
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
        if (this.episodeRepository.existsById(episode.getId())) {
            Episode foundEpisode = this.episodeRepository.getById(episode.getId());
            foundEpisode.setTitle(episode.getTitle());
            foundEpisode.setReleaseDate(episode.getReleaseDate());
            foundEpisode.setEpisodeNumber(episode.getEpisodeNumber());
            foundEpisode.setSeason(episode.getSeason());
            return this.episodeRepository.save(foundEpisode);
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
    public void deleteEpisode(Long episodeId) {
        this.episodeRepository.deleteById(episodeId);
    }
    /************************************************************************************************************************************/
}
