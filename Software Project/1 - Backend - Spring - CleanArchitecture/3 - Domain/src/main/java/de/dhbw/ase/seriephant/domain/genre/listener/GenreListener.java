package de.dhbw.ase.seriephant.domain.genre.listener;

import de.dhbw.ase.seriephant.domain.genre.Genre;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.persistence.PrePersist;

public class GenreListener {
    private static Log log = LogFactory.getLog(GenreListener.class);

    @PrePersist
    private void beforeAnyUpdate(Genre genre) {
        log.info("[GENRE AUDIT] About to add a genre");
    }
}
