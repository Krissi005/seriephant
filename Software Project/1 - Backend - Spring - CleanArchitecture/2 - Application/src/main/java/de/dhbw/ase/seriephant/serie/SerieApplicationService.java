package de.dhbw.ase.seriephant.serie;

import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import de.dhbw.ase.seriephant.domain.serie.Serie;
import de.dhbw.ase.seriephant.domain.serie.SerieRepository;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class SerieApplicationService {
    private final SeasonRepository seasonRepository;
    private final SerieRepository serieRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;

    @Autowired
    public SerieApplicationService(SeasonRepository seasonRepository, SerieRepository serieRepository, GenreRepository genreRepository, UserRepository userRepository) {
        this.seasonRepository = seasonRepository;
        this.serieRepository = serieRepository;
        this.genreRepository = genreRepository;
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
    public Serie saveSerie(Serie serie) throws ValidationException {
        if (serie != null && (serie.getId() == null || !this.serieRepository.existsById(serie.getId()))) {
            if (serie.getGenre() != null && serie.getGenre().getId() != null && this.genreRepository.existsById(serie.getGenre().getId())) {
                serie.setGenre(this.genreRepository.getById(serie.getGenre().getId()));
            } else {
                serie.setGenre(null);
            }
            return this.serieRepository.save(serie);
        }
        throw new ValidationException("Serie is not valid.");
    }

    public Serie saveSerie(String title, String description, Integer releaseYear) {
        Serie serieToCreate = new Serie(title, description, releaseYear);
        return this.serieRepository.save(serieToCreate);
    }

    public Serie saveSerie(String title, String description, Integer releaseYear, Long genreId) throws ValidationException {
        if (this.genreRepository.existsById(genreId)) {
            Serie serieToCreate = new Serie(title, description, releaseYear, this.genreRepository.getById(genreId));
            return this.serieRepository.save(serieToCreate);
        }
        throw new ValidationException("Id of Genre is not known.");
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
    public Serie getSerieById(Long serieId) throws ValidationException {
        if (this.serieRepository.existsById(serieId)) {
            return this.serieRepository.getById(serieId);
        }
        throw new ValidationException("Id of Serie is not known.");
    }

    public List<Serie> getAllSeries() {
        return this.serieRepository.findAll();
    }

    public List<Serie> getSeriesByTitleAndAndReleaseYear(String title, Integer releaseYear) {
        return this.serieRepository.getSeriesByTitleAndAndReleaseYear(title, releaseYear);
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
    public Serie updateSerie(Serie serie) throws ValidationException {
        if (serie != null && serie.getId() != null && this.serieRepository.existsById(serie.getId())) {
            Serie foundSerie = this.serieRepository.getById(serie.getId());
            foundSerie.setTitle(serie.getTitle());
            foundSerie.setDescription(serie.getDescription());
            foundSerie.setReleaseYear(serie.getReleaseYear());
            if (serie.getGenre() != null && serie.getGenre().getId() != null && this.genreRepository.existsById(serie.getGenre().getId())) {
                foundSerie.setGenre(this.genreRepository.getById(serie.getGenre().getId()));
            }
            return this.serieRepository.save(foundSerie);
        }
        throw new ValidationException("Id of Serie is not known.");
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
    public void deleteSerie(Long serieId) throws ValidationException {
        if (this.serieRepository.existsById(serieId)) {
            this.serieRepository.deleteById(serieId);
        } else {
            throw new ValidationException("Id of Serie is not known.");
        }
    }

    public void deleteSerieWithAllSeasons(Long serieId) throws ValidationException {
        if (this.serieRepository.existsById(serieId)) {
            for (Season season : this.serieRepository.getById(serieId).getSeasons()) {
                this.seasonRepository.deleteById(season.getId());
            }
            this.serieRepository.deleteById(serieId);
        } else {
            throw new ValidationException("Id of Serie is not known.");
        }
    }

    /************************************************************************************************************************************/
}
