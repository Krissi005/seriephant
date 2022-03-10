package de.dhbw.ase.seriephant.rating;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({

})
@Generated("jsonschema2pojo")
public final class RatingKeyDTO {

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("episodeId")
    private Long episodeId;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RatingKeyDTO(Long userId, Long episodeId) {
        this.userId = userId;
        this.episodeId = episodeId;
    }

    @JsonProperty("userId")
    public Long getUserId() {
        return userId;
    }

    @JsonProperty("episodeId")
    public Long getEpisodeId() {
        return episodeId;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}