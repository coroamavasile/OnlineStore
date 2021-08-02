package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.CredentialsDTO;
import com.ps.ElectronicsStore.dto.LoginSuccesDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import org.springframework.stereotype.Component;

@Component
public interface LoginService {
    LoginSuccesDTO login(CredentialsDTO dto, int loginCode) throws ApiExceptionResponse;
}