package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartService {
    Cart findById(Long id);
    Cart findByIdClient(Long id);
    List <Cart> findAll();
    Cart save(Cart cart);
}
