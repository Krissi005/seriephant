package de.dhbw.ase.plugins.persistence.hibernate.actor;

import de.dhbw.ase.seriephant.domain.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SpringDataActorRepository extends JpaRepository<Actor, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    Actor getById(Long actorId);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
