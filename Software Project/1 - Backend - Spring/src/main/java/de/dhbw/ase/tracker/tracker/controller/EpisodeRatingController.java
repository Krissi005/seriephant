package de.dhbw.ase.tracker.tracker.controller;

import de.dhbw.ase.tracker.tracker.model.EpisodeRating;
import de.dhbw.ase.tracker.tracker.service.EpisodeRatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/episodeRating")
@RequiredArgsConstructor
public class EpisodeRatingController {
    private final EpisodeRatingService episodeRatingService;
    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */

    @PostMapping(value="/new", params = {"userId", "episodeId", "rating"})
        public EpisodeRating createEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Integer rating) throws ValidationException {
        return episodeRatingService.saveEpisodeRating(userId, episodeId, rating);
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
    public List<EpisodeRating> getAllEpisodeRatings(){
        return episodeRatingService.getAllEpisodeRatings();
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
    public EpisodeRating updateEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Integer rating) throws ValidationException {
        return episodeRatingService.updateEpisodeRating(userId, episodeId, rating);
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
    public void deleteEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        episodeRatingService.deleteEpisodeRating(userId, episodeId);
    }
    /************************************************************************************************************************************/
}
