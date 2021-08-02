package com.ps.ElectronicsStore.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Telephone extends Product {
    @NotNull
    private String specifications;
    @NotNull
    private String operatingSystem;
    @NotNull
    private String company;

    public Telephone(Long id, String nume, Double pret, String specificatii1, String SO, String companie1) {
        super(id, nume, pret, null);
        specifications = specificatii1;
        operatingSystem = SO;
        company = companie1;
    }
}
