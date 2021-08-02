package com.ps.ElectronicsStore.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlTransient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    @NotNull
    @XmlTransient
    private String username;
    @NotNull
/*    @Pattern(regexp = "^(?=.*[0-9])" +
            "(?=.[a-z])" +
            "(?=.[A-Z])" +
            "(?=\\S+$)" +
            ".{4,}" +
            "$", message = "invalid password")*/
    @XmlTransient
    private String password;
    private Boolean blocked = false;
    private Boolean conected;
}
