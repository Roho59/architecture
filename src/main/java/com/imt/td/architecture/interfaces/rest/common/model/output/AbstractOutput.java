package com.imt.td.architecture.interfaces.rest.common.model.output;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AbstractOutput implements Serializable {
    private static final long serialVersionUID = -8341955146070775741L;
}
