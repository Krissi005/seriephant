package de.dhbw.ase.tracker.tracker.model;

import de.dhbw.ase.tracker.tracker.model.listener.GenreListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="genre")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(GenreListener.class)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;

    public Genre(String title, String description){
        this.title = title;
        this.description = description;
    }
}
