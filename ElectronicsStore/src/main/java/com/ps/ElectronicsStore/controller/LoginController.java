package com.ps.ElectronicsStore.controller;

import com.ps.ElectronicsStore.dto.ConnectionTimeUpdateDTO;
import com.ps.ElectronicsStore.dto.CredentialsDTO;
import com.ps.ElectronicsStore.dto.ForgotPasswordDTO;
import com.ps.ElectronicsStore.exceptions.ApiExceptionResponse;
import com.ps.ElectronicsStore.model.Admin;
import com.ps.ElectronicsStore.model.User;
import com.ps.ElectronicsStore.service.ConnectionTimeService;
import com.ps.ElectronicsStore.service.LoginService;
import com.ps.ElectronicsStore.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {
    private final LoginService loginService;
    private final UserService userService;
    private final ConnectionTimeService connectionTimeService;
    private final boolean twoStepAutentification = true;
    private final JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginController(LoginService loginService, UserService userService, ConnectionTimeService connectionTimeService, JavaMailSender javaMailSender) {
        this.loginService = loginService;
        this.userService = userService;
        this.connectionTimeService = connectionTimeService;
        this.javaMailSender = javaMailSender;
    }

    @ApiOperation(value = "Returns a login request")
    @PostMapping("/logout")
    public ResponseEntity logoutReq(@ApiParam(value = "Requires user credentials") @RequestBody ConnectionTimeUpdateDTO dto) throws ApiExceptionResponse {
        return ResponseEntity.status(HttpStatus.OK).body(connectionTimeService.updateLogout(dto));
    }


    @ApiOperation(value = "Returns a login request")
    @PostMapping("/login")
    public ResponseEntity loginReq(@ApiParam(value = "Requires user credentials") @RequestBody CredentialsDTO dto) throws ApiExceptionResponse {

        User user = userService.findByUsername(dto.getUsername());
        //generam un cod random pentru admin
        int random_int = (int) (Math.random() * (9999 - 1000 + 1) + 1000);
        if (twoStepAutentification && user instanceof Admin) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo("coroama.vasile1@gmail.com");//aici ar trebui mail-ul adminului
            String codDeTrimis = "Codul de autentificare este:" + random_int + ".";
            msg.setSubject("Autentificare in doi pasi");
            msg.setText(codDeTrimis);
            javaMailSender.send(msg);
        }

        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(dto, random_int));
    }

    @ApiOperation(value = "It will change password for a user")
    @PostMapping("/changepassword")
    public ResponseEntity changePassword(@ApiParam(value = "Requires a new password and username") @RequestBody CredentialsDTO dto) throws ApiExceptionResponse {


        return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(dto));
    }

    @ApiOperation(value = "If a user forgets the password, he can change it")
    @PostMapping("/forgotpassword")
    public ResponseEntity forgotPassword(@ApiParam(value = "Requires a username ") @RequestBody CredentialsDTO dto) throws ApiExceptionResponse {
        int validationCode = (int) (Math.random() * (9999 - 1000 + 1) + 1000);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("coroama.vasile1@gmail.com");//aici ar trebui mail-ul adminului
        String codDeTrimis = "Codul pentru resetarea parolei este:" + validationCode + ".";
        msg.setSubject("Resetare parola");
        msg.setText(codDeTrimis);

        javaMailSender.send(msg);

        ForgotPasswordDTO forgotPasswordDTO = new ForgotPasswordDTO(dto.getUsername(), validationCode);

        return ResponseEntity.status(HttpStatus.OK).body(forgotPasswordDTO);
    }


}