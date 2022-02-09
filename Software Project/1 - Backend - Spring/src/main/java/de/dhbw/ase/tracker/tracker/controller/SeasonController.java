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
    /*@PostMapping(value = "/create")
    public Episode createGenre(@RequestBody EpisodeDTO episodeDTO){
        return episodeService.saveEpisode(episodeDTO);
    }*/

    @PostMapping(value="/new", params = {"seasonNumber"})
        public Season createGenre(@RequestParam Integer seasonNumber){
        return seasonService.saveSeason(seasonNumber);
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
    public List<Episode> getAllEpisodesOfSeason(@RequestParam Long id){
        return seasonService.getAllEpisodesOfSeason(id);
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
    public Season updateSeason(@RequestParam Long id, SeasonDTO seasonDTO) throws ValidationException {
        return seasonService.updateSeason(id, seasonDTO);
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
    public void deleteSeason(@RequestParam Long id) {
        seasonService.deleteSeason(id);
    }

    @DeleteMapping("/deleteWithAllEpisodes")
    public void deleteSeasonWithAllEpisodes(@RequestParam Long id) {
        seasonService.deleteSeasonWithAllEpisodes(id);
    }

    /************************************************************************************************************************************/
}
