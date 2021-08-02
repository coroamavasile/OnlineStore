package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.model.Product;
import com.ps.ElectronicsStore.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    public ProductController(com.ps.ElectronicsStore.service.ProductService productService) {
        this.productService = productService;
    }


    @ApiOperation(value = "Returns a list of all products")
    @GetMapping()
    public ResponseEntity findAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @ApiOperation(value = "Return a product")
    @GetMapping("/find")
    public ResponseEntity findProductByNume(@ApiParam(value = "Requires a name for a product") @RequestParam String nume) {
        System.out.println("Intra");
        return ResponseEntity.status(HttpStatus.OK).body(productService.fetchAllProductsByMatch(nume));
    }

    @ApiOperation(value = "It will add in database a new product")
    @PostMapping
    public ResponseEntity saveNewProdus(@ApiParam(value = "Requires a new product") @RequestBody Product produs) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.save(produs));
    }


}
