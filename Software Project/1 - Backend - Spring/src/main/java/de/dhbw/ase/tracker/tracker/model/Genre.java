package de.dhbw.ase.tracker.tracker.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.dhbw.ase.tracker.tracker.model.listener.GenreListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Table(name="genre")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(GenreListener.class)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy="genre")
    private List<Serie> series;

    public Genre(String title, String description){
        this.title = title;
        this.description = description;
    }

    @PreRemove
    public void preRemove(){
        series.forEach(serie -> serie.setGenre(null));
    }
}
