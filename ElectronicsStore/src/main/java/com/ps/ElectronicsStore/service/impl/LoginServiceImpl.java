package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.CredentialsDTO;
import com.ps.ElectronicsStore.dto.LoginSuccesDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import com.ps.ElectronicsStore.model.ConnectionTime;
import com.ps.ElectronicsStore.model.User;
import com.ps.ElectronicsStore.repository.UserRepository;
import com.ps.ElectronicsStore.service.ConnectionTimeService;
import com.ps.ElectronicsStore.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.Locale;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final ConnectionTimeService connectionTimeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginServiceImpl(UserRepository userRepository, ConnectionTimeService connectionTimeService) {
        this.userRepository = userRepository;
        this.connectionTimeService = connectionTimeService;
    }


    @Override
    public LoginSuccesDTO login(CredentialsDTO dto, int loginCode) throws ApiExceptionResponse {
        User user = userRepository.findByUsername(dto.getUsername());

        if (user == null) {
            throw ApiExceptionResponse.builder().errors(Collections.singletonList("Bad credentials"))
                    .message("User not found").status(HttpStatus.NOT_FOUND).build();
        }

        LoginSuccesDTO response;
        String role = user.getClass().getSimpleName().toUpperCase(Locale.ROOT);
        if (role.equals("ADMIN")) {
            response = LoginSuccesDTO.builder().id(user.getId()).role(role).blocked(user.getBlocked()).autentificationCodeAdmin(loginCode).build();
        } else {
            response = LoginSuccesDTO.builder().id(user.getId()).role("CLIENT").blocked(user.getBlocked()).build();
        }

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            connectionTimeService.save(new ConnectionTime(null, new Date(), null, false, user));
            return response;
        }
        throw ApiExceptionResponse.builder().errors(Collections.singletonList("Bad credentials"))
                .message("User not found").status(HttpStatus.NOT_FOUND).build();
    }
}
