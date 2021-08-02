package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import com.ps.ElectronicsStore.model.Component;
import com.ps.ElectronicsStore.service.ComponentService;
import com.ps.ElectronicsStore.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/component")
@CrossOrigin

public class ComponentController {
    private final ComponentService componentService;
    private final ProductService productService;
    public ComponentController(ComponentService componentService, ProductService productService) {
        this.componentService = componentService;
        this.productService = productService;
    }


    @ApiOperation(value = "Returns a list of all components")
    @GetMapping()
    public ResponseEntity findAllComponents(){
        return ResponseEntity.status(HttpStatus.OK).body(componentService.findAll());
    }

    @ApiOperation(value = "Return a component with name = nume")
    @GetMapping("/{nume}")
    public ResponseEntity findProductByNume(@ApiParam(value = "Requires a name for a component") @PathVariable String nume){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(nume));
    }

    @ApiOperation(value = "It will add in database a component")
    @PostMapping
    public ResponseEntity saveNewComponent(@ApiParam(value = "Requires a new component")@RequestBody Component componenta){
        return ResponseEntity.status(HttpStatus.OK).body(componentService.save(componenta));
    }

    @ApiOperation(value = "It will delete from database a component")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCalculatorById(@ApiParam(value = "Requires an existant component")@PathVariable Long id)throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(componentService.delete(id));
    }

    @ApiOperation(value = "It will update the price of a component")
    @PutMapping
    public ResponseEntity updateComponentaPret(@ApiParam(value = "Requires an existant component")@RequestBody Component dto){
        return ResponseEntity.status(HttpStatus.OK).body(componentService.updatePrice(dto));
    }

    @ApiOperation(value = "Returns a list with components whose price is beetween minPrice and maxPrice")
    @PostMapping("/pricefilter")
    public ResponseEntity priceFilterComponent(@ApiParam(value = "Requires two doubles: minPrice and maxPrice, which are the minimum and the maximium price for a list of components")@RequestBody FilterDTO filterDTO)throws ApiExceptionResponse  {

        return ResponseEntity.status(HttpStatus.OK).body(componentService.priceFilter(filterDTO));
    }
}
