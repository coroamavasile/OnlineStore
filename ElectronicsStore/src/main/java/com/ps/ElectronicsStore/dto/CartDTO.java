package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartDTO {
    private Long idClient;
    private Long idProduct;
}
