package de.dhbw.ase.tracker.tracker.controller;

import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.EpisodeDTO;
import de.dhbw.ase.tracker.tracker.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/episode")
@RequiredArgsConstructor
public class EpisodeController {
    private final EpisodeService episodeService;
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
    public Episode createEpisode(@RequestBody EpisodeDTO episodeDTO) throws ValidationException {
        return episodeService.saveEpisode(episodeDTO);
    }

    @PostMapping(value="/new", params = {"title", "episodeNumber", "seasonId"})
        public Episode createEpisode(@RequestParam String title, @RequestParam Integer episodeNumber, @RequestParam Long seasonId) throws ValidationException {
        return episodeService.saveEpisode(title, episodeNumber, seasonId);
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
    public List<Episode> getAllEpisodes(){
        return episodeService.getAllEpisodes();
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
    public Episode updateEpisode(@RequestParam Long episodeId, @RequestBody EpisodeDTO episodeDTO) throws ValidationException {
        return episodeService.updateEpisode(episodeId, episodeDTO);
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
    public void deleteEpisode(@RequestParam Long episodeId) {
        episodeService.deleteEpisode(episodeId);
    }
    /************************************************************************************************************************************/
}
