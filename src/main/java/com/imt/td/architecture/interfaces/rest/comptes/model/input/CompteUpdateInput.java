package com.imt.td.architecture.interfaces.rest.comptes.model.input;

import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractUpdateInput;
import com.imt.td.architecture.interfaces.rest.common.model.input.UpdatableProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class CompteUpdateInput extends AbstractUpdateInput {
    private static final long serialVersionUID = -7279826297314089026L;
    
    private UpdatableProperty<String> name = UpdatableProperty.empty();
    private UpdatableProperty<String> type = UpdatableProperty.empty();
    private UpdatableProperty<Double> solde = UpdatableProperty.empty();
    
    public void setName(final String name) {
        this.name = UpdatableProperty.makesChanges(name);
    }
    
    public void setType(final String type) {
        this.type = UpdatableProperty.makesChanges(type);
    }
    
    public void setSolde(final Double solde) {
        this.solde = UpdatableProperty.makesChanges(solde);
    }
}
