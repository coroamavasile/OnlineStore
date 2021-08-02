package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class AdminWarrantyDTO {
    private Long idWarranty;
    private String firstName;
    private String lastName;
    private String username;
    private String address;
    private String productName;
    private Date data;
    private Integer days;
}
