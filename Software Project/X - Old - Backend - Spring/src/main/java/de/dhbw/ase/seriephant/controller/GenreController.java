package de.dhbw.ase.seriephant.controller;

import de.dhbw.ase.seriephant.model.Genre;
import de.dhbw.ase.seriephant.model.Serie;
import de.dhbw.ase.seriephant.model.GenreDTO;
import de.dhbw.ase.seriephant.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    @PostMapping(value = "/create")
    public Genre createGenre(@RequestBody GenreDTO genreDTO){
        return genreService.saveGenre(genreDTO);
    }

    @PostMapping(value="/new", params = {"title"})
        public Genre createGenre(@RequestParam String title, @RequestParam(required = false) String description){
        return genreService.saveGenre(title, description);
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

    @GetMapping(value = "/read")
    public List<Genre> getAllGenres(){
        return genreService.getAllGenres();
    }

    @GetMapping(value = "/readAllSeries")
    public List<Serie> getAllSeriesOfGenre(@RequestParam Long genreId){
        return genreService.getAllSeriesOfGenre(genreId);
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
    public Genre updateGenre(@RequestParam Long genreId, @RequestBody GenreDTO genreDTO) throws ValidationException {
        return genreService.updateGenre(genreId, genreDTO);
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
    @DeleteMapping("/delete")
    public void deleteGenre(@RequestParam Long genreId) {
        genreService.deleteGenre(genreId);
    }
    /************************************************************************************************************************************/
}
