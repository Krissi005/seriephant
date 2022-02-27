package de.dhbw.ase.seriephant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Column(name = "releaseYear")
    private Integer releaseYear;
    @ManyToOne
    @JsonIgnoreProperties("series")
    @JoinColumn(name="genre_id")
    private Genre genre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="serie")
    @JsonIgnoreProperties("episodes")
    private List<Season> seasons;

    public Serie(String title, String description){
        this(title,description,null, null);
    }

    public Serie(String title, String description, Integer releaseYear){
       this(title,description, releaseYear, null);
    }

    public Serie(String title, String description, Genre genre){
        this(title, description, null, genre);
    }

    public Serie(String title, String description, Integer releaseYear, Genre genre){
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }
}
