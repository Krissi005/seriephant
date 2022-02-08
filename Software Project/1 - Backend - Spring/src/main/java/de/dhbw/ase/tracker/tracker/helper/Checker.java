package de.dhbw.ase.tracker.tracker.helper;

import de.dhbw.ase.tracker.tracker.model.Season;
import de.dhbw.ase.tracker.tracker.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.xml.bind.ValidationException;

@Component
public class Checker {
    static SeasonRepository seasonRepository;

    @Autowired
    private SeasonRepository seasonRepositoryInitalize;

    @PostConstruct
    public void init() {
        Checker.seasonRepository = seasonRepositoryInitalize;
    }

    public static Season getSeasonById(Long id) throws ValidationException {
        if (id != null && seasonRepository.existsById(id)) {
            return seasonRepository.getById(id);
        }
        throw new ValidationException("SeasonId is not known.");
    }
}
