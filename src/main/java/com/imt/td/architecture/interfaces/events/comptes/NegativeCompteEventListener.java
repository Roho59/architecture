package com.imt.td.architecture.interfaces.events.comptes;

import com.imt.td.architecture.business.comptes.ComptesService;
import com.imt.td.architecture.interfaces.events.comptes.model.NegativeCompteEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NegativeCompteEventListener implements ApplicationListener<NegativeCompteEvent> {
    
    private final ComptesService service;
    
    @Async
    @Override
    public void onApplicationEvent(final NegativeCompteEvent event) {
        this.service.applyAgios(event.getClientIdentifier(), event.getCompteIdentifier());
    }
}