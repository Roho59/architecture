package com.imt.td.architecture.business.clients.validator;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.common.validator.AbstractValidatorStep;
import com.imt.td.architecture.infrastructures.bdd.clients.ClientsBddService;
import com.imt.td.architecture.interfaces.rest.common.exception.ConflictException;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
public class ClientAlreadyExistValidatorStep extends AbstractValidatorStep<Client> {
    private ClientsBddService service;
    
    @Override
    public void check(final Client toValidate) {
        if(Objects.requireNonNullElse(this.service.getAll(), Collections.<Client>emptySet()).stream().anyMatch(alreadySaved -> this.isSameThing(toValidate, alreadySaved))) {
            throw new ConflictException(String.format("Un client ayant ses infos existe déjà : lastname : %s, firstname : %s, genre : %s", toValidate.getLastname(), toValidate.getFirstname(), toValidate.getGenre().name()));
        }
    }
    
    private boolean isSameThing(final Client input, final Client alreadySaved){
        return alreadySaved.getLastname().equalsIgnoreCase(input.getLastname())
                && alreadySaved.getFirstname().equalsIgnoreCase(input.getFirstname())
                && alreadySaved.getGenre().equals(input.getGenre());
    }
}
