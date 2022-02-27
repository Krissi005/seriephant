package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.actor.ActorApplicationService;
import de.dhbw.ase.seriephant.actor.ActorDTO;
import de.dhbw.ase.seriephant.actor.ActorDTOToActorMapper;
import de.dhbw.ase.seriephant.actor.ActorToActorDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorApplicationService actorApplicationService;
    private final ActorDTOToActorMapper actorDTOToActorMapper;
    private final ActorToActorDTOMapper actorToActorDTOMapper;

    @Autowired
    public ActorController(ActorApplicationService actorApplicationService, ActorDTOToActorMapper actorDTOToActorMapper, ActorToActorDTOMapper actorToActorDTOMapper) {
        this.actorApplicationService = actorApplicationService;
        this.actorDTOToActorMapper = actorDTOToActorMapper;
        this.actorToActorDTOMapper = actorToActorDTOMapper;
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
    public ActorDTO createActor(@RequestBody ActorDTO actor) throws ValidationException {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.saveActor(this.actorDTOToActorMapper.apply(actor)));
    }

    @PostMapping(value = "/new", params = {"firstName"})
    public ActorDTO createActor(@RequestParam String firstName, @RequestParam(required = false) String lastName) {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.saveActor(firstName, lastName));
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
    @GetMapping(value = "/readById", params = {"actorId"})
    public ActorDTO getActorById(@RequestParam Long actorId) throws ValidationException {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.getActorById(actorId));
    }

    @GetMapping(value = "/read")
    public List<ActorDTO> getAllActors() {
        return this.actorApplicationService.getAllActors()
                .stream()
                .map(this.actorToActorDTOMapper::apply)
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
    public ActorDTO updateActor(@RequestBody ActorDTO actor) throws ValidationException {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.updateActor(this.actorDTOToActorMapper.apply(actor)));
    }

    @PutMapping(value = "/updateEpisodes")
    public ActorDTO updateSeenEpisodesOfActor(@RequestParam Long actorId, @RequestParam Long episodeId) throws ValidationException {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.updatePlayedInEpisodesOfActor(actorId, episodeId));
    }

    @PutMapping(value = "/removeEpisode")
    public ActorDTO removePlayedInEpisodesOfActor(@RequestParam Long actorId, @RequestParam Long episodeId) throws ValidationException {
        return this.actorToActorDTOMapper.apply(this.actorApplicationService.removePlayedInEpisodesOfActor(actorId, episodeId));
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
        this.actorApplicationService.deleteActor(actorId);
    }

    /************************************************************************************************************************************/
}
