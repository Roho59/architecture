package com.imt.td.architecture.interfaces.rest.clients.model.input;

import com.imt.td.architecture.interfaces.rest.common.model.input.AbstractInput;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientInput extends AbstractInput {
    private static final long serialVersionUID = 6731110414572622876L;
    private String lastname;
    private String firstname;
    private String genre;
}
