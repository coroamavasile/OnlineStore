package com.ps.ElectronicsStore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Component extends Product{
    private String specifications;

    public Component(Long id, String nume, Double pret,String specificatii1)
    {
        super(id,nume,pret,null);
        specifications = specificatii1;

    }
}
