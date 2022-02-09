package de.dhbw.ase.tracker.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO implements Serializable {
    private String firstName;
    private String lastName;
}