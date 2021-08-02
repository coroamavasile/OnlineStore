package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.FilterDTO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;
import com.ps.ElectronicsStore.model.*;
import java.util.*;
@Component
public interface ComponentService {
    List<com.ps.ElectronicsStore.model.Component> findAll();
    com.ps.ElectronicsStore.model.Component save(com.ps.ElectronicsStore.model.Component component);
    com.ps.ElectronicsStore.model.Component updatePrice(com.ps.ElectronicsStore.model.Component dto);
    com.ps.ElectronicsStore.model.Component delete(Long id);
    List<com.ps.ElectronicsStore.model.Component> priceFilter(FilterDTO filterDTO);
}
