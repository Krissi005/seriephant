package de.dhbw.ase.seriephant.season;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import de.dhbw.ase.seriephant.domain.serie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class SeasonApplicationService {
    private final EpisodeRepository episodeRepository;
    private final SeasonRepository seasonRepository;
    private final SerieRepository serieRepository;

    @Autowired
    public SeasonApplicationService(EpisodeRepository episodeRepository, SeasonRepository seasonRepository, SerieRepository serieRepository) {
        this.episodeRepository = episodeRepository;
        this.seasonRepository = seasonRepository;
        this.serieRepository = serieRepository;
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
    public Season saveSeason(Season season) throws ValidationException {
        if (season != null &&
                (season.getId() == null || !this.seasonRepository.existsById(season.getId()))) {
            if (season.getSerie() != null &&
                    season.getSerie().getId() != null &&
                    this.serieRepository.existsById(season.getSerie().getId())) {
                season.setSerie(this.serieRepository.getById(season.getSerie().getId()));
                return this.seasonRepository.save(season);
            }
            throw new ValidationException("Serie is not correct.");
        }
        throw new ValidationException("Season is not valid.");
    }

    public Season saveSeason(Integer seasonNumber, Long serieId) throws ValidationException {
        if (this.serieRepository.existsById(serieId)) {
            Season seasonToCreate = new Season(seasonNumber, this.serieRepository.getById(serieId));
            return this.seasonRepository.save(seasonToCreate);
        }
        throw new ValidationException("Id of Serie is not known.");
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
    public Season getSeasonById(Long seasonId) throws ValidationException {
        if (this.seasonRepository.existsById(seasonId)) {
            return this.seasonRepository.getById(seasonId);
        }
        throw new ValidationException("Id of Season is not known.");
    }

    public List<Season> getAllSeasons() {
        return this.seasonRepository.findAll();
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
    public Season updateSeason(Season season) throws ValidationException {
        if (season != null && season.getId() != null && this.seasonRepository.existsById(season.getId())) {
            Season foundSeason = this.seasonRepository.getById(season.getId());
            foundSeason.setSeasonNumber(season.getSeasonNumber());
            if (season.getSerie() != null && season.getSerie().getId() != null && this.serieRepository.existsById(season.getSerie().getId())) {
                foundSeason.setSerie(this.serieRepository.getById(season.getSerie().getId()));
            }
            return this.seasonRepository.save(season);
        }
        throw new ValidationException("Id of Season is not known.");
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
        if (this.seasonRepository.existsById(seasonId)) {
            this.seasonRepository.deleteById(seasonId);
        }
    }

    public void deleteSeasonWithAllEpisodes(Long seasonId) {
        if (this.seasonRepository.existsById(seasonId)) {
            for (Episode episode : this.seasonRepository.getById(seasonId).getEpisodes()) {
                this.episodeRepository.deleteById(episode.getId());
            }
            this.seasonRepository.deleteById(seasonId);
        }
    }

    /************************************************************************************************************************************/
}
