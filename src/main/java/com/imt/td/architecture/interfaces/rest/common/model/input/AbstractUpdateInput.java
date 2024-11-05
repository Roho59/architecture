package com.imt.td.architecture.interfaces.rest.common.model.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serial;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AbstractUpdateInput implements Serializable {
    private static final long serialVersionUID = 1782685441014652520L;
}
