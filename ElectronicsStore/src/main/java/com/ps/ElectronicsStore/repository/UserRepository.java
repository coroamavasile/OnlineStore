package com.ps.ElectronicsStore.repository;

import com.ps.ElectronicsStore.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    User findFirstByFirstName(String firstName);
    User findByUsername(String username);
    User findByUsernameAndPassword(String userName, String password);
    List<User> findAll();
}
