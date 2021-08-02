package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.model.Computer;
import com.ps.ElectronicsStore.model.Telephone;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface TelephoneService {
    Telephone findByName(String name);

    Telephone delete(Long id);

    List<Telephone> findAll();

    Telephone save(Telephone dto);

    Telephone updatePret(@PathVariable Long id, @RequestBody Telephone dto);

    List<Telephone> priceFilter(FilterDTO filterDTO);

}
