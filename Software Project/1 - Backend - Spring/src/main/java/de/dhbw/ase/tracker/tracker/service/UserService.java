package de.dhbw.ase.tracker.tracker.service;

import de.dhbw.ase.tracker.tracker.helper.Checker;
import de.dhbw.ase.tracker.tracker.helper.DTOMapper;
import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.User;
import de.dhbw.ase.tracker.tracker.model.UserDTO;
import de.dhbw.ase.tracker.tracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    public User saveUser(UserDTO userDTO) throws ValidationException {
        return saveUser(userDTO.getFirstName(), userDTO.getLastName());
    }

    public User saveUser(String firstName, String lastName) throws ValidationException {
        User userToCreate = new User(firstName, lastName);
        userRepository.save(userToCreate);
        return userToCreate;
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Episode> getAllEpisodesOfUser(Long userId) {
        return userRepository.findById(userId).get().getSeenEpisodes();
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
    public User updateUser(Long userId, UserDTO userDTO) throws ValidationException {
        if (userRepository.existsById(userId)) {
            User foundUser = userRepository.getById(userId);
            DTOMapper.updateUserFromDTO(foundUser, userDTO);
            userRepository.save(foundUser);
            return foundUser;
        }
        throw new ValidationException("Id is not known.");
    }
    public User updateSeenEpisodesOfUser(Long userId, Long episodeId) throws ValidationException {
        Episode episode = Checker.getEpisodeById(episodeId);
        if (userRepository.existsById(userId)) {
            User foundUser = userRepository.getById(userId);
            foundUser.seeEpisode(episode);
            userRepository.save(foundUser);
            return foundUser;
        }
        throw new ValidationException("Id is not known.");
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
        userRepository.deleteById(userId);
    }

    /************************************************************************************************************************************/
}
