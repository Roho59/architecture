package com.imt.td.architecture.interfaces.rest.comptes.model.output;

import com.imt.td.architecture.interfaces.rest.common.model.output.AbstractOutput;
import lombok.*;

import java.io.Serial;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ComptesOutput extends AbstractOutput {
    private static final long serialVersionUID = 6226233848065487400L;
    private final String identifier;
    private final String name;
    private final String type;
    private final Double solde;
}
