package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Warranty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WarrantyRepository extends CrudRepository<Warranty, Long> {
    Warranty findByProductName(String productName);

    List<Warranty> findAll();
}
