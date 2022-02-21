package de.dhbw.ase.seriephant.repository;

import de.dhbw.ase.seriephant.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ActorRepository extends JpaRepository<Actor, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/

    /** UPDATE **/

    /**
     * DELETE
     **/
}
