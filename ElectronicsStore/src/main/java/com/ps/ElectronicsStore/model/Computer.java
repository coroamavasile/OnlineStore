package com.ps.ElectronicsStore.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Computer extends Product {
    @NotNull
    private String specifications;
    @NotNull
    private String color;
    @NotNull
    private String year;

    public Computer(Long id, String nume, Double pret, String specificatii, String color, String anul) {
        super(id, nume, pret, null);
        this.specifications = specificatii;
        this.color = color;
        this.year = anul;
    }
}
