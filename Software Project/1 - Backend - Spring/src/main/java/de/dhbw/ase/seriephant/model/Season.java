package de.dhbw.ase.seriephant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name="season")
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy="season")
    @JsonIgnoreProperties("season")
    private List<Episode> episodes;
    @ManyToOne
    @JsonIgnoreProperties("seasons")
    @JoinColumn(name="serie_id", nullable = false)
    private Serie serie;

    public Season(Integer seasonNumber, Serie serie){
        this.seasonNumber = seasonNumber;
        this.serie = serie;
    }
}
