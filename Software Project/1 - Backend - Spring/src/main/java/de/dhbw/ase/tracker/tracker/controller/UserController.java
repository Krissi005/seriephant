package de.dhbw.ase.tracker.tracker.controller;

import de.dhbw.ase.tracker.tracker.model.Episode;
import de.dhbw.ase.tracker.tracker.model.User;
import de.dhbw.ase.tracker.tracker.model.UserDTO;
import de.dhbw.ase.tracker.tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    /************************************************************************************************************************************/

    /*
         _____                _
        / ____|              | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    @PostMapping(value = "/create")
    public User createUser(@RequestBody UserDTO userDTO) throws ValidationException {
        return userService.saveUser(userDTO);
    }

    @PostMapping(value="/new", params = {"firstName", "lastName"})
        public User createUser(@RequestParam String firstName, @RequestParam String lastName) throws ValidationException {
        return userService.saveUser(firstName, lastName);
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

    @GetMapping(value = "/read")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/readEpisodes")
    public List<Episode> getAllEpisodesOfUser(@RequestParam Long userId){
        return userService.getAllEpisodesOfUser(userId);
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

    @PutMapping(value = "/update")
    public User updateUser(@RequestParam Long userId, UserDTO userDTO) throws ValidationException {
        return userService.updateUser(userId, userDTO);
    }

    @PutMapping(value = "/updateEpisodes")
    public User updateSeenEpisodesOfUser(@RequestParam Long userId, Long episodeId) throws ValidationException {
        return userService.updateSeenEpisodesOfUser(userId, episodeId);
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
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);
    }

    /************************************************************************************************************************************/
}
