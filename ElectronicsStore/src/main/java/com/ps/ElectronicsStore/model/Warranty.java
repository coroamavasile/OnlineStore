package com.ps.ElectronicsStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Warranty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private Integer days;
    private Date date = new Date();
    private Boolean repairRequest;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "client_id")
    private Client client;
}
