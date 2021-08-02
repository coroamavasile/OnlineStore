package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    Cart findFirstById(Long id);
    List<Cart> findAll();
}
