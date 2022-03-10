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
public final class UserDTO {

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

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("watchedEpisodes")
    public List<EpisodeDTO> getWatchedEpisodeDTOs() {
        return watchedEpisodeDTOs;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
}