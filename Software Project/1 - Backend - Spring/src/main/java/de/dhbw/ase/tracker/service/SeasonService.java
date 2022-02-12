package de.dhbw.ase.tracker.service;

import de.dhbw.ase.tracker.helper.Checker;
import de.dhbw.ase.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.model.Episode;
import de.dhbw.ase.tracker.model.SeasonDTO;
import de.dhbw.ase.tracker.model.Serie;
import de.dhbw.ase.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.model.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class SeasonService {

    @Autowired
    SeasonRepository seasonRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Season saveSeason(SeasonDTO seasonDTO) throws ValidationException {
        return saveSeason(seasonDTO.getSeasonNumber(), seasonDTO.getSerieId());
    }

    public Season saveSeason(Integer seasonNumber, Long serieId) throws ValidationException {
        Serie serie = Checker.getSerieById(serieId);
        Season seasonToCreate = new Season(seasonNumber, serie);
        seasonRepository.save(seasonToCreate);
        return seasonToCreate;
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
    public List<Season> getAllSeasons() {
        return seasonRepository.findAll();
    }
    public List<Episode> getAllEpisodesOfSeason(Long seasonId) {
        return seasonRepository.findById(seasonId).get().getEpisodes();
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
    public Season updateSeason(Long seasonId, SeasonDTO seasonDTO) throws ValidationException {
        if (seasonRepository.existsById(seasonId)) {
            Season foundSeason = seasonRepository.getById(seasonId);
            DTOMapper.updateSeasonFromDTO(foundSeason, seasonDTO);
            seasonRepository.save(foundSeason);
            return foundSeason;
        }
        throw new ValidationException("Id is not known.");
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
    public void deleteSeason(Long seasonId) {
        seasonRepository.deleteById(seasonId);
    }

    public void deleteSeasonWithAllEpisodes(Long seasonId) {
        for (Episode episode : seasonRepository.getById(seasonId).getEpisodes()) {
            episodeRepository.deleteById(episode.getId());
        }
        seasonRepository.deleteById(seasonId);
    }

    /************************************************************************************************************************************/
}
