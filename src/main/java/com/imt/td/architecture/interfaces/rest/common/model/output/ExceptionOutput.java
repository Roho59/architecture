package com.imt.td.architecture.interfaces.rest.common.model.output;

import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class ExceptionOutput extends AbstractOutput {
    private static final long serialVersionUID = -7329611615051700808L;
    
    private final String type;
    private final String message;
    private final String timestamp;
    private final String path;
}
