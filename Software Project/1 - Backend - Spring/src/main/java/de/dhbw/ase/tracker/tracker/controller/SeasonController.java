package de.dhbw.ase.tracker.tracker.controller;

import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.SeasonDTO;
import de.dhbw.ase.tracker.tracker.service.SeasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/season")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;
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
    public Season createSeason(@RequestBody SeasonDTO seasonDTO) throws ValidationException {
        return seasonService.saveSeason(seasonDTO);
    }

    @PostMapping(value="/new", params = {"seasonNumber", "serieId"})
        public Season createSeason(@RequestParam Integer seasonNumber, @RequestParam Long serieId) throws ValidationException {
        return seasonService.saveSeason(seasonNumber, serieId);
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
    public List<Season> getAllSeasons(){
        return seasonService.getAllSeasons();
    }

    @GetMapping(value = "/readEpisodes")
    public List<Episode> getAllEpisodesOfSeason(@RequestParam Long seasonId){
        return seasonService.getAllEpisodesOfSeason(seasonId);
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
    public Season updateSeason(@RequestParam Long seasonId, @RequestBody SeasonDTO seasonDTO) throws ValidationException {
        return seasonService.updateSeason(seasonId, seasonDTO);
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
    public void deleteSeason(@RequestParam Long seasonId) {
        seasonService.deleteSeason(seasonId);
    }

    @DeleteMapping("/deleteWithAllEpisodes")
    public void deleteSeasonWithAllEpisodes(@RequestParam Long seasonId) {
        seasonService.deleteSeasonWithAllEpisodes(seasonId);
    }

    /************************************************************************************************************************************/
}
