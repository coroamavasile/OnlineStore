package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import com.ps.ElectronicsStore.model.Computer;
import com.ps.ElectronicsStore.service.ComputerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/computer")
@CrossOrigin
public class ComputerController {

    private final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }


    @ApiOperation(value = "Returns a list of all computers.")
    @GetMapping()
    public ResponseEntity findAllCalculators() {
        return ResponseEntity.status(HttpStatus.OK).body(computerService.findAll());
    }

    @ApiOperation(value = "Return a component with name = nume")
    @GetMapping("/{name}")
    public ResponseEntity findCalculatorByNume(@ApiParam(value = "Requires a computer name") @PathVariable String name) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(computerService.findByName(name));
    }


    @ApiOperation(value = "It will add in database a computer")
    @PostMapping
    public ResponseEntity saveNewComputer(@ApiParam(value = "Requires a new computer") @RequestBody Computer calculator) {
        return ResponseEntity.status(HttpStatus.OK).body(computerService.save(calculator));
    }

    @ApiOperation(value = "It will delete from database a computer")
    @DeleteMapping("/delete/{id}")

    public ResponseEntity deleteCalculatorById(@ApiParam(value = "Requires an existant computer") @PathVariable Long id) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(computerService.deleteCalculator(id));
    }

    @ApiOperation(value = "Returns a list with computers whose price is beetween minPrice and maxPrice")
    @PostMapping("/pricefilter")
    public ResponseEntity priceFilterComputers(@ApiParam(value = "Requires two doubles: minPrice and maxPrice, which are the minimum and the maximium price for a list of computers") @RequestBody FilterDTO filterDTO) throws ApiExceptionResponse {

        return ResponseEntity.status(HttpStatus.OK).body(computerService.priceFilter(filterDTO));
    }

    @ApiOperation(value = "It will update the price for a computer")
    @PutMapping("/{id}")
    public ResponseEntity updateCalculatorPret(@ApiParam(value = "Requires id and new price for a computer") @PathVariable Long id, @RequestBody Computer dto) {

        return ResponseEntity.status(HttpStatus.OK).body(computerService.updatePret(id, dto));
    }
}

