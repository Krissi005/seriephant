package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.rating.*;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/episodeRating")
public class RatingController {
    private final RatingApplicationService ratingApplicationService;
    private final RatingDTOToRatingMapper episodeRatingDTOToRatingMapper;
    private final RatingToRatingDTOMapper ratingToRatingDTOMapper;
    private final RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper;

    public RatingController(RatingApplicationService ratingApplicationService, RatingDTOToRatingMapper episodeRatingDTOToRatingMapper, RatingToRatingDTOMapper ratingToRatingDTOMapper, RatingKeyDTOToRatingKeyMapper ratingKeyDTOToRatingKeyMapper) {
        this.ratingApplicationService = ratingApplicationService;
        this.episodeRatingDTOToRatingMapper = episodeRatingDTOToRatingMapper;
        this.ratingToRatingDTOMapper = ratingToRatingDTOMapper;
        this.ratingKeyDTOToRatingKeyMapper = ratingKeyDTOToRatingKeyMapper;
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
    public RatingDTO createEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Integer rating) throws ValidationException {
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
    @GetMapping(value = "/readById")
    public RatingDTO getRatingById(@RequestParam Long userId, @RequestParam Long episodeId) throws ValidationException {
        return this.ratingToRatingDTOMapper.apply(this.ratingApplicationService.getRatingById(this.ratingKeyDTOToRatingKeyMapper.apply(new RatingKeyDTO(userId, episodeId))));
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
    public RatingDTO updateEpisodeRating(@RequestParam Long userId, @RequestParam Long episodeId, @RequestParam Integer rating) throws ValidationException {
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
