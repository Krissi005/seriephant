package de.dhbw.ase.seriephant.plugins.persistence.hibernate.episode;

import de.dhbw.ase.seriephant.domain.episode.Episode;
import de.dhbw.ase.seriephant.domain.episode.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EpisodeRepositoryBridge implements EpisodeRepository {
    private SpringDataEpisodeRepository springDataEpisodeRepository;

    @Autowired
    public EpisodeRepositoryBridge(SpringDataEpisodeRepository springDataEpisodeRepository) {
        this.springDataEpisodeRepository = springDataEpisodeRepository;
    }

    @Override
    public boolean existsById(Long episodeId) {
        return this.springDataEpisodeRepository.existsById(episodeId);
    }

    @Override
    public Episode save(Episode episodeToCreate) {
        return this.springDataEpisodeRepository.saveAndFlush(episodeToCreate);
    }

    @Override
    public Episode getById(Long episodeId) {
        return this.springDataEpisodeRepository.getById(episodeId);
    }

    @Override
    public List<Episode> getEpisodesByUsersEquals(Long userId) {
        return this.springDataEpisodeRepository.getEpisodesByUsersEquals(userId);
    }

    @Override
    public List<Episode> getEpisodesByUserNotEquals(Long userId) {
        return this.springDataEpisodeRepository.getEpisodesByUsersIsNullOrUsersIsNot(userId);
    }

    @Override
    public List<Episode> getEpisodesByTitle(String title) {
        return this.springDataEpisodeRepository.getEpisodesByTitle(title);
    }

    @Override
    public List<Episode> findAll() {
        return this.springDataEpisodeRepository.findAll();
    }

    @Override
    public void deleteById(Long episodeId) {
        this.springDataEpisodeRepository.deleteById(episodeId);
    }
}
