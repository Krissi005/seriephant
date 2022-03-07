package de.dhbw.ase.seriephant.plugins.persistence.hibernate.serie;

import de.dhbw.ase.seriephant.domain.serie.Serie;
import de.dhbw.ase.seriephant.domain.serie.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SerieRepositoryBridge implements SerieRepository {
    private SpringDataSerieRepository springDataSerieRepository;

    @Autowired
    public SerieRepositoryBridge(SpringDataSerieRepository springDataSerieRepository) {
        this.springDataSerieRepository = springDataSerieRepository;
    }

    @Override
    public boolean existsById(Long serieId) {
        return this.springDataSerieRepository.existsById(serieId);
    }

    @Override
    public Serie save(Serie serie) {
        return this.springDataSerieRepository.saveAndFlush(serie);
    }

    @Override
    public Serie getById(Long serieId) {
        return this.springDataSerieRepository.getById(serieId);
    }

    @Override
    public List<Serie> getSeriesByTitleAndAndReleaseYear(String title, Integer releaseYear) {
        return this.springDataSerieRepository.getSeriesByTitleAndAndReleaseYear(title, releaseYear);
    }

    @Override
    public List<Serie> findAll() {
        return this.springDataSerieRepository.findAll();
    }

    @Override
    public void deleteById(Long serieId) {
        this.springDataSerieRepository.deleteById(serieId);
    }
}
