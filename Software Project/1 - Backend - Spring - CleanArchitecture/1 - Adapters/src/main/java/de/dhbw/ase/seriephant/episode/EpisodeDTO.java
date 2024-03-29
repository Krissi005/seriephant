package de.dhbw.ase.seriephant.episode;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.actor.ActorDTO;
import de.dhbw.ase.seriephant.season.SeasonDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "title",
        "releaseDate",
        "episodeNumber",
        "season",
        "actors"
})
@Generated("jsonschema2pojo")
public final class EpisodeDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("episodeNumber")
    private Integer episodeNumber;
    @JsonProperty("season")
    private SeasonDTO seasonDTO;
    @JsonProperty("actors")
    private List<ActorDTO> actorDTOs = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public EpisodeDTO(Long id, String title, String releaseDate, Integer episodeNumber, SeasonDTO seasonDTO, List<ActorDTO> actorDTOs) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.episodeNumber = episodeNumber;
        this.seasonDTO = seasonDTO;
        this.actorDTOs = actorDTOs;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("episodeNumber")
    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    @JsonProperty("season")
    public SeasonDTO getSeasonDTO() {
        return seasonDTO;
    }

    @JsonProperty("actors")
    public List<ActorDTO> getActorDTOs() {
        return actorDTOs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}