package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.ConnectionDTO;
import com.ps.ElectronicsStore.dto.CredentialsDTO;
import com.ps.ElectronicsStore.model.User;
import com.ps.ElectronicsStore.repository.UserRepository;
import com.ps.ElectronicsStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByFirstName(String firstName) {
        return userRepository.findFirstByFirstName(firstName);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User dto) {
        return userRepository.save(dto);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    @Transactional
    public User changePassword(CredentialsDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    @Override
    @Transactional
    public User changeConnectionState(ConnectionDTO dto) {
        User user = userRepository.findById(dto.getId()).get();
        user.setConected(dto.getConnected());
        userRepository.save(user);
        return user;
    }
}
