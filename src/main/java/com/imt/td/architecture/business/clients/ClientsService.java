package com.imt.td.architecture.business.clients;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.clients.validator.ClientAlreadyExistValidatorStep;
import com.imt.td.architecture.business.clients.validator.ClientGenreValidatorStep;
import com.imt.td.architecture.business.common.validator.ConstraintValidatorStep;
import com.imt.td.architecture.infrastructures.bdd.clients.ClientsBddService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ClientsService {
    private ClientsBddService service;
    
    public Collection<Client> getAll() {
        return Objects.requireNonNullElse(this.service.getAll(), Collections.emptySet());
    }
    
    public Optional<Client> getOne(final UUID identifier) {
        return this.service.get(identifier);
    }
    
    public Client create(final Client newClient) {
        new ConstraintValidatorStep<Client>()
                .linkWith(new ClientAlreadyExistValidatorStep(this.service))
                .linkWith(new ClientGenreValidatorStep())
                .validate(newClient)
                .throwIfInvalid();
        
        return this.service.save(newClient);
    }
    
    public void update(final Client updatedClient) {
        new ConstraintValidatorStep<Client>()
                .linkWith(new ClientGenreValidatorStep())
                .validate(updatedClient)
                .throwIfInvalid();
        
        this.service.save(updatedClient);
    }
    
    public void delete(final UUID identifier) {
        this.service.delete(identifier);
    }
}
