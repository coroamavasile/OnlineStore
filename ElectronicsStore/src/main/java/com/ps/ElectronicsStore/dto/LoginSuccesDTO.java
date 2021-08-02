package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class LoginSuccesDTO {
    private String role;
    private Long id;
    private Integer autentificationCodeAdmin;
    private Boolean blocked;
}
