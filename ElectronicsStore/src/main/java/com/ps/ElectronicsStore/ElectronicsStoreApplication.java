package com.ps.ElectronicsStore;

import com.ps.ElectronicsStore.model.*;
import com.ps.ElectronicsStore.repository.*;
import com.ps.ElectronicsStore.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ElectronicsStoreApplication {


    public static void main(String[] args) {
        SpringApplication.run(ElectronicsStoreApplication.class, args);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PasswordEncoder encoder2;

    @Bean
    CommandLineRunner init(TelephoneRepository telephoneRepository, ComponentRepository componentRepository,
                           ComputerRepository computerRepository,
                           ClientRepository clientRepository,
                           AdminRepository adminRepository,
                           WarrantyService warrantyService,
                           ClientService clientService,
                           ComponentService componentService,
                           ProductService productService, TelephoneService telephoneService) {
        return args -> {
            Boolean cond = false;
            if (cond) {
                Admin admin = new Admin(null, "Coroama", "Vasile", "coroama.vasile1@gmail.com", "admin", "admin", false, false);
                adminRepository.save(admin);

                Client client1 = new Client(null, "last1", "first1", "email1@email.com", "client1", "admin", "0872163841", 22,
                        "adresa1", false, false);
                clientRepository.save(client1);

                Client client2 = new Client(null, "last2", "first2", "email2@email.com", "client2", "admin", "0872163241", 22,
                        "adresa2", false, false);
                clientRepository.save(client2);

                Client client3 = new Client(null, "last3", "first3", "email3@email.com", "client3", "admin", "0872163241", 22,
                        "adresa3", false, false);
                clientRepository.save(client3);

                Client client4 = new Client(null, "last4", "first4", "email4@email.com", "client4", "admin", "0872163241", 22,
                        "adresa4", false, false);
                clientRepository.save(client4);

                Client client5 = new Client(null, "last5", "first5", "email5@email.com", "client5", "admin",
                        "0872163241", 22,
                        "adresa5", false, false);
                clientRepository.save(client5);

                Telephone telefon1 = new Telephone(null, "Iphonex", 3021D, "CPU:A12", "IOS", "Apple");


            }

/*            String pass = encoder2.encode("admin1");
            Client client6 = new Client(null, "last6", "first6", "email6@email.com", "client6",
                    pass,
                    "0872163241", 22,
                    "adresa5", false, false);
            clientRepository.save(client6);
//            String pass = encoder.encode("hello");
//            System.out.println(pass);*/
        };
    }
}
