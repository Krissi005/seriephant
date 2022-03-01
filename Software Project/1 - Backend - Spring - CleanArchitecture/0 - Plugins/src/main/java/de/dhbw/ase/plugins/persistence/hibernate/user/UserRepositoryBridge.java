package de.dhbw.ase.plugins.persistence.hibernate.user;

import de.dhbw.ase.seriephant.domain.user.User;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryBridge implements UserRepository {
    private SpringDataUserRepository springDataUserRepository;

    @Autowired
    public UserRepositoryBridge(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public boolean existsById(Long userId) {
        return this.springDataUserRepository.existsById(userId);
    }

    @Override
    public User save(User user) {
        return this.springDataUserRepository.saveAndFlush(user);
    }

    @Override
    public User getById(Long userId) {
        return this.springDataUserRepository.getById(userId);
    }

    @Override
    public List<User> getUsersByFirstNameAndLastName(String firstName, String lastName) {
        return this.springDataUserRepository.getUsersByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<User> findAll() {
        return this.springDataUserRepository.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        this.springDataUserRepository.deleteById(userId);
    }
}
