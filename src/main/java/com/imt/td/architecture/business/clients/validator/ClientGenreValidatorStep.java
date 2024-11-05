package com.imt.td.architecture.business.clients.validator;

import com.imt.td.architecture.business.clients.model.Client;
import com.imt.td.architecture.business.common.validator.AbstractValidatorStep;
import com.imt.td.architecture.common.model.GenreEnum;
import com.imt.td.architecture.infrastructures.bdd.clients.ClientsBddService;
import com.imt.td.architecture.interfaces.rest.common.exception.BadRequestException;
import com.imt.td.architecture.interfaces.rest.common.exception.ConflictException;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.Objects;

@AllArgsConstructor
public class ClientGenreValidatorStep extends AbstractValidatorStep<Client> {
    
    @Override
    public void check(final Client toValidate) {
        if(GenreEnum.INCONNU.equals(toValidate.getGenre())) {
            throw new BadRequestException(String.format("Le genre communiqué ne correspond pas aux valeurs acceptables : %s", GenreEnum.ACCEPTABLE_VALUES));
        }
    }
}
