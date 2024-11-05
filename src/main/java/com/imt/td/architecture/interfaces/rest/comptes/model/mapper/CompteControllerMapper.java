package com.imt.td.architecture.interfaces.rest.comptes.model.mapper;

import com.imt.td.architecture.common.model.TypeCompteEnum;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.interfaces.rest.common.model.mapper.AbstractControllerMapper;
import com.imt.td.architecture.interfaces.rest.comptes.model.input.CompteInput;
import com.imt.td.architecture.interfaces.rest.comptes.model.input.CompteUpdateInput;
import com.imt.td.architecture.interfaces.rest.comptes.model.output.ComptesOutput;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompteControllerMapper extends AbstractControllerMapper<Compte, CompteInput, CompteUpdateInput, ComptesOutput> {
    @Override
    public Compte from(final CompteInput input) {
        return Compte.builder()
                     .name(input.getName())
                     .type(TypeCompteEnum.fromOrDefault(input.getType()))
                     .build();
    }
    
    @Override
    public Compte from(final CompteUpdateInput input, final Compte alreadySaved) {
        return Compte.builder()
                     .identifier(alreadySaved.getIdentifier())
                     .name(input.getName().defaultIfNotOverwrite(alreadySaved.getName()))
                     .solde(input.getSolde().defaultIfNotOverwrite(alreadySaved.getSolde()))
                     .type(input.getType().defaultIfNotOverwrite(TypeCompteEnum::fromOrDefault, alreadySaved.getType()))
                     .build();
    }
    
    @Override
    public ComptesOutput to(final Compte compte) {
        return ComptesOutput.builder()
                            .identifier(compte.getIdentifier().toString())
                            .name(compte.getName())
                            .solde(compte.getSolde())
                            .type(compte.getType().name())
                            .build();
    }
}
