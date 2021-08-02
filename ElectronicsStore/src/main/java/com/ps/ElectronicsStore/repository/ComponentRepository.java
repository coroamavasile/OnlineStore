package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Component;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentRepository extends CrudRepository<Component,Long> {
    Component findByName(String NAME);
    Component findFirstById(Long id);
    List<Component> findAll();
}
