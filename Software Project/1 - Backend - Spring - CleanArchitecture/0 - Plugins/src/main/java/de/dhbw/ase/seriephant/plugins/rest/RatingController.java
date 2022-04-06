package de.dhbw.ase.seriephant.plugins.rest;

import de.dhbw.ase.seriephant.rating.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/rating")
public class RatingController {
    private final RatingApplicationService ratingApplicationService;
    private final RatingDTOToRatingMapper episodeRatingDTOToRatingMapper;
    private final RatingToRatingDTOMapper ratingToRatingDTOMapper;
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;
    private final RatingAverageToRatingAverageDTOMapper ratingAverageToRatingAverageDTOMapper;

    public RatingController(RatingApplicationService ratingApplicationService, RatingDTOToRatingMapper episodeRatingDTOToRatingMapper, RatingToRatingDTOMapper ratingToRatingDTOMapper, RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper, RatingAverageToRatingAverageDTOMapper ratingAverageToRatingAverageDTOMapper) {
        this.ratingApplicationService = ratingApplicationService;
        this.episodeRatingDTOToRatingMapper = episodeRatingDTOToRatingMapper;
        this.ratingToRatingDTOMapper = ratingToRatingDTOMapper;
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
        this.ratingAverageToRatingAverageDTOMapper = ratingAverageToRatingAverageDTOMapper;
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
    @PostMapping(value = "/create")
    public RatingDTO createEpisodeRating(@RequestBody RatingDTO ratingDTO) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.saveEpisodeRating(this.episodeRatingDTOToRatingMapper.apply(ratingDTO)));
    }

    @PostMapping(value = "/new", params = {"userId", "episodeId", "rating"})
    public RatingDTO createEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Double rating) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.saveEpisodeRating(userId, episodeId, rating));
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
    @GetMapping(value = "/readById", params = {"userId", "episodeId"})
    public RatingDTO getRatingById(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.getRatingById(this.ratingKeyDTOToRatingKeyMapper.apply(new RatingKeyDTO(userId, episodeId))));
    }

    @GetMapping(value = "/readByIdDTO")
    public RatingDTO getRatingById(@RequestBody RatingDTO ratingDTO) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.getRatingByRating(this.episodeRatingDTOToRatingMapper.apply(ratingDTO)));
    }

    @GetMapping(value = "/readByUserId")
    public List<RatingDTO> getRatingByUserId(@RequestParam Long userId) throws ValidationException {
        return this.ratingApplicationService.getRatingByUserId(userId).stream().map(this.ratingToRatingDTOMapper::apply)
                .collect(Collectors.toList());
    }

    /*@GetMapping(value = "/readsByUserId")
    public List<RatingDTO> getRatingsByUser(@RequestParam Long userId) throws ValidationException {
        return this.ratingApplicationService.getRatingsByUser(userId).stream().map(this.ratingToRatingDTOMapper::apply)
                .collect(Collectors.toList());
    }*/

    @GetMapping(value = "/readRatingsNotByUser")
    public List<RatingDTO> getRatingsNotByUser(@RequestParam Long userId) throws ValidationException {
        return this.ratingApplicationService.getRatingsNotByUser(userId).stream().map(this.ratingToRatingDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/readRatingsByUser")
    public List<RatingDTO> getRatingsByUser(@RequestParam Long userId) throws ValidationException {
        return this.ratingApplicationService.getRatingsByUser(userId).stream().map(this.ratingToRatingDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/readAllEpisodes")
    public List<RatingDTO> getAllEpisodesRatings() {
        return this.ratingApplicationService.getAllEpisodesRatings()
                .stream()
                .map(this.ratingToRatingDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/readAllEpisodesWithRatings")
    public List<RatingAverageDTO> getAllEpisodesWithRatings() {
        return this.ratingApplicationService.getAllEpisodesWithRatings().stream().map(this.ratingAverageToRatingAverageDTOMapper::apply).collect(Collectors.toList());
    }

    @GetMapping(value = "/read")
    public List<RatingDTO> getAllEpisodeRatings() {
        return this.ratingApplicationService.getAllEpisodeRatings()
                .stream()
                .map(this.ratingToRatingDTOMapper::apply)
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
    public RatingDTO updateEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Double rating) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.updateEpisodeRating(userId, episodeId, rating));
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
    public void deleteEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        this.ratingApplicationService.deleteEpisodeRating(userId, episodeId);
    }
    /************************************************************************************************************************************/
}
