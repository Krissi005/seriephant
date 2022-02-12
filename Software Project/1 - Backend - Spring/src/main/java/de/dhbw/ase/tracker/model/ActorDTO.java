package de.dhbw.ase.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ActorDTO implements Serializable {
    private String firstName;
    private String lastName;
}
