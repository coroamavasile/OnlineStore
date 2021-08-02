package com.ps.ElectronicsStore.dto;
import com.ps.ElectronicsStore.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class CartProductsDTO {
    private List<Product> products;
}
