package de.dhbw.ase.tracker.tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private long id;
    @Column(name = "seasonNumber")
    private Integer seasonNumber;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="season")
    private List<Episode> episodes;

    public Season(int seasonNumber){
        this.seasonNumber = seasonNumber;
    }
}
