package com.imt.td.architecture.business.comptes.validator;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.common.validator.AbstractValidatorStep;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.infrastructures.bdd.clients.ClientsBddService;
import com.imt.td.architecture.interfaces.rest.common.exception.ConflictException;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.UUID;

@AllArgsConstructor
public class CompteAlreadyExistValidatorStep extends AbstractValidatorStep<Compte> {
    private ClientsBddService service;
    private UUID clientIdentifier;
    
    @Override
    public void check(final Compte toValidate) {
        if(this.service.get(clientIdentifier).map(Client::getComptes).stream().flatMap(Collection::stream).anyMatch(alreadySaved -> this.isSameThing(toValidate, alreadySaved))) {
            throw new ConflictException(String.format("Un compte ayant les mêmes caractéristique existe déjà pour le client %s : name : %s, type : %s", this.clientIdentifier.toString(), toValidate.getName(), toValidate.getType().name()));
        }
    }
    
    private boolean isSameThing(final Compte input, final Compte alreadySaved){
        return alreadySaved.getName().equalsIgnoreCase(input.getName())
                && alreadySaved.getType().equals(input.getType());
    }
}
