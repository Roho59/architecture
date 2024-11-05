package com.imt.td.architecture.interfaces.events.comptes.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class NegativeCompteEvent extends ApplicationEvent {
    private static final long serialVersionUID = -8544198668558330365L;
    
    private final UUID clientIdentifier;
    private final UUID compteIdentifier;
    
    public NegativeCompteEvent(final Object source, final UUID clientIdentifier, final UUID compteIdentifier) {
        super(source);
        this.clientIdentifier = clientIdentifier;
        this.compteIdentifier = compteIdentifier;
    }
}
