package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.CartProductsDTO;
import com.ps.ElectronicsStore.dto.PurchasedProductsDTO;
import com.ps.ElectronicsStore.model.Cart;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Product;
import com.ps.ElectronicsStore.model.Warranty;
import com.ps.ElectronicsStore.service.CartService;
import com.ps.ElectronicsStore.service.ClientService;
import com.ps.ElectronicsStore.service.ProductService;
import com.ps.ElectronicsStore.service.WarrantyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin

public class CartController {
    final private CartService cartService;
    final private ProductService productService;
    final private ClientService clientService;
    final private WarrantyService warrantyService;
    private final JavaMailSender javaMailSender;

    public CartController(CartService cartService, ProductService productService, ClientService clientService, WarrantyService warrantyService, JavaMailSender javaMailSender) {
        this.cartService = cartService;
        this.productService = productService;
        this.clientService = clientService;
        this.warrantyService = warrantyService;
        this.javaMailSender = javaMailSender;
    }

    @ApiOperation(value = "It will display products from a user cart")
    @GetMapping("/{id}")
    public ResponseEntity findCartByIdClient(@ApiParam(value = "Requires an id for a user") @PathVariable Long id) {
        Cart cart = cartService.findByIdClient(id);
        List<Product> products = productService.findAll();
        List<Product> produseFinale = new ArrayList<>();
        for (Product product : products) {
            try {
                if (product.getCart().getId() == cart.getId()) {
                    produseFinale.add(product);
                }
            } catch (Exception e) {
            }
        }
        CartProductsDTO cartProductsDTO = new CartProductsDTO();
        cartProductsDTO.setProducts(produseFinale);
        return ResponseEntity.status(HttpStatus.OK).body(produseFinale);
    }

    @ApiOperation(value = "It will submit an order")
    @PutMapping("/submitorder/{id}")
    public ResponseEntity submitOrder(@ApiParam(value = "Requires an id for a user") @PathVariable Long id) {
        Cart cart = cartService.findByIdClient(id);
        Client client = clientService.findById(id);

        List<Product> products = productService.findAll();
        String productsToSend = "";
        Integer numar_produse = 0;
        for (Product product : products) {
            try {
                if (product.getCart().getId() == cart.getId()) {
                    numar_produse++;
                    productsToSend = productsToSend + "Product: " + product.getName() + " Price: " + product.getPrice() + "\n";
                }
            } catch (Exception e) {
            }
        }
        productsToSend = productsToSend + "\n" + "Total price: " + cart.getTotalPrice();

        PurchasedProductsDTO purchasedProductsDTO = new PurchasedProductsDTO(client.getId(), numar_produse);
        clientService.updatePurchasedProducts(purchasedProductsDTO);

        Double voucherVal = client.getVoucher();


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("coroama.vasile1@gmail.com");//aici ar trebui mail-ul adminului
        String message =
                "Salut, " + client.getFirstName() + "\n\n" + productsToSend + ".\n" + "Voucher: " + voucherVal + "\n" + "Pret final: " + (cart.getTotalPrice() - voucherVal);
        msg.setSubject("Factura");
        msg.setText(message);
        javaMailSender.send(msg);

        try {
            while (cart.getProducts().get(0) != null) {
                Product product = cart.getProducts().get(0);
                CartDTO cartDto = new CartDTO(client.getId(), product.getId());
                clientService.removeProductByClientId(cartDto);

            }
        } catch (Exception e) {

        }

        clientService.updateVoucher(client.getId(), 0D);
        return ResponseEntity.status(HttpStatus.OK).body(productsToSend);
    }


    @ApiOperation(value = "It will delete one product from a user cart")
    @PutMapping("/deleteproduct")
    public ResponseEntity deleteProductFromCart(@ApiParam(value = "Requires an id for a user and product") @RequestBody CartDTO cartDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.removeProductByClientId(cartDTO));
    }
}
