package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.model.Computer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public interface ComputerService {
    Computer findByName(String name);
    Computer deleteCalculator(Long id);
    List<Computer> findAll();
    Computer findCalculatorByIdAndColor(Long id, String culoare);
    Computer save(Computer dto);
    Computer updatePret(@PathVariable Long id, @RequestBody Computer dto);
    List<Computer> priceFilter(FilterDTO filterDTO);
}
