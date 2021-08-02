package com.ps.ElectronicsStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double totalPrice = 0.0;

    @OneToMany(targetEntity = Product.class, mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Product> products;

    public void addProduct(Product produs1) {
        produs1.setCart(this);
        products.add(produs1);
        calcularePret();

    }

    public void removeProduct(Product product) {
        product.setCart(null);
        products.remove(product);
        calcularePret();
    }

    public void calcularePret() {
        totalPrice = 0.0D;
        for (Product pr1 : products) {
            totalPrice = totalPrice + pr1.getPrice();
        }

    }
}
