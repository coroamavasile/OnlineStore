package com.ps.ElectronicsStore.service;

import com.ps.ElectronicsStore.dto.BlockDTO;
import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.PurchasedProductsDTO;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientService {
    Client findById(Long id);

    Client findClientByUsername(String userName);

    Client addProductByClientId(Long id, Product product);

    Client removeProductByClientId(CartDTO cartDTO);

    Client deleteClientById(Long id);

    List<Client> findAll();

    Client save(Client client);

    Client block(BlockDTO blockDTO);

    Client updatePurchasedProducts(PurchasedProductsDTO dto);

    Client updateVoucher(Long id, Double val);
}
