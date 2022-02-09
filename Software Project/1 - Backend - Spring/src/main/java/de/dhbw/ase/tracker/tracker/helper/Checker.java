package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.model.Serie;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import de.dhbw.ase.tracker.tracker.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;

@Component
public class Checker {
    static SeasonRepository seasonRepository;
    static SerieRepository serieRepository;

    @Autowired
    private SeasonRepository seasonRepositoryInitalize;
    private SerieRepository serieRepositoryInitalize;

    @PostConstruct
    public void init() {
        Checker.seasonRepository = seasonRepositoryInitalize;
        Checker.serieRepository = serieRepositoryInitalize;
    }

    public static Season getSeasonById(Long id) throws ValidationException {
        if (id != null && seasonRepository.existsById(id)) {
            return seasonRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }

    public static Serie getSerieById(Long id) throws ValidationException {
        if (id != null && serieRepository.existsById(id)) {
            return serieRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }
}
