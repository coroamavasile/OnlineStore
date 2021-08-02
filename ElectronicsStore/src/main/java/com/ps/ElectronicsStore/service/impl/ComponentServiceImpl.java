package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.controller.ComponentController;
import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.model.Component;
import com.ps.ElectronicsStore.model.Telephone;
import com.ps.ElectronicsStore.repository.ComponentRepository;
import com.ps.ElectronicsStore.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ComponentServiceImpl implements ComponentService {
    @Autowired
    private SimpMessagingTemplate template;

    private final ComponentRepository componentRepository;

    public ComponentServiceImpl(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public List<Component> findAll() {
        return componentRepository.findAll();
    }

    @Override
    public Component save(Component component) {
        return componentRepository.save(component);
    }

    @Override
    @Transactional
    public Component updatePrice(Component dto) {
        Component component = componentRepository.findById(dto.getId()).get();
        double old_price = component.getPrice();
        component.setPrice(dto.getPrice());

        String MESSAGE =
                component.getName() + " price was modified (old price: " + old_price + " new price: " + dto.getPrice() +
                        ")";
        template.convertAndSend("/topic/socket/components", MESSAGE);

        return component;
    }

    @Override
    @Transactional
    public Component delete(Long id) {
        Component component = componentRepository.findById(id).get();
        componentRepository.delete(component);
        return component;
    }

    @Override
    public List<Component> priceFilter(FilterDTO filterDTO) {
        Double minValue = filterDTO.getMinPrice();
        Double maxValue = filterDTO.getMaxPrice();
        List<Component> result = new ArrayList<Component>();
        List<Component> components = componentRepository.findAll();

        for (Component component : components) {
            if (component.getPrice() >= minValue && component.getPrice() <= maxValue) {
                result.add(component);
            }
        }

        return result;
    }
}
