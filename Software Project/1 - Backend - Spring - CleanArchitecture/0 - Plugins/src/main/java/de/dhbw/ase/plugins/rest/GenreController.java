package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.genre.GenreApplicationService;
import de.dhbw.ase.seriephant.genre.GenreDTO;
import de.dhbw.ase.seriephant.genre.GenreDTOToGenreMapper;
import de.dhbw.ase.seriephant.genre.GenreToGenreDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/genre")
public class GenreController {
    private final GenreApplicationService genreApplicationService;
    private final GenreDTOToGenreMapper genreDTOToGenreMapper;
    private final GenreToGenreDTOMapper genreToGenreDTOMapper;

    @Autowired
    public GenreController(GenreApplicationService genreApplicationService, GenreDTOToGenreMapper genreDTOToGenreMapper, GenreToGenreDTOMapper genreToGenreDTOMapper) {
        this.genreApplicationService = genreApplicationService;
        this.genreDTOToGenreMapper = genreDTOToGenreMapper;
        this.genreToGenreDTOMapper = genreToGenreDTOMapper;
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
    @PostMapping(value = "/create")
    public GenreDTO createGenre(@RequestBody GenreDTO genreDTO) {
        return this.genreToGenreDTOMapper.apply(this.genreApplicationService.saveGenre(this.genreDTOToGenreMapper.apply(genreDTO)));
    }

    @PostMapping(value = "/new", params = {"title", "description"})
    public GenreDTO createGenre(@RequestParam String title, @RequestParam String description) {
        return this.genreToGenreDTOMapper.apply(this.genreApplicationService.saveGenre(title, description));
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
    @GetMapping(value = "/readById", params = {"genreId"})
    public GenreDTO getGenreById(@RequestParam Long genreId) throws ValidationException {
        return this.genreToGenreDTOMapper.apply(this.genreApplicationService.getGenreById(genreId));
    }

    @GetMapping(value = "/read")
    public List<GenreDTO> getAllGenres() {
        return this.genreApplicationService.getAllGenres()
                .stream()
                .map(this.genreToGenreDTOMapper::apply)
                .collect(Collectors.toList());
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
    @PutMapping(value = "/update")
    public GenreDTO updateGenre(@RequestBody GenreDTO genre) throws ValidationException {
        return this.genreToGenreDTOMapper.apply(this.genreApplicationService.updateGenre(this.genreDTOToGenreMapper.apply(genre)));
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
    @DeleteMapping(value = "/delete", params = {"genreId"})
    public void deleteGenre(@RequestParam Long genreId) {
        this.genreApplicationService.deleteGenre(genreId);
    }
    /************************************************************************************************************************************/
}
