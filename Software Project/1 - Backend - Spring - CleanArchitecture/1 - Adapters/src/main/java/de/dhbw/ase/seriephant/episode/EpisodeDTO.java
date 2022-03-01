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
public class EpisodeDTO {

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

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("releaseDate")
    public String getReleaseDate() {
        return releaseDate;
    }

    @JsonProperty("releaseDate")
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @JsonProperty("episodeNumber")
    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    @JsonProperty("episodeNumber")
    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    @JsonProperty("season")
    public SeasonDTO getSeasonDTO() {
        return seasonDTO;
    }

    @JsonProperty("season")
    public void setSeasonDTO(SeasonDTO seasonDTO) {
        this.seasonDTO = seasonDTO;
    }

    @JsonProperty("actors")
    public List<ActorDTO> getActorDTOs() {
        return actorDTOs;
    }

    @JsonProperty("actors")
    public void setActorDTOs(List<ActorDTO> actorDTOs) {
        this.actorDTOs = actorDTOs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}