package de.dhbw.ase.seriephant.user;

import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import de.dhbw.ase.seriephant.domain.user.User;
import de.dhbw.ase.seriephant.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class UserApplicationService {

    private final UserRepository userRepository;
    private final EpisodeRepository episodeRepository;

    @Autowired
    public UserApplicationService(UserRepository userRepository, EpisodeRepository episodeRepository) {
        this.userRepository = userRepository;
        this.episodeRepository = episodeRepository;
    }

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    public User saveUser(String firstName, String lastName) {
        User userToCreate = new User(firstName, lastName);
        return this.userRepository.save(userToCreate);
    }

    /************************************************************************************************************************************/
    /*
         _____                _
        |  __ \              | |
        | |__) |___  __ _  __| |
        |  _  // _ \/ _` |/ _` |
        | | \ \  __/ (_| | (_| |
        |_|  \_\___|\__,_|\__,_|
    */
    public User getUserById(Long userId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            return this.userRepository.getById(userId);
        }
        throw new ValidationException("Id of User is not known.");
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /************************************************************************************************************************************/
    /*
         _    _           _       _
        | |  | |         | |     | |
        | |  | |_ __   __| | __ _| |_ ___
        | |  | | '_ \ / _` |/ _` | __/ _ \
        | |__| | |_) | (_| | (_| | ||  __/
        \____/| .__/ \__,_|\__,_|\__\___|
              | |
              |_|
    */
    public User updateUser(User user) throws ValidationException {
        if (this.userRepository.existsById(user.getId())) {
            User foundUser = this.userRepository.getById(user.getId());
            this.userRepository.save(foundUser);
            return foundUser;
        }
        throw new ValidationException("Id of User is not known.");
    }

    public User updateSeenEpisodesOfUser(Long userId, Long episodeId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            if (this.episodeRepository.existsById(episodeId)) {
                User foundUser = this.userRepository.getById(userId);
                foundUser.watchEpisode(this.episodeRepository.getById(episodeId));
                return this.userRepository.save(foundUser);
            }
            throw new ValidationException("Id of Episode is not known.");
        }
        throw new ValidationException("Id of User is not known.");

    }

    public User removePlayedInEpisodesOfActor(Long userId, Long episodeId) throws ValidationException {
        if (this.userRepository.existsById(userId)) {
            if (this.episodeRepository.existsById(episodeId)) {
                User foundUser = this.userRepository.getById(userId);
                foundUser.removeWatchedEpisode(this.episodeRepository.getById(episodeId));
                this.userRepository.save(foundUser);
                return foundUser;
            }
            throw new ValidationException("Id of Episode is not known.");
        }
        throw new ValidationException("Id of Actor is not known.");
    }

    /************************************************************************************************************************************/
    /*
         _____       _      _
        |  __ \     | |    | |
        | |  | | ___| | ___| |_ ___
        | |  | |/ _ \ |/ _ \ __/ _ \
        | |__| |  __/ |  __/ ||  __/
        |_____/ \___|_|\___|\__\___|
    */
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    /************************************************************************************************************************************/
}
