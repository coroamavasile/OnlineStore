package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.AdminWarrantyDTO;
import com.ps.ElectronicsStore.dto.WarrantyRepairRequestDTO;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Warranty;
import com.ps.ElectronicsStore.repository.ClientRepository;
import com.ps.ElectronicsStore.repository.WarrantyRepository;
import com.ps.ElectronicsStore.service.WarrantyService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;
import javax.transaction.Transactional;

@Service
public class WarrantyServiceImpl implements WarrantyService {

    private final WarrantyRepository warrantyRepository;
    private final JavaMailSender javaMailSender;
    private final ClientRepository clientRepository;

    public WarrantyServiceImpl(WarrantyRepository warrantyRepository, JavaMailSender javaMailSender, ClientRepository clientRepository) {
        this.warrantyRepository = warrantyRepository;
        this.javaMailSender = javaMailSender;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional
    public Warranty save(Warranty dto) {
        return warrantyRepository.save(dto);
    }

    @Override
    public List<Warranty> findAll() {
        return warrantyRepository.findAll();
    }

    @Override
    @Transactional
    public Warranty addWarranty(Long idClient, Warranty warranty) {
        Client client = clientRepository.findById(idClient).get();
        client.addWarranty1(warranty);
        return warranty;
    }

    @Override
    @Transactional
    public Warranty modifyRepairRequest(WarrantyRepairRequestDTO dto) {
        Warranty warranty = warrantyRepository.findById(dto.getWarrantyId()).get();
        warranty.setRepairRequest(dto.getRepairRequest());
        warrantyRepository.save(warranty);
        return warranty;
    }

    @Override
    public List<AdminWarrantyDTO> findAllWarrantyForAdmin() {
        List<Warranty> warranties = findAll();
        List<AdminWarrantyDTO> rezult = new ArrayList<>();

        for (Warranty w : warranties) {
            if (w.getRepairRequest()) {
                Client client = w.getClient();
                AdminWarrantyDTO aux = new AdminWarrantyDTO(w.getId(), client.getFirstName(), client.getLastName(),
                        client.getUsername(), client.getAddress(), w.getProductName(), w.getDate(), w.getDays());

                rezult.add(aux);
            }
        }

        return rezult;
    }

    @Override
    public Warranty sendEmail(Long id) {
        Warranty warranty = warrantyRepository.findById(id).get();
        Client client = warranty.getClient();
        String firstName = client.getFirstName();
        String address = client.getAddress();
        String productName = warranty.getProductName();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("coroama.vasile1@gmail.com");//aici ar trebui mail-ul clientului
        String mesaj = "Salutare, " + firstName + "\n" + "Cererea ta de garantie petru produsul " + productName + " a " +
                "fost aprobata!\n Iti trimitem un curier la adresa " + address + " in urmatoarele 3 zile!\n" +
                "Va multumim!";
        msg.setSubject("Garantie");
        msg.setText(mesaj);
        javaMailSender.send(msg);

        return warranty;
    }
}
