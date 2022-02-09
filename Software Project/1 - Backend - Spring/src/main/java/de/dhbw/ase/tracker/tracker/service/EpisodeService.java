package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.helper.Checker;
import de.dhbw.ase.tracker.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.EpisodeDTO;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class EpisodeService {

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
    public Episode saveEpisode(EpisodeDTO episodeDTO) throws ValidationException {
        return saveEpisode(episodeDTO.getTitle(), episodeDTO.getEpisodeNumber(), episodeDTO.getSeasonId());
    }

    public Episode saveEpisode(String title, Integer episodeNumber, Long seasonId) throws ValidationException {
        Season season = Checker.getSeasonById(seasonId);
        Episode episodeToCreate = new Episode(title, episodeNumber, season);
        episodeRepository.save(episodeToCreate);
        return episodeToCreate;
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
    public List<Episode> getAllEpisodes() {
        return episodeRepository.findAll();
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
    public Episode updateEpisode(Long id, EpisodeDTO episodeDTO) throws ValidationException {
        if (episodeRepository.existsById(id)) {
            Episode foundEpisode = episodeRepository.getById(id);
            DTOMapper.updateEpisodeFromDTO(foundEpisode, episodeDTO);
            episodeRepository.save(foundEpisode);
            return foundEpisode;
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
    public void deleteEpisode(Long id) {
        episodeRepository.deleteById(id);
    }
    /************************************************************************************************************************************/
}
