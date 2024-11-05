package com.imt.td.architecture.infrastructures.bdd.clients.repository.mapper;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.common.model.GenreEnum;
import com.imt.td.architecture.infrastructures.bdd.clients.repository.entity.ClientEntity;
import com.imt.td.architecture.infrastructures.bdd.clients.repository.entity.CompteEntity;
import com.imt.td.architecture.infrastructures.bdd.common.model.mapper.AbstractBddMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientBddMapper extends AbstractBddMapper<Client, ClientEntity> {
    private CompteBddMapper compteMapper;
    
    @Override
    public Client from(final ClientEntity input) {
        return Client.builder()
                     .identifier(UUID.fromString(input.getIdentifier()))
                     .lastname(input.getLastname())
                     .firstname(input.getFirstname())
                     .genre(GenreEnum.fromOrDefault(input.getGenre()))
                     .comptes(Objects.requireNonNullElse(input.getComptes(), Collections.<CompteEntity>emptySet()).stream()
                                     .map(this.compteMapper::from)
                                     .collect(Collectors.toSet()))
                     .build();
    }
    
    @Override
    public ClientEntity to(final Client object) {
        return ClientEntity.builder()
                           .identifier(object.getIdentifier().toString())
                           .lastname(object.getLastname())
                           .firstname(object.getFirstname())
                           .genre(object.getGenre().name())
                           .comptes(Objects.requireNonNullElse(object.getComptes(), Collections.<Compte>emptySet()).stream()
                                           .map(this.compteMapper::to)
                                           .collect(Collectors.toSet()))
                           .build();
    }
}
