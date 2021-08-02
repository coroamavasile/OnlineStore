package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.repository.CartRepository;
import com.ps.ElectronicsStore.repository.ClientRepository;
import com.ps.ElectronicsStore.service.CartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;

    public CartServiceImpl(CartRepository cartRepository, ClientRepository clientRepository) {
        this.cartRepository = cartRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart findByIdClient(Long id) {
        Client client = clientRepository.findById(id).get();
        Cart cart = client.getCart();
        return cart;
    }

    @Override
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }
}
