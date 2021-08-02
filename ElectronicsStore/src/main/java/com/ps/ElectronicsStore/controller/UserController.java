package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.model.User;
import com.ps.ElectronicsStore.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @ApiOperation(value = "Returns a list of all users")
    @GetMapping()
    public ResponseEntity findAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @ApiOperation(value = "Return a user")
    @GetMapping("/{username}")
    public ResponseEntity findUserByUsername(@ApiParam(value = "Requires a username for a user") @PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByUsername(username));
    }

    @ApiOperation(value = "It will add a user")
    @PostMapping
    public ResponseEntity saveNewUtilizator(@ApiParam(value = "Requires a new user") @RequestBody User user) {
      
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }
}
