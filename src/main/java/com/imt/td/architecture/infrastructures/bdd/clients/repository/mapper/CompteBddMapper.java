package com.imt.td.architecture.infrastructures.bdd.clients.repository.mapper;

import com.imt.td.architecture.common.model.TypeCompteEnum;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.infrastructures.bdd.clients.repository.entity.CompteEntity;
import com.imt.td.architecture.infrastructures.bdd.common.model.mapper.AbstractBddMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CompteBddMapper extends AbstractBddMapper<Compte, CompteEntity> {
    
    @Override
    public Compte from(final CompteEntity input) {
        return Compte.builder()
              .identifier(UUID.fromString(input.getIdentifier()))
              .name(input.getName())
              .solde(input.getSolde())
              .type(TypeCompteEnum.fromOrDefault(input.getType()))
              .build();
    }
    
    @Override
    public CompteEntity to(final Compte object) {
        return CompteEntity.builder()
                           .identifier(object.getIdentifier().toString())
                           .name(object.getName())
                           .solde(object.getSolde())
                           .type(object.getType().name())
                           .build();
    }
}
