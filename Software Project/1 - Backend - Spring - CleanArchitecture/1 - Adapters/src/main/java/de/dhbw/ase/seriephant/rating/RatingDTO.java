package de.dhbw.ase.seriephant.rating;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.episode.EpisodeDTO;
import de.dhbw.ase.seriephant.user.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "user",
        "episode",
        "rating"
})
@Generated("jsonschema2pojo")
public final class RatingDTO {

    @JsonProperty("id")
    private RatingKeyDTO ratingKeyDTO;
    @JsonProperty("user")
    @JsonIgnoreProperties({"watchedEpisodes"})
    private UserDTO userDTO;
    @JsonIgnoreProperties({"actors"})
    @JsonProperty("episode")
    private EpisodeDTO episodeDTO;
    @JsonProperty("rating")
    private Double rating;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RatingDTO(RatingKeyDTO ratingKeyDTO, UserDTO userDTO, EpisodeDTO episodeDTO, Double rating) {
        this.ratingKeyDTO = ratingKeyDTO;
        this.userDTO = userDTO;
        this.episodeDTO = episodeDTO;
        this.rating = rating;
    }

    @JsonProperty("id")
    public RatingKeyDTO getRatingKeyDTO() {
        return ratingKeyDTO;
    }

    @JsonProperty("user")
    public UserDTO getUserDTO() {
        return userDTO;
    }

    @JsonProperty("episode")
    public EpisodeDTO getEpisodeDTO() {
        return episodeDTO;
    }

    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}