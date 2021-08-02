package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.BlockDTO;
import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.PurchasedProductsDTO;
import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Product;
import com.ps.ElectronicsStore.model.Warranty;
import com.ps.ElectronicsStore.repository.CartRepository;
import com.ps.ElectronicsStore.repository.ClientRepository;
import com.ps.ElectronicsStore.repository.ProductRepository;
import com.ps.ElectronicsStore.repository.WarrantyRepository;
import com.ps.ElectronicsStore.service.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final WarrantyRepository warrantyRepository;

    public ClientServiceImpl(ClientRepository clientRepository, CartRepository cartRepository, ProductRepository productRepository, WarrantyRepository warrantyRepository) {
        this.clientRepository = clientRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.warrantyRepository = warrantyRepository;
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findFirstById(id);
    }

    @Override
    public Client findClientByUsername(String userName) {
        return clientRepository.findByUsername(userName);
    }

    @Override
    @Transactional
    public Client addProductByClientId(Long id, Product product) {
        Client client = clientRepository.findFirstById(id);
        Cart cart = client.getCart();
        cart.addProduct(product);
        cartRepository.save(cart);
        return client;
    }

    @Override
    @Transactional
    public Client removeProductByClientId(CartDTO cartDTO) {
        Client client = clientRepository.findFirstById(cartDTO.getIdClient());
        Cart cart = client.getCart();
        Product product = productRepository.findById(cartDTO.getIdProduct()).get();
        Warranty warranty = new Warranty(null, product.getName(), 367, new Date(), false, client);
        client.addWarranty1(warranty);
        cart.removeProduct(product);
        warrantyRepository.save(warranty);
        cartRepository.save(cart);
        return client;
    }

    @Override
    @Transactional
    public Client deleteClientById(Long id) {
        Client client = clientRepository.findById(id).get();
        clientRepository.delete(client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional
    public Client block(BlockDTO blockDTO) {
        Client client = clientRepository.findById(blockDTO.getId()).get();
        Boolean blocked = blockDTO.getBlocked();
        client.setBlocked(blocked);

        return client;
    }

    @Override
    @Transactional
    public Client updatePurchasedProducts(PurchasedProductsDTO dto) {
        Client client = clientRepository.findById(dto.getId()).get();
        Integer crt_value = client.getPurchasedProducts();
        Integer nrProducts = crt_value + dto.getPurchasedProducts();
        client.setPurchasedProducts(nrProducts);

        while (nrProducts >= 3) {
            client.setVoucher(client.getVoucher() + 100D);
            client.setPurchasedProducts(client.getPurchasedProducts() - 3);
            nrProducts = nrProducts - 3;
        }

        clientRepository.save(client);
        return client;
    }

    @Override
    @Transactional
    public Client updateVoucher(Long id, Double val) {
        Client client = clientRepository.findById(id).get();
        client.setVoucher(val);
        clientRepository.save(client);
        return client;
    }
}
