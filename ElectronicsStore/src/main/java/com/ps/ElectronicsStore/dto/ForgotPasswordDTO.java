package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ForgotPasswordDTO {
    private String username;
    private Integer codeValidation;
}
