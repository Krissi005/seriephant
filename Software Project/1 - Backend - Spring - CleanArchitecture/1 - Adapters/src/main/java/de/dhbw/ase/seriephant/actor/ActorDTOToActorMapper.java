package de.dhbw.ase.seriephant.actor;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ActorDTOToActorMapper implements Function<ActorDTO, Actor> {
    @Override
    public Actor apply(ActorDTO actorDTO) {
        return this.map(actorDTO);
    }

    private Actor map(ActorDTO actorDTO) {
        return new Actor(
                actorDTO.getId(),
                actorDTO.getFirstName(),
                actorDTO.getLastName()
        );
    }
}
