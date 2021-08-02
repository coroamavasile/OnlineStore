package com.ps.ElectronicsStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement(name = "connection")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionTime {
    @XmlElementWrapper(name = "list-of-connections")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @XmlElement
    private Date login;
    @XmlElement
    private Date logout;
    @XmlElement
    private Boolean finished;

    @XmlElement
    @OneToOne(cascade = {CascadeType.ALL})
    @JsonIgnore
    private User user;
}
