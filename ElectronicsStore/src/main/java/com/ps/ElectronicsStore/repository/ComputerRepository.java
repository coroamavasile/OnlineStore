package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Computer;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends CrudRepository<Computer,Long> {
    Computer findFirstById(Long id);
    Computer findFirstByName(String nume);
    List<Computer> findAllByName(String nume);
    List<Computer> findAll();
    Computer findFirstByIdAndColor(Long id, String color);

}
