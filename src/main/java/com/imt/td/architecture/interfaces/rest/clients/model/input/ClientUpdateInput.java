package com.imt.td.architecture.interfaces.rest.clients.model.input;

import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractUpdateInput;
import com.imt.td.architecture.interfaces.rest.common.model.input.UpdatableProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientUpdateInput extends AbstractUpdateInput {
    private static final long serialVersionUID = 1820740284109665168L;
    
    private UpdatableProperty<String> lastname = UpdatableProperty.empty();
    private UpdatableProperty<String> firstname = UpdatableProperty.empty();
    private UpdatableProperty<String> genre = UpdatableProperty.empty();
    
    public void setLastname(final String lastname) {
        this.lastname = UpdatableProperty.makesChanges(lastname);
    }
    
    public void setFirstname(final String firstname) {
        this.firstname = UpdatableProperty.makesChanges(firstname);
    }
    
    public void setGenre(final String genre) {
        this.genre = UpdatableProperty.makesChanges(genre);
    }
}
