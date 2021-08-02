package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.model.Computer;
import com.ps.ElectronicsStore.repository.ComputerRepository;
import com.ps.ElectronicsStore.service.ComputerService;
import jdk.jfr.Timespan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    private SimpMessagingTemplate template;

    private final ComputerRepository computerRepository;

    public ComputerServiceImpl(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    @Override
    public Computer findByName(String name) {
        return computerRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public Computer deleteCalculator(Long id) {
        Computer computer = computerRepository.findById(id).get();
        computerRepository.delete(computer);
        return computer;
    }

    @Override
    public List<Computer> findAll() {
        return computerRepository.findAll();
    }

    @Override
    public Computer findCalculatorByIdAndColor(Long id, String culoare) {
        return null;
    }

    @Override
    public Computer save(Computer dto) {
        return computerRepository.save(dto);
    }

    @Override
    @Transactional
    public Computer updatePret(Long id, Computer dto) {
        Computer calculator = computerRepository.findById(id).get();
        double old_price = calculator.getPrice();

        calculator.setPrice(dto.getPrice());
        String MESSAGE =
                calculator.getName() + " price was modified (old price: " + old_price + " new price: " + dto.getPrice() + ")";
        template.convertAndSend("/topic/socket/computers", MESSAGE);
        return calculator;
    }

    @Override
    public List<Computer> priceFilter(FilterDTO filterDTO) {
        Double minValue = filterDTO.getMinPrice();
        Double maxValue = filterDTO.getMaxPrice();
        List<Computer> result = new ArrayList<Computer>();
        List<Computer> computers = computerRepository.findAll();

        for (Computer computer : computers) {
            if (computer.getPrice() >= minValue && computer.getPrice() <= maxValue) {
                result.add(computer);
            }
        }

        return result;
    }
}
