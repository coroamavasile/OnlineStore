package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.ConnectionTimeDTO;
import com.ps.ElectronicsStore.dto.ConnectionTimeUpdateDTO;
import com.ps.ElectronicsStore.model.ConnectionTime;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ConnectionTimeService {
    ConnectionTime save(ConnectionTime connectionTime);

    ConnectionTime updateLogout(ConnectionTimeUpdateDTO dto);

    String exportTimeStamps(String fileType);

    List<ConnectionTimeDTO> findAll();
}
