package com.imt.td.architecture.business.comptes;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.common.validator.ConstraintValidatorStep;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.business.comptes.validator.CompteAlreadyExistValidatorStep;
import com.imt.td.architecture.business.comptes.validator.CompteTypeValidatorStep;
import com.imt.td.architecture.common.utils.CollectionImtUtils;
import com.imt.td.architecture.infrastructures.bdd.clients.ClientsBddService;
import com.imt.td.architecture.infrastructures.events.comptes.NegativeCompteEventPublisher;
import com.imt.td.architecture.interfaces.events.comptes.NegativeCompteEventListener;
import com.imt.td.architecture.interfaces.rest.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ComptesService {
    private static final int MONTANT_AGIOS = 5;
    
    private final ClientsBddService service;
    private final NegativeCompteEventPublisher negativePublisher;
    
    public Collection<Compte> getAllFilteredByClient(final UUID clientIdentifier) {
        return this.service.get(clientIdentifier).map(Client::getComptes).orElse(Collections.emptySet());
    }
    
    public Optional<Compte> getOneFilteredByClient(final UUID clientIdentifier, final UUID identifier) {
        return this.service.get(clientIdentifier)
                           .map(Client::getComptes)
                           .stream()
                           .flatMap(Collection::stream)
                           .filter(compte -> Objects.equals(compte.getIdentifier(), identifier))
                           .findFirst();
    }
    
    public Compte create(final UUID clientIdentifier, final Compte newCompte) {
        new ConstraintValidatorStep<Compte>()
                .linkWith(new CompteAlreadyExistValidatorStep(this.service, clientIdentifier))
                .linkWith(new CompteTypeValidatorStep())
                .validate(newCompte)
                .throwIfInvalid();
        
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(client.toBuilder().comptes(CollectionImtUtils.append(client.getComptes(), newCompte)).build());
        return newCompte;
    }
    
    public void update(final UUID clientIdentifier, final Compte updatedCompte) {
        new ConstraintValidatorStep<Compte>()
                .linkWith(new CompteTypeValidatorStep())
                .validate(updatedCompte)
                .throwIfInvalid();
        
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(
                client.toBuilder().comptes(CollectionImtUtils.append(
                        Objects.requireNonNullElse(client.getComptes(), Collections.<Compte>emptySet())
                               .stream()
                               .filter(compte -> !Objects.equals(compte.getIdentifier(), updatedCompte.getIdentifier()))
                               .collect(Collectors.toSet()),
                        updatedCompte
                )).build()
        );
        
        this.negativePublisher.accept(clientIdentifier, updatedCompte);
    }
    
    public void applyAgios(final UUID clientIdentifier, final UUID identifier) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(
                client.toBuilder().comptes(
                        Objects.requireNonNullElse(client.getComptes(), Collections.<Compte>emptySet())
                               .stream()
                               .map(compte -> Objects.equals(compte.getIdentifier(), identifier)
                                       ? compte.toBuilder().solde(compte.getSolde() - MONTANT_AGIOS).build()
                                       : compte)
                               .collect(Collectors.toSet())
                ).build()
        );
    }
    
    public void delete(final UUID clientIdentifier, final UUID identifier) {
        final Client client = this.service.get(clientIdentifier).orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", clientIdentifier)));
        this.service.save(client.toBuilder().comptes(
                Objects.requireNonNullElse(client.getComptes(), Collections.<Compte>emptySet())
                       .stream()
                       .filter(compte -> !Objects.equals(compte.getIdentifier(), identifier))
                       .collect(Collectors.toSet())
        ).build());
    }
}
