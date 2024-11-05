package com.imt.td.architecture.business.comptes.model;

import com.imt.td.architecture.common.model.TypeCompteEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Compte {
    private static final String NAME_PATTERN = "^[a-z-A-Z1-9_-]{1,250}$";
    
    @Builder.Default
    @NotNull(message = "L'identifiant ne peut pas être nul")
    private final UUID identifier = UUID.randomUUID();
    
    @NotNull(message = "Le nom ne peut pas être nul")
    @Pattern(regexp = NAME_PATTERN, message = "Le nom n'est pas valide : il doit être composé de lettre, de chiffre, d'underscore ou de tiré et doit faire entre 1 et 250 caractères")
    private final String name;
    
    @NotNull(message = "Le type ne peut pas être nul")
    private final TypeCompteEnum type;
    
    @Builder.Default
    private final Double solde = 0D;
}
