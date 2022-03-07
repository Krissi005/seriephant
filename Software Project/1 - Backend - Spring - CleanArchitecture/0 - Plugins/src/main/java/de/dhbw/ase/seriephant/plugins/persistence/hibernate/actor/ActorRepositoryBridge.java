package de.dhbw.ase.seriephant.plugins.persistence.hibernate.actor;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import de.dhbw.ase.seriephant.domain.actor.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActorRepositoryBridge implements ActorRepository {
    private SpringDataActorRepository springDataActorRepository;

    @Autowired
    public ActorRepositoryBridge(SpringDataActorRepository springDataActorRepository) {
        this.springDataActorRepository = springDataActorRepository;
    }

    @Override
    public boolean existsById(Long actorId) {
        return this.springDataActorRepository.existsById(actorId);
    }

    @Override
    public Actor save(Actor actorToCreate) {
        return this.springDataActorRepository.saveAndFlush(actorToCreate);
    }

    @Override
    public Actor getById(Long actorId) {
        return this.springDataActorRepository.getById(actorId);
    }

    @Override
    public List<Actor> findAll() {
        return this.springDataActorRepository.findAll();
    }

    @Override
    public void deleteById(Long actorId) {
        this.springDataActorRepository.deleteById(actorId);
    }
}
