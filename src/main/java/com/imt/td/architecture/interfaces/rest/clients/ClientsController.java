package com.imt.td.architecture.interfaces.rest.clients;

import com.imt.td.architecture.business.clients.ClientsService;
import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.interfaces.rest.clients.model.input.ClientInput;
import com.imt.td.architecture.interfaces.rest.clients.model.input.ClientUpdateInput;
import com.imt.td.architecture.interfaces.rest.clients.model.mapper.ClientControllerMapper;
import com.imt.td.architecture.interfaces.rest.clients.model.output.ClientOutput;
import com.imt.td.architecture.interfaces.rest.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/imt/v1/clients")
public class ClientsController {
    private final ClientsService service;
    private final ClientControllerMapper mapper;
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<ClientOutput> getAll() {
        return Objects.requireNonNullElse(this.service.getAll(), Collections.<Client>emptySet())
                      .stream()
                      .map(this.mapper::to)
                      .sorted(Comparator.comparing(ClientOutput::getIdentifier, Comparator.nullsLast(Comparator.naturalOrder())))
                      .collect(Collectors.toList());
    }
    
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientOutput create(@RequestBody final ClientInput client) {
        return this.mapper.to(this.service.create(this.mapper.from(client)));
    }
    
    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientOutput getOne(@PathVariable("idClient") final String identifier) {
        return this.service.getOne(UUID.fromString(identifier))
                           .map(this.mapper::to)
                           .orElseThrow(() -> new NotFoundException(String.format("Le client %s n'existe pas", identifier)));
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PatchMapping(value = "/{idClient}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable("idClient") final String identifier, @RequestBody final ClientUpdateInput client) {
        this.service.getOne(UUID.fromString(identifier))
                    .map(finded -> this.mapper.from(client, finded))
                    .ifPresentOrElse(
                            this.service::update,
                            () -> { throw new NotFoundException(String.format("Le client %s n'existe pas", identifier)); }
                    );
    }
    
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{idClient}")
    public void delete(@PathVariable("idClient") final String identifier) {
        this.service.delete(UUID.fromString(identifier));
    }
}
