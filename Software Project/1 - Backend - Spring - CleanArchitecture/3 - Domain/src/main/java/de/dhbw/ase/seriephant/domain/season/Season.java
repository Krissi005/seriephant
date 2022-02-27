package de.dhbw.ase.seriephant.domain.season;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.serie.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name = "season")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seasonNumber")
    private Integer seasonNumber;

    @ManyToOne
    @JsonIgnoreProperties("seasons")
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "season")
    @JsonIgnoreProperties("season")
    private List<Episode> episodes;

    public Season(Integer seasonNumber, Serie serie) {
        this.seasonNumber = seasonNumber;
        this.serie = serie;
    }

    public Season(Long id, Integer seasonNumber, Serie serie) {
        this.id = id;
        this.seasonNumber = seasonNumber;
        this.serie = serie;
    }
}
