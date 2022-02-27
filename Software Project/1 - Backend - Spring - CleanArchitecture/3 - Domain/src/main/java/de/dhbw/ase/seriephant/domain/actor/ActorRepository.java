package de.dhbw.ase.seriephant.domain.actor;


import java.util.List;

public interface ActorRepository {

    boolean existsById(Long actorId);

    /**
     * CREATE
     **/
    Actor save(Actor actorToCreate);

    /**
     * READ
     **/
    Actor getById(Long actorId);

    List<Actor> findAll();

    /**
     * UPDATE
     **/

    /**
     * DELETE
     **/

    void deleteById(Long actorId);
}
