package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.service.ConnectionTimeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminController {
    private final ConnectionTimeService connectionTimeService;

    public AdminController(ConnectionTimeService connectionTimeService) {
        this.connectionTimeService = connectionTimeService;
    }

    @ApiOperation(value = "Returns a list of login timestamp.")
    @GetMapping("/timestamp")
    public ResponseEntity findAllTimeStamp() {
        return ResponseEntity.status(HttpStatus.OK).body(connectionTimeService.findAll());
    }

    @GetMapping("/exporttimestamp")
    public ResponseEntity exportTimestamp(@RequestParam String fileType) {
        return ResponseEntity.status(HttpStatus.OK).body(connectionTimeService.exportTimeStamps(fileType));
    }


}
