package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.serie.SerieApplicationService;
import de.dhbw.ase.seriephant.serie.SerieDTO;
import de.dhbw.ase.seriephant.serie.SerieDTOToSerieMapper;
import de.dhbw.ase.seriephant.serie.SerieToSerieDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/serie")
public class SerieController {
    private final SerieApplicationService serieApplicationService;
    private final SerieDTOToSerieMapper serieDTOToSerieMapper;
    private final SerieToSerieDTOMapper serieToSerieDTOMapper;

    @Autowired
    public SerieController(SerieApplicationService serieApplicationService, SerieDTOToSerieMapper serieDTOToSerieMapper, SerieToSerieDTOMapper serieToSerieDTOMapper) {
        this.serieApplicationService = serieApplicationService;
        this.serieDTOToSerieMapper = serieDTOToSerieMapper;
        this.serieToSerieDTOMapper = serieToSerieDTOMapper;
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
    @PostMapping(value = "/create")
    public SerieDTO createSerie(@RequestBody SerieDTO serieDTO) throws ValidationException {
        return this.serieToSerieDTOMapper.apply(this.serieApplicationService.saveSerie(this.serieDTOToSerieMapper.apply(serieDTO)));
    }

    @PostMapping(value = "/new", params = {"title"})
    public SerieDTO createSerie(@RequestParam String title, @RequestParam(required = false) String description, @RequestParam(required = false) Integer releaseYear) throws ValidationException {
        return this.serieToSerieDTOMapper.apply(this.serieApplicationService.saveSerie(title, description, releaseYear));
    }

    @PostMapping(value = "/newWithGenre", params = {"title"})
    public SerieDTO createSerie(@RequestParam String title, @RequestParam(required = false) String description, @RequestParam(required = false) Integer releaseYear, @RequestParam Long genreId) throws ValidationException {
        return this.serieToSerieDTOMapper.apply(this.serieApplicationService.saveSerie(title, description, releaseYear, genreId));
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
    @GetMapping(value = "/readById", params = {"serieId"})
    public SerieDTO getSerieById(@RequestParam Long serieId) throws ValidationException {
        return this.serieToSerieDTOMapper.apply(this.serieApplicationService.getSerieById(serieId));
    }

    @GetMapping(value = "/read")
    public List<SerieDTO> getAllSeries() {
        return this.serieApplicationService.getAllSeries()
                .stream()
                .map(this.serieToSerieDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/readSerieByNameAndReleaseYear")
    public List<SerieDTO> getSeriesByTitleAndAndReleaseYear(@RequestParam String title, @RequestParam Integer releaseYear) {
        return this.serieApplicationService.getSeriesByTitleAndAndReleaseYear(title, releaseYear)
                .stream()
                .map(this.serieToSerieDTOMapper::apply)
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
    public SerieDTO updateSerie(@RequestBody SerieDTO serieDTO) throws ValidationException {
        return this.serieToSerieDTOMapper.apply(this.serieApplicationService.updateSerie(this.serieDTOToSerieMapper.apply(serieDTO)));
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
        this.serieApplicationService.deleteSerie(serieId);
    }

    @DeleteMapping("/deleteWithAllSeasons")
    public void deleteSerieWithAllEpisodes(@RequestParam Long serieId) {
        this.serieApplicationService.deleteSerieWithAllSeasons(serieId);
    }

    /************************************************************************************************************************************/
}
