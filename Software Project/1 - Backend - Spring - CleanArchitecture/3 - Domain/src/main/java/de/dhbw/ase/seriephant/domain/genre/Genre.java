package de.dhbw.ase.seriephant.domain.genre;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.dhbw.ase.seriephant.domain.genre.listener.GenreListener;
import de.dhbw.ase.seriephant.domain.serie.Serie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "genre")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(GenreListener.class)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "genre")
    @JsonIgnoreProperties("genre")
    private List<Serie> series;

    public Genre(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Genre(String title, String description) {
        this.title = title;
        this.description = description;
        this.series = new ArrayList<>();
    }

    @PreRemove
    public void preRemove() {
        this.series.forEach(serie -> serie.setGenre(null));
    }
}
