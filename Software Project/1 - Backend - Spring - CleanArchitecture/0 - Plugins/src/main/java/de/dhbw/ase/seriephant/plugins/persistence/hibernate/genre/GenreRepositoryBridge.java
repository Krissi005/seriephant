package de.dhbw.ase.seriephant.plugins.persistence.hibernate.genre;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import de.dhbw.ase.seriephant.domain.genre.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GenreRepositoryBridge implements GenreRepository {
    private SpringDataGenreRepository springDataGenreRepository;

    @Autowired
    public GenreRepositoryBridge(SpringDataGenreRepository springDataGenreRepository) {
        this.springDataGenreRepository = springDataGenreRepository;
    }

    @Override
    public boolean existsById(Long genreId) {
        return this.springDataGenreRepository.existsById(genreId);
    }

    @Override
    public Genre save(Genre genre) {
        return this.springDataGenreRepository.saveAndFlush(genre);
    }

    @Override
    public Genre getById(Long genreId) {
        return this.springDataGenreRepository.getById(genreId);
    }

    @Override
    public List<Genre> getGenresByTitle(String title) {
        return this.springDataGenreRepository.getGenresByTitle(title);
    }

    @Override
    public List<Genre> findAll() {
        return this.springDataGenreRepository.findAll();
    }

    @Override
    public void deleteById(Long genreId) {
        this.springDataGenreRepository.deleteById(genreId);
    }
}
