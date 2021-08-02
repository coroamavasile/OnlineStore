package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.ConnectionDTO;
import com.ps.ElectronicsStore.dto.CredentialsDTO;
import com.ps.ElectronicsStore.dto.LoginSuccesDTO;
import com.ps.ElectronicsStore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User findByFirstName(String firstName);

    User findByUsername(String username);

    List<User> findAll();

    User save(User dto);

    User findByUsernameAndPassword(String username, String password);

    User changePassword(CredentialsDTO dto);

    User changeConnectionState(ConnectionDTO dto);

}
