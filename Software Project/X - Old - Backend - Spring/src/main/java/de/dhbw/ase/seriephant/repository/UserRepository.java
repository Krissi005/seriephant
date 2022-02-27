package de.dhbw.ase.seriephant.repository;

import de.dhbw.ase.seriephant.model.User;
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
