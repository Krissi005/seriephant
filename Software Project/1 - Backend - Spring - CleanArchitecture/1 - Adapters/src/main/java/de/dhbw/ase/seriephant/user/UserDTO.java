package de.dhbw.ase.seriephant.user;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
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
        "firstName",
        "lastName",
        "watchedEpisodes"
})
@Generated("jsonschema2pojo")
public class UserDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("watchedEpisodes")
    private List<EpisodeDTO> watchedEpisodeDTOs = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public UserDTO(Long id, String firstName, String lastName, List<EpisodeDTO> watchedEpisodeDTOs) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.watchedEpisodeDTOs = watchedEpisodeDTOs;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("watchedEpisodes")
    public List<EpisodeDTO> getWatchedEpisodeDTOs() {
        return watchedEpisodeDTOs;
    }

    @JsonProperty("watchedEpisodes")
    public void setWatchedEpisodeDTOs(List<EpisodeDTO> watchedEpisodeDTOs) {
        this.watchedEpisodeDTOs = watchedEpisodeDTOs;
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