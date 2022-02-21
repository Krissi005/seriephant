package de.dhbw.ase.seriephant.controller;

import de.dhbw.ase.seriephant.model.Actor;
import de.dhbw.ase.seriephant.model.Episode;
import de.dhbw.ase.seriephant.model.ActorDTO;
import de.dhbw.ase.seriephant.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;
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
    public Actor createActor(@RequestBody ActorDTO actorDTO) {
        return actorService.saveActor(actorDTO);
    }

    @PostMapping(value="/new", params = {"firstName"})
        public Actor createActor(@RequestParam String firstName, @RequestParam(required = false) String lastName) {
        return actorService.saveActor(firstName, lastName);
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
    public List<Actor> getAllActors(){
        return actorService.getAllActors();
    }

    @GetMapping(value = "/readEpisodes")
    public List<Episode> getAllEpisodesOfActor(@RequestParam Long actorId){
        return actorService.getAllEpisodesOfActor(actorId);
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
    public Actor updateActor(@RequestParam Long actorId, @RequestBody ActorDTO actorDTO) throws ValidationException {
        return actorService.updateActor(actorId, actorDTO);
    }

    @PutMapping(value = "/updateEpisodes")
    public Actor updateSeenEpisodesOfActor(@RequestParam Long actorId, @RequestParam Long episodeId) throws ValidationException {
        return actorService.updatePlayedInEpisodesOfActor(actorId, episodeId);
    }

    @PutMapping(value = "/removeEpisode")
    public Actor removePlayedInEpisodesOfActor(@RequestParam Long actorId, @RequestParam Long episodeId) throws ValidationException {
        return actorService.removePlayedInEpisodesOfActor(actorId, episodeId);
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
    public void deleteActor(@RequestParam Long actorId) {
        actorService.deleteActor(actorId);
    }

    /************************************************************************************************************************************/
}
