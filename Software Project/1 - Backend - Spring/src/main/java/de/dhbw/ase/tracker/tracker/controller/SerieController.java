package de.dhbw.ase.tracker.tracker.controller;

import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.Serie;
import de.dhbw.ase.tracker.tracker.model.SerieDTO;
import de.dhbw.ase.tracker.tracker.service.SerieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/serie")
@RequiredArgsConstructor
public class SerieController {
    private final SerieService serieService;
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
    public Serie createSerie(@RequestBody SerieDTO serieDTO) throws ValidationException {
        return serieService.saveSerie(serieDTO);
    }

    @PostMapping(value="/new", params = {"title"})
        public Serie createSerie(@RequestParam String title, @RequestParam(required = false) String description, @RequestParam(required = false) Long genreId) throws ValidationException {
        return serieService.saveSerie(title, description, genreId);
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
    public List<Serie> getAllSeries(){
        return serieService.getAllSeries();
    }

    @GetMapping(value = "/readSeasons")
    public List<Season> getAllSeasonsOfSerie(@RequestParam Long serieId){
        return serieService.getAllSeasonsOfSerie(serieId);
    }

    @GetMapping(value = "/readSerieByNameAndReleaseYear")
    public List<Serie> getSeriesByTitleAndAndReleaseYear(@RequestParam String title, @RequestParam Integer releaseYear){
        return serieService.getSeriesByTitleAndAndReleaseYear(title, releaseYear);
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
    public Serie updateSerie(@RequestParam Long serieId, @RequestBody SerieDTO serieDTO) throws ValidationException {
        return serieService.updateSerie(serieId, serieDTO);
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
    public void deleteSerie(@RequestParam Long serieId) {
        serieService.deleteSerie(serieId);
    }

    @DeleteMapping("/deleteWithAllSeasons")
    public void deleteSerieWithAllEpisodes(@RequestParam Long serieId) {
        serieService.deleteSerieWithAllSeasons(serieId);
    }

    /************************************************************************************************************************************/
}
