package com.ps.ElectronicsStore.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User {

    @Builder
    public Admin(Long id, String name, String prenume, String email, String nume_utilizator, String parola,
                 Boolean blocked, Boolean connected) {
        super(id, name, prenume, email, nume_utilizator, parola, blocked, connected);
    }

}
