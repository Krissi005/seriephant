package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.season.SeasonApplicationService;
import de.dhbw.ase.seriephant.season.SeasonDTO;
import de.dhbw.ase.seriephant.season.SeasonDTOToSeasonMapper;
import de.dhbw.ase.seriephant.season.SeasonToSeasonDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/season")
public class SeasonController {
    private final SeasonApplicationService seasonApplicationService;
    private final SeasonToSeasonDTOMapper seasonToSeasonDTOMapper;
    private final SeasonDTOToSeasonMapper seasonDTOToSeasonMapper;

    @Autowired
    public SeasonController(SeasonApplicationService seasonApplicationService, SeasonToSeasonDTOMapper seasonToSeasonDTOMapper, SeasonDTOToSeasonMapper seasonDTOToSeasonMapper) {
        this.seasonApplicationService = seasonApplicationService;
        this.seasonToSeasonDTOMapper = seasonToSeasonDTOMapper;
        this.seasonDTOToSeasonMapper = seasonDTOToSeasonMapper;
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
    public SeasonDTO createSeason(@RequestBody SeasonDTO season) throws ValidationException {
        return this.seasonToSeasonDTOMapper.apply(this.seasonApplicationService.saveSeason(this.seasonDTOToSeasonMapper.apply(season)));
    }

    @PostMapping(value = "/new", params = {"seasonNumber", "serieId"})
    public SeasonDTO createSeason(@RequestParam Integer seasonNumber, @RequestParam Long serieId) throws ValidationException {
        return this.seasonToSeasonDTOMapper.apply(this.seasonApplicationService.saveSeason(seasonNumber, serieId));
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
    @GetMapping(value = "/readById", params = {"seasonId"})
    public SeasonDTO getSeasonById(@RequestParam Long seasonId) throws ValidationException {
        return this.seasonToSeasonDTOMapper.apply(this.seasonApplicationService.getSeasonById(seasonId));
    }

    @GetMapping(value = "/read")
    public List<SeasonDTO> getAllSeasons() {
        return this.seasonApplicationService.getAllSeasons()
                .stream()
                .map(this.seasonToSeasonDTOMapper::apply)
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
    public SeasonDTO updateSeason(@RequestBody SeasonDTO season) throws ValidationException {
        return this.seasonToSeasonDTOMapper.apply(this.seasonApplicationService.updateSeason(this.seasonDTOToSeasonMapper.apply(season)));
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
    public void deleteSeason(@RequestParam Long seasonId) throws ValidationException {
        this.seasonApplicationService.deleteSeason(seasonId);
    }

    @DeleteMapping("/deleteWithAllEpisodes")
    public void deleteSeasonWithAllEpisodes(@RequestParam Long seasonId) throws ValidationException {
        this.seasonApplicationService.deleteSeasonWithAllEpisodes(seasonId);
    }

    /************************************************************************************************************************************/
}
