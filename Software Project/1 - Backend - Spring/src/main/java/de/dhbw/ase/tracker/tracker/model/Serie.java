package de.dhbw.ase.tracker.tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name="serie")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name="genre_id")
    private Genre genre;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy="serie")
    private List<Season> seasons;

    public Serie(String title, String description){
        this.title = title;
        this.description = description;
    }

    public Serie(String title, String description, Genre genre){
        this.title = title;
        this.description = description;
        this.genre = genre;
    }
}
