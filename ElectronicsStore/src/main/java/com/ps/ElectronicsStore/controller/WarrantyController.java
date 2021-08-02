package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.ConnectionDTO;
import com.ps.ElectronicsStore.dto.WarrantyDTO;
import com.ps.ElectronicsStore.dto.WarrantyRepairRequestDTO;
import com.ps.ElectronicsStore.model.Client;
import com.ps.ElectronicsStore.model.Warranty;
import com.ps.ElectronicsStore.service.ClientService;
import com.ps.ElectronicsStore.service.WarrantyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/warranty")
@CrossOrigin
public class WarrantyController {
    private final WarrantyService warrantyService;
    private final ClientService clientService;

    public WarrantyController(WarrantyService warrantyService, ClientService clientService) {
        this.warrantyService = warrantyService;
        this.clientService = clientService;
    }

    @ApiOperation(value = "It will display warranties of a user")
    @GetMapping("/{id}")
    public ResponseEntity findWarrantiesByClientId(@ApiParam(value = "Requires an id for a user") @PathVariable Long id) {
        Client client = clientService.findById(id);
        List<Warranty> warranties = warrantyService.findAll();
        List<WarrantyDTO> warranties2 = new ArrayList<>();
        for (Warranty warranty : warranties) {
            if (warranty.getClient().getId() == id) {
                warranties2.add(new WarrantyDTO(warranty.getId(), warranty.getProductName(), warranty.getDays(),
                        warranty.getDate(), warranty.getRepairRequest()));
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(warranties2);
    }

    @ApiOperation(value = "It will display warranties for admin")
    @GetMapping("/adminwarranty")
    public ResponseEntity findWarrantiesForAdmin() {
        return ResponseEntity.status(HttpStatus.OK).body(warrantyService.findAllWarrantyForAdmin());
    }

    @ApiOperation(value = "It will modify repair request column")
    @PutMapping("/repairrequest")
    public ResponseEntity connectionClient(@ApiParam(value = "Requires a WarrantyRepairRequestDTO (idWarranty, " +
            "repairRequest:false/true)") @RequestBody WarrantyRepairRequestDTO warrantyRepairRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(warrantyService.modifyRepairRequest(warrantyRepairRequestDTO));
    }

    @ApiOperation(value = "It will send an email to client")
    @PostMapping("/sendemail/{id}")
    public ResponseEntity sendEmailToClientWarranty(@ApiParam(value = "Requires a warranty id") @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(warrantyService.sendEmail(id));
    }


}
