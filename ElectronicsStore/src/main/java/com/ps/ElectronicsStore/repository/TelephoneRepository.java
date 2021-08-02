package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Telephone;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelephoneRepository extends CrudRepository<Telephone,Long> {
    Telephone findFistById(Long id);
    Telephone findFirstByName(String nume);
    List<Telephone> findAll();
}
