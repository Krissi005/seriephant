package de.dhbw.ase.seriephant.serie;

import com.fasterxml.jackson.annotation.*;
import de.dhbw.ase.seriephant.genre.GenreDTO;
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
        "title",
        "description",
        "releaseYear",
        "genre"
})
@Generated("jsonschema2pojo")
public final class SerieDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("releaseYear")
    private Integer releaseYear;
    @JsonProperty("genre")
    private GenreDTO genreDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public SerieDTO(Long id, String title, String description, Integer releaseYear, GenreDTO genreDTO) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genreDTO = genreDTO;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("releaseYear")
    public Integer getReleaseYear() {
        return releaseYear;
    }

    @JsonProperty("genre")
    public GenreDTO getGenreDTO() {
        return genreDTO;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

}