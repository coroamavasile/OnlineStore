package com.ps.ElectronicsStore.service.impl;

import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.ProductDTO;
import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Product;
import com.ps.ElectronicsStore.repository.ClientRepository;
import com.ps.ElectronicsStore.repository.ProductRepository;
import com.ps.ElectronicsStore.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public ProductServiceImpl(ProductRepository productRepository, ClientRepository clientRepository) {
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Product findByName(String name) {
        return productRepository.findFirstByName(name);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findFirstById(id);
    }

    @Override
    @Transactional
    public Product updatePrice(Product dto) {
        Product product = productRepository.findById(dto.getId()).get();
        product.setPrice(dto.getPrice());
        productRepository.save(dto);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public Product delete(Product product) {
        Product product2 = productRepository.findFirstById(product.getId());
        productRepository.delete(product2);
        return product2;
    }

    @Override
    @Transactional
    public Product deleteById(Long id) {
        Product product2 = productRepository.findFirstById(id);
        productRepository.delete(product2);
        return product2;
    }

    @Override
    @Transactional
    public Product addProductInCart(CartDTO cartDTO) {
        Long idClient = cartDTO.getIdClient();
        Long idProduct = cartDTO.getIdProduct();
        Client client = clientRepository.findById(idClient).get();
        Product product = productRepository.findById(idProduct).get();
        Cart cart = client.getCart();
        cart.addProduct(product);
        return product;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<ProductDTO> fetchAllProductsByMatch(String text) {
        List<ProductDTO> rezult = new ArrayList<>();
        List<Product> products = this.findAll();


        Pattern pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        for (Product product : products) {
            if (pattern.matcher(product.getName()).find())
                rezult.add(new ProductDTO(product.getId(), product.getName(), product.getPrice()));
        }
        return rezult;
    }
}
