package com.ps.ElectronicsStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionTimeDTOList {
    @XmlElementWrapper(name = "list-of-cars")
    @XmlElement(name = "connections")
    private List<ConnectionTimeDTO> connectionTimeDTOS;
}
