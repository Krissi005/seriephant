package de.dhbw.ase.tracker.tracker.repository;

import de.dhbw.ase.tracker.tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    List<User> getUsersByFirstNameAndLastName(String firstName, String lastName);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
