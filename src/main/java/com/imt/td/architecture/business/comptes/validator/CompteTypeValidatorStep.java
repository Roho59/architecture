package com.imt.td.architecture.business.comptes.validator;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.common.validator.AbstractValidatorStep;
import com.imt.td.architecture.business.comptes.model.Compte;
import com.imt.td.architecture.common.model.GenreEnum;
import com.imt.td.architecture.common.model.TypeCompteEnum;
import com.imt.td.architecture.interfaces.rest.common.exception.BadRequestException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CompteTypeValidatorStep extends AbstractValidatorStep<Compte> {
    
    @Override
    public void check(final Compte toValidate) {
        if(TypeCompteEnum.INCONNU.equals(toValidate.getType())) {
            throw new BadRequestException(String.format("Le type communiqué ne correspond pas aux valeurs acceptables : %s", TypeCompteEnum.ACCEPTABLE_VALUES));
        }
    }
}
