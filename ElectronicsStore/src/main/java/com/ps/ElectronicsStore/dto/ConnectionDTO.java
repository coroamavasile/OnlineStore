package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ConnectionDTO {
    private Long id;
    private Boolean connected;
}
