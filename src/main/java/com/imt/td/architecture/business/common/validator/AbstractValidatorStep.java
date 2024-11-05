package com.imt.td.architecture.business.common.validator;

import com.imt.td.architecture.business.common.model.ValidatorResult;
import com.imt.td.architecture.interfaces.rest.common.exception.AbstractRestException;

import java.util.Objects;

public abstract class AbstractValidatorStep<T> {
    private AbstractValidatorStep<T> nextStep;
    
    public abstract void check(final T toValidate);
    
    public ValidatorResult validate(final T toValidate){
        Objects.requireNonNull(toValidate, "Impossible de valider un objet nul");
        try {
            this.check(toValidate);
        } catch(final AbstractRestException e){
            return ValidatorResult.invalid(e);
        }
        
        if(Objects.nonNull(this.nextStep)){
            return this.nextStep.validate(toValidate);
        }
        return ValidatorResult.valid();
    }
    
    public AbstractValidatorStep<T> linkWith(final AbstractValidatorStep<T> nextStep){
        if(Objects.isNull(this.nextStep)){
            this.nextStep = nextStep;
        } else {
            this.nextStep.linkWith(nextStep);
        }
        return this;
    }
}
