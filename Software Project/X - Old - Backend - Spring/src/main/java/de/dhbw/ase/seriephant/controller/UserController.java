package de.dhbw.ase.seriephant.controller;

import de.dhbw.ase.seriephant.model.EpisodeRating;
import de.dhbw.ase.seriephant.service.UserService;
import de.dhbw.ase.seriephant.model.Episode;
import de.dhbw.ase.seriephant.model.User;
import de.dhbw.ase.seriephant.model.UserDTO;
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
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping(value="/new", params = {"firstName"})
        public User createUser(@RequestParam String firstName, @RequestParam(required = false) String lastName) {
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

    @GetMapping(value = "/readRatings")
    public List<EpisodeRating> getAllRatingsOfUser(@RequestParam Long userId){
        return userService.getAllRatingsOfUser(userId);
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
    public User updateUser(@RequestParam Long userId, @RequestBody UserDTO userDTO) throws ValidationException {
        return userService.updateUser(userId, userDTO);
    }

    @PutMapping(value = "/updateEpisodes")
    public User updateSeenEpisodesOfUser(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
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
