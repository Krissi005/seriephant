package de.dhbw.ase.seriephant.season;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.serie.SerieDTO;
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
        "seasonNumber",
        "serie"
})
@Generated("jsonschema2pojo")
public class SeasonDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("seasonNumber")
    private Integer seasonNumber;
    @JsonProperty("serie")
    private SerieDTO serieDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SeasonDTO(Long id, Integer seasonNumber, SerieDTO serieDTO) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.serieDTO = serieDTO;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("seasonNumber")
    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    @JsonProperty("serie")
    public SerieDTO getSerieDTO() {
        return serieDTO;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}