package de.dhbw.ase.plugins.persistence.hibernate.season;

import de.dhbw.ase.seriephant.domain.season.Season;
import de.dhbw.ase.seriephant.domain.season.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeasonRepositoryBridge implements SeasonRepository {
    private SpringDataSeasonRepository springDataSeasonRepository;

    @Autowired
    public SeasonRepositoryBridge(SpringDataSeasonRepository springDataSeasonRepository) {
        this.springDataSeasonRepository = springDataSeasonRepository;
    }

    @Override
    public boolean existsById(Long seasonId) {
        return this.springDataSeasonRepository.existsById(seasonId);
    }

    @Override
    public Season save(Season season) {
        return this.springDataSeasonRepository.saveAndFlush(season);
    }

    @Override
    public Season getById(Long seasonId) {
        return this.springDataSeasonRepository.getById(seasonId);
    }

    @Override
    public List<Season> findAll() {
        return this.springDataSeasonRepository.findAll();
    }

    @Override
    public void deleteById(Long seasonId) {
        this.springDataSeasonRepository.deleteById(seasonId);
    }
}
