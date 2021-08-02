package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Product;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    Product findFirstByName(String nume);
    Product findFirstById(Long id);
    List<Product> findAll();
}
