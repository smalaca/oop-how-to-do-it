package com.smalaca.oophowtodoit.domain.dto;

import com.smalaca.oophowtodoit.domain.Address;
import com.smalaca.oophowtodoit.domain.EmployerIdentifcationNumber;
import com.smalaca.oophowtodoit.domain.Name;

public class ContractorDTO {
    public final EmployerIdentifcationNumber ein;
    public final Name name;
    public final Address address;

    public ContractorDTO(EmployerIdentifcationNumber ein, Name name, Address address) {
        this.name = name;
        this.ein = ein;
        this.address = address;
    }
}
