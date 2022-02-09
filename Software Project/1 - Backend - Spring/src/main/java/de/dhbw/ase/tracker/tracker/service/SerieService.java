package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.helper.Checker;
import de.dhbw.ase.tracker.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.tracker.model.*;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.tracker.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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

    public List<Season> getAllSeasonsOfSerie(Long id) {
        return serieRepository.findById(id).get().getSeasons();
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
    public Serie updateSerie(Long id, SerieDTO serieDTO) throws ValidationException {
        if (serieRepository.existsById(id)) {
            Serie foundSerie = serieRepository.getById(id);
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
    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

    public void deleteSerieWithAllSeasons(Long id) {
        for (Season season : serieRepository.getById(id).getSeasons()) {
            seasonRepository.deleteById(season.getId());
        }
        serieRepository.deleteById(id);
    }

    /************************************************************************************************************************************/
}
