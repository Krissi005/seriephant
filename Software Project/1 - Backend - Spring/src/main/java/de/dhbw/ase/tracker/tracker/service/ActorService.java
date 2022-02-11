package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.helper.Checker;
import de.dhbw.ase.tracker.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Actor;
import de.dhbw.ase.tracker.tracker.model.ActorDTO;
import de.dhbw.ase.tracker.tracker.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    ActorRepository actorRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public Actor saveActor(ActorDTO actorDTO) {
        return saveActor(actorDTO.getFirstName(), actorDTO.getLastName());
    }

    public Actor saveActor(String firstName, String lastName) {
        Actor actorToCreate = new Actor(firstName, lastName);
        actorRepository.save(actorToCreate);
        return actorToCreate;
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
    public List<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public List<Episode> getAllEpisodesOfActor(Long actorId) {
        return actorRepository.findById(actorId).get().getPlayedInEpisodes();
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
    public Actor updateActor(Long actorId, ActorDTO actorDTO) throws ValidationException {
        if (actorRepository.existsById(actorId)) {
            Actor foundActor = actorRepository.getById(actorId);
            DTOMapper.updateActorFromDTO(foundActor, actorDTO);
            actorRepository.save(foundActor);
            return foundActor;
        }
        throw new ValidationException("Id is not known.");
    }

    public Actor updatePlayedInEpisodesOfActor(Long actorId, Long episodeId) throws ValidationException {
        Episode episode = Checker.getEpisodeById(episodeId);
        if (actorRepository.existsById(actorId)) {
            Actor foundActor = actorRepository.getById(actorId);
            foundActor.playInEpisode(episode);
            actorRepository.save(foundActor);
            return foundActor;
        }
        throw new ValidationException("Id is not known.");
    }

    public Actor removePlayedInEpisodesOfActor(Long actorId, Long episodeId) throws ValidationException {
        Episode episode = Checker.getEpisodeById(episodeId);
        if (actorRepository.existsById(actorId)) {
            Actor foundActor = actorRepository.getById(actorId);
            foundActor.removePlayedInEpisode(episode);
            actorRepository.save(foundActor);
            return foundActor;
        }
        throw new ValidationException("Id is not known.");
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
    public void deleteActor(Long actorId) {
        actorRepository.deleteById(actorId);
    }

    /************************************************************************************************************************************/
}
