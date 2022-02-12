package de.dhbw.ase.tracker.service;

import de.dhbw.ase.tracker.model.Genre;
import de.dhbw.ase.tracker.helper.Checker;
import de.dhbw.ase.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.model.Season;
import de.dhbw.ase.tracker.model.Serie;
import de.dhbw.ase.tracker.model.SerieDTO;
import de.dhbw.ase.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;

    @Autowired
    SeasonRepository seasonRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Serie saveSerie(SerieDTO serieDTO) throws ValidationException {
        return saveSerie(serieDTO.getTitle(), serieDTO.getDescription(), serieDTO.getGenreId());
    }

    public Serie saveSerie(String title, String description, Long genreId) throws ValidationException {
        Genre genre = Checker.getGenreById(genreId);
        Serie serieToCreate = new Serie(title, description, genre);
        serieRepository.save(serieToCreate);
        return serieToCreate;
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
    public List<Serie> getAllSeries() {
        return serieRepository.findAll();
    }

    public List<Season> getAllSeasonsOfSerie(Long serieId) {
        return serieRepository.findById(serieId).get().getSeasons();
    }

    public List<Serie> getSeriesByTitleAndAndReleaseYear(String title, Integer releaseYear) {
        return serieRepository.getSeriesByTitleAndAndReleaseYear(title, releaseYear);
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
    public Serie updateSerie(Long serieId, SerieDTO serieDTO) throws ValidationException {
        if (serieRepository.existsById(serieId)) {
            Serie foundSerie = serieRepository.getById(serieId);
            DTOMapper.updateSerieFromDTO(foundSerie, serieDTO);
            serieRepository.save(foundSerie);
            return foundSerie;
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
    public void deleteSerie(Long serieId) {
        serieRepository.deleteById(serieId);
    }

    public void deleteSerieWithAllSeasons(Long serieId) {
        for (Season season : serieRepository.getById(serieId).getSeasons()) {
            seasonRepository.deleteById(season.getId());
        }
        serieRepository.deleteById(serieId);
    }

    /************************************************************************************************************************************/
}
