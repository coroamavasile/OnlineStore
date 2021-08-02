package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.BlockDTO;
import com.ps.ElectronicsStore.dto.CartDTO;
import com.ps.ElectronicsStore.dto.ConnectionDTO;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.service.ClientService;
import com.ps.ElectronicsStore.service.ProductService;
import com.ps.ElectronicsStore.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@CrossOrigin

public class ClientController {
    private final ClientService clientService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ClientController(ClientService clientService, UserService userService, ProductService productService) {
        this.clientService = clientService;
        this.userService = userService;
        this.productService = productService;
    }


    @ApiOperation(value = "Returns a list of all users")
    @GetMapping()
    public ResponseEntity findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @ApiOperation(value = "Returns one user")
    @GetMapping("/{username}")
    public ResponseEntity findUserByUsername(@ApiParam(value = "Requires a username for a user") @PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findClientByUsername(username));
    }

    @ApiOperation(value = "It will add one product in a user cart")
    @PostMapping("/addcart")
    public ResponseEntity addInCart(@ApiParam(value = "Requires a CardDTO object (idClient, idProduct") @RequestBody CartDTO cartDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProductInCart(cartDTO));
    }

    @ApiOperation(value = "It will add a client in database")
    @PostMapping
    public ResponseEntity saveNewUtilizator(@ApiParam(value = "Requires a client") @RequestBody Client client) {
        String password = client.getPassword();
        client.setPassword(passwordEncoder.encode(password));
        return ResponseEntity.status(HttpStatus.OK).body(clientService.save(client));
    }

    @ApiOperation(value = "It will block/unblock a client")
    @PutMapping("/block")
    public ResponseEntity blockClient(@ApiParam(value = "Requires a blockDTO (idClient, blocked:false/true)") @RequestBody BlockDTO blockDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.block(blockDTO));
    }

    @ApiOperation(value = "It will modify connection column")
    @PutMapping("/connection")
    public ResponseEntity connectionClient(@ApiParam(value = "Requires a ConnectionDTO (idClient, " +
            "connected:false/true)") @RequestBody ConnectionDTO connectionDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.changeConnectionState(connectionDTO));
    }

}
