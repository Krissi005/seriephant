package de.dhbw.ase.seriephant.actor;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class ActorApplicationService {
    private final ActorRepository actorRepository;


    @Autowired
    private ActorApplicationService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
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
    public Actor saveActor(Actor actor) throws ValidationException {
        if (actor != null && (actor.getId() == null || !this.actorRepository.existsById(actor.getId()))) {
            return this.actorRepository.save(actor);
        }
        throw new ValidationException("Actor is not valid.");
    }

    public Actor saveActor(String firstName, String lastName) {
        Actor actorToCreate = new Actor(firstName, lastName);
        return this.actorRepository.save(actorToCreate);
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
    public Actor getActorById(Long actorId) throws ValidationException {
        if (this.actorRepository.existsById(actorId)) {
            return this.actorRepository.getById(actorId);
        }
        throw new ValidationException("Id of Actor is not known.");
    }

    public List<Actor> getAllActors() {
        return this.actorRepository.findAll();
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
    public Actor updateActor(Actor actor) throws ValidationException {
        if (actor != null && actor.getId() != null && this.actorRepository.existsById(actor.getId())) {
            Actor foundActor = this.actorRepository.getById(actor.getId());
            foundActor.setFirstName(actor.getFirstName());
            foundActor.setLastName(actor.getLastName());
            return this.actorRepository.save(foundActor);
        }
        throw new ValidationException("Id of Actor is not known.");
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
    public void deleteActor(Long actorId) throws ValidationException {
        if (this.actorRepository.existsById(actorId)) {
            this.actorRepository.deleteById(actorId);
        } else {
            throw new ValidationException("Id of Actor is not known.");
        }
    }

    /************************************************************************************************************************************/
}
