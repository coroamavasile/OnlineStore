package com.ps.ElectronicsStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Client extends User {

    @NotNull
    private String address;
    @NotNull
    private Integer age;
    @Size(min = 10, max = 10, message = "Length must be 10")
    private String telephone;
    private Double voucher = new Double(0D);

    private Integer purchasedProducts = new Integer((0));

    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    private Cart cart = new Cart();

    @JsonIgnore
    @OneToMany(targetEntity = Warranty.class, mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Warranty> warranties = new ArrayList<>();

    public Client(Long id, String name, String prenume, String email, String nume_utilizator, String parola,
                  String telefon, Integer varsta1, String adresa1, Boolean blocked, Boolean connected) {
        super(id, name, prenume, email, nume_utilizator, parola, blocked, connected);
        this.address = adresa1;
        this.age = varsta1;
        this.telephone = telefon;
        if (purchasedProducts == null)
            this.purchasedProducts = new Integer(0);
        this.voucher = new Double(0d);
    }

    public void addWarranty1(Warranty warranty) {
        warranty.setClient(this);
        warranties.add(warranty);
    }

}

