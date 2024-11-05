package com.imt.td.architecture.interfaces.rest.comptes;

import com.imt.td.architecture.business.comptes.ComptesService;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.interfaces.rest.common.exception.NotFoundException;
import com.imt.td.architecture.interfaces.rest.comptes.model.input.CompteInput;
import com.imt.td.architecture.interfaces.rest.comptes.model.input.CompteUpdateInput;
import com.imt.td.architecture.interfaces.rest.comptes.model.mapper.CompteControllerMapper;
import com.imt.td.architecture.interfaces.rest.comptes.model.output.ComptesOutput;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/imt/v1/clients/{idClient}/comptes")
public class ComptesController {
    private final ComptesService service;
    private final CompteControllerMapper mapper;
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ComptesOutput> getAll(@PathVariable("idClient") final String clientIdentifier) {
        return Objects.requireNonNullElse(this.service.getAllFilteredByClient(UUID.fromString(clientIdentifier)), Collections.<Compte>emptySet())
                      .stream()
                      .map(this.mapper::to)
                      .sorted(Comparator.comparing(ComptesOutput::getIdentifier, Comparator.nullsLast(Comparator.naturalOrder())))
                      .collect(Collectors.toList());
    }
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ComptesOutput create(@PathVariable("idClient") final String clientIdentifier, @RequestBody final CompteInput client) {
        return this.mapper.to(
                this.service.create(
                        UUID.fromString(clientIdentifier),
                        this.mapper.from(client)
                )
        );
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{idCompte}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ComptesOutput getOne(@PathVariable("idClient") final String clientIdentifier, @PathVariable("idCompte") final String identifier) {
        return this.service.getOneFilteredByClient(UUID.fromString(clientIdentifier), UUID.fromString(identifier))
                           .map(this.mapper::to)
                           .orElseThrow(() -> new NotFoundException(String.format("Le compte %s pour le client %s n'existe pas", identifier, clientIdentifier)));
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idCompte}")
    public void update(@PathVariable("idClient") final String clientIdentifier, @PathVariable("idCompte") final String identifier, @RequestBody final CompteUpdateInput client) {
        this.service.getOneFilteredByClient(UUID.fromString(clientIdentifier), UUID.fromString(identifier))
                    .map(finded -> this.mapper.from(client, finded))
                    .ifPresentOrElse(
                            updatedCompte -> this.service.update(UUID.fromString(clientIdentifier), updatedCompte),
                            () -> { throw new NotFoundException(String.format("Le compte %s pour le client %s n'existe pas", identifier, clientIdentifier)); }
                    );
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idCompte}")
    public void delete(@PathVariable("idClient") final String clientIdentifier, @PathVariable("idCompte") final String identifier) {
        this.service.delete(UUID.fromString(clientIdentifier), UUID.fromString(identifier));
    }
}
