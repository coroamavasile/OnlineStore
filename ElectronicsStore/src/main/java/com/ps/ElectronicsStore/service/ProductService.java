package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.ProductDTO;
import com.ps.ElectronicsStore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductService {
    Product findByName(String name);

    Product findById(Long id);

    Product updatePrice(Product dto);

    List<Product> findAll();

    Product deleteById(Long id);

    Product addProductInCart(CartDTO cartDTO);

    Product delete(Product product);

    Product save(Product product);

    List<ProductDTO> fetchAllProductsByMatch(String text);
}
