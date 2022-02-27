package de.dhbw.ase.seriephant.domain.user;

import java.util.List;

public interface UserRepository {

    boolean existsById(Long userId);

    /**
     * CREATE
     **/

    User save(User user);

    /**
     * READ
     **/
    User getById(Long userId);

    List<User> getUsersByFirstNameAndLastName(String firstName, String lastName);

    List<User> findAll();


    /** UPDATE **/

    /**
     * DELETE
     **/
    void deleteById(Long userId);
}
