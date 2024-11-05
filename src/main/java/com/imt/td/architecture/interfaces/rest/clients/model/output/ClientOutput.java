package com.imt.td.architecture.interfaces.rest.clients.model.output;

import com.imt.td.architecture.interfaces.rest.common.model.output.AbstractOutput;
import lombok.*;

import java.io.Serial;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ClientOutput extends AbstractOutput {
    private static final long serialVersionUID = -1850311121554580919L;
    
    private final String identifier;
    private final String lastname;
    private final String firstname;
    private final String genre;
}
