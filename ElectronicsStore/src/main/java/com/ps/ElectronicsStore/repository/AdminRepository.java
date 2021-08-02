package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Admin;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
    Admin findFirstById(Long id);
    Admin findByUsername(String username);
    List<Admin> findAllByFirstName(String firstName);
    List<Admin> findAll();
}
