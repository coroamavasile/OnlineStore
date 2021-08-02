package com.ps.ElectronicsStore.dto;

import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "connection2")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class ConnectionTimeDTO {

    @XmlElement
    private Long id;
    @XmlElement
    private Date login;
    @XmlElement
    private Date logout;
    @XmlElement
    private Boolean finished;
    @XmlElement
    private String username;
}
