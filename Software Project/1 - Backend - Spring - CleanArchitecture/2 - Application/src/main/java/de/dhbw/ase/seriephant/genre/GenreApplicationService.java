package de.dhbw.ase.seriephant.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class GenreApplicationService {
    private final GenreRepository genreRepository;

    @Autowired
    private GenreApplicationService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /************************************************************************************************************************************/

    /*
         _____                 _
        / ____|               | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Genre saveGenre(Genre genre) throws ValidationException {
        if (genre == null) {
            throw new ValidationException("Genre is not valid.");
        }
        if (genre.getId() == null || this.genreRepository.existsById(genre.getId())) {
            throw new ValidationException("Id of Genre exists already.");
        }
        return this.genreRepository.save(genre);
    }

    public Genre saveGenre(String title, String description) {
        Genre genreToCreate = new Genre(title, description);
        return this.genreRepository.save(genreToCreate);
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
    public Genre getGenreById(Long genreId) throws ValidationException {
        if (!this.genreRepository.existsById(genreId)) {
            throw new ValidationException("Id of Genre is not known.");
        }
        return this.genreRepository.getById(genreId);
    }

    public List<Genre> getAllGenres() {
        return this.genreRepository.findAll();
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
    public Genre updateGenre(Genre genre) throws ValidationException {
        if (genre == null) {
            throw new ValidationException("Genre is not valid.");
        }
        if (genre.getId() == null || !this.genreRepository.existsById(genre.getId())) {
            throw new ValidationException("Id of Genre is not known.");
        }
        Genre foundGenre = this.genreRepository.getById(genre.getId());
        foundGenre.setTitle(genre.getTitle());
        foundGenre.setDescription(genre.getDescription());
        return this.genreRepository.save(foundGenre);
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
    public void deleteGenre(Long genreId) throws ValidationException {
        if (!this.genreRepository.existsById(genreId)) {
            throw new ValidationException("Id of Genre is not known.");
        }
        this.genreRepository.deleteById(genreId);

    }

    /************************************************************************************************************************************/
}
