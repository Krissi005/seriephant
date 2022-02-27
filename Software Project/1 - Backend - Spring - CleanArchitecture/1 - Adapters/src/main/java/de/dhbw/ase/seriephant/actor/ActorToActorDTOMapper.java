package de.dhbw.ase.seriephant.actor;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActorToActorDTOMapper implements Function<Actor, ActorDTO> {

    @Override
    public ActorDTO apply(Actor actor) {
        return this.map(actor);
    }

    private ActorDTO map(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getFirstName(),
                actor.getLastName()
        );
    }
}
