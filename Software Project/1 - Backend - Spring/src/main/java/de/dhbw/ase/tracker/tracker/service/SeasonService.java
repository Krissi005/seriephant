package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.SeasonDTO;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
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
    public Season saveSeason(SeasonDTO seasonDTO) {
        return saveSeason(seasonDTO.getSeasonNumber());
    }

    public Season saveSeason(Integer seasonNumber) {
        Season seasonToCreate = new Season(seasonNumber);
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
    public List<Episode> getAllEpisodesOfSeason(Long id) {
        return seasonRepository.findById(id).get().getEpisodes();
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
    public Season updateSeason(Long id, SeasonDTO seasonDTO) throws ValidationException {
        if (seasonRepository.existsById(id)) {
            Season foundSeason = seasonRepository.getById(id);
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
    public void deleteSeason(Long id) {
        seasonRepository.deleteById(id);
    }

    public void deleteSeasonWithAllEpisodes(Long id) {
        for (Episode episode : seasonRepository.getById(id).getEpisodes()) {
            episodeRepository.deleteById(episode.getId());
        }
        seasonRepository.deleteById(id);
    }

    /************************************************************************************************************************************/
}
