package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.model.Genre;
import de.dhbw.ase.tracker.tracker.model.GenreDTO;
import de.dhbw.ase.tracker.tracker.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Genre saveGenre(GenreDTO genreDTO){
        return saveGenre(genreDTO.getTitle(), genreDTO.getDescription());
    }

    public Genre saveGenre(String title, String description) {
        Genre genreToCreate = new Genre(title, description);
        genreRepository.save(genreToCreate);
        return genreToCreate;
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
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
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
    public Genre updateGenre(Long id, GenreDTO genreDTO) throws ValidationException {
        if(genreRepository.existsById(id)){
            Genre foundGenre = genreRepository.getById(id);
            foundGenre.updateFromDTO(genreDTO);
            genreRepository.save(foundGenre);
            return foundGenre;
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
    public void deleteGenre(Long id){
        genreRepository.deleteById(id);
    }
    /************************************************************************************************************************************/
}
