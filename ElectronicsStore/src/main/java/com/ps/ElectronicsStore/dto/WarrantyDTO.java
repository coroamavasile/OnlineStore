package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class WarrantyDTO {
    private Long id;
    private String productName;
    private Integer days;
    private Date date;
    private Boolean expired;

}
