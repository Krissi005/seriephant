package de.dhbw.ase.plugins.persistence.hibernate.user;

import de.dhbw.ase.seriephant.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpringDataUserRepository extends JpaRepository<User, Long> {
    // intentionally left blank, see JpaRepository interface definition

    /** CREATE **/

    /**
     * READ
     **/
    User getById(Long userId);

    List<User> getUsersByFirstNameAndLastName(String firstName, String lastName);

    /** UPDATE **/

    /**
     * DELETE
     **/
}
