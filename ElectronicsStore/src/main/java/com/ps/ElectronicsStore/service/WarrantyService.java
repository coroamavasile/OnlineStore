package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.AdminWarrantyDTO;
import com.ps.ElectronicsStore.dto.WarrantyRepairRequestDTO;
import com.ps.ElectronicsStore.model.Warranty;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public interface WarrantyService {
    Warranty save(Warranty dto);

    List<Warranty> findAll();

    Warranty addWarranty(Long idClient, Warranty warranty);

    Warranty modifyRepairRequest(WarrantyRepairRequestDTO dto);

    List<AdminWarrantyDTO> findAllWarrantyForAdmin();

    Warranty sendEmail(Long id);
}
