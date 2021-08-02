package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.FilterDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import com.ps.ElectronicsStore.model.Telephone;
import com.ps.ElectronicsStore.service.TelephoneService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telephone")
@CrossOrigin

public class TelephoneController {
    private final TelephoneService telephoneService;

    public TelephoneController(TelephoneService telephoneService) {
        this.telephoneService = telephoneService;
    }


    @ApiOperation(value = "Returns a list of all telephones")
    @GetMapping()
    public ResponseEntity findAllTelephones() {
        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.findAll());
    }

    @ApiOperation(value = "Return a telephone with name = nume")
    @GetMapping("/{nume}")
    public ResponseEntity findProductByNume(@ApiParam(value = "Requires a name for a telephone") @PathVariable String nume) {
        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.findByName(nume));
    }

    @ApiOperation(value = "It will add in database a telephone")
    @PostMapping
    public ResponseEntity saveNewTelefon(@ApiParam(value = "Requires a new telephone") @RequestBody Telephone telefon) {
        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.save(telefon));
    }

    @ApiOperation(value = "It will delete a telephone from database ")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCalculatorById(@ApiParam(value = "Requires an existant telephone") @PathVariable Long id) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.delete(id));
    }

    @ApiOperation(value = "It will update the price of a telephone")
    @PutMapping("/updateprice")
    public ResponseEntity updateTelefonPret(@ApiParam(value = "Requires an existant telephone") @RequestBody Telephone dto) {
        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.updatePret(dto.getId(), dto));
    }

    @ApiOperation(value = "Returns a list with telephones whose price is beetween minPrice and maxPrice")
    @PostMapping("/pricefilter")
    public ResponseEntity priceFilterTelephone(@ApiParam(value = "Requires two doubles: minPrice and maxPrice, which are the minimum and the maximium price for a list of telephones") @RequestBody FilterDTO filterDTO) throws ApiExceptionResponse {

        return ResponseEntity.status(HttpStatus.OK).body(telephoneService.priceFilter(filterDTO));
    }
}
