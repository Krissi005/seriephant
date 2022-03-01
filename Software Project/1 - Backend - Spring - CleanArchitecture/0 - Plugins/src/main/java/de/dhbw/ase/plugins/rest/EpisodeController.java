package de.dhbw.ase.plugins.rest;

import de.dhbw.ase.seriephant.episode.EpisodeApplicationService;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
import de.dhbw.ase.seriephant.episode.EpisodeDTOToEpisodeMapper;
import de.dhbw.ase.seriephant.episode.EpisodeToEpisodeDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/episode")
public class EpisodeController {
    private final EpisodeApplicationService episodeApplicationService;
    private final EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper;
    private final EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper;

    @Autowired
    public EpisodeController(EpisodeApplicationService episodeApplicationService, EpisodeDTOToEpisodeMapper episodeDTOToEpisodeMapper, EpisodeToEpisodeDTOMapper episodeToEpisodeDTOMapper) {
        this.episodeApplicationService = episodeApplicationService;
        this.episodeDTOToEpisodeMapper = episodeDTOToEpisodeMapper;
        this.episodeToEpisodeDTOMapper = episodeToEpisodeDTOMapper;
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
    public EpisodeDTO createEpisode(@RequestBody EpisodeDTO episode) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.saveEpisode(this.episodeDTOToEpisodeMapper.apply(episode)));
    }

    @PostMapping(value = "/new", params = {"title", "episodeNumber", "seasonId"})
    public EpisodeDTO createEpisode(@RequestParam String title, @RequestParam("localDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate releaseDate, @RequestParam Integer episodeNumber, @RequestParam Long seasonId) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.saveEpisode(title, releaseDate, episodeNumber, seasonId));
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
    @GetMapping(value = "/readById", params = "episodeId")
    public EpisodeDTO getEpisodById(@RequestParam Long episodeId) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.getEpisodeById(episodeId));
    }

    @GetMapping(value = "/readByUserId", params = "userId")
    public List<EpisodeDTO> getEpisodByUserId(@RequestParam Long userId) throws ValidationException {
        return this.episodeApplicationService.getEpisodeByUserId(userId)
                .stream()
                .map(this.episodeToEpisodeDTOMapper::apply)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/read")
    public List<EpisodeDTO> getAllEpisodes() {
        return this.episodeApplicationService.getAllEpisodes()
                .stream()
                .map(this.episodeToEpisodeDTOMapper::apply)
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
    public EpisodeDTO updateEpisode(@RequestBody EpisodeDTO episode) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.updateEpisode(this.episodeDTOToEpisodeMapper.apply(episode)));
    }

    @PutMapping(value = "/addActor")
    public EpisodeDTO updateSeenEpisodesOfActor(@RequestParam Long episodeId, @RequestParam Long actorId) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.addActor(episodeId, actorId));
    }

    @PutMapping(value = "/removeActor")
    public EpisodeDTO removePlayedInEpisodesOfActor(@RequestParam Long actorId, @RequestParam Long episodeId) throws ValidationException {
        return this.episodeToEpisodeDTOMapper.apply(this.episodeApplicationService.removeActor(episodeId, actorId));
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
    public void deleteEpisode(@RequestParam Long episodeId) throws ValidationException {
        this.episodeApplicationService.deleteEpisode(episodeId);
    }
    /************************************************************************************************************************************/
}
