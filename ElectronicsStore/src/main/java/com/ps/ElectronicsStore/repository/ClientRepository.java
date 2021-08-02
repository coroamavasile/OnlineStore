package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findFirstByFirstName(String firstName);

    Client findFirstById(Long id);

    Client findByUsername(String userName);

    List<Client> findAllByFirstName(String firstName);

    List<Client> findAll();
}
