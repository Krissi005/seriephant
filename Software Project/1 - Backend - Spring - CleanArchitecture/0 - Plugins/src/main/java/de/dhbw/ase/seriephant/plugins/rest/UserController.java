package de.dhbw.ase.seriephant.plugins.rest;

import de.dhbw.ase.seriephant.user.UserApplicationService;
import de.dhbw.ase.seriephant.user.UserDTO;
import de.dhbw.ase.seriephant.user.UserDTOToUserMapper;
import de.dhbw.ase.seriephant.user.UserToUserDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserApplicationService userApplicationService;
    private final UserDTOToUserMapper userDTOToUserMapper;
    private final UserToUserDTOMapper userToUserDTOMapper;

    @Autowired
    public UserController(UserApplicationService userApplicationService, UserDTOToUserMapper userDTOToUserMapper, UserToUserDTOMapper userToUserDTOMapper) {
        this.userApplicationService = userApplicationService;
        this.userDTOToUserMapper = userDTOToUserMapper;
        this.userToUserDTOMapper = userToUserDTOMapper;
    }

    /************************************************************************************************************************************/

    /*
         _____                 _
        / ____|               | |
        | |     _ __ ___  __ _| |_ ___
        | |    | '__/ _ \/ _` | __/ _ \
        | |____| | |  __/ (_| | ||  __/
        \_____|_|  \___|\__,_|\__\___|
     */
    @PostMapping(value = "/create")
    public UserDTO createUser(@RequestBody UserDTO user) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.saveUser(this.userDTOToUserMapper.apply(user)));
    }

    @PostMapping(value = "/new", params = {"firstName", "lastName"})
    public UserDTO createUser(@RequestParam String firstName, @RequestParam(required = false) String lastName) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.saveUser(firstName, lastName));
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
    @GetMapping(value = "/readById", params = {"userId"})
    public UserDTO getUserById(@RequestParam Long userId) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.getUserById(userId));
    }

    @GetMapping(value = "/read")
    public List<UserDTO> getAllUsers() {
        return this.userApplicationService.getAllUsers()
                .stream()
                .map(this.userToUserDTOMapper::apply)
                .collect(Collectors.toList());
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
    public UserDTO updateUser(@RequestBody UserDTO user) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.updateUser(this.userDTOToUserMapper.apply(user)));
    }

    @PutMapping(value = "/updateEpisode", params = {"userId", "episodeId"})
    public UserDTO updateSeenEpisodeOfUser(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.updateSeenEpisodesOfUser(userId, episodeId));
    }

    @PutMapping(value = "/updateEpisodes")
    public UserDTO updateSeenEpisodesOfUser(@RequestBody UserDTO user) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.updateSeenEpisodesOfUser(this.userDTOToUserMapper.apply(user)));
    }

    @PutMapping(value = "/removeEpisode", params = {"userId", "episodeId"})
    public UserDTO removeSeenEpisodeOfUser(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.removeSeenEpisodeOfUser(userId, episodeId));
    }

    @PutMapping(value = "/removeEpisodes")
    public UserDTO removeSeenEpisodesOfUser(@RequestBody UserDTO user) throws ValidationException {
        return this.userToUserDTOMapper.apply(this.userApplicationService.removeSeenEpisodeOfUser(this.userDTOToUserMapper.apply(user)));
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
    public void deleteUser(@RequestParam Long userId) throws ValidationException {
        this.userApplicationService.deleteUser(userId);
    }

    /************************************************************************************************************************************/
}
