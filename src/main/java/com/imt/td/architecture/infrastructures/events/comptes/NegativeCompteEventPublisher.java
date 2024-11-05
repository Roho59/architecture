package com.imt.td.architecture.infrastructures.events.comptes;

import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.interfaces.events.comptes.model.NegativeCompteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NegativeCompteEventPublisher {
    private final ApplicationEventPublisher publisher;
    
    public void accept(final UUID clientIdentifier, final Compte compte){
        Objects.requireNonNull(clientIdentifier, "Impossible de vérifier un compte avec un clientIdentifier nul");
        Objects.requireNonNull(compte, "Impossible de vérifier un compte nul");
        
        if(compte.getSolde() < 0){
            this.publisher.publishEvent(new NegativeCompteEvent(this, clientIdentifier, compte.getIdentifier()));
        }
    }
}
