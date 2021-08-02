package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.model.Computer;
import com.ps.ElectronicsStore.model.Telephone;
import com.ps.ElectronicsStore.repository.TelephoneRepository;
import com.ps.ElectronicsStore.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TelephoneServiceImpl implements TelephoneService {
    @Autowired
    private SimpMessagingTemplate template;

    private final TelephoneRepository telephoneRepository;

    public TelephoneServiceImpl(TelephoneRepository telefonRepository) {
        this.telephoneRepository = telefonRepository;
    }

    @Override
    public Telephone findByName(String name) {
        return telephoneRepository.findFirstByName(name);
    }

    @Override
    @Transactional
    public Telephone delete(Long id) {
        Telephone telephone = telephoneRepository.findById(id).get();
        telephoneRepository.delete(telephone);
        return telephone;
    }

    @Override
    public List<Telephone> findAll() {
        return telephoneRepository.findAll();
    }

    @Override
    @Transactional
    public Telephone save(Telephone dto) {
        return telephoneRepository.save(dto);
    }

    @Override
    @Transactional
    public Telephone updatePret(Long id, Telephone dto) {
        Telephone telephone = telephoneRepository.findById(id).get();
        double old_price = telephone.getPrice();
        telephone.setPrice(dto.getPrice());

        String MESSAGE =
                telephone.getName() + " price was modified (old price: " + old_price + " new price: " + dto.getPrice() + ")";
        template.convertAndSend("/topic/socket/telephones", MESSAGE);

        return telephone;
    }

    @Override
    public List<Telephone> priceFilter(FilterDTO filterDTO) {
        Double minValue = filterDTO.getMinPrice();
        Double maxValue = filterDTO.getMaxPrice();
        List<Telephone> result = new ArrayList<Telephone>();
        List<Telephone> telephones = telephoneRepository.findAll();

        for (Telephone telephone : telephones) {
            if (telephone.getPrice() >= minValue && telephone.getPrice() <= maxValue) {
                result.add(telephone);
            }
        }

        return result;
    }
}
